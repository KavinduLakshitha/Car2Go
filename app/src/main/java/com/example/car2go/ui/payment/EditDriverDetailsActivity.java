package com.example.car2go.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car2go.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EditDriverDetailsActivity extends AppCompatActivity {
    private DatabaseReference dbDriverDetails;
    private Button btn_Edit, btn_Delete;
    private  Button btn_SaveDetails;
    private EditText et_FullName, et_driverEmail, et_driverNIC,et_drivingLicense, etDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_driver_details);

        btn_Edit=findViewById(R.id.btn_Edit);
        btn_Delete=findViewById(R.id.btn_Delete);
        btn_SaveDetails = findViewById(R.id.btn_OK);

        //user = FirebaseAuth.getInstance().getCurrentUser();

        dbDriverDetails = FirebaseDatabase.getInstance().getReference().child("DriverDetails")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        et_FullName = (EditText)findViewById(R.id.et_FullName);
        et_driverEmail = (EditText)findViewById(R.id.et_driverEmail);
        et_driverNIC = (EditText)findViewById(R.id.et_driverNIC);
        etDate = findViewById(R.id.et_expiryDate);
        et_drivingLicense = (EditText) findViewById(R.id.et_drivingLicense);

        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = et_FullName.getText().toString().trim();
                String driverEmail = et_driverEmail.getText().toString().trim();
                String driverNIC = et_driverNIC.getText().toString().trim();
                String licenseNumber = et_drivingLicense.getText().toString().trim();
                String expiryDate = etDate.getText().toString().trim();

                Map<String,Object> map = new HashMap<>();
                map.put("fullName",fullName);
                map.put("driverEmail",driverEmail);
                map.put("driverNIC",driverNIC);
                map.put("licenseNumber",licenseNumber);
                map.put("expiryDate",expiryDate);

                dbDriverDetails.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbDriverDetails.updateChildren(map);
                        Toast.makeText(EditDriverDetailsActivity.this,"Details Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditDriverDetailsActivity.this,LoginActivity.class));
                    }
                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                           Toast.makeText(EditDriverDetailsActivity.this,"Failed to update",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDetails();
            }
        });

        final EditText et_driverName = (EditText) findViewById(R.id.et_driverName);
        final EditText et_driveEmail = (EditText) findViewById(R.id.et_driveEmail);
        final EditText et_driverNic = (EditText) findViewById(R.id.et_driverNic);
        final EditText et_licenseNum = (EditText) findViewById(R.id.et_licenseNum);
        final EditText et_licenseExpiry = (EditText) findViewById(R.id.et_licenseExpiry);

        dbDriverDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    et_driverName.setText(dataSnapshot.child("driverName").getValue().toString());
                    et_driveEmail.setText(dataSnapshot.child("driverEmail").getValue().toString());
                    et_driverNic.setText(dataSnapshot.child("nicNumber").getValue().toString());
                    et_licenseNum.setText(dataSnapshot.child("licenseNumber").getValue().toString());
                    et_licenseExpiry.setText(dataSnapshot.child("expiryDate").getValue().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "not successful", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void deleteDetails() {
        dbDriverDetails.removeValue();
        Toast.makeText(this,"Rental Details Deleted Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditDriverDetailsActivity.this,LoginActivity.class));
    }

}
