package com.bones.navdrawertask;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends Fragment {


    public TransactionFragment() {
        // Required empty public constructor
    }

    EditText IncomeTitle,IncomeAmount,OutcomeTitle,OutcomeAmount;
    Button btnIncome,btnOutcome;
    DBHelper helper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        helper = new DBHelper(getActivity());

        IncomeTitle = (EditText) view.findViewById(R.id.txtIncomeDesc);
        IncomeAmount = (EditText) view.findViewById(R.id.txtIncomeAmount);
        OutcomeTitle = (EditText) view.findViewById(R.id.txtOutcomeDesc);
        OutcomeAmount = (EditText) view.findViewById(R.id.txtOutcomeAmount);

        btnIncome = (Button) view.findViewById(R.id.btnAddIncome);
        btnOutcome = (Button) view.findViewById(R.id.btnAddOutcome);

        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TitleIncome = IncomeTitle.getText().toString();
                String AmountIncome = IncomeAmount.getText().toString();

                InsertData(TitleIncome,AmountIncome,"tbl_income");
            }
        });

        btnOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TitleOutcome = OutcomeTitle.getText().toString();
                String AmountOutcome = OutcomeAmount.getText().toString();

                InsertData(TitleOutcome,AmountOutcome,"tbl_outcome");

            }
        });


        return view;
    }

    public void InsertData(String title,String amount,String table){

        if(title.equals("")){
            Toast.makeText(getActivity(), "Description is empty", Toast.LENGTH_SHORT).show();

        } else if (amount.equals("")) {
            Toast.makeText(getActivity(), "Amount is empty", Toast.LENGTH_SHORT).show();

        } else {
            helper.addData(table,title,Integer.valueOf(amount));
            Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();
        }


    }

}
