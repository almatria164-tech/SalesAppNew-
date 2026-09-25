package com.example.salesappnew;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductsActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productCategory;
    private EditText productPrice;
    private EditText productQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        productName = findViewById(R.id.productName);
        productCategory = findViewById(R.id.productCategory);
        productPrice = findViewById(R.id.productPrice);
        productQuantity = findViewById(R.id.productQuantity);

        Button saveButton = findViewById(R.id.saveProductButton);
        Button backButton = findViewById(R.id.backButton);

        saveButton.setOnClickListener(v -> saveProduct());

        backButton.setOnClickListener(v -> finish());
    }

    private void saveProduct() {

        String name = productName.getText().toString().trim();
        String category = productCategory.getText().toString().trim();
        String priceText = productPrice.getText().toString().trim();
        String quantityText = productQuantity.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(
                    this,
                    "اكتب اسم المنتج",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (priceText.isEmpty()) {
            Toast.makeText(
                    this,
                    "اكتب سعر المنتج",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (quantityText.isEmpty()) {
            Toast.makeText(
                    this,
                    "اكتب الكمية",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        try {

            double price = Double.parseDouble(priceText);
            double quantity = Double.parseDouble(quantityText);

            if (price < 0 || quantity < 0) {
                Toast.makeText(
                        this,
                        "السعر والكمية لا يمكن أن يكونا سالبين",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            Toast.makeText(
                    this,
                    "تم تجهيز المنتج للحفظ",
                    Toast.LENGTH_SHORT
            ).show();

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "تأكد من كتابة السعر والكمية بشكل صحيح",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
