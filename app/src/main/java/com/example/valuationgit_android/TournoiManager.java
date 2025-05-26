package com.example.valuationgit_android;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TournoiManager {
    private List<Equipe> equipes = new ArrayList<>();
    private List<Match> matchs = new ArrayList<>();

    public void ajouterEquipe(Equipe equipe) {
        equipes.add(equipe);
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void ajouterMatch(Match match) {
        matchs.add(match);
    }

    public List<Match> getMatchs() {
        return matchs;
    }

}

