package com.example.myrvaapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<DataModel> dataset;
    private LinearLayoutManager layoutManager;
    private CustomAdapter adapter;

    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recycleViewContainer);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataset = new ArrayList<DataModel>();

        dbRef.child("character").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {

                final long ONE_MEGABYTE = 1024 * 1024;
                ArrayList<HashMap<String, String>> arrayList = (ArrayList<HashMap<String, String>>) task.getResult().getValue();

                for (int i = 0; i < arrayList.size(); i++) {
                    String name = arrayList.get(i).get("name");
                    String description = arrayList.get(i).get("description");
                    Integer index = i;
                    StorageReference islandRef = storage.getReference().child((index + 1) + ".png");
                    islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                        dataset.add(new DataModel(name, description, index, bytes));
                        if (index == arrayList.size() - 1) {
                            adapter = new CustomAdapter(dataset);
                            recyclerView.setAdapter(adapter);
                        }
                    }).addOnFailureListener(exception -> {
                        Log.d("e", exception.getMessage());
                    });
                }
            }
        });

        return view;
    }
}