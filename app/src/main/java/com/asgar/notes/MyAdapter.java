package com.asgar.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>{


    ArrayList<String> title,txtime,txnote;
    MyAdapter(ArrayList<String> m1, ArrayList<String> m2, ArrayList<String> m3)
    {
        title=m1;
        txtime=m2;
        txnote=m3;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyAdapter.MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position)
    {
        holder.p1.setText(title.get(position));
        holder.p2.setText(txtime.get(position));
        holder.p3.setText(txnote.get(position));

    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView p1,p2,p3;
        public MyViewHolder(View itemView) {
            super(itemView);
            p1 = (TextView) itemView.findViewById(R.id.txt);
            p2 = (TextView) itemView.findViewById(R.id.txttime);
            p3 = (TextView) itemView.findViewById(R.id.txnote);
        }
    }
}
