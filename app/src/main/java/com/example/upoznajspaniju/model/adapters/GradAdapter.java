package com.example.upoznajspaniju.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.entities.Grad;

import java.util.ArrayList;
import java.util.List;

public class GradAdapter extends BaseAdapter {

    private List<Grad> gradovi=new ArrayList<>();
    LayoutInflater layoutInflater;
    Fragment fragment;

    public GradAdapter(List<Grad> gradovi,Fragment fragment)
    {
        this.gradovi=gradovi;
        this.fragment=fragment;
    }
    @Override
    public int getCount() {
        return gradovi.size();
    }

    @Override
    public Object getItem(int i) {
        return gradovi.get(i);
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
            view=layoutInflater.inflate(R.layout.list_item_grad,null);
        }

        TextView name = view.findViewById(R.id.gradItemList);
        name.setText(gradovi.get(i).getNaziv());
        return view;
    }
}
