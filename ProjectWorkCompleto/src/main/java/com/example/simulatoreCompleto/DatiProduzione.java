package com.example.simulatoreCompleto;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**Questa classe si occupa di immagazzinare i dati di produzione inerenti alla fattoria, gli attributi presenti 
 * sono gli ettari di superficie dati al singolo raccolto,il nome del raccotlo,il prezzo dei semi , il prezzo di vendita del raccolto,
 * le tonnelate totali raccolte,la media di tonnellate di raccolto per ettaro,i giorni necessari alla crescità prima della raccolta,
 * l'umidià della coltura al momento del raccolto, costi ricavi e utili del raccolto , e l'anno di produzione della coltura */
public class DatiProduzione {

    private double ettari = 0;
    private String nome = "";
    private double prezzoSemi = 0;
    private double prezzoRaccolto = 0;
    private double totaleRaccolto = 0;
    private double raccoltoPerEttaro=0;
    private int tempoCrescita = 0;
    private double umiditàRaccolto = 0;
    private double costi = 0;
    private double ricavi = 0;
    private double utile = 0;
    private int anno=0;  // Aggiungi il campo 'anno'
    

    /**Il costruttore che inizializza i dati , troncandoli a 2 cifre decimali per una dashboard più intuitva */
    public DatiProduzione(String nome, double ettari, double prezzoSemi, double prezzoRaccolto, 
                          double totaleRaccolto, int tempoCrescita, double umiditàRaccolto) {
        this.nome = nome;
        this.ettari = formatDouble(ettari);
        this.prezzoSemi = formatDouble(prezzoSemi);
        this.prezzoRaccolto = formatDouble(prezzoRaccolto);
        this.totaleRaccolto = formatDouble(totaleRaccolto);
        this.tempoCrescita = tempoCrescita;
        this.umiditàRaccolto = formatDouble(umiditàRaccolto);
        this.costi = formatDouble(prezzoSemi * 2.0 * ettari);
        this.ricavi = formatDouble(totaleRaccolto * prezzoRaccolto);
        this.utile = formatDouble(ricavi - costi);
        this.raccoltoPerEttaro=formatDouble(totaleRaccolto/ettari);
    }

    /** Getter per l'anno*/
    public int getAnno() {
        return anno;
    }
    /**Setter per l'anno*/
    public void setAnno(int anno) {
        this.anno = anno;
    }
    /**Funzione statica legata alla classe che si occupa di troncare a 2 cifre decimali il valore double passato come parametro*/
    public static double formatDouble(double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**Getter per gli ettari */
    public double getEttari() {
        return ettari;
    }
    /**Getter per restituire il nome della coltura */
    public String getNome() {
        return nome;
    }
    /**Getter per prezzo semi */
    public double getPrezzoSemi() {
        return prezzoSemi;
    }
    /**Getter per prezzo raccolto */
    public double getPrezzoRaccolto() {
        return prezzoRaccolto;
    }
    /**Getter per le tonnellate raccolte */
    public double getTotaleRaccolto() {
        return totaleRaccolto;
    }
    /**Getter per i giorni di crescità della coltura */
    public int getTempoCrescita() {
        return tempoCrescita;
    }
    /**Getter per tonnelate per ettaro */
    public double getRaccoltoPerEttaro() {
        return raccoltoPerEttaro;
    }
    /**Getter per costi */
    public double getCosti() {
        return costi;
    }
    /**Getter per ricavi */
    public double getRicavi() {
        return ricavi;
    }
    /**Getter per gli utili */
    public double getUtile() {
        return utile;
    }
    /**Getter per l'umidità raccolto */
    public double getUmiditàRaccolto() {
        return umiditàRaccolto;
    }
    /**Metodo per stampare gli attributi */
    public void stampaInformazioni() {
    	System.out.println("Nome : " + getAnno());
        System.out.println("Nome : " + getNome());
        System.out.println("Ettari coltivati : " + getEttari());
        System.out.println("Prezzo Semi : " + getPrezzoSemi() + " euro");
        System.out.println("Prezzo " + getNome() + ":" + getPrezzoRaccolto() + " euro");
        System.out.println("Tonnellate raccolte : " + getTotaleRaccolto());
        System.out.println("Raccolto per Ettaro: " + getRaccoltoPerEttaro() + " tonnellate/ettaro"); // Mostra il nuovo dato
        System.out.println("Tempo di crescita : " + getTempoCrescita() + " giorni");
        System.out.println("Costi: " + getCosti() + " euro");
        System.out.println("Ricavi: " + getRicavi() + " euro");
        System.out.println("Utile: " + getUtile() + " euro");
        System.out.println("Umidità del raccolto: " + getUmiditàRaccolto() + " %");
    }

}
