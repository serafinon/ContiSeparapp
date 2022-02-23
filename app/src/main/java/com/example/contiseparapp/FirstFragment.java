package com.example.contiseparapp;

import android.app.AlertDialog;
import android.app.Dialog;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.contiseparapp.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    //private ArrayList<Coppia> a = GuestList.getGuests();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    //private DialogAddGuest dag;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        aggiornaTab();
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                /*Snackbar.make(view, "boh", Snackbar.LENGTH_LONG)
                        .setAction("AddGuest", dag.show(binding., "tag"));*/

                /*AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create(); //Read Update
                alertDialog.setTitle("Quanto dovresti aver speso?");*/

                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Quanto dovresti aver speso?");

                // Set up the input
                final EditText inputNome = new EditText(getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                inputNome.setInputType(InputType.TYPE_CLASS_TEXT);
                inputNome.setHint( "Nome" );
                layout.addView(inputNome);

                final EditText inputQuota = new EditText(getContext());
                inputQuota.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                inputQuota.setHint("Quanto avrai speso?");
                layout.addView(inputQuota);

                builder.setView(layout);


                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome = inputNome.getText().toString();
                        String quota1 = inputQuota.getText().toString();

                        if(!nome.equals("") && !quota1.equals("")) {
                            Double quota = Double.parseDouble(quota1);
                            Guests.addEl(nome,quota);
                            aggiornaTab();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                builder.show();

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void aggiornaTab(){

        TableLayout table = binding.tableLayout;
        table.removeAllViews();
        Double totMoney = 0.0;

        for(Coppia c : Guests.getList()){
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
/*
    TableLayout table = binding.tableLayout;
for(ResourceBalance b : xmlDoc.balance_info)
    {
        // Inflate your row "template" and fill out the fields.
        TableRow row = (TableRow)LayoutInflater.from(CheckBalanceActivity.this).inflate(R.layout.fragment_first, null);
        ((TextView)row.findViewById(R.id.attrib_name)).setText(b.NAME);
        ((TextView)row.findViewById(R.id.attrib_value)).setText(b.VALUE);
        table.addView(row);
    }
table.requestLayout();
*/

}