package com.example.gestioncontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.LayoutInflaterFactory;

import java.util.ArrayList;

public class MonAdapter extends BaseAdapter {
    //Role =la création des views
    Context con;
    ArrayList<Contact>d;
    MonAdapter(Context con, ArrayList<Contact>d)
    {
        this.d=d;
        this.con=con;
    }

    @Override
    public int getCount() {
        //renvoi nombre de view
        return d.size();//Acceuil.data.size();
    }
    @Override
    public Contact getItem(int i) {
        return d.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //creation de chaque view selon la position
        LayoutInflater inf= LayoutInflater.from(con);
        //parsing code XML
        LinearLayout l=(LinearLayout) inf.inflate(R.layout.view_contact,null);
        TextView tvnom=l.findViewById(R.id.tvnom_view);
        TextView tvprenom=l.findViewById(R.id.tvprenom_view);
        TextView tvnum=l.findViewById(R.id.tvnum_view);
        Contact c=Acceuil.data.get(i);
        tvnom.setText(c.nom);
        tvprenom.setText(c.prenom);
        tvnum.setText(c.numero);
        /*TextView tv=new TextView(con);
        tv.setText(Acceuil.data.get(i).toString());
        //tvnom.setText(Acceuil.data.get(i).nom);
        //return tv;
         */
        return l;
    }
}
