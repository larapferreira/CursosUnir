package com.example.cursosunir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Curso> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.actList), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list = CursosData.getCursos();
        Intent intent = getIntent();
        String campus = intent.getStringExtra("campus");
        String grau = intent.getStringExtra("grau");
        boolean noturno = intent.getBooleanExtra("noturno", false);

        ArrayList<Curso> exibirlist = new ArrayList<>();
        /*if(noturno){
            if(grau.equalsIgnoreCase("Todos")){
                for(Curso cursos : list){
                    if(cursos.getCampus().equalsIgnoreCase(campus) && cursos.getTurno().equalsIgnoreCase("Noturno")){
                        exibirlist.add(cursos);
                    }
                }
            }else{
                for(Curso cursos : list){
                    if(cursos.getCampus().equalsIgnoreCase(campus) && cursos.getGrau().equalsIgnoreCase(grau) && cursos.getTurno().equalsIgnoreCase("Noturno")){
                        exibirlist.add(cursos);
                    }
                }
            }

        }else if(grau.equalsIgnoreCase("Todos")){
            for(Curso cursos : list){
                if(cursos.getCampus().equalsIgnoreCase(campus)){
                    exibirlist.add(cursos);
                }
            }
        }else{
            for(Curso cursos : list){
                if(cursos.getCampus().equalsIgnoreCase(campus) && cursos.getGrau().equalsIgnoreCase(grau)){
                    exibirlist.add(cursos);
                }
            }
        }*/

        for (Curso curso : list) {

            boolean campusCorreto =
                    curso.getCampus().trim().equalsIgnoreCase(campus.trim());

            boolean grauCorreto =
                    grau.equalsIgnoreCase("Todos") ||
                            curso.getGrau().trim().equalsIgnoreCase(grau.trim());

            boolean turnoCorreto =
                    !noturno ||
                            curso.getTurno().trim().equalsIgnoreCase("Noturno");

            if (campusCorreto && grauCorreto && turnoCorreto) {
                exibirlist.add(curso);
            }
        }

        recyclerView = findViewById(R.id.recyclerView);
        Adapter adapter = new Adapter(exibirlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Curso curso = exibirlist.get(position);
                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                intent.putExtra("nomeCurso", curso.getNome());
                intent.putExtra("campus", curso.getCampus());
                intent.putExtra("grau", curso.getGrau());
                intent.putExtra("turno", curso.getTurno());
                intent.putExtra("descri", curso.getDescricao());
                intent.putExtra("link", curso.getSite());
                intent.putExtra("imagem", curso.getImagem());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });


    }


}
