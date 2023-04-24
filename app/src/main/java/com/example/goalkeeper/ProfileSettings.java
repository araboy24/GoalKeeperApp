package com.example.goalkeeper;


import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileSettings extends AppCompatActivity {
    Button save;
    EditText firstEdt, lastEdt;

    FirebaseUser user;
    FirebaseAuth auth;
    String userId;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();
        //Added by Zain
        setContentView(R.layout.activity_profile_settings);
        getSupportActionBar().hide();
        firstEdt = findViewById(R.id.first_edt);
        lastEdt = findViewById(R.id.last_edt);
        //setContentView(R.layout)


        save  = findViewById(R.id.save_button);

        CollectionReference infoCollection = db.collection("Users").document(userId).collection("Info");
        infoCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (value != null && !value.isEmpty()) {
                    List<DocumentSnapshot> infoDocs = value.getDocuments();
                    List<Goal> info = new ArrayList<>();
                    for (DocumentSnapshot document : infoDocs) {
                        firstEdt.setText(document.getString("first"));
                        lastEdt.setText(document.getString("last"));
                    }
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String first, last;
                first = String.valueOf(firstEdt.getText().toString());
                last = String.valueOf(lastEdt.getText().toString());

                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                finish();
                if (TextUtils.isEmpty(first)){
                    Toast.makeText(ProfileSettings.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(last)){
                    Toast.makeText(ProfileSettings.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("first", first);
                userInfo.put("last", last);
                CollectionReference userInfoColl = db.collection("Users").document(userId).collection("Info");
                String infoId = "info";
                userInfoColl.document(infoId)
                        .set(userInfo)
                        .addOnSuccessListener((new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ProfileSettings.this, "Changes Saved", Toast.LENGTH_SHORT).show();

                    }
                }));
            }
        });

    }







}
