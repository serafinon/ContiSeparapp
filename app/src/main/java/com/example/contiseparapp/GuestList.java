package com.example.contiseparapp;

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
        Double riportoIntero = differenza % guests.size();
        Double riporto= differenza/guests.size();
        Double riportoDecimal = riporto - riportoIntero;
        Double TotDecimal = riportoDecimal * guests.size();


        for (int i=0;i<guests.size();i++) {
            Coppia c= new Coppia();
            c.nome = guests.get(i).nome;
            c.quota = guests.get(i).quota + riportoIntero;
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
