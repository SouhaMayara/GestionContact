package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.gestioncontact.Acceuil.save_data;

public class Ajout extends AppCompatActivity {


    private Button btnqte,btnval;
    private EditText ednom,edprenom,ednum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        btnval = findViewById(R.id.btnval_ajout);
        btnqte = findViewById(R.id.btnqte_ajout);

        ednom = findViewById(R.id.ednom_ajout);
        edprenom = findViewById(R.id.edprenom_ajout);
        ednum = findViewById(R.id.ednum_ajout);

        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ajout.this.finish();
            }
        });
        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Ajout!!!!!!!!!!!!!!!");
                String n = ednom.getText().toString();
                String p = edprenom.getText().toString();
                String num = ednum.getText().toString();
                Contact c = new Contact(n,p,num);
                System.out.println(c);
                Acceuil.data.add(c);
                //System.out.println(Acceuil.data.add(c));
            }
        });
        save_data();
        //System.out.println(Acceuil.data);

    }
}