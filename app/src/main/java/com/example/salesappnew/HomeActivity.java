package com.example.salesappnew;

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

        Button logoutButton = findViewById(R.id.logoutButton);

        // بيانات المستخدم
        if (mAuth.getCurrentUser() != null) {

            String email = mAuth.getCurrentUser().getEmail();

            userEmailText.setText(
                    email != null ? email : "المستخدم"
            );
        }

        // القيم الابتدائية
        totalSalesText.setText("0.00");
        totalReceiptsText.setText("0.00");
        commissionText.setText("0.00");

        // تسجيل الخروج
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            finish();
        });
    }
}
