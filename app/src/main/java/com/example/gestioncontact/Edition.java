package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edition extends AppCompatActivity {

    private Button btnqte,btnval;
    private EditText ednom,edprenom,ednum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        Intent x=this.getIntent();
        String x1=x.getStringExtra("Nom");
        String x2=x.getStringExtra("Prenom");
        String x3=x.getStringExtra("Num");
        final int indice=x.getIntExtra("Indice",0);


        btnval = findViewById(R.id.btnval_edit);
        btnqte = findViewById(R.id.btnqte_edit);
        ednom = findViewById(R.id.ednom_edit);
        ednom.setText(x1);
        edprenom = findViewById(R.id.edprenom_edit);
        edprenom.setText(x2);
        ednum = findViewById(R.id.ednum_edit);
        ednum.setText(x3);
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edition.this.finish();
            }
        });

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = ednom.getText().toString();
                String p = edprenom.getText().toString();
                String num = ednum.getText().toString();
                Contact c = Acceuil.data.get(indice);
                c.setNom(n);
                c.setPrenom(p);
                c.setNumero(num);
                Acceuil.data.set(indice,c);
            }
        });
    }
}