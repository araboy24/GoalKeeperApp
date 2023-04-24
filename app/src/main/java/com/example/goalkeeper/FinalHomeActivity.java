package com.example.goalkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalHomeActivity extends AppCompatActivity {
    Button add_goal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_home);
        getSupportActionBar().hide();

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