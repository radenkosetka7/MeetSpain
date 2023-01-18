package com.example.upoznajspaniju.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.entities.Grad;
import com.example.upoznajspaniju.model.entities.Novost;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NovostAdapter extends BaseAdapter {
    private List<Novost> novosti;
    LayoutInflater layoutInflater;
    Fragment fragment;

    public NovostAdapter(List<Novost> novosti,Fragment fragment)
    {
        this.novosti=novosti;
        this.fragment=fragment;
    }
    @Override
    public int getCount() {
        return novosti.size();
    }

    @Override
    public Object getItem(int i) {
        return novosti.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(layoutInflater==null)
        {
            layoutInflater=fragment.getLayoutInflater();
        }

        if(view==null)
        {
            view=layoutInflater.inflate(R.layout.list_item_novost,null);
        }

        TextView naslov = view.findViewById(R.id.naslovNovosti);
        ImageView slika = view.findViewById(R.id.slikaNovost);
        Picasso.with(view.getContext()).load(novosti.get(i).getSlika()).into(slika);
        naslov.setText(novosti.get(i).getNaslov());
        return view;
    }
}
