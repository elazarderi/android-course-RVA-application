package com.example.myrvaapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView textView = view.findViewById(R.id.character);
//        textView.setText(getArguments().getString("character"));

        Bundle args = getArguments();
        String characterJsonString = args.getString("character");
        Character character = Utils.getGsonParser().fromJson(characterJsonString, Character.class);
        textView.setText(character.getDescription());
        return view;
    }
}