package com.example.exincidencies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exincidencies.Clases.MainActivity;
import com.example.exincidencies.Fragments.Info_Incidences;

import java.util.ArrayList;

import DB.Incidence;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Incidence> array_incidencies;
    private Context context;

    public RecyclerViewAdapter(Context con, ArrayList<Incidence> arrI){
        array_incidencies = arrI;
        context = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(viewList);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.i("prova", "recycler " + array_incidencies.get(position).getTitle());
        holder.incID.setText(String.valueOf(array_incidencies.get(position).getID()));
        holder.incName.setText(array_incidencies.get(position).getTitle());
        holder.incStatus.setText(array_incidencies.get(position).getStatus());
        holder.incPriority.setText(array_incidencies.get(position).getPriority());




        //Depends of the status it has a color background or another
        Log.i("prova", array_incidencies.get(position).getStatus());
        switch(array_incidencies.get(position).getStatus()) {
            case "UNFIXED":
                holder.incStatus.setBackgroundColor(context.getResources().getColor(R.color.soft_pinky));
                break;
            case "ASIGNED":
                holder.incStatus.setBackgroundColor(context.getResources().getColor(R.color.soft_orange));
                break;
            case "FIXED":
                holder.incStatus.setBackgroundColor(context.getResources().getColor(R.color.soft_turquqise));
                break;
        }
        //If click, then put data in a bundle to get it in Info_Incidences

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("INC_ID", array_incidencies.get(position).getID());
                bundle.putString("INC_TITLE", array_incidencies.get(position).getTitle());
                bundle.putString("INC_PRIO", array_incidencies.get(position).getPriority());
                bundle.putString("INC_STATUS", array_incidencies.get(position).getStatus());
                bundle.putString("INC_DESC", array_incidencies.get(position).getDescription());
                bundle.putString("INC_DATE", array_incidencies.get(position).getDate());

                Info_Incidences info = new Info_Incidences();
                info.setArguments(bundle);
                //Need AppCompatActivity to use fragmentmanager with support manager, no funciona bien
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getFragmentManager().beginTransaction().replace(R.id.fragmentID, info).addToBackStack(RecyclerViewAdapter.class.getSimpleName()).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return array_incidencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView incName, incID, incPriority, incStatus;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            incName = itemView.findViewById(R.id.itemListTitleInc);
            incID = itemView.findViewById(R.id.itemListID);
            incPriority = itemView.findViewById(R.id.itemListPriority);
            incStatus = itemView.findViewById(R.id.itemListStatus);
            layout = itemView.findViewById(R.id.layout);
        }
    }

}
