package com.example.salesappnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView userEmailText;
    private TextView totalSalesText;
    private TextView totalReceiptsText;
    private TextView commissionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        userEmailText = findViewById(R.id.userEmailText);
        totalSalesText = findViewById(R.id.totalSalesText);
        totalReceiptsText = findViewById(R.id.totalReceiptsText);
        commissionText = findViewById(R.id.commissionText);

        Button productsButton = findViewById(R.id.productsButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        if (mAuth.getCurrentUser() != null) {

            String email = mAuth.getCurrentUser().getEmail();

            userEmailText.setText(
                    email != null ? email : "المستخدم"
            );
        }

        totalSalesText.setText("0.00");
        totalReceiptsText.setText("0.00");
        commissionText.setText("0.00");

        // فتح شاشة المنتجات
        productsButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this,
                    ProductsActivity.class
            );

            startActivity(intent);
        });

        // تسجيل الخروج
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            finish();
        });
    }
}
