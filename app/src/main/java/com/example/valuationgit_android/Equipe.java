package com.example.valuationgit_android;

public class Equipe {
    private String nom;
    private String ville;

    public Equipe(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    @Override
    public String toString() {
        return nom + " (" + ville + ")";
    }


}
