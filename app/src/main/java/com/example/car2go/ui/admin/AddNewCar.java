package com.example.car2go.ui.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car2go.Add_Car;
import com.example.car2go.R;
import com.example.car2go.ui.DAOAdd_Car;

import java.util.Objects;

public class AddNewCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
        final EditText car_name_hint = findViewById(R.id.car_name_hint);
        final EditText distance_hint = findViewById(R.id.distance_hint);
        final EditText insurance_hint = findViewById(R.id.insurance_hint);
        final EditText description_hint = findViewById(R.id.description_hint);
        Button anc_edit_btn = findViewById(R.id.anc_edit_btn);
        Button anc_dlt_btn = findViewById(R.id.anc_dlt_btn);
        Button anc_save_btn = findViewById(R.id.anc_save_btn);
        DAOAdd_Car dao= new DAOAdd_Car();
        anc_save_btn.setOnClickListener(v -> {
            Add_Car car = new Add_Car(car_name_hint.getText().toString(),distance_hint.getText().toString(),insurance_hint.getText().toString(),description_hint.getText().toString());
            dao.add(car).addOnSuccessListener(suc->{
                Toast.makeText(this,"Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            if(bundle.get("some") !=null){
                Toast.makeText(getApplicationContext(),
                        "data:"+ bundle.getString("some"),
                                Toast.LENGTH_SHORT).show();
            }
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle("Add New Car");
    }
}