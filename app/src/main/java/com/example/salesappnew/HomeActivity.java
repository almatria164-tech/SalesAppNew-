package com.example.salesappnew;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        TextView welcomeText = findViewById(R.id.welcomeText);
        Button logoutButton = findViewById(R.id.logoutButton);

        if (mAuth.getCurrentUser() != null) {
            String email = mAuth.getCurrentUser().getEmail();

            welcomeText.setText(
                    "مرحبًا بك في Sales App\n" +
                    (email != null ? email : "")
            );
        }

        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            finish();
        });
    }
}
