package com.example.upoznajspaniju.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.upoznajspaniju.MainActivity;
import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.adapters.GradAdapter;
import com.example.upoznajspaniju.model.entities.Grad;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class GradoviFragment extends Fragment implements AdapterView.OnItemClickListener {

    View root;
    List<Grad> gradovi=new ArrayList<>();
    ListView listView;
    GradAdapter gradAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
    @Override
    public void handleOnBackPressed() {
        NavHostFragment.findNavController(GradoviFragment.this).navigate(R.id.action_nav_gradovi_to_nav_home);
    }
};
    requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

        root=inflater.inflate(R.layout.fragment_gradovi,container,false);
        listView=root.findViewById(R.id.listGradovi);
        gradovi= MainActivity.dbHelper.getAllGradovi();
        gradAdapter=new GradAdapter(gradovi,this);
        listView.setAdapter(gradAdapter);
        listView.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Grad grad=gradovi.get(i);
        GradFragment.grad=grad;
        Navigation.findNavController(root).navigate(R.id.action_nav_gradovi_to_gradFragment);
    }

}