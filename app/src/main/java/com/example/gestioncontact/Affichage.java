package com.example.gestioncontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.gestioncontact.Acceuil.save_data;

public class Affichage extends AppCompatActivity
        implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private EditText edrech;
    private ListView lv_affiche;

    private ArrayList<Contact> arr_sort= new ArrayList();
    int textlength=0;
    private ArrayList<String> map= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_affichage);

        edrech =findViewById(R.id.edrech_affiche);
        /////a completer
        edrech.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                arr_sort.clear();
                //textchanged=true ;
                int textlength=edrech.getText().length();
                for(int j=0;j<Acceuil.data.size();j++)
                {
                    if(textlength<=Acceuil.data.get(j).nom.toString().length())
                    {
                        if(edrech.getText().toString().equalsIgnoreCase((String) Acceuil.data.get(j).nom.subSequence(0, textlength)))
                        {
                            arr_sort.add(Acceuil.data.get(j));
                        }
                    }
                }
                /*lv_affiche.invalidateViews();
                MonAdapter adapter = new MonAdapter(Affichage.this,arr_sort);
                lv_affiche.setAdapter(adapter);*/
                lv_affiche.setAdapter(new ArrayAdapter(Affichage.this,android.R.layout.simple_list_item_1 , arr_sort));
            }
            @Override
            public void afterTextChanged(Editable editable) {
               /* ArrayList<Contact> resultSearch = new ArrayList<>();
                for(Contact c : Acceuil.data){
                    if(c.nom.contains(editable)){
                        resultSearch.add(c);
                    }
                }
                lv_affiche.invalidateViews();
                MonAdapter adapter = new MonAdapter(Affichage.this,resultSearch);
                lv_affiche.setAdapter(adapter);*/
            }
        });
         /* ArrayAdapter adapter=new ArrayAdapter(Affichage.this,android.R.layout.simple_list_item_1,Acceuil.data);*/
        lv_affiche=findViewById(R.id.lv_affiche);
        MonAdapter adapter=new MonAdapter(Affichage.this,Acceuil.data);
        lv_affiche.setAdapter(adapter);
        lv_affiche.setOnItemClickListener(this);
    }
    //**********************************************************************************************
    int indice;
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        indice=i;
        AlertDialog.Builder alert=new AlertDialog.Builder(Affichage.this);
        alert.setTitle("Attention...");
        alert.setMessage("Veuillez choisir une action:");
        alert.setPositiveButton("supprimer",this);
        alert.setNegativeButton("Modifier",this);
        alert.setNeutralButton("Supprimer tous",this);

        alert.show();
    }
    //**********************************************************************************************
    //dialog=objet alerte affiché
    @Override
    public void onClick(DialogInterface dialog, int which) {
        // if(dialog==alert){}
        if(which==dialog.BUTTON_POSITIVE){
            Acceuil.data.remove(indice);
            lv_affiche.invalidateViews();
            //save_data();
        }
        if(which==dialog.BUTTON_NEGATIVE){
            //a faire
            String nom=Acceuil.data.get(indice).nom;
            String prenom=Acceuil.data.get(indice).prenom;
            String num=Acceuil.data.get(indice).numero;

            Intent i = new Intent(Affichage.this, Edition.class);
            i.putExtra("Nom",nom);
            i.putExtra("Prenom",prenom);
            i.putExtra("Num",num);
            i.putExtra("Indice",indice);

            startActivity(i);
            //save_data();
        }
        if(which==dialog.BUTTON_NEUTRAL){
            Acceuil.data.clear();
            lv_affiche.invalidateViews();//rafraichissement de la liste view
            //save_data();
        }
    }
}