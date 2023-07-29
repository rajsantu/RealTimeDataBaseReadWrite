package com.mainpackage.realtimedatabasereadwrite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail;
    TextView txtName, txtEmail;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);

        btnSubmit = findViewById(R.id.btnSubmit);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");

        btnSubmit.setOnClickListener(v ->
        {
            UserDataModel user = new UserDataModel
                    (
                            etName.getText().toString(),
                            etEmail.getText().toString()
                    );

            //Write data on database
            databaseReference.setValue(user);
        });
        // Read data from database
        databaseReference.child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                txtEmail.setText(Objects.requireNonNull(snapshot.getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error:" + error.getCode());
            }
        });

        databaseReference.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtName.setText(Objects.requireNonNull(snapshot.getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error:" + error.getCode());
            }
        });
    }
}