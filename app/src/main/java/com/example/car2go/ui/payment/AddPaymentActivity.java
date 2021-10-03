package com.example.car2go.ui.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.car2go.R;
import com.example.car2go.data.Payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddPaymentActivity extends AppCompatActivity {
    private EditText et_cardNumber, et_expiryDate, et_cardNumber2, et_cardName;
    private Button btn_OK;
    DatabaseReference dbPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        et_cardNumber = (EditText)findViewById(R.id.et_cardNumber);
        et_expiryDate = (EditText)findViewById(R.id.et_expiryDate);
        et_cardNumber2 = (EditText)findViewById(R.id.et_cardNumber2);
        et_cardName = (EditText)findViewById(R.id.et_cardName);
        btn_OK = (Button)findViewById(R.id.btn_OK);


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

        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = et_cardNumber.getText().toString().trim() ;
                String expiryDate = et_expiryDate.getText().toString().trim();
                String cvv = et_cardNumber2.getText().toString().trim();
                String cardName = et_cardName.getText().toString().trim();
//
//                //validate credit card number
//                String creditCardRegex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
//                Matcher creditCardMatcher;
//                Pattern creditCardPattern = Pattern.compile(creditCardRegex);
//                creditCardMatcher = creditCardPattern.matcher(cardNumber);
//
//                //validate cvv
//                String cvvRegex = "^[0-9]{3, 4}$";
//                Matcher cvvMatcher;
//                Pattern cvvPattern = Pattern.compile(cvvRegex);
//                cvvMatcher = cvvPattern.matcher(cardNumber);


                if (cardNumber.isEmpty()) {
                    et_cardNumber.setError("Card Number is required");
                    et_cardNumber.requestFocus();
                    return;
                }
//                if (!creditCardMatcher.find()) {
//                    et_cardNumber.setError("Credit Card Number is not valid");
//                    et_cardNumber.requestFocus();
//                    return;
//                }
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
//                if (!cvvMatcher.find()) {
//                    et_cardNumber2.setError("CVV Number is not valid");
//                    et_cardNumber2.requestFocus();
//                    return;
//                }

                if (cardName.isEmpty()) {
                    et_cardName.setError("Card Name is required");
                    et_cardName.requestFocus();
                    return;
                }

                String id = cardNumber;
                Payment payment = new Payment(id,cardNumber, expiryDate, cvv, cardName);

                dbPayment.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        dbPayment.child(id).setValue(payment);
                        Toast.makeText(AddPaymentActivity.this,"Payment added successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddPaymentActivity.this,ConfirmRentalActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(AddPaymentActivity.this,"Error is "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }



}