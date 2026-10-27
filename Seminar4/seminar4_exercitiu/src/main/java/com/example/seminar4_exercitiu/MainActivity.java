package com.example.seminar4_exercitiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvAddPlane;
    private static final int CODE_ADAUGA = 100;

    public static ArrayList<Avion> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvAddPlane = findViewById(R.id.tvAddPlane);

        Button btnLista = findViewById(R.id.btnVeziLista);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);

                startActivity(intent);
            }
        });
    }

    public void deschideAdaugareAvion(View view) {
        Intent intent = new Intent(getApplicationContext(), adauga_avion.class);

        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Adauga datele avionului");
        intent.putExtras(bundle);

        startActivityForResult(intent, CODE_ADAUGA);
    }

    public void deschideAdaugareAI(View view) {
        Intent intent = new Intent(getApplicationContext(), adauga_avion.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean("foloseste_design_ai", true);
        intent.putExtras(bundle);

        startActivityForResult(intent, CODE_ADAUGA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_ADAUGA) {
            if(resultCode == RESULT_OK && data != null) {
                Avion avion = (Avion) data.getSerializableExtra("avion_nou");

                if(avion != null) {

                    lista.add(avion);

                    tvAddPlane.setText(avion.toString());
                    Toast.makeText(getApplicationContext(), "Avion salvat: " + avion.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}