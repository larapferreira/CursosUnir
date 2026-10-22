package com.example.cursosunir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Spinner spinnerCampi;
    private RadioGroup radioGroup;
    private CheckBox checkbox;
    private String[] campi = {"Ariquemes", "Cacoal","Guajará-Mirim","Ji-paraná", "Porto Velho", "Rolim de Moura", "Vilhena"};

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

        button = findViewById(R.id.btnCursos);
        spinnerCampi = findViewById(R.id.spinnerCampi);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, campi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
        spinnerCampi.setAdapter(adapter);

        radioGroup = findViewById(R.id.radioGroup);
        checkbox = findViewById(R.id.checkBox);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String campus = campi[spinnerCampi.getSelectedItemPosition()];
                RadioButton info = findViewById(radioGroup.getCheckedRadioButtonId());
                String grau = info.getText().toString();
                boolean noturno = checkbox.isChecked();

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("campus", campus);
                intent.putExtra("grau", grau);
                intent.putExtra("noturno", noturno);
                startActivity(intent);
            }
        });
    }

}