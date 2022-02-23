package com.example.contiseparapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.example.contiseparapp.databinding.FragmentSecondBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    //private ArrayList<Coppia> risultati;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Quanto è venuto il conto?");

        // Set up the input
        final EditText inputConto = new EditText(getContext());
        // Specify the type of input expected;
        inputConto.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        inputConto.setHint("totale conto");
        layout.addView(inputConto);

        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String conto1 = inputConto.getText().toString();

                if(!conto1.equals("")) {
                    Double conto = Double.parseDouble(conto1);
                    Results.calcRes(conto);
                    VisualizzaRisultati();
                }
            }
        });

        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void VisualizzaRisultati(){

        TableLayout table = binding.tableLayout;
        table.removeAllViews();
        Double totMoney = 0.0;
        FragmentActivity fa = getActivity();

        for(Coppia c : Results.getList()){
            Utility.aggiungiRigaTab(fa, table, c.nome, df.format(c.quota)+" €", 22);
            totMoney += c.quota;
        }

        //aggiungi monetine
        Utility.aggiungiRigaBlank(fa, table);
        Utility.aggiungiRigaTab(fa, table, "monetine:", df.format(Results.getMonetine())+" €", 20);
        totMoney += Results.getMonetine();

        //aggiungi riga del totale
        Utility.aggiungiRigaBlank(fa, table);
        Utility.aggiungiRigaTab(fa, table, "Tot.", df.format(totMoney)+" €", 24);

        table.requestLayout();

    }

}