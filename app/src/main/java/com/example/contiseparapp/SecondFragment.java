package com.example.contiseparapp;

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

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ArrayList<Coppia> risultati;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
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
                    risultati = GuestList.calcRes(conto);
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

    public void VisualizzaRisultati(){

        TableLayout table = binding.tableLayout;
        table.removeAllViews();

        for(Coppia c : risultati){
            //TableRow row = (TableRow)LayoutInflater.from(getContext()).inflate(R.layout.fragment_first, null);
            TableRow row = new TableRow(getActivity().getApplicationContext());

            TextView textview1 = new TextView(getActivity().getApplicationContext());
            textview1.setText(c.nome);
            textview1.setTextSize(22);
            row.addView(textview1);

            TextView textview2 = new TextView(getActivity().getApplicationContext());
            textview2.setText(String.valueOf(c.quota)+" €");
            textview2.setTextSize(22);
            row.addView(textview2);

            table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //binding.attribName.setText(c.nome);
            //binding.attribValue.setText(String.valueOf(c.quota));
        }

        table.requestLayout();


    }

}