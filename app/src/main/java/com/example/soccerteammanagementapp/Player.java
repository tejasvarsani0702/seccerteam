package com.example.soccerteammanagementapp;

public class Player implements SoccerEntity {
    private String name;
    private int age;
    private String nationality;
    private String position;
    private String team;
    private int jerseyNumber;

    public Player(String name, int age, String nationality, String position, String team, int jerseyNumber) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.position = position;
        this.team = team;
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }
}