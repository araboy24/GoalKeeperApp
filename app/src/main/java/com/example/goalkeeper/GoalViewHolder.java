package com.example.goalkeeper;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class GoalViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public CheckBox checkboxView;

    public GoalViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.goal_deadline);
        checkboxView = itemView.findViewById(R.id.goal_checkbox);
    }
}
