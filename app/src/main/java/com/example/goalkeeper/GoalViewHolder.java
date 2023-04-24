package com.example.goalkeeper;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class GoalViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public CheckBox checkboxView;

    FirebaseUser user;
    FirebaseAuth auth;
    String userId;



    public GoalViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.goal_deadline);
        checkboxView = itemView.findViewById(R.id.goal_checkbox);


        checkboxView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String goalId = (String) checkboxView.getTag();
            // Update the database with the new completion status
            updateCompletionStatus(isChecked, goalId);
        });

    }

    private void updateCompletionStatus(boolean isCompleted, String goalId) {
        // Get a reference to your Firestore database
        System.out.println("Here");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        // Update the completion status of the goal in Firestore
        if (goalId != null) {
            DocumentReference goalRef = db.collection("Users").document(userId).collection("Goals").document(goalId);

            goalRef.update("is_completed", isCompleted)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Show a success message
//                        Toast.makeText(MainActivity.this, "Goal completion status updated.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Show an error message
//                        Toast.makeText(MainActivity.this, "Failed to update goal completion status.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

}
