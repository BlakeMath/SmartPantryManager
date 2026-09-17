package com.example.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder>{
    private ArrayList<PantryItem> pantryItems;

    //constructor
    public PantryAdapter(ArrayList<PantryItem> pantryItems){
        this.pantryItems = pantryItems;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pantry, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = pantryItems.get(position);
        holder.textViewItemName.setText(item.getName());

        String quantityText = item.getQuantity() + " " + item.getUnit();

        holder.textViewItemQuantity.setText(quantityText);

        if(item.getExpiryDate() == null || item.getExpiryDate().isEmpty()){
            holder.textViewItemExpiry.setText("N/A");
        }else {
            holder.textViewItemExpiry.setText("Expiry: " + item.getExpiryDate());
        }
    }

    //get item count
    @Override
    public int getItemCount(){
        return pantryItems.size();
    }


    public static class PantryViewHolder extends RecyclerView.ViewHolder{
        TextView textViewItemName;
        TextView textViewItemQuantity;
        TextView textViewItemExpiry;

        public PantryViewHolder(@NonNull View itemView){
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            textViewItemQuantity = itemView.findViewById(R.id.textViewItemQuantity);
            textViewItemExpiry = itemView.findViewById(R.id.textViewItemExpiry);
        }
    }

}
