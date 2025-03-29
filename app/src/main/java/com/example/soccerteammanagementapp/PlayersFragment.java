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

public class PlayersFragment extends Fragment {
    private Repository<Player> playerRepository;
    private PlayerAdapter adapter;
    private EditText searchEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Initialize repository with sample data
        playerRepository = new Repository<>();
        for (Player player : DataProvider.createSamplePlayers()) {
            playerRepository.add(player);
        }

        // Setup RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PlayerAdapter(new ArrayList<>(playerRepository.getAll()));
        recyclerView.setAdapter(adapter);

        // Setup search
        searchEditText = view.findViewById(R.id.searchEditText);
        view.findViewById(R.id.searchButton).setOnClickListener(v -> performSearch());

        // Sort buttons
        view.findViewById(R.id.sortByNameButton).setOnClickListener(v -> sortByName());
        view.findViewById(R.id.sortByCountryButton).setOnClickListener(v -> sortByAge());

        return view;
    }

    private void performSearch() {
        String query = searchEditText.getText().toString().toLowerCase();
        List<Player> filtered = playerRepository.filter(player -> 
            player.getName().toLowerCase().contains(query) || 
            player.getTeam().toLowerCase().contains(query) ||
            player.getPosition().toLowerCase().contains(query));
        adapter.updateData(filtered);
    }

    private void sortByName() {
        List<Player> sorted = new ArrayList<>(playerRepository.getAll());
        sorted.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        adapter.updateData(sorted);
    }

    private void sortByAge() {
        List<Player> sorted = new ArrayList<>(playerRepository.getAll());
        sorted.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        adapter.updateData(sorted);
    }
}