package com.example.car2go.ui.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.content.Intent;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;


import com.example.car2go.R;
import com.example.car2go.databinding.FragmentServicesBinding;


public class ServicesFragment extends Fragment {
    private Button inquiry,rate_us,con_us;


    private @NonNull FragmentServicesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentServicesBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_services, container, false);

        inquiry = root.findViewById(R.id.inquiry);
        inquiry.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                            Intent intent = new Intent(getActivity(), AddInquiry.class);
                            startActivity(intent);
            }
        });
        rate_us = root.findViewById(R.id.rate_us);
        rate_us.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AddRating.class);
                startActivity(intent);
            }
        });
        con_us = root.findViewById(R.id.con_us);
        con_us.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), ContactUs.class);
                startActivity(intent);
            }
        });


        return root;

    }
}