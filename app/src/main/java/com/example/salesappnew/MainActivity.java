package com.example.salesappnew;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_main);

            auth = FirebaseAuth.getInstance();

            EditText email = findViewById(R.id.email);
            EditText password = findViewById(R.id.password);
            Button loginButton = findViewById(R.id.loginButton);
            Button registerButton = findViewById(R.id.registerButton);

            loginButton.setOnClickListener(v -> {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(
                            this,
                            "أدخل البريد وكلمة المرور",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                auth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(
                                        this,
                                        "تم تسجيل الدخول بنجاح",
                                        Toast.LENGTH_SHORT
                                ).show();
                            } else {
                                Toast.makeText(
                                        this,
                                        "فشل تسجيل الدخول",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        });
            });

            registerButton.setOnClickListener(v -> {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(
                            this,
                            "أدخل البريد وكلمة المرور",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                if (passwordText.length() < 6) {
                    Toast.makeText(
                            this,
                            "كلمة المرور 6 أحرف على الأقل",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(
                                emailText,
                                passwordText
                        )
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(
                                        this,
                                        "تم إنشاء الحساب",
                                        Toast.LENGTH_SHORT
                                ).show();
                            } else {
                                Toast.makeText(
                                        this,
                                        "فشل إنشاء الحساب",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        });
            });

        } catch (Exception e) {
            Toast.makeText(
                    this,
                    "حدث خطأ في التطبيق",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
