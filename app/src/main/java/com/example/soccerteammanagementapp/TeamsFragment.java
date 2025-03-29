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

public class TeamsFragment extends Fragment {
    private Repository<Team> teamRepository;
    private TeamAdapter adapter;
    private EditText searchEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Initialize repository with sample data
        teamRepository = new Repository<>();
        for (Team team : DataProvider.createSampleTeams()) {
            teamRepository.add(team);
        }

        // Setup RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TeamAdapter(new ArrayList<>(teamRepository.getAll()));
        recyclerView.setAdapter(adapter);

        // Setup search
        searchEditText = view.findViewById(R.id.searchEditText);
        view.findViewById(R.id.searchButton).setOnClickListener(v -> performSearch());

        // Sort buttons
        view.findViewById(R.id.sortByNameButton).setOnClickListener(v -> sortByName());
        view.findViewById(R.id.sortByCountryButton).setOnClickListener(v -> sortByCountry());

        return view;
    }

    private void performSearch() {
        String query = searchEditText.getText().toString().toLowerCase();
        List<Team> filtered = teamRepository.filter(team -> 
            team.getName().toLowerCase().contains(query) || 
            team.getCountry().toLowerCase().contains(query)
        );
        adapter.updateData(filtered);
    }

    private void sortByName() {
        List<Team> sorted = new ArrayList<>(teamRepository.getAll());
        sorted.sort((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName()));
        adapter.updateData(sorted);
    }

    private void sortByCountry() {
        List<Team> sorted = new ArrayList<>(teamRepository.getAll());
        sorted.sort((t1, t2) -> t1.getCountry().compareToIgnoreCase(t2.getCountry()));
        adapter.updateData(sorted);
    }
}