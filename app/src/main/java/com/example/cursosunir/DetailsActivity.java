package com.example.cursosunir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bumptech.glide.Glide;


public class DetailsActivity extends AppCompatActivity {
    TextView textCurso, textGrau, textCampus, textTurno, textDescri;
    ImageView imagem;
    Button btnLink;
    Button btnCompartilhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.actDetails), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textCurso = findViewById(R.id.textCurso);
        textGrau = findViewById(R.id.textGrau);
        textCampus = findViewById(R.id.textCampus);
        textTurno = findViewById(R.id.textTurno);
        textDescri = findViewById(R.id.textDescri);
        imagem= findViewById(R.id.image);
        btnLink = findViewById(R.id.btnAcessarSite);
        btnCompartilhar = findViewById(R.id.btnCompartilhar);

        Intent intent = getIntent();
        String nomeCurso = getIntent().getStringExtra("nomeCurso");
        String campus = getIntent().getStringExtra("campus");
        String grau = getIntent().getStringExtra("grau");
        String turno = getIntent().getStringExtra("turno");
        String descri = getIntent().getStringExtra("descri");
        String link = getIntent().getStringExtra("link");
        String imagemCurso = getIntent().getStringExtra("imagem");

        textCurso.setText(nomeCurso);
        textGrau.setText(grau);
        textCampus.setText(campus);
        textTurno.setText(turno);
        textDescri.setText(descri);
        Glide.with(this).load(imagemCurso).into(imagem);

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent site = new Intent(Intent.ACTION_SEND);
                site.setType("text/plain");
                site.putExtra(Intent.EXTRA_TEXT, link);

                Intent chooser = Intent.createChooser(site, "Abrir com:");
                if(site.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }
            }
        });

        btnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent site = new Intent(Intent.ACTION_SEND);
                site.setType("text/plain");

                String infos = "";
                infos += nomeCurso + '\n';
                infos += grau + '\n';
                infos += campus + '\n';
                site.putExtra(Intent.EXTRA_TEXT, infos);


                Intent chooser = Intent.createChooser(site, "Compartilhar com:");
                if(site.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }
            }
        });
    }
}
