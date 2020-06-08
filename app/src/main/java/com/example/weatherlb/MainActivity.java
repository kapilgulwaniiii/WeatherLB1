package com.example.weatherlb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth;
    }

    public void login(View view) {

        final TextView phone,pass;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        phone = (TextView) findViewById(R.id.pno);
        pass = (TextView) findViewById(R.id.password);

        DocumentReference docRef = db.collection("user").document(phone.getText().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("hey", "DocumentSnapshot data: " + document.getData());
                        if(pass.getText().toString().equals(document.getData().get("Password"))){
                            Log.d("","pass matched");
                            Intent mainIntent = new Intent(MainActivity.this,Locations.class);
                            startActivity(mainIntent);
                        }else {
                            Log.e("","failed");
                            Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("Error", "No such document");
                    }
                } else {
                    Log.d("MajorError", "get failed with ", task.getException());
                }
            }
        });

    }

    public void register(View view) {
        Intent mainIntent = new Intent(MainActivity.this,Register.class);
        startActivity(mainIntent);
    }
}

