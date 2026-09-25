package com.example.salesappnew;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        Button productsButton = findViewById(R.id.productsButton);
        Button customersButton = findViewById(R.id.customersButton);
        Button salesButton = findViewById(R.id.salesButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        productsButton.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "قسم المنتجات",
                        Toast.LENGTH_SHORT
                ).show()
        );

        customersButton.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "قسم العملاء",
                        Toast.LENGTH_SHORT
                ).show()
        );

        salesButton.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "قسم المبيعات",
                        Toast.LENGTH_SHORT
                ).show()
        );

        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            finish();
        });
    }
}
