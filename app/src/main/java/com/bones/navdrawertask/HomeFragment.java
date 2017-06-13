package com.bones.navdrawertask;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }

    DBHelper helper;

    RecyclerView rvIncome,rvOutcome;
    IncomeAdapter incomeAdapter;
    OutcomeAdapter outcomeAdapter;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    List<DatabaseModel> indataList,outdataList;

    TextView IncomeTotal,OutcomeTotal,Balance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        helper = new DBHelper(getActivity());

        rvOutcome = (RecyclerView) view.findViewById(R.id.rv_expenses);
        rvIncome = (RecyclerView) view.findViewById(R.id.rv_income);

        indataList = helper.getData("tbl_income");
        rvIncome.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvIncome.setLayoutManager(layoutManager);
        incomeAdapter = new IncomeAdapter(indataList);
        rvIncome.setAdapter(incomeAdapter);

        outdataList = helper.getData("tbl_outcome");
        rvOutcome.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getActivity());
        rvOutcome.setLayoutManager(layoutManager2);
        outcomeAdapter = new OutcomeAdapter(outdataList);
        rvOutcome.setAdapter(outcomeAdapter);


        IncomeTotal = (TextView) view.findViewById(R.id.txtIncomeTotal);
        OutcomeTotal = (TextView) view.findViewById(R.id.txtOutcomeTotal);
        Balance = (TextView) view.findViewById(R.id.txtBalance);

        int income = helper.getTotalIncome();
        int outcome = helper.getTotalOutcome();

        IncomeTotal.setText("$"+income);
        OutcomeTotal.setText("$"+outcome);

        int totalBalance = income - outcome;
        Balance.setText("Balance : $"+totalBalance);




        return view;
    }




}
