package com.example.car2go.ui.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.car2go.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddInquiry extends AppCompatActivity {
    EditText name,nic,phone,email,inquiry;
    Button buttonSend;
    DatabaseReference databaseInquiry;
    ListView listViewInquiry;
    List<Inquiry> inquiryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inquiry);

        databaseInquiry = FirebaseDatabase.getInstance().getReference("inquiries");
        name =(EditText) findViewById(R.id.enter_name);
        phone = (EditText) findViewById(R.id.enter_phone);
        nic = (EditText) findViewById(R.id.enter_nic);
        email = (EditText) findViewById(R.id.enter_email);
        inquiry = (EditText) findViewById(R.id.enter_inquiry);
        buttonSend = (Button) findViewById(R.id.Send) ;

        listViewInquiry=(ListView) findViewById(R.id.listViewInquiry);

        inquiryList = new ArrayList<>();
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addInquiry();


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseInquiry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                inquiryList.clear();
                for(DataSnapshot inquirySnapshot : dataSnapshot.getChildren()){
                    Inquiry inquiry = inquirySnapshot.getValue(Inquiry.class);
                    inquiryList.add(inquiry);
                }
                InquiryList adapter = new InquiryList(AddInquiry.this,inquiryList);
                listViewInquiry.setAdapter(adapter);
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
    }

    private void addInquiry(){

        String Name = name.getText().toString().trim();
        String NIC = nic.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Inquiry = inquiry.getText().toString().trim();

        if(!TextUtils.isEmpty(Inquiry)){
            String id = databaseInquiry.push().getKey();
            Inquiry inquiry = new Inquiry(id, Name , NIC, Phone, Email, Inquiry );
            databaseInquiry.child(id).setValue(inquiry);
            Toast.makeText(this,"Inquiry Added Successful!!", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"You Should Enter Inquiry ", Toast.LENGTH_LONG).show();
        }


    }
}