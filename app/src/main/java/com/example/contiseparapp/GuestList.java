package com.example.contiseparapp;

import java.util.ArrayList;

public class GuestList {

    private static ArrayList<Coppia> guests = new ArrayList<Coppia>();
    private static ArrayList<Coppia> results;

    public static ArrayList<Coppia> addGuest(String nome, Integer quota){

        Coppia coppia=new Coppia(nome,quota);

        guests.add(coppia);

        return guests;

    }

    public static ArrayList<Coppia> calcRes(Integer conto) {

        results = new ArrayList<Coppia>();
        float sum = calcSumGuests();
        Float riporto= (float) (conto - sum)/guests.size();


        for (int i=0;i<guests.size();i++) {
            Coppia c= new Coppia();
            c.nome = guests.get(i).nome;
            c.quota = guests.get(i).quota + riporto;
            results.add(c);
        }

        return results;
    }

    private static float calcSumGuests(){
        float sum=0;
        for (Coppia c : guests) {
            sum+=c.quota;
        }
        return sum;
    }

    public static ArrayList<Coppia> getGuests(){
        return guests;
    }
}
