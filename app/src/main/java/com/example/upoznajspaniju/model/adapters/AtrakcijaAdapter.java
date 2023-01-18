package com.example.upoznajspaniju.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.entities.Atrakcija;
import com.example.upoznajspaniju.model.entities.Grad;

import java.util.ArrayList;
import java.util.List;

public class AtrakcijaAdapter extends BaseAdapter {

    private List<Atrakcija> atrkacije=new ArrayList<>();
    LayoutInflater layoutInflater;
    Fragment fragment;

    public AtrakcijaAdapter(List<Atrakcija> atrakcije,Fragment fragment)
    {
        this.atrkacije=atrakcije;
        this.fragment=fragment;
    }
    @Override
    public int getCount() {
        return atrkacije.size();
    }

    @Override
    public Object getItem(int i) {
        return atrkacije.get(i);
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
            view=layoutInflater.inflate(R.layout.list_item_atrakcija,null);
        }

        TextView name = view.findViewById(R.id.atrakcijaItemList);
        name.setText(atrkacije.get(i).getIme());
        return view;
    }
}
