package com.example.soccerteammanagementapp;

public class Match implements SoccerEntity {
    private String homeTeam;
    private String awayTeam;
    private String score;
    private String competition;
    private String date;
    private String venue;

    public Match(String homeTeam, String awayTeam, String score, String competition, String date, String venue) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.competition = competition;
        this.date = date;
        this.venue = venue;
    }

    @Override
    public String getName() {
        return homeTeam + " vs " + awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getScore() {
        return score;
    }

    public String getCompetition() {
        return competition;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }
}