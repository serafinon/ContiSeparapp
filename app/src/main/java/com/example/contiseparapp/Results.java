package com.example.contiseparapp;

import java.util.ArrayList;

public class Results{

    private static ArrayList<Coppia> results;
    private static Double monetine;


    public static void addEl(String nome, Double quota) {
        Coppia coppia=new Coppia(nome,quota);
        results.add(coppia);
    }

    public static Coppia getEl(int index) {
        return results.get(index);
    }

    public static Double getMonetine(){
        return monetine;
    }

    public static void delEl(int index) {
        results.remove(index);
    }

    public static int getSize() {
        return results.size();
    }

    public static ArrayList<Coppia> getList() {
        return new ArrayList<Coppia>(results);
    }

    public static void calcRes(Double conto) {

        results = new ArrayList<Coppia>();

        Double sum = Guests.calcSum();
        Double differenza = conto - sum;

        //PRIMA ITERAZIONE -> ripartisco la differenza in pezzi da 1€
        Double totDecimal = Utility.round( differenza % Guests.getSize() , 2)  ;
        Double riporto = Utility.round( (differenza - totDecimal)/Guests.getSize() , 2) ;

        //SECONDA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.50€ tra i guests
        if( (double)Guests.getSize()*0.5 <= totDecimal ){
            riporto+=0.5;
            totDecimal = Utility.round( totDecimal - ( 0.5 * Guests.getSize()), 2)  ;
        }

        //TERZA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.20€ tra i guests
        while( (double)Guests.getSize()*0.2 <= totDecimal ){
            riporto+=0.2;
            totDecimal = Utility.round( totDecimal - ( 0.2 * Guests.getSize()) , 2 );
        }

        //QUARTA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.10€ tra i guests
        while( (double)Guests.getSize()*0.1 <= totDecimal ){
            riporto+=0.1;
            totDecimal = Utility.round(totDecimal - ( 0.1 * Guests.getSize()) , 2 );
        }

        System.out.println("conto = " + conto + "\nsum = " + sum + "\nriporto = " + riporto + "\ntotDecimal = " + totDecimal);

        //aggiorno i risultati
        for (int i=0;i<Guests.getSize();i++) {
            Results.addEl( Guests.getEl(i).nome, Guests.getEl(i).quota + riporto);
        }

        //monetine contiene quella porzione di conto che non è suddivisibile ulteriormente tra le persone
        monetine = totDecimal;

    }


}
