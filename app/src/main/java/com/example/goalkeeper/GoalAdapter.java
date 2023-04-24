package com.example.goalkeeper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalViewHolder> {
    private List<Goal> goals;

    public GoalAdapter(List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goal_item, parent, false);
        return new GoalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        Goal goal = goals.get(position);
        holder.nameTextView.setText(goal.getDeadlineString());
        holder.checkboxView.setChecked(goal.isCompleted());
        holder.checkboxView.setText(goal.getName());
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }
}
