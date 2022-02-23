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

        /*binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/

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
                    //risultati = GuestList.calcRes(conto);
                    Results.calcRes(conto);
                    VisualizzaRisultati();
                }
            }
        });
        /*builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });*/


        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    public void VisualizzaRisultati(){

        TableLayout table = binding.tableLayout;
        table.removeAllViews();
        Double totMoney = 0.0;

        for(Coppia c : Results.getList()){
            //TableRow row = (TableRow)LayoutInflater.from(getContext()).inflate(R.layout.fragment_first, null);


            TableRow row = new TableRow(getActivity().getApplicationContext());

            TextView textview1 = new TextView(getActivity().getApplicationContext());
            textview1.setText(c.nome);
            textview1.setTextSize(22);
            row.addView(textview1);

            TextView textview2 = new TextView(getActivity().getApplicationContext());
            textview2.setText(df.format(c.quota)+" €");
            totMoney += c.quota;
            textview2.setTextSize(22);
            row.addView(textview2);

            table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //binding.attribName.setText(c.nome);
            //binding.attribValue.setText(String.valueOf(c.quota));
        }

        //aggiungi monetine
        TableRow rowblank0 = new TableRow(getActivity().getApplicationContext());
        TextView textview00 = new TextView(getActivity().getApplicationContext());
        textview00.setText("\n________________\n");
        rowblank0.addView(textview00);
        table.addView(rowblank0, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TableRow row0 = new TableRow(getActivity().getApplicationContext());
        TextView textview10 = new TextView(getActivity().getApplicationContext());
        textview10.setText("monetine:");
        textview10.setTextSize(20);
        row0.addView(textview10);
        TextView textview20 = new TextView(getActivity().getApplicationContext());
        textview20.setText(df.format(Results.getMonetine())+" €");
        totMoney += Results.getMonetine();
        textview20.setTextSize(20);
        row0.addView(textview20);
        table.addView(row0, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        //aggiungi riga del totale
        TableRow rowblank = new TableRow(getActivity().getApplicationContext());
        TextView textview0 = new TextView(getActivity().getApplicationContext());
        textview0.setText("\n________________\n\n");
        rowblank.addView(textview0);
        table.addView(rowblank, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TableRow row = new TableRow(getActivity().getApplicationContext());
        TextView textview1 = new TextView(getActivity().getApplicationContext());
        textview1.setText("Tot.");
        textview1.setTextSize(24);
        row.addView(textview1);
        TextView textview2 = new TextView(getActivity().getApplicationContext());
        textview2.setText(df.format(totMoney)+" €");
        textview2.setTextSize(24);
        row.addView(textview2);
        table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        table.requestLayout();


    }

}