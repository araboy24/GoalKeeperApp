package com.example.goalkeeper;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ProfileActivity extends AppCompatActivity {

    ImageView logOutBtn;

    Button go_back;
    Button open_settings;

    FirebaseUser user;
    FirebaseAuth auth;
    String userId;
    TextView firstTV, lastTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Added by Zain
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        //setContentView(R.layout)
        logOutBtn = findViewById(R.id.logoutImg);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        firstTV = findViewById(R.id.first_name);
        lastTV = findViewById(R.id.last);
        String first, last;
        CollectionReference infoCollection = db.collection("Users").document(userId).collection("Info");

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
                        firstTV.setText(document.getString("first"));
                        lastTV.setText(document.getString("last"));
                    }
                }

            }
        });


        go_back  = findViewById(R.id.back_btn_profile);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FinalHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        open_settings = findViewById(R.id.edit_button);
        open_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileSettings.class);
                startActivity(intent);
                finish();
            }
        });

    }




}