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

import com.example.car2go.R;
import com.example.car2go.data.DriverDetails;
import com.example.car2go.data.Payment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPaymentActivity extends AppCompatActivity {

    private Button btn_OK;
    private EditText et_cardNumber, et_expiryDate, et_cardNumber2, et_cardName;
    private ProgressBar progressBar;
    DatabaseReference dbPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        et_cardNumber = (EditText)findViewById(R.id.et_cardNumber);
        et_expiryDate = (EditText)findViewById(R.id.et_expiryDate);
        et_cardNumber2 = (EditText)findViewById(R.id.et_cardNumber2);
        et_cardName = (EditText)findViewById(R.id.et_cardName);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        dbPayment = FirebaseDatabase.getInstance().getReference().child("Payment");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        et_expiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddPaymentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        et_expiryDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

//        btn_OK = (Button) findViewById(R.id.btn_save);
//        btn_OK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                savePayment();
//            }
//        });

    }

    private void savePayment() {
       String cardNumber = et_cardNumber.getText().toString().trim() ;
       String expiryDate = et_expiryDate.getText().toString().trim();
       String cvv = et_cardNumber2.getText().toString().trim();
       String cardName = et_cardName.getText().toString().trim();



        if (cardNumber.isEmpty()) {
            et_cardNumber.setError("Card Number is required");
            et_cardNumber.requestFocus();
            return;
        }
        if (expiryDate.isEmpty()) {
            et_expiryDate.setError("Expiry Date is required");
            et_expiryDate.requestFocus();
            return;
        }
        if (cvv.isEmpty()) {
            et_cardNumber2.setError("CVV is required");
            et_cardNumber2.requestFocus();
            return;
        }

        if (cardName.isEmpty()) {
            et_cardName.setError("Card Name is required");
            et_cardName.requestFocus();
            return;
        }

        else{
            String id = dbPayment.push().getKey();

            Payment paymentDetails = new Payment(id,cardNumber, expiryDate, cvv, cardName);
            dbPayment.child(id).setValue(paymentDetails);
            startActivity(new Intent(AddPaymentActivity.this, LoginActivity.class));
        }
    }

}