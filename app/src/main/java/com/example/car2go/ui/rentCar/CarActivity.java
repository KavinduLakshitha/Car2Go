package com.example.car2go.ui.rentCar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.car2go.R;
import com.example.car2go.data.TripDetails;
import com.example.car2go.databinding.ActivityCarBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CarActivity extends AppCompatActivity {

     private Button proceed, delete;
     private EditText start, end, totalDays, pickup, retLocation;
     DatabaseReference dbTripDetails;

     ActivityCarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Intent intent = this.getIntent();

        if (intent != null) {

            String name = intent.getStringExtra("name");
            String cost = intent.getStringExtra("cost");
            String color = intent.getStringExtra("color");
            int imageId = intent.getIntExtra("imageId", R.drawable.a);

            binding.name.setText(name);
            binding.cost.setText(cost);
            binding.color.setText(color);
            binding.image.setImageResource(imageId);
        }

        start = (EditText) findViewById(R.id.start);
        end = (EditText) findViewById(R.id.end);
        totalDays = (EditText) findViewById(R.id.totalDays);
        pickup = (EditText) findViewById(R.id.pickup);
        retLocation = (EditText) findViewById(R.id.retLocation);

        dbTripDetails = FirebaseDatabase.getInstance().getReference().child("TripDetails");
        proceed = (Button) findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { saveTripDetails(); }
        });
    }



    private void saveTripDetails(){
        String Start = start.getText().toString().trim();
        String End = end.getText().toString().trim();
        String TotalDays = totalDays.getText().toString().trim();
        String Pickup = pickup.getText().toString().trim();
        String RetLocation = retLocation.getText().toString().trim();

        if(Start.isEmpty()){
             start.setError("Starting Date is required");
             start.requestFocus();
         }
        if(End.isEmpty()){
             end.setError("Ending Date is required");
             end.requestFocus();
        }
        if(TotalDays.isEmpty()){
             totalDays.setError("required");
             totalDays.requestFocus();
        }
        if(Pickup.isEmpty()){
             pickup.setError("required");
             pickup.requestFocus();
        }
         if(RetLocation.isEmpty()){
             retLocation.setError("required");
             retLocation.requestFocus();
         }
         else{

             String id  = dbTripDetails.push().getKey();

             TripDetails tripDetails = new TripDetails(id, Start, End, TotalDays, Pickup, RetLocation);
             dbTripDetails.child(id).setValue(tripDetails);
             startActivity(new Intent(CarActivity.this, SummaryActivity.class));

         }
    }
}