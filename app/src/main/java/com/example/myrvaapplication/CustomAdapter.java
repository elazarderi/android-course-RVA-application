package com.example.myrvaapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataset;

    public CustomAdapter(ArrayList<DataModel> dataset) {
        this.dataset = dataset;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardViewCardPage);
            textViewName = itemView.findViewById(R.id.textViewOneCard);
            textViewDescription = itemView.findViewById(R.id.textViewTwoCard);
            imageView = itemView.findViewById(R.id.imageViewCard);

            itemView.setOnClickListener(v -> {
                showDetails(v);
            });
        }

        public void showDetails(View view) {
            String characterName = textViewName.getText().toString();
            String characterDescription = textViewDescription.getText().toString();
            Character character = new Character(characterName, characterDescription);

            NavController navController = Navigation.findNavController(view);
            view.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                String characterJsonString = Utils.getGsonParser().toJson(character);
                bundle.putString("character", characterJsonString);
                navController.navigate(R.id.action_listFragment_to_detailsFragment, bundle);
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewDescription = holder.textViewDescription;
        ImageView imageView = holder.imageView;
        CardView cardView = holder.cardView;

        textViewName.setText(dataset.get(position).getName());
        textViewDescription.setText(dataset.get(position).getDescription());
        Bitmap bitmap = BitmapFactory.decodeByteArray(dataset.get(position).getImage(), 0, dataset.get(position).getImage().length);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
