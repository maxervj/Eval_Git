package com.example.valuationgit_android;

public class Match {
    private Equipe equipe1;
    private Equipe equipe2;
    private String date;
    private String lieu;
    private int scoreEquipe1;
    private int scoreEquipe2;
    private boolean termine;

    public Match(Equipe equipe1, Equipe equipe2, String date, String lieu) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date = date;
        this.lieu = lieu;
        this.scoreEquipe1 = 0;
        this.scoreEquipe2 = 0;
        this.termine = false;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public String getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public int getScoreEquipe1() {
        return scoreEquipe1;
    }

    public int getScoreEquipe2() {
        return scoreEquipe2;
    }

    public void ajouterPointsEquipe1(int points) {
        scoreEquipe1 += points;
    }

    public void ajouterPointsEquipe2(int points) {
        scoreEquipe2 += points;
    }

    public boolean isTermine() {
        return termine;
    }

    public void terminerMatch() {
        this.termine = true;
    }

    @Override
    public String toString() {
        return equipe1.getNom() + " vs " + equipe2.getNom() + " (" + date + ")";
    }
}

