package com.example.salesappnew;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProductsActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productCategory;
    private EditText productPrice;
    private EditText productQuantity;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

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

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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

        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(
                    this,
                    "يجب تسجيل الدخول أولاً",
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

            String uid = mAuth.getCurrentUser().getUid();

            CollectionReference productsRef = db
                    .collection("users")
                    .document(uid)
                    .collection("products");

            Map<String, Object> product = new HashMap<>();

            product.put("name", name);
            product.put("category", category);
            product.put("price", price);
            product.put("quantity", quantity);
            product.put("createdAt",
                    com.google.firebase.firestore.FieldValue.serverTimestamp());

            productsRef.add(product)
                    .addOnSuccessListener(documentReference -> {

                        Toast.makeText(
                                ProductsActivity.this,
                                "تم حفظ المنتج بنجاح",
                                Toast.LENGTH_SHORT
                        ).show();

                        productName.setText("");
                        productCategory.setText("");
                        productPrice.setText("");
                        productQuantity.setText("");

                    })
                    .addOnFailureListener(e -> {

                        Toast.makeText(
                                ProductsActivity.this,
                                "فشل حفظ المنتج: " + e.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();

                    });

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "تأكد من كتابة السعر والكمية بشكل صحيح",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
