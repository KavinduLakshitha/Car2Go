package com.example.car2go.ui.services;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car2go.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddRating extends AppCompatActivity {
    EditText AddCustomerID,AddCustomerName,AddReview;
    Button publish;
    Spinner spinnerRateUs;
    DatabaseReference databaseRating;
    ListView listViewRating;
    List<Rating> ratingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rating);
        databaseRating = FirebaseDatabase.getInstance().getReference("Ratings");
        AddCustomerID = (EditText) findViewById(R.id.AddCustomerID);
        AddCustomerName =(EditText) findViewById(R.id.AddCustomerName);
        AddReview = (EditText) findViewById(R.id.AddReview);
        publish =(Button) findViewById(R.id.publish);
        spinnerRateUs =(Spinner) findViewById(R.id.spinnerRateUs);

        listViewRating = (ListView) findViewById(R.id.listViewRating);
        ratingList = new ArrayList<>();
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRating();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseRating.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {
                ratingList.clear();
             for(DataSnapshot ratingSnapshot : datasnapshot.getChildren()){

                 Rating rating = ratingSnapshot.getValue(Rating.class);
                 ratingList.add(rating);
             }

                RatingList adapter = new RatingList(AddRating.this,ratingList);
                listViewRating.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void addRating (){
        String CusID = AddCustomerID.getText().toString().trim();
        String Name = AddCustomerName.getText().toString().trim();
        String Review = AddReview.getText().toString().trim();
        String Rate = spinnerRateUs.getSelectedItem().toString();

        if(!TextUtils.isEmpty(Name)){
            String id = databaseRating.push().getKey();
            Rating rating = new Rating(id,CusID,Name,Review,Rate);
            databaseRating.child(id).setValue(rating);
            Toast.makeText(this,"Rating Added",Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(this,"You Should Enter A Name",Toast.LENGTH_LONG).show();
        }


    }
}