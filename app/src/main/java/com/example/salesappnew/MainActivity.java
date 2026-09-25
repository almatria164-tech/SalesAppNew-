package com.example.salesappnew;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "واجهة التطبيق تعمل",
                        Toast.LENGTH_SHORT
                ).show()
        );

        registerButton.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "واجهة التطبيق تعمل",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }
}
