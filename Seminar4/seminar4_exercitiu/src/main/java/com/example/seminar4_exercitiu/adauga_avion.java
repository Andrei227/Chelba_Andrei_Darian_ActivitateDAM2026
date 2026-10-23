package com.example.seminar4_exercitiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class adauga_avion extends AppCompatActivity {

    private EditText etModel, etCapacitate;
    private Spinner spinnerCompanie;
    private RatingBar ratingBar;
    private Switch switchDisponibil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_avion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        boolean useAIdesign = false;

        if(intent != null && intent.getExtras() != null) {
            useAIdesign = intent.getExtras().getBoolean("foloseste_design_ai", false);
        }

        if(useAIdesign) {
            setContentView(R.layout.activity_adauga_avion_ai);
        } else {
            setContentView(R.layout.activity_adauga_avion);
        }

        etModel = findViewById(R.id.etModel);
        etCapacitate = findViewById(R.id.etCapacitate);
        spinnerCompanie = findViewById(R.id.spinnerCompanie);
        ratingBar = findViewById(R.id.ratingBar);
        switchDisponibil = findViewById(R.id.switchDisponibil);

        if(intent != null && intent.getExtras() != null) {
            String mesajPrimit = intent.getExtras().getString("mesaj");
            if (mesajPrimit != null) {
                Toast.makeText(this, mesajPrimit, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void intoarceRezultat(View view) {
        if(etModel.getText().toString().isEmpty() || etCapacitate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Completeaza modelul si capacitatea", Toast.LENGTH_SHORT).show();
            return;
        }

        String model = etModel.getText().toString();
        int capacitate = Integer.parseInt(etCapacitate.getText().toString());
        String companie = spinnerCompanie.getSelectedItem().toString();
        float rating = ratingBar.getRating();
        boolean disponibil = switchDisponibil.isChecked();

        Avion avionNou = new Avion(model, capacitate, companie, rating, disponibil);

        Intent intent = new Intent();
        intent.putExtra("avion_nou", avionNou);

        setResult(RESULT_OK, intent);
        finish();
    }
}