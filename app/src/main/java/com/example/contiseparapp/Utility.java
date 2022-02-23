package com.example.contiseparapp;

import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utility {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void aggiungiRigaTab(FragmentActivity fa, TableLayout table, String col1, String col2, int textSize){

        TableRow row = new TableRow(fa.getApplicationContext());

        TextView textview1 = new TextView(fa.getApplicationContext());
        textview1.setText(col1);
        textview1.setTextSize(textSize);
        row.addView(textview1);

        TextView textview2 = new TextView(fa.getApplicationContext());
        textview2.setText(col2);
        textview2.setTextSize(textSize);
        row.addView(textview2);

        table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public static void aggiungiRigaBlank(FragmentActivity fa, TableLayout table){
        TableRow rowblank0 = new TableRow(fa.getApplicationContext());
        TextView textview00 = new TextView(fa.getApplicationContext());
        textview00.setText("\n________________\n");
        rowblank0.addView(textview00);
        table.addView(rowblank0, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
