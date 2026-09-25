package com.example.smartpantrymanager;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder>{
    private ArrayList<PantryItem> pantryItems;
    private OnItemClickListener listener;

    //interface
    public interface OnItemClickListener{
        void onEditClick(PantryItem item);
        void onDeleteClick(PantryItem item);
    }

    //constructor
    public PantryAdapter(ArrayList<PantryItem> pantryItems, OnItemClickListener listener){
        this.pantryItems = pantryItems;
        this.listener = listener;
    }

    //create view holder
    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pantry, parent, false);
        return new PantryViewHolder(view);
    }

    //bind view holder
    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = pantryItems.get(position);
        holder.textViewItemName.setText(item.getName());

        String quantityText = item.getQuantity() + " " + item.getUnit();
        holder.textViewItemQuantity.setText(quantityText);

        //check if expiry date is null or empty
        if(item.getExpiryDate() == null || item.getExpiryDate().isEmpty()){
            holder.textViewItemExpiry.setText("Expiry: N/A");
        }else {
            Context context = holder.itemView.getContext();
            SharedPreferences preferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
            boolean alertsEnabled = preferences.getBoolean("expiry_alerts_enabled", false);

            //check if expiry alerts are enabled
            if (alertsEnabled){
                String expiryMessage = getExpiryMessage(item.getExpiryDate());
                holder.textViewItemExpiry.setText(expiryMessage);
            }else{
                holder.textViewItemExpiry.setText("Expiry: " + item.getExpiryDate());
            }
        }
        holder.buttonEdit.setOnClickListener(v -> {
            listener.onEditClick(item);
        });
        holder.buttonDelete.setOnClickListener(v -> {
            listener.onDeleteClick(item);
        });
    }

    private String getExpiryMessage(String expiryDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateFormat.setLenient(false);

        try{
            Date expiry = dateFormat.parse(expiryDate);
            Date today = dateFormat.parse(dateFormat.format(new Date()));
            long timeDifference = expiry.getTime() - today.getTime();
            long daysUntilExpiry = timeDifference / (1000 * 60 * 60 * 24);

            if (daysUntilExpiry < 0){
                return "Expired!!! (" + expiryDate + ")";
            } else if (daysUntilExpiry == 0) {
                return "Expires Today! (" + expiryDate + ")";
            } else if (daysUntilExpiry <= 3) {
                return "Expiring Soon! (" + expiryDate + ")";
            }
        }catch(ParseException e){
            return "Expiry: " + expiryDate;
        }

        return "Expiry: " + expiryDate;
    }

    //get item count
    @Override
    public int getItemCount(){
        return pantryItems.size();
    }

    //view holder
    public static class PantryViewHolder extends RecyclerView.ViewHolder{
        TextView textViewItemName;
        TextView textViewItemQuantity;
        TextView textViewItemExpiry;
        Button buttonEdit;
        Button buttonDelete;

        public PantryViewHolder(@NonNull View itemView){
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            textViewItemQuantity = itemView.findViewById(R.id.textViewItemQuantity);
            textViewItemExpiry = itemView.findViewById(R.id.textViewItemExpiry);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }

}
