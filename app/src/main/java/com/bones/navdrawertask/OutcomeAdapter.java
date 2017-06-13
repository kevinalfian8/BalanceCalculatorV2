package com.bones.navdrawertask;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo ip on 13/06/2017.
 */

public class OutcomeAdapter extends RecyclerView.Adapter<OutcomeAdapter.ViewHolder> {

    private List<DatabaseModel> dataList;

    public OutcomeAdapter(List<DatabaseModel> dataList){
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,amount;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.itemTitle);
            amount = (TextView) itemView.findViewById(R.id.itemDetail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getOutcomeTitle());
        holder.amount.setText("$"+dataList.get(position).getOutcomeAmount());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



}
