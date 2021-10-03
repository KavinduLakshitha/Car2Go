package com.example.car2go.ui.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car2go.R;
import com.example.car2go.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_linkSignUp;
    private Button btn_createAccount;
    private EditText et_cardNumber, et_email, et_phone, et_password;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        btn_createAccount = (Button) findViewById(R.id.btn_createAccount);
        btn_createAccount.setOnClickListener(this);

        tv_linkSignUp = (TextView) findViewById(R.id.tv_linkSignUp);
        tv_linkSignUp.setOnClickListener(this);

        et_cardNumber = (EditText)findViewById(R.id.et_cardNumber);
        et_email = (EditText)findViewById(R.id.et_email);
        et_phone = (EditText)findViewById(R.id.et_phone);
        et_password = (EditText)findViewById(R.id.et_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_linkSignUp:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_createAccount:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String name = et_cardNumber.getText().toString().trim();
        String contactNo = et_phone.getText().toString().trim();

        if(name.isEmpty()){
            et_cardNumber.setError("Full name is required");
            et_cardNumber.requestFocus();
            return;
        }

        if(contactNo.isEmpty()){
            et_phone.setError("Age is required");
            et_phone.requestFocus();
            return;
        }

        if(email.isEmpty()){
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Please provide a valid email");
            et_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            et_password.setError("Password is required");
            et_password.requestFocus();
            return;
        }

        if(password.length() < 6){
            et_password.setError("Password length should be more than 6 characters");
            et_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name, email, contactNo, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull  Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        //redirect to login layout
                                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                    }else{
                                        Toast.makeText(SignUpActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUpActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}