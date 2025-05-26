package com.example.valuationgit_android;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText editNomEquipe, editVilleEquipe;
    private EditText editDate, editLieu;
    private Spinner spinnerEquipe1, spinnerEquipe2, spinnerMatchs;
    private TextView scoreEquipe1Text, scoreEquipe2Text;
    private Button finMatchButton;

    private ArrayAdapter<Equipe> equipeAdapter;
    private ArrayAdapter<Match> matchAdapter;

    private TournoiManager tournoiManager = new TournoiManager();
    private Match matchEnCours = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Champs équipe
        editNomEquipe = findViewById(R.id.nomEquipe);
        editVilleEquipe = findViewById(R.id.villeEquipe);

        // Champs match
        editDate = findViewById(R.id.dateMatch);
        editLieu = findViewById(R.id.lieuMatch);

        spinnerEquipe1 = findViewById(R.id.spinnerEquipe1);
        spinnerEquipe2 = findViewById(R.id.spinnerEquipe2);
        spinnerMatchs = findViewById(R.id.spinnerMatchs);

        scoreEquipe1Text = findViewById(R.id.scoreEquipe1);
        scoreEquipe2Text = findViewById(R.id.scoreEquipe2);
        finMatchButton = findViewById(R.id.finMatch);

        // Adapters
        equipeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tournoiManager.getEquipes());
        equipeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        matchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tournoiManager.getMatchs());
        matchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEquipe1.setAdapter(equipeAdapter);
        spinnerEquipe2.setAdapter(equipeAdapter);
        spinnerMatchs.setAdapter(matchAdapter);

        // Boutons
        findViewById(R.id.boutonCreerEquipe).setOnClickListener(v -> ajouterEquipe());
        findViewById(R.id.boutonCreerMatch).setOnClickListener(v -> ajouterMatch());
        spinnerMatchs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matchEnCours = tournoiManager.getMatchs().get(position);
                afficherScores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        findViewById(R.id.btnAdd1Equipe1).setOnClickListener(v -> ajouterPoints(1, true));
        findViewById(R.id.btnAdd3Equipe1).setOnClickListener(v -> ajouterPoints(3, true));
        findViewById(R.id.btnAdd1Equipe2).setOnClickListener(v -> ajouterPoints(1, false));
        findViewById(R.id.btnAdd3Equipe2).setOnClickListener(v -> ajouterPoints(3, false));
        finMatchButton.setOnClickListener(v -> terminerMatch());
    }
    private void ajouterEquipe() {
        String nom = editNomEquipe.getText().toString().trim();
        String ville = editVilleEquipe.getText().toString().trim();

        if (!nom.isEmpty() && !ville.isEmpty()) {
            Equipe equipe = new Equipe(nom, ville);
            tournoiManager.ajouterEquipe(equipe);
            equipeAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Équipe ajoutée !", Toast.LENGTH_SHORT).show();
        }
    }
    private void ajouterMatch() {
        Equipe e1 = (Equipe) spinnerEquipe1.getSelectedItem();
        Equipe e2 = (Equipe) spinnerEquipe2.getSelectedItem();
        String date = editDate.getText().toString();
        String lieu = editLieu.getText().toString();

        if (e1 != null && e2 != null && !e1.equals(e2) && !date.isEmpty() && !lieu.isEmpty()) {
            Match match = new Match(e1, e2, date, lieu);
            tournoiManager.ajouterMatch(match);
            matchAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Match ajouté !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Veuillez saisir des équipes différentes et toutes les infos.", Toast.LENGTH_SHORT).show();
        }
    }

    private void afficherScores() {
        if (matchEnCours != null) {
            scoreEquipe1Text.setText(String.valueOf(matchEnCours.getScoreEquipe1()));
            scoreEquipe2Text.setText(String.valueOf(matchEnCours.getScoreEquipe2()));
        }
    }
    private void terminerMatch() {
        if (matchEnCours != null && !matchEnCours.isTermine()) {
            matchEnCours.terminerMatch();
            Toast.makeText(this, "Match terminé !", Toast.LENGTH_SHORT).show();
        }
    }


}