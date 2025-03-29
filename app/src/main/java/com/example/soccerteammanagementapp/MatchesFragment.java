package com.example.soccerteammanagementapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {
    private Repository<Match> matchRepository;
    private MatchAdapter adapter;
    private EditText searchEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Initialize repository with sample data
        matchRepository = new Repository<>();
        for (Match match : DataProvider.createSampleMatches()) {
            matchRepository.add(match);
        }

        // Setup RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MatchAdapter(new ArrayList<>(matchRepository.getAll()));
        recyclerView.setAdapter(adapter);

        // Setup search
        searchEditText = view.findViewById(R.id.searchEditText);
        view.findViewById(R.id.searchButton).setOnClickListener(v -> performSearch());

        // Sort buttons
        view.findViewById(R.id.sortByNameButton).setOnClickListener(v -> sortByDate());
        view.findViewById(R.id.sortByCountryButton).setOnClickListener(v -> sortByCompetition());

        return view;
    }

    private void performSearch() {
        String query = searchEditText.getText().toString().toLowerCase();
        List<Match> filtered = matchRepository.filter(match -> 
            match.getHomeTeam().toLowerCase().contains(query) || 
            match.getAwayTeam().toLowerCase().contains(query) ||
            match.getCompetition().toLowerCase().contains(query));
        adapter.updateData(filtered);
    }

    private void sortByDate() {
        List<Match> sorted = new ArrayList<>(matchRepository.getAll());
        sorted.sort((m1, m2) -> m2.getDate().compareTo(m1.getDate())); // Newest first
        adapter.updateData(sorted);
    }

    private void sortByCompetition() {
        List<Match> sorted = new ArrayList<>(matchRepository.getAll());
        sorted.sort((m1, m2) -> m1.getCompetition().compareToIgnoreCase(m2.getCompetition()));
        adapter.updateData(sorted);
    }
}