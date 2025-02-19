package com.example.simulatoreCompleto;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**Classe che immagazzina i dati metereologici da generare(temperatura,umidità in % e mm di pioggia)*/
public class VariabiliAmbientali {
    private double temperatura=0;
    private double umidità=0;
    private double precipitazioni=0;
    /** Costruttore */
    public VariabiliAmbientali(double temperatura,double umidità,double precipitazioni) {
    	this.temperatura=DatiProduzione.formatDouble(temperatura);
    	this.umidità=DatiProduzione.formatDouble(umidità);
    	this.precipitazioni=DatiProduzione.formatDouble(precipitazioni);
    	
    }
    
  

    /** Getter temperatura*/
    public  double getTemperatura() {
        return temperatura;
    }

    /** Getter umidità*/
    public  double getUmidità() {
        return umidità;
    }

    /** Getter mm di pioggia*/
    public  double getPrecipitazioni() {
        return precipitazioni;
    }
    /** Metodo per stampare gli attributi*/
    public void stampaVariabili() {
    	System.out.println("Temperatura:"+ getTemperatura()+" gradi");
    	System.out.println("Umidità:"+getUmidità()+" %");
    	System.out.println("Precipitazioni:"+getPrecipitazioni()+" mm");
    }
}
