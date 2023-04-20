package com.example.goalkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TestLogOutActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button logOutButton;
    TextView textView;
    FirebaseUser user;
    EditText editName;

    Button submitName;

    String userId;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_log_out);
        editName = findViewById(R.id.edit_name);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        textView = findViewById(R.id.user_details);
        logOutButton = findViewById(R.id.log_out);
        submitName = findViewById(R.id.submit_name);
        db = FirebaseFirestore.getInstance();
        userId = user.getUid();
        submitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                String name = editName.getText().toString();
//                int age = 3;
//                Map<String, Object> userInfo = new HashMap<>();
//                userInfo.put("name", name);
//                userInfo.put("age", age);
//                db.collection("user").document(userId)
//                        .collection(userInfo)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Toast.makeText(TestLogOutActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(TestLogOutActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });

                String name = editName.getText().toString();
                int age = 3;
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("name", name);
                userInfo.put("age", age);
                DocumentReference documentReference = db.collection("Users").document(userId);
                documentReference.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TestLogOutActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TestLogOutActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
//                 DocumentReference documentReference2 = db.collection(userId)
            }
        });


        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}