package com.example.car2go.ui.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car2go.R;

import java.util.Objects;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Objects.requireNonNull(getSupportActionBar()).setTitle("History");
    }
}