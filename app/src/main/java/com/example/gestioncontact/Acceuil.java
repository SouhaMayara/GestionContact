package com.example.gestioncontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Acceuil extends AppCompatActivity {

    static ArrayList<Contact> data = new ArrayList<Contact>();

    private TextView tvusername;
    private Button btnajout,btnaff,btnaffC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        tvusername=findViewById(R.id.tvuser_acc);
        btnajout=findViewById(R.id.btnajout_acc);
        btnaff=findViewById(R.id.btnaff_acc);
        btnaffC=findViewById(R.id.btnaffC_acc);
        //x est l'intente de l'acceuil ==i
        Intent x= this.getIntent();
        //bundle:stoque les données
        Bundle b=x.getExtras();
        String u=b.getString("USER");
        data.clear();

        tvusername.setText("Acceuil de M. "+u);

        btnaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Acceuil.this,Affichage.class) ;
                startActivity(i);
            }
        });

        btnaffC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Acceuil.this,RecupContactDuTel.class) ;
                startActivity(i);
            }
        });

        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Acceuil.this, Ajout.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "started", Toast.LENGTH_SHORT).show();
        if(ContextCompat.checkSelfPermission(Acceuil.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            import_data();
            permission_write=true;
        }
        else{
            //autorisation n'est pas accordée pas de sauvegarde
            //Demander de nouveau avec boite de dialogue :methode
            ActivityCompat.requestPermissions(Acceuil.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
            //permet d'afficher boite de dialog accord ou refuser
        }
    }

    private void import_data() {
        //data.clear();
        String dir=Environment.getExternalStorageDirectory().getPath();
        File f=new File(dir,"fichier.txt");
        if(f.exists())
        {

            try {
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String ligne=null;
                ligne=br.readLine();
                while ((ligne=br.readLine())!=null) {
                    String[] t = ligne.split("#");
                    Contact c = new Contact(t[0], t[1], t[2]);
                    data.add(c);
                }
                br.close();
                fr.close();

            //} catch (FileNotFoundException e) {
             //   e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected void onStop() {
       
        Toast.makeText(this, "stopped", Toast.LENGTH_SHORT).show();
        
        //sauvegarder
        if(permission_write==true)
        {
            save_data();
        }
        else{
            Toast.makeText(this, "permission d'ecriture non autorisée", Toast.LENGTH_SHORT).show();
        }
        super.onStop();
    }
    //on peut utiliser save_data dans Ajout
    static void save_data() {
        String dir= Environment.getExternalStorageDirectory().getPath();
        File f=new File(dir,"fichier.txt");
        try {
            FileWriter fw=new FileWriter(f,false);//creation du ficher
            BufferedWriter bw=new BufferedWriter(fw);
            for(int i=0;i<data.size();i++){
                bw.write(data.get(i).nom+"#"+data.get(i).prenom+"#"+data.get(i).numero+"\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destroyed", Toast.LENGTH_SHORT).show();

    }

    boolean permission_write=false;
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                //accordé
                permission_write=true;
            }
            else{
                //refusé
                permission_write=false;
            }
        }
    }
}