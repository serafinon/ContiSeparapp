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
        Double TotDecimal =  differenza % guests.size() ;
        Double riporto= (differenza - TotDecimal)/guests.size() ;

        //SECONDA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.50€ tra i guests
        if( (double)guests.size()*0.5 <= TotDecimal ){
            riporto+=0.5;
            TotDecimal = TotDecimal - ( 0.5 * guests.size()) ;
        }

        //TERZA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.20€ tra i guests
        if( (double)guests.size()*0.2 <= TotDecimal ){
            riporto+=0.2;
            TotDecimal = TotDecimal - ( 0.2 * guests.size()) ;
        }

        //QUARTA ITERAZIONE PER RIDURRE il TotDecimal (monetine)
        //suddivido monetine in pezzi da 0.20€ tra i guests
        if( (double)guests.size()*0.2 <= TotDecimal ){
            riporto+=0.2;
            TotDecimal = TotDecimal - ( 0.2 * guests.size()) ;
        }

        //Double riportoIntero = riporto - riportoDecimal;
        //Double TotDecimal = riportoDecimal * guests.size();

        System.out.printf("conto = %f \nsum = %f \nriporto = %f \ntotDecimal = %f", conto, sum, riporto,TotDecimal);

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
