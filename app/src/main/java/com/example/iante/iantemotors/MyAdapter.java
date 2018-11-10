package com.example.iante.iantemotors;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Records> Records;

    public MyAdapter(Context c , ArrayList<Records> p)
    {
        context = c;
        Records = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_records,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.make.setText(Records.get(position).getMake());
        holder.model.setText(Records.get(position).getModel());
        holder.Engine.setText(Records.get(position).getEngine());
        holder.Reg.setText(Records.get(position).getReg());
        holder.odometer.setText(Records.get(position).getOdometer());
        holder.litres.setText(Records.get(position).getLitres());
        holder.amount.setText(Records.get(position).getAmount());


            holder.btn.setVisibility(View.VISIBLE);
            holder.onClick(position);


    }

    @Override
    public int getItemCount() {
        return Records.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView model,make,Engine,Reg,odometer,litres,amount;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            model = (TextView) itemView.findViewById(R.id.model);
            make = (TextView) itemView.findViewById(R.id.make);
            Engine = (TextView) itemView.findViewById(R.id.Engine);
            Reg = (TextView) itemView.findViewById(R.id.Reg);
            odometer = (TextView) itemView.findViewById(R.id.odometer);
            litres = (TextView) itemView.findViewById(R.id.litres);
            amount = (TextView) itemView.findViewById(R.id.amount);
            btn = (Button) itemView.findViewById(R.id.CheckRecords);
        }
        public void onClick(final int position)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

