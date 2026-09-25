package com.example.salesappnew;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreHelper {

    private final FirebaseFirestore db;
    private final FirebaseAuth auth;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    private String getUserId() {
        if (auth.getCurrentUser() == null) {
            return null;
        }

        return auth.getCurrentUser().getUid();
    }

    public CollectionReference getProducts() {
        String uid = getUserId();
        if (uid == null) return null;

        return db.collection("users")
                .document(uid)
                .collection("products");
    }

    public CollectionReference getCustomers() {
        String uid = getUserId();
        if (uid == null) return null;

        return db.collection("users")
                .document(uid)
                .collection("customers");
    }

    public CollectionReference getInvoices() {
        String uid = getUserId();
        if (uid == null) return null;

        return db.collection("users")
                .document(uid)
                .collection("invoices");
    }

    public CollectionReference getReceipts() {
        String uid = getUserId();
        if (uid == null) return null;

        return db.collection("users")
                .document(uid)
                .collection("receipts");
    }

    public CollectionReference getPaymentVouchers() {
        String uid = getUserId();
        if (uid == null) return null;

        return db.collection("users")
                .document(uid)
                .collection("paymentVouchers");
    }

    public CollectionReference getInventoryMovements() {
        String uid = getUserId();
        if (uid == null) return null;

        return db.collection("users")
                .document(uid)
                .collection("inventoryMovements");
    }

    public CollectionReference getSettings() {
        String uid = getUserId();
        if (uid == null) return null;

        return db.collection("users")
                .document(uid)
                .collection("settings");
    }
}
