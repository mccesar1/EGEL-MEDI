package com.kmpegel.medi.ui.Preguntas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kmpegel.egelmedi.R;
import com.kmpegel.medi.ui.Subtemas.subtemas;
import com.kmpegel.medi.ui.Subtemas.subtemas2;
import com.kmpegel.medi.ui.Subtemas.subtemas3;
import com.kmpegel.medi.ui.Subtemas.subtemas4;
import com.kmpegel.medi.ui.Subtemas.subtemas5;
import com.kmpegel.medi.ui.Subtemas.subtemas6;
import com.kmpegel.medi.ui.dashboard.DashboardFragment;

public class FinalActivity extends AppCompatActivity {
    Button button1;
    TextView textView;

    private int tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalscreen);


        // Establecer el modo de pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Ocultar la barra de navegación
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Obtener la puntuación del Intent
        button1 = findViewById(R.id.buttonSalir);
        textView = findViewById(R.id.puntuacionTextView);
        double puntuacion = getIntent().getDoubleExtra("puntuacion", 0);
        tipo = getIntent().getIntExtra("tipo", 0);
        int puntuacionRedondeada = (int) Math.round(puntuacion);

        textView.setText("Tu puntuación es:  " + puntuacionRedondeada);

        if (puntuacion >= 6 && tipo == 0) {
            textView.setText("¡Felicidades! Has aprobado \nTu puntuación es: " + puntuacionRedondeada);
            ImageView gifImageView = findViewById(R.id.gifImageView);
            Glide.with(this).load(R.raw.my_gif2).into(gifImageView);
        } else if(puntuacion < 6 && tipo == 0) {
            textView.setText("¡Lo siento! Has reprobado\nTu puntuación es: " + puntuacionRedondeada);
            ImageView gifImageView = findViewById(R.id.gifImageView);
            Glide.with(this).load(R.raw.my_gif).into(gifImageView);
        } else if (puntuacion > 15 && tipo ==1 ) {
            textView.setText("¡Felicidades! Has aprobado \nTu puntuación es: " + puntuacionRedondeada);
            ImageView gifImageView = findViewById(R.id.gifImageView);
            Glide.with(this).load(R.raw.my_gif2).into(gifImageView);
        }else{
            textView.setText("¡Lo siento! Has reprobado\nTu puntuación es: " + puntuacionRedondeada);
            ImageView gifImageView = findViewById(R.id.gifImageView);
            Glide.with(this).load(R.raw.my_gif).into(gifImageView);
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tipo==0) {
                    String activityName = getActivityName(); // Obtiene el nombre de la actividad anterior
                    Intent intent = getNextActivityIntent(activityName); // Obtiene el Intent de la siguiente actividad

                    //destruye la actividad actual
                    finish();

                    // Inicia la nueva actividad
                    startActivity(intent);
                }else if(tipo==1){
                    //Intent intent = new Intent(FinalActivity.this, DashboardFragment.class);
                    //destruye la actividad actual

                    //Intent intent = getNextActivityIntent(DashboardFragment.class.getSimpleName());
                    finish();
                    //startActivity(intent);
                }
            }
        });
    }

    // Obtiene el nombre de la actividad anterior desde SharedPreferences
    private String getActivityName() {
        SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        return sharedPreferences.getString("ultimaActividad", "");
    }

    // Obtiene el Intent de la siguiente actividad según el nombre de la actividad anterior
    private Intent getNextActivityIntent(String activityName) {
        if (activityName.equals("preguntasActivity")) {
            return new Intent(FinalActivity.this, DashboardFragment.class);
        } else if (activityName.equals("preguntasActivity2")) {
            return new Intent(FinalActivity.this, subtemas2.class);
        } else if (activityName.equals("preguntasActivity3")) {
            return new Intent(FinalActivity.this, subtemas3.class);
        } else if (activityName.equals("preguntasActivity4")) {
            return new Intent(FinalActivity.this, subtemas4.class);
        } else if (activityName.equals("preguntasActivity5")) {
            return new Intent(FinalActivity.this, subtemas5.class);
        } else if (activityName.equals("preguntasActivity6")) {
            return new Intent(FinalActivity.this, subtemas6.class);
        } else {
            return new Intent(FinalActivity.this, subtemas6.class);
        }
    }
}
