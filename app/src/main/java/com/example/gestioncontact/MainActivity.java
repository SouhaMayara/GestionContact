package com.example.gestioncontact;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.gestioncontact.Acceuil.save_data;
public class MainActivity extends AppCompatActivity{


    //declaration global
    private Button btnval,btnqte;
    private EditText ednom,edmp;
    //@RequiresApi(api = Build.VERSION_CODES.M)
    boolean permission= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.this.setContentView(R.layout.activity_main);

        System.out.println(Acceuil.data);
        //Contact c = new Contact("s","m","123");
        //Acceuil.data.add(c);
        //save_data();
        //traitement des permissions
       // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        /*if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }*/
        ////////////////////////////////////////
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            permission = true;
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_CONTACTS}
                    ,
                    2);
        }
        /////////////////////////////////////////
        btnval=findViewById(R.id.btnval_auth);
        btnqte=findViewById(R.id.btnqte_auth);
        ednom=findViewById(R.id.ednom_auth);
        edmp=findViewById(R.id.edmp_auth);

        //Evenement: Ecouteur
        btnqte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    MainActivity.this.finish();
            }
        });

        btnval.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom=ednom.getText().toString();
                String mp=edmp.getText().toString();
                if(nom.equalsIgnoreCase( "test")&& mp.equals("000"))
                {
                    Intent i=new Intent(MainActivity.this,Acceuil.class);
                    i.putExtra( "USER",nom);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Valeur non valide", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //btnval et btnqte : source de l'evt


    }


}
