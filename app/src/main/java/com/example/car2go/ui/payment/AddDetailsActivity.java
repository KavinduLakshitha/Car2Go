package com.example.car2go.ui.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.car2go.R;
import com.example.car2go.data.DriverDetails;
import com.example.car2go.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDetailsActivity extends AppCompatActivity  {

    private Button btn_continue;
    private EditText et_reservationCode, et_FullName, et_driverEmail, et_driverNIC;
    private ProgressBar progressBar;
    private Button btn_OK;
    Button mChooseBtn;
    private EditText et_drivingLicense;
    EditText etDate;

    DatabaseReference dbDriverDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        et_reservationCode = (EditText)findViewById(R.id.et_reservationCode);
        et_FullName = (EditText)findViewById(R.id.et_FullName);
        et_driverEmail = (EditText)findViewById(R.id.et_driverEmail);
        et_driverNIC = (EditText)findViewById(R.id.et_driverNIC);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        etDate = findViewById(R.id.et_expiryDate);
        et_drivingLicense = (EditText) findViewById(R.id.et_drivingLicense);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        dbDriverDetails = FirebaseDatabase.getInstance().getReference().child("DriverDetails")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        btn_continue = (Button) findViewById(R.id.btn_save);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDriverDetails();
            }
        });
    }

    private void saveDriverDetails() {
        String reservationCode = et_reservationCode.getText().toString().trim();
        String fullName = et_FullName.getText().toString().trim();
        String driverEmail = et_driverEmail.getText().toString().trim();
        String driverNIC = et_driverNIC.getText().toString().trim();
        String licenseNumber = et_drivingLicense.getText().toString().trim();
        String expiryDate = etDate.getText().toString().trim();

        //validate nic number
        String nicRegex = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";
        Matcher nicMatcher;
        Pattern nicPattern = Pattern.compile(nicRegex);
        nicMatcher = nicPattern.matcher(driverNIC);

        if (fullName.isEmpty()) {
            et_FullName.setError("Driver's name is required");
            et_FullName.requestFocus();
            return;
        }
        if (reservationCode.isEmpty()) {
            et_reservationCode.setError("Reservation Code is required");
            et_reservationCode.requestFocus();
            return;
        }
        if (driverEmail.isEmpty()) {
            et_driverEmail.setError("Driver's email is required");
            et_driverEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(driverEmail).matches()) {
            et_driverEmail.setError("Please provide a valid email");
            et_driverEmail.requestFocus();
            return;
        }
        if (driverNIC.isEmpty()) {
            et_driverNIC.setError("Driver's name is required");
            et_driverNIC.requestFocus();
            return;
        }
        if (!nicMatcher.find()) {
            et_driverNIC.setError("NIC Number is not valid");
            et_driverNIC.requestFocus();
            return;
        }
        if(licenseNumber.isEmpty()){
            et_drivingLicense.setError("License number is required");
            et_drivingLicense.requestFocus();
            return;
        }
        if(expiryDate.isEmpty()){
            etDate.setError("Expiry Date is required");
            etDate.requestFocus();
            return;
        }
        else{
            String id = dbDriverDetails.push().getKey();

            DriverDetails driverDetails = new DriverDetails(id,reservationCode, fullName, driverEmail, driverNIC, licenseNumber, expiryDate);
            dbDriverDetails.setValue(driverDetails);
            startActivity(new Intent(AddDetailsActivity.this, EditDriverDetailsActivity.class));

        }

    }

}