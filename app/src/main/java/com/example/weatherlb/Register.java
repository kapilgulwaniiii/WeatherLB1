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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
        final TextView phonenumber, pass;
        phonenumber = (TextView) findViewById(R.id.pno);
        pass = (TextView) findViewById(R.id.password);

        final String ph = phonenumber.getText().toString();
        String password = pass.getText().toString();
        if(phonenumber.getText().toString().equals("")&&pass.getText().toString().equals(""))
        {
            Toast.makeText(Register.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();

        }
        else {

            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            final Map<String, Object> user = new HashMap<>();
            user.put("Password", password);

            DocumentReference docRef = db.collection("user").document(ph);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.e("Already exists", "DocumentSnapshot data: " + document.getData());
                            Toast.makeText(Register.this, "User already exists", Toast.LENGTH_SHORT).show();

                        } else {
                            db.collection("user").document(ph)
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("Yes", "DocumentSnapshot successfully written!");
                                            String phnno = phonenumber.getText().toString();
                                            Log.e("hellolelelel", phnno);

                                            Intent mainIntent = new Intent(Register.this, Locations.class);
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
                }
            });
        }
    }
}