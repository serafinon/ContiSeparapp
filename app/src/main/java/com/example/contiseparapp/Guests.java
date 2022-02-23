package com.example.contiseparapp;

import java.util.ArrayList;
import java.util.List;

public class Guests {

    private static ArrayList<Coppia> guests = new ArrayList<Coppia>();

    public static void addEl(String nome, Double quota) {
        Coppia coppia=new Coppia(nome,quota);
        guests.add(coppia);
    }

    public static Coppia getEl(int index) {
        return guests.get(index);
    }

    public static void delEl(int index) {
        guests.remove(index);
    }

    public static int getSize(){
        return guests.size();
    }

    public static ArrayList<Coppia> getList() {
        return new ArrayList<Coppia>(guests);
    }

    public static Double calcSum(){
        Double sum= 0.0;
        for (Coppia c : guests) {
            sum+=c.quota;
        }
        return sum;
    }
}
