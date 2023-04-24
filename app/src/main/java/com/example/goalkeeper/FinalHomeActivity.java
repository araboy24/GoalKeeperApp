package com.example.goalkeeper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinalHomeActivity extends AppCompatActivity {
    Button add_goal;
    private FirebaseFirestore db;
    FirebaseUser user;
    FirebaseAuth auth;
    String userId;

    ImageView logoutBtn;

    private List<Goal> goals;
    private RecyclerView recyclerView;
    private GoalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_home);
        getSupportActionBar().hide();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();
        logoutBtn = findViewById(R.id.profile);


        // Get a reference to the user's "Goals" sub-collection
        CollectionReference goalsCollection = db.collection("Users").document(userId).collection("Goals");

        // Create a query to retrieve all documents in the "Goals" sub-collection
        Query query = goalsCollection.orderBy("deadline", Query.Direction.ASCENDING);

        // Attach a listener to handle the query results
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }

                if (snapshot != null && !snapshot.isEmpty()) {

                    // Get the list of goal documents from the query result
                    List<DocumentSnapshot> goalDocuments = snapshot.getDocuments();

                    // Convert each goal document to a Goal object and add it to a list
                    List<Goal> goals = new ArrayList<>();
                    for (DocumentSnapshot document : goalDocuments) {

                        String name = document.getString("name");
                        String deadline = document.getString("deadline");
                        boolean isCompleted = document.getBoolean("is_completed");
                        String id = document.getString("id");
                        Goal goal = new Goal(name, deadline, isCompleted, id);
                        goals.add(goal);
                    }

                    // Create a new adapter for the RecyclerView and set it
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    GoalAdapter adapter = new GoalAdapter(goals);
                    recyclerView.setAdapter(adapter);

                    // Set the RecyclerView's layout manager
                    LinearLayoutManager layoutManager = new LinearLayoutManager(FinalHomeActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                }
            }
        });



        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        add_goal = findViewById(R.id.plusButton);

        add_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddGoalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}