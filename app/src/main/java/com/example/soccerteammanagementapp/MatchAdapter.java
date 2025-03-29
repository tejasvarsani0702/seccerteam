package com.example.soccerteammanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {
    private List<Match> matches;

    public MatchAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);
        holder.teamsTextView.setText(match.getHomeTeam() + " vs " + match.getAwayTeam());
        holder.detailsTextView.setText(match.getScore() + " | " + match.getCompetition() + " | " + match.getDate());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public void updateData(List<Match> newMatches) {
        matches = newMatches;
        notifyDataSetChanged();
    }

    static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView teamsTextView;
        TextView detailsTextView;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            teamsTextView = itemView.findViewById(android.R.id.text1);
            detailsTextView = itemView.findViewById(android.R.id.text2);
        }
    }
}