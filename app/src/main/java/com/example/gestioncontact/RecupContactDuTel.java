package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecupContactDuTel extends AppCompatActivity {

    ListView lv_recup_contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recup_contact_du_tel);
        recupererContacts();
    }
    //Bonus!!!
    /**
     * récupération des contacts
     */
        public void recupererContacts(){
            ContentResolver contentResolver = this.getContentResolver(); // accèes au contenu du mobile

            //Récupération des contacts dans un curseur
            Cursor cursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null,null);
            if(cursor==null){
                Log.d("recup ","******************************** erreur curseur");
            }
            else{
                Acceuil.data.clear();
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                    String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contact c = new Contact(name,"",phone);
                    Acceuil.data.add(c);
                    //Affichage de la récupération
                    lv_recup_contacts = findViewById(R.id.lv_recup_contacts);
                    ArrayAdapter adapter = new ArrayAdapter(RecupContactDuTel.this, android.R.layout.simple_list_item_1,Acceuil.data);  //pour mettre les données
                    lv_recup_contacts.setAdapter(adapter);                                                                                     //"data" dans des views

                }
            }

        }


}