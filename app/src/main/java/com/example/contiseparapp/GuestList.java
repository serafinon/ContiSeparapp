package com.example.contiseparapp;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GuestList {

    private static ArrayList<Coppia> guests = new ArrayList<Coppia>();
    private static ArrayList<Coppia> results;


    public static ArrayList<Coppia> addGuest(String nome, Double quota){

        Coppia coppia=new Coppia(nome,quota);

        guests.add(coppia);

        return guests;

    }

    public static ArrayList<Coppia> calcRes(Double conto) {

        results = new ArrayList<Coppia>();
        Double sum = calcSumGuests();
        Double differenza = conto - sum;

        //PRIMA ITERAZIONE -> ripartisco la differenza in pezzi da 1€
        Double TotDecimal = Utility.round( differenza % guests.size() , 2)  ;
        Double riporto = Utility.round( (differenza - TotDecimal)/guests.size() , 2) ;

        //SECONDA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.50€ tra i guests
        if( (double)guests.size()*0.5 <= TotDecimal ){
            riporto+=0.5;
            TotDecimal = Utility.round( TotDecimal - ( 0.5 * guests.size()), 2)  ;
        }

        //TERZA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.20€ tra i guests
        while( (double)guests.size()*0.2 <= TotDecimal ){
            riporto+=0.2;
            TotDecimal = Utility.round( TotDecimal - ( 0.2 * guests.size()) , 2 );
        }

        //QUARTA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.10€ tra i guests
        while( (double)guests.size()*0.1 <= TotDecimal ){
            riporto+=0.1;
            TotDecimal = Utility.round(TotDecimal - ( 0.1 * guests.size()) , 2 );
        }

        //Double riportoIntero = riporto - riportoDecimal;
        //Double TotDecimal = riportoDecimal * guests.size();

        System.out.println("conto = " + conto + "\nsum = " + sum + "\nriporto = " + riporto + "\ntotDecimal = " + TotDecimal);

        for (int i=0;i<guests.size();i++) {
            Coppia c= new Coppia();
            c.nome = guests.get(i).nome;
            c.quota = guests.get(i).quota + riporto;
            results.add(c);
        }

        Coppia totDecimal = new Coppia();
        totDecimal.nome = "monetine: ";
        totDecimal.quota = TotDecimal;
        results.add(totDecimal);

        return results;
    }

    private static Double calcSumGuests(){
        Double sum= 0.0;
        for (Coppia c : guests) {
            sum+=c.quota;
        }
        return sum;
    }

    public static ArrayList<Coppia> getGuests(){
        return guests;
    }
}
