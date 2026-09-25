package com.example.salesappnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        // تسجيل الدخول
        loginButton.setOnClickListener(v -> {

            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(
                        this,
                        "اكتب البريد الإلكتروني وكلمة المرور",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, task -> {

                        if (task.isSuccessful()) {

                            Toast.makeText(
                                    this,
                                    "تم تسجيل الدخول بنجاح",
                                    Toast.LENGTH_SHORT
                            ).show();

                            // الانتقال إلى الصفحة الرئيسية
                            Intent intent = new Intent(
                                    MainActivity.this,
                                    HomeActivity.class
                            );

                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(
                                    this,
                                    "فشل تسجيل الدخول: "
                                            + task.getException().getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
        });

        // إنشاء حساب
        registerButton.setOnClickListener(v -> {

            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(
                        this,
                        "اكتب البريد الإلكتروني وكلمة المرور",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, task -> {

                        if (task.isSuccessful()) {

                            Toast.makeText(
                                    this,
                                    "تم إنشاء الحساب بنجاح",
                                    Toast.LENGTH_SHORT
                            ).show();

                            // الانتقال إلى الصفحة الرئيسية
                            Intent intent = new Intent(
                                    MainActivity.this,
                                    HomeActivity.class
                            );

                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(
                                    this,
                                    "فشل إنشاء الحساب: "
                                            + task.getException().getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
        });
    }
}
