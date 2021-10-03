package com.example.car2go.ui.rentCar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.car2go.R;

import com.example.car2go.databinding.ActivityMainBinding;
import com.example.car2go.databinding.ActivityRentCarBinding;

import java.util.ArrayList;

public class RentCarActivity extends AppCompatActivity {

    ActivityRentCarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRentCarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };

        String[] name = {
                "Audi A3 (2020)",
                "BMW X2 (2012)",
                "Premio (2020)",
                "Axio (2021)",
                "Swift RS (2018)"
        };

        String[] cost = {
                "LKR 40,000/DAY",
                "LKR 35,000/DAY",
                "LKR 30,000/DAY",
                "LKR 25,000/DAY",
                "LKR 15,000/DAY"
        };

        String[] color= {
                "Black",
                "Blue",
                "White",
                "Indigo",
                "Red"
        };

        ArrayList<Car> carArrayList = new ArrayList<>();

        for(int i= 0; i<imageId.length;i++){
            Car car = new Car(name[i], cost[i], color[i], imageId[i]);
            carArrayList.add(car);
        }

        ListAdapter listAdapter = new ListAdapter(RentCarActivity.this, carArrayList);

        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(RentCarActivity.this,CarActivity.class);
                i.putExtra("name",name[position]);
                i.putExtra("cost",cost[position]);
                i.putExtra("color",color[position]);
                i.putExtra("imageId",imageId[position]);
                startActivity(i);

            }
        });
    }
}