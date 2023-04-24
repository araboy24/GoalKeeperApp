package com.example.goalkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddGoalActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private DatePickerDialog datePickerDialog;

    EditText newGoalEdt, deadlineEdt;
    TextView text_home;

    Button text_status1, saveGoalBtn, selectDeadlineBtn;
    FirebaseUser user;
    FirebaseAuth auth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        getSupportActionBar().hide();
        db = FirebaseFirestore.getInstance();

        text_home = (TextView) findViewById(R.id.text_home);
        newGoalEdt = (EditText) findViewById(R.id.text_goal1);
        deadlineEdt = (EditText) findViewById(R.id.deadline);
        saveGoalBtn = (Button) findViewById(R.id.submit_1);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        saveGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String goal, deadline;
                goal = String.valueOf(newGoalEdt.getText().toString());
                deadline = String.valueOf(deadlineEdt.getText().toString());
                if (TextUtils.isEmpty(goal)){
                    Toast.makeText(AddGoalActivity.this, "Enter Goal", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(deadline)){
                    Toast.makeText(AddGoalActivity.this, "Enter Deadline", Toast.LENGTH_SHORT).show();
                    return;
                }
                String regex = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[0-1])/\\d{4}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(deadline);
                if (!matcher.matches()) {
                    Toast.makeText(AddGoalActivity.this, "Deadline must be (MM/DD/YYYY)", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Create a new goal document
                Map<String, Object> goalData = new HashMap<>();
                goalData.put("name", goal);
                goalData.put("deadline", deadline);

// Add the goal document to the "Goals" sub-collection
                CollectionReference goalsCollection = db.collection("Users").document(userId).collection("Goals");
                goalsCollection.add(goalData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddGoalActivity.this, "Goal added", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddGoalActivity.this, "Failed to add goal", Toast.LENGTH_SHORT).show();
                            }
                        });

                Intent intent = new Intent(getApplicationContext(), FinalHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


//        //  Adds text listeners for disabling submit1
//        newGoalEdt.addTextChangedListener(loginTextWatcher);
//        selectDeadlineBtn.addTextChangedListener(loginTextWatcher);

        /*

        Issue here

        I'm not completely sure how to update the goal text immediately upon startup.

        The pseudo would be:
        If (User has Firebase Goal) then
            Update Goal1 Text to have Firebase Goal Text
        else
            Do nothing (since there is no goal)
        end

        Some other stuff worth noting is that we may not need status of goal app.
        I've made it so that if you've press "complete", it just clears it.
        Also, I'm not sure if we want to make multiple goals or just stick to the singular one
        we have right now. We can just say on the presentation that it would be allow
        more goals instead of just one goal in an ideal version.


         */

    }

    //  Disables "Submit Goal" button whenever there is no text inside of "goal"
//    private TextWatcher loginTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            String goalinput1 = newGoalEdt.getText().toString().trim();
//            //String dateinput1 = text_date1.getText().toString().trim();
//
//            saveGoalBtn.setEnabled(!goalinput1.isEmpty());
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    };
//
//    //  Saves Goal 1
//    public void updateText1(View view){
//        //  This would be used to save the goal data to Firebase but I couldn't get it to work
//        //  Goal1 = text_goal1.getText()
//        //  Deadline1 = text_date1.getText()
//        //  It would also be nice to have a confirmation prompt but that may not be necessary.
//    }
//
//    //  Changes status of Goal 1
//    public void complete_goal1(View view){
//        newGoalEdt.setText(" ");
//
//        //  This would be used to clear goal data from goal1
//        //  Goal1 = " "
//        //  Deadline1 = " "
//    }
}