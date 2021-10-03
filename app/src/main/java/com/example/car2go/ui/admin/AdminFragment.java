package com.example.car2go.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.car2go.R;

public class AdminFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        Button add_new_car_btn = (Button) view.findViewById(R.id.add_new_car_btn);
        Button existingcars_btn = (Button) view.findViewById(R.id.existingcars_btn);
        Button history_btn = (Button) view.findViewById(R.id.history_btn);

        add_new_car_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddNewCar.class);
                startActivity(intent);
            }
        });

        existingcars_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ExistingCars.class);
                startActivity(intent);
            }
        });

        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),History.class);
                startActivity(intent);
            }
        });

        return view;
    }

}