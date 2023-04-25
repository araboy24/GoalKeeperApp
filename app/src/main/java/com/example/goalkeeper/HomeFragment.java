package com.example.goalkeeper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment  extends AppCompatActivity {
//    private FirebaseFirestore db;
    private DatePickerDialog datePickerDialog;

    EditText text_goal1;
    TextView text_home;

    Button text_status1, submit_1, text_date1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
//        db = FirebaseFirestore.getInstance();

        text_home = (TextView) findViewById(R.id.text_home);
        text_goal1 = (EditText) findViewById(R.id.text_goal1);
        //  Date Button was supposed to open up a dialog prompt for calendar but I couldn't get it to work
        text_date1 = (Button) findViewById(R.id.text_date1);
        submit_1 = (Button) findViewById(R.id.submit_1);
        text_status1 = (Button) findViewById(R.id.text_status1);

        //  Adds text listeners for disabling submit1
        text_goal1.addTextChangedListener(loginTextWatcher);
        text_date1.addTextChangedListener(loginTextWatcher);

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
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String goalinput1 = text_goal1.getText().toString().trim();
            //String dateinput1 = text_date1.getText().toString().trim();

            submit_1.setEnabled(!goalinput1.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    //  Saves Goal 1
    public void updateText1(View view){
        //  This would be used to save the goal data to Firebase but I couldn't get it to work
        //  Goal1 = text_goal1.getText()
        //  Deadline1 = text_date1.getText()
        //  It would also be nice to have a confirmation prompt but that may not be necessary.
    }

    //  Changes status of Goal 1
    public void complete_goal1(View view){
        text_goal1.setText(" ");

        //  This would be used to clear goal data from goal1
        //  Goal1 = " "
        //  Deadline1 = " "
    }


}

