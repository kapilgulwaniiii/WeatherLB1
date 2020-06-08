package com.example.weatherlb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void main(View view) {
        TextView phonenumber, pass;
        phonenumber = (TextView) findViewById(R.id.pno);
        pass = (TextView) findViewById(R.id.password);

        String ph = phonenumber.getText().toString();
        String password = pass.getText().toString();


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("Password", password);


        db.collection("user").document(ph)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Yes", "DocumentSnapshot successfully written!");
                        Intent mainIntent = new Intent(Register.this,DisplayLB.class);
                        startActivity(mainIntent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("fail", "Error writing document", e);
                    }
                });
    }
}