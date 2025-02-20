package com.example.simulatoreCompleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.ArrayList;
/** Questa classe è il controller che si occupa di passare i dati del backend alla dashboard */
@Controller
public class DashboardController {
    private Simulatore simulatore;
    /** Costruttore con iniezione*/
    @Autowired
    public DashboardController(Simulatore simulatore) {
        this.simulatore = simulatore;
    }
    /**Metodo che si occupa di mostrare effettivamente la dashboard , qui vengono generati i valori random 
     * dopodichè vengono passati al modello per poter esser mostrati nella dashboard.*/
    @GetMapping("/")
    public String showDashboard(Model model) {
    	
    	//Questa  parte egnera i dati e li aggiunge  alle liste, 
        
       
        // Liste per variabili ambientali per 10 anni
        List<Double> listaVariabiliT = new ArrayList<>();//Temperatura
        List<Double> listaVariabiliU = new ArrayList<>();//Umidità
        List<Double> listaVariabiliP = new ArrayList<>();//Precipitazioni
        // Lista per tutti i dati di produzione
        List<DatiProduzione> tuttiDati = new ArrayList<>();//Tutti i dati di produzione

        // Genera dati per 10 anni
        for (int i = 0; i < 10; i++) {
            // Genera nuove variabili ambientali per ogni anno
            VariabiliAmbientali variabili = simulatore.generaVariabili();
            listaVariabiliT.add(variabili.getTemperatura());
            listaVariabiliU.add(variabili.getUmidità());
            listaVariabiliP.add(variabili.getPrecipitazioni());
            
            // Genera dati di produzione per l'anno corrente
            List<DatiProduzione> datiProduzione = simulatore.generaDati(variabili);
            for (DatiProduzione dato : datiProduzione) {
                // Imposta l'anno corrente (1-based)
                dato.setAnno(i + 1);
                tuttiDati.add(dato);
            }
        }
        //Questa parte si occupa di calcolare i costi , i ricavi e gli utili totali dei 10 anni di produzione, 
        // Calcola i totali annuali
        List<Double> costiTotaliAnnuali = new ArrayList<>();
        List<Double> ricaviTotaliAnnuali = new ArrayList<>();
        List<Double> utileTotaleAnnuale = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            double costiTotali = 0;
            double ricaviTotali = 0;
            double utileTotale = 0;
            
            for (DatiProduzione dato : tuttiDati) {
                if (dato.getAnno() == (i + 1)) {
                    costiTotali += dato.getCosti();
                    ricaviTotali += dato.getRicavi();
                    utileTotale += dato.getUtile();
                }
            }
            costiTotaliAnnuali.add(DatiProduzione.formatDouble(costiTotali));
            ricaviTotaliAnnuali.add(DatiProduzione.formatDouble(ricaviTotali));
            utileTotaleAnnuale.add(DatiProduzione.formatDouble(utileTotale));
        }
        
        // Prepara i dati per il grafico dei dati di produzione (Costi, Ricavi, Utile)
        List<String> etichetta = new ArrayList<>();
        List<Double> costiAnnuali = new ArrayList<>();
        List<Double> ricaviAnnuali = new ArrayList<>();
        List<Double> utileAnnuale = new ArrayList<>();

        // Prepara i dati per il nuovo grafico dettagliato di coltivazione
        List<Double> ettariAnnuali = new ArrayList<>();
        List<Double> prezzSemiAnnuali = new ArrayList<>();
        List<Double> prezzoRaccoltoAnnuali = new ArrayList<>();
        List<Double> totaleRaccoltoAnnuali = new ArrayList<>();
        List<Double> raccoltoPerEttaroAnnuali = new ArrayList<>();
        List<Double> umiditàRaccoltoAnnuali = new ArrayList<>();

        for (DatiProduzione dato : tuttiDati) {
            // Etichetta formata da anno e nome della coltura (es. "2013 - Grano")
            etichetta.add((dato.getAnno() + 2012) + " - " + dato.getNome());
            costiAnnuali.add(dato.getCosti());
            ricaviAnnuali.add(dato.getRicavi());
            utileAnnuale.add(dato.getUtile());
            
            // Nuovi dati per il grafico dettagliato
            ettariAnnuali.add(dato.getEttari());
            prezzSemiAnnuali.add(dato.getPrezzoSemi());
            prezzoRaccoltoAnnuali.add(dato.getPrezzoRaccolto());
            totaleRaccoltoAnnuali.add(dato.getTotaleRaccolto());
            raccoltoPerEttaroAnnuali.add(dato.getRaccoltoPerEttaro());
            umiditàRaccoltoAnnuali.add(dato.getUmiditàRaccolto());
        }
        
        //Questa parte aggiunge alle varie liste i dati da passare al grafico , infine tutti i dati vengono passati al modello.

        // Aggiungi i dati al modello
        model.addAttribute("tuttiDatiAnnuali", tuttiDati);
        model.addAttribute("costiTotaliAnnuali", costiTotaliAnnuali);
        model.addAttribute("ricaviTotaliAnnuali", ricaviTotaliAnnuali);
        model.addAttribute("utileTotaleAnnuale", utileTotaleAnnuale);
        model.addAttribute("datiTemperatura", listaVariabiliT);
        model.addAttribute("datiUmidita", listaVariabiliU);
        model.addAttribute("datiPrecipitazioni", listaVariabiliP);

        // Dati per il grafico dei Costi, Ricavi, Utile
        model.addAttribute("etichetta", etichetta);
        model.addAttribute("costiAnnuali", costiAnnuali);
        model.addAttribute("ricaviAnnuali", ricaviAnnuali);
        model.addAttribute("utileAnnuale", utileAnnuale);

        // Dati per il nuovo grafico dettagliato di coltivazione
        model.addAttribute("ettariAnnuali", ettariAnnuali);
        model.addAttribute("prezzSemiAnnuali", prezzSemiAnnuali);
        model.addAttribute("prezzoRaccoltoAnnuali", prezzoRaccoltoAnnuali);
        model.addAttribute("totaleRaccoltoAnnuali", totaleRaccoltoAnnuali);
        model.addAttribute("raccoltoPerEttaroAnnuali", raccoltoPerEttaroAnnuali);
        model.addAttribute("umiditàRaccoltoAnnuali", umiditàRaccoltoAnnuali);

        return "dashboard"; // Ritorna la vista dashboard.html
    }
}
