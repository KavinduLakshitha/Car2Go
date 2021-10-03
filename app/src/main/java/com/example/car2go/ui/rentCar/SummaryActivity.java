package com.example.car2go.ui.rentCar;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car2go.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SummaryActivity extends AppCompatActivity {

    private Button delete,submit;
    private TextView nic, start,end,totalDays,pickup,retLocation;
    DatabaseReference dbTripDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        nic = findViewById(R.id.nic);
        start = findViewById(R.id.start);
        end= findViewById(R.id.end);
        totalDays= findViewById(R.id.totalDays);
        pickup= findViewById(R.id.pickup);
        retLocation= findViewById(R.id.retLocation);

        String NIC = getIntent().getStringExtra("nic");
        String Start = getIntent().getStringExtra("start");
        String End = getIntent().getStringExtra("end");
        String TotalDays = getIntent().getStringExtra("totalDays");
        String Pickup = getIntent().getStringExtra("pickup");
        String RetLocation = getIntent().getStringExtra("retLocation");

        nic.setText(NIC);
        start.setText(Start);
        end.setText(End);
        totalDays.setText(TotalDays);
        pickup.setText(Pickup);
        retLocation.setText(RetLocation);

        dbTripDetails = FirebaseDatabase.getInstance().getReference().child("TripDetails");
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDetails();
            }
        });
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDetails();
            }
        });
    }

    private void deleteDetails(){
        dbTripDetails.removeValue();
        Toast.makeText(this,"Deleted Successfully!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(SummaryActivity.this, RentCarActivity.class));
    }
    private void submitDetails(){
        Toast.makeText(this,"Submitted Successfully!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(SummaryActivity.this, SubmittedActivity.class));
    }
}