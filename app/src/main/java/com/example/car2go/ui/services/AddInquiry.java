package com.example.car2go.ui.services;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car2go.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        databaseInquiry = FirebaseDatabase.getInstance().getReference("Inquiries");
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
           listViewInquiry.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               /**
                * Callback method to be invoked when an item in this view has been
                * clicked and held.
                * <p>
                * Implementers can call getItemAtPosition(position) if they need to access
                * the data associated with the selected item.
                *
                * @param parent   The AbsListView where the click happened
                * @param view     The view within the AbsListView that was clicked
                * @param position The position of the view in the list
                * @param id       The row id of the item that was clicked
                * @return true if the callback consumed the long click, false otherwise
                */
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   Inquiry inquiry = inquiryList.get(position);
                   showUpdateDialog(inquiry.getInquiryId(),inquiry.getCustomerName(),inquiry.getCustomerNIC(),inquiry.getCustomerEmail(),inquiry.getCustomerPhone(),inquiry.getCustomerInquiry());
                   return false;
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

    private void showUpdateDialog(String inquiryID, String customerName, String customerNIC, String customerEmail, String customerPhone, String customerInquiry){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_inquiry,null);
        dialogBuilder.setView(dialogView);
        final EditText editName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editNIC = (EditText) dialogView.findViewById(R.id.editTextNIC);
        final EditText editPhone = (EditText) dialogView.findViewById(R.id.editTextPhone);
        final EditText editEmail = (EditText) dialogView.findViewById(R.id.editTextEmail);
        final EditText editInquiry= (EditText) dialogView.findViewById(R.id.editTextInquiry);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.btnDelete);
        dialogBuilder.setTitle("Updating Inquiry" + inquiryID);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String nic = editNIC.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String email= editEmail.getText().toString().trim();
                String inquiry = editInquiry.getText().toString().trim();
                if (TextUtils.isEmpty(inquiry)) {
                    editInquiry.setError("Inquiry Required");
                    return;
               }
                updateArtist(inquiryID, name, nic, phone, email,inquiry);
                alertDialog.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                deleteInquiry(inquiryID);
            }
        });

    }
    private void deleteInquiry(String inquiryID){
       DatabaseReference drInquiry = FirebaseDatabase.getInstance().getReference("Inquiries").child(inquiryID);
       drInquiry.removeValue();
        Toast.makeText(this,"Inquiry Delete Successful",Toast.LENGTH_LONG).show();


    }
    private boolean updateArtist(String inquiryId, String customerName, String customerNIC, String customerEmail, String customerPhone, String customerInquiry){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("inquiries").child(inquiryId);
        Inquiry inquiry  = new Inquiry(inquiryId, customerName , customerNIC,customerEmail,customerPhone,customerInquiry);


        databaseReference.setValue(inquiry);
        Toast.makeText(this,"Inquiry Updated Successful",Toast.LENGTH_LONG).show();
        return true;
    }

    private void addInquiry(){

        String Name = name.getText().toString().trim();
        String NIC = nic.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Inquiry = inquiry.getText().toString().trim();

        //validate nic number
        String nicRegex = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";
        Matcher nicMatcher;
        Pattern nicPattern = Pattern.compile(nicRegex);
        nicMatcher = nicPattern.matcher(NIC);

        if(!TextUtils.isEmpty(Inquiry)){


            if (!nicMatcher.find()) {
                nic.setError("NIC Number is not valid");
                nic.requestFocus();

                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    email.setError("Please provide a valid email");
                    email.requestFocus();

                    String id = databaseInquiry.push().getKey();
                    Inquiry inquiry = new Inquiry(id, Name , NIC, Phone, Email, Inquiry );
                    databaseInquiry.child(id).setValue(inquiry);

                    Toast.makeText(this, "Inquiry Added Successful!!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        else{
            Toast.makeText(this,"You Should Enter Inquiry ", Toast.LENGTH_LONG).show();

        }


    }
}