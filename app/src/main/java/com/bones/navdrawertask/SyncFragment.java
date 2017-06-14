package com.bones.navdrawertask;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SyncFragment extends Fragment {


    public SyncFragment() {
        // Required empty public constructor
    }

    DatabaseReference databaseReference;
    List<DatabaseModel> dataListIncome,dataListOutcome;
    DBHelper helper;
    ProgressBar pb;
    Synchronize synchronize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sync, container, false);

        pb = (ProgressBar) view.findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);

        synchronize = new Synchronize();
        synchronize.execute();



        return view;
    }



    class Synchronize extends AsyncTask<Void,String,Void>{

        ProgressDialog progress_dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            //this for init progress dialog
            progress_dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress_dialog.setTitle("On Progress ....");
            progress_dialog.setCancelable(false);
            progress_dialog.setProgress(0);

            progress_dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    synchronize.cancel(true);
                    pb.setVisibility(View.INVISIBLE);
                    dialogInterface.dismiss();
                }
            });
            progress_dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            helper = new DBHelper(getActivity());
            dataListIncome = helper.getData("tbl_income");
            dataListOutcome = helper.getData("tbl_outcome");
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            DatabaseModel dm = new DatabaseModel();
            for(int i = 0;i<dataListIncome.size();i++){
                dm.setIncomeId(dataListIncome.get(i).getIncomeId());
                dm.setIncomeTitle(dataListIncome.get(i).getIncomeTitle());
                dm.setIncomeAmount(dataListIncome.get(i).getIncomeAmount());
                databaseReference.child("Income").child(""+dm.getIncomeId()).setValue(dm);
            }

            for(int i = 0;i<dataListOutcome.size();i++){
                dm.setOutcomeId(dataListOutcome.get(i).getOutcomeId());
                dm.setOutcomeTitle(dataListOutcome.get(i).getOutcomeTitle());
                dm.setOutcomeAmount(dataListOutcome.get(i).getOutcomeAmount());
                databaseReference.child("Outcome").child(""+dm.getOutcomeId()).setValue(dm);
            }

            try {

                Thread.sleep(100);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            if(isCancelled()) {

                synchronize.cancel(true);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //hide top progress bar
            pb.setVisibility(View.INVISIBLE);
            //remove progress dialog
            progress_dialog.dismiss();
            Toast.makeText(getActivity(), "Synchronized to server", Toast.LENGTH_SHORT).show();
        }
    }

}
