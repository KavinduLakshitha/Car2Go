package com.example.car2go.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.car2go.databinding.FragmentAdminBinding;

public class AdminFragment extends Fragment {

    private AdminViewModel adminViewModel;
    private FragmentAdminBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adminViewModel =
                new ViewModelProvider(this).get(AdminViewModel.class);

        binding = FragmentAdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAdmin;
        adminViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    final EditText et_carName = (EditText) findViewById(R.id.et_carName);
    final EditText et_distance = (EditText) findViewById(R.id.et_distance);
    final EditText et_insurance = (EditText) findViewById(R.id.et_insurance);
    final EditText et_description= (EditText) findViewById(R.id.et_description);

        dbDriverDetails.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.hasChildren()) {
                et_carName.setText(dataSnapshot.child("carName").getValue().toString());
                et_distance.setText(dataSnapshot.child("distance").getValue().toString());
                et_insurance.setText(dataSnapshot.child("insurance").getValue().toString());
                et_description.setText(dataSnapshot.child("description").getValue().toString());

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