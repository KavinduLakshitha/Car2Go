package com.example.car2go.ui;

import com.example.car2go.Add_Car;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOAdd_Car
{
    private final DatabaseReference databaseReference;
    public DAOAdd_Car()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Add_Car.class.getSimpleName());
    }
    public Task<Void> add(Add_Car car)
    {
        return databaseReference.push().setValue(car);
    }


}
