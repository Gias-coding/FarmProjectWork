package com.example.simulatoreCompleto;

import org.springframework.stereotype.Component;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


/** Classe che si occupa di generare valori casuali di condizioni meteorologiche e dati di produzione basato su valori realistici*/
@Component
public class Simulatore {
    private Random random;
    /** Costruttore*/
    public Simulatore() {
        this.random = new Random();
    }
    /** Metodo per generare casualmente condizioni metereologiche*/
    public VariabiliAmbientali generaVariabili() {
        double temperatura = 20 + (random.nextGaussian() * 5); // Valori random con media di  20°C e  deviazione standard 5
        double umidità = 50 + (random.nextGaussian() * 10); // Valori random con media  50% di umidità e deviazione standard 10
        double precipitazioni = Math.max(0.1, random.nextGaussian() * 5); // Valori random con media 0,1 mm, deviazione standard 5
        return new VariabiliAmbientali(temperatura, umidità, precipitazioni);
    }
    /** Metodo per generare casualmente dati di produzione,
     *  i valori generati casualmente sono influenzati da condizioni meteorologche random del metodo generaVariabili*/
    public List<DatiProduzione> generaDati(VariabiliAmbientali variabili) {
        String[] nomi = {"Grano", "Mais", "Orzo", "Riso", "Soia"};
        List<DatiProduzione> listaDati = new ArrayList<>();
        double ettariTotali = 1000;
        
        //la prima parte che comprende tutto fino al secondo ciclo for , si assicura che i dati generati non superino i 1000 ettari ,
        // che sarebbero gli  ettari disponibili su tutta la fattoria ,

        // Generazione degli ettari casuali
        double[] arrayEttari = new double[5];
        double sommaEttari = 0;
        for (int i = 0; i < 5; i++) {
            arrayEttari[i] = random.nextDouble() * (300 - 100) + 100; // Random tra 100 e 300 ettari
            sommaEttari += arrayEttari[i];
        }

        // Normalizzazione per fare in modo che la somma degli ettari sia uguale a ettariTotali
        double fattoreNormalizzazione = ettariTotali / sommaEttari;

        // Assegniamo gli ettari normalizzati
        for (int i = 0; i < 5; i++) {
            arrayEttari[i] *= fattoreNormalizzazione;
        }
        
        //Questa parte si occupa della generazione degli altri valori 
        //tutti i valori generati sono immagazzinati in una lista di tipi DatiProduzione

        // Creazione dei DatiProduzione
        for (int i = 0; i < 5; i++) {
            double ettari = arrayEttari[i];
            double prezzoSemi = random.nextDouble() * (200 - 100) + 100; // Random tra 100 e 200 euro a tonnellata
            double prezzoRaccolto = random.nextDouble() * (500 - 250) + 250; // Random tra 250 e 500 euro a tonnellata
            double totaleRaccolto = (random.nextDouble() * (7 - 2) + 2) * ettari;

            // Influenza delle precipitazioni sul raccolto
            if (variabili.getPrecipitazioni() < 30) {
                totaleRaccolto *= 0.8; // Ridotto del 20% se le precipitazioni sono troppo basse
            } else if (variabili.getPrecipitazioni() > 100) {
                totaleRaccolto *= 0.9; // Ridotto del 10% se le precipitazioni sono troppo alte
            }

         // Calcoliamo l'umidità del raccolto
            double umiditàRaccolto = Math.max(0.1, 10 + (random.nextGaussian() * 5)); // Assicuriamo che sia sempre maggiore di 0.0
            if (umiditàRaccolto < 40) {
                totaleRaccolto *= 0.85; // Ridotto del 15% se l'umidità del raccolto è troppo bassa
            } else if (umiditàRaccolto > 60) {
                totaleRaccolto *= 0.9; // Ridotto del 10% se l'umidità del raccolto è troppo alta
            }

            // Calcolo del tempo di crescita
            int tempoCrescita = random.nextInt(250 - 150) + 150;
            if (variabili.getTemperatura() > 30) {
                tempoCrescita += 5; // Aumenta il tempo di crescita se la temperatura è troppo alta
            }

            // Creazione dell'oggetto DatiProduzione
            DatiProduzione dati = new DatiProduzione(nomi[i], ettari, prezzoSemi, prezzoRaccolto, totaleRaccolto, tempoCrescita, umiditàRaccolto);
            listaDati.add(dati);
        }

        return listaDati;
    }
}
