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

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    private List<DatabaseModel> dataList;

    public IncomeAdapter(List<DatabaseModel> dataList){
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
    public IncomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getIncomeTitle());
        holder.amount.setText("$"+dataList.get(position).getIncomeAmount());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
