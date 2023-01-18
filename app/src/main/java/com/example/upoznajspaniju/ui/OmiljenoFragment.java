package com.example.upoznajspaniju.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.upoznajspaniju.MainActivity;
import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.adapters.AtrakcijaAdapter;
import com.example.upoznajspaniju.model.entities.Atrakcija;

import java.util.ArrayList;
import java.util.List;

public class OmiljenoFragment extends Fragment implements AdapterView.OnItemClickListener {

    View root;
    List<Atrakcija> atrakcije=new ArrayList<>();
    ListView listView;
    AtrakcijaAdapter atrakcijaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(OmiljenoFragment.this).navigate(R.id.action_nav_omiljeno_to_nav_home);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
        root=inflater.inflate(R.layout.fragment_omiljeno,container,false);
        listView=root.findViewById(R.id.listOmiljeno);
        atrakcije=MainActivity.dbHelper.getAllOmiljeno();
        atrakcijaAdapter=new AtrakcijaAdapter(atrakcije,this);
        listView.setAdapter(atrakcijaAdapter);
        listView.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Atrakcija atrakcija=atrakcije.get(i);
        AtrakcijaFragment.atrakcija=atrakcija;
        AtrakcijaFragment.omiljeno=true;
        Navigation.findNavController(root).navigate(R.id.action_nav_omiljeno_to_atrakcijaFragment);
    }
}