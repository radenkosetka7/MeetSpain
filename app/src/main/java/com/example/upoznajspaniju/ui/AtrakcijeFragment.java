package com.example.upoznajspaniju.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.upoznajspaniju.MainActivity;
import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.adapters.AtrakcijaAdapter;
import com.example.upoznajspaniju.model.entities.Atrakcija;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class AtrakcijeFragment extends Fragment implements AdapterView.OnItemClickListener,OnMapReadyCallback {

    View root;
    List<Atrakcija> atrakcije=new ArrayList<>();
    ListView listView;
    AtrakcijaAdapter atrakcijaAdapter;
    GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(AtrakcijeFragment.this).navigate(R.id.action_nav_znamenitosti_to_nav_home);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

        root=inflater.inflate(R.layout.fragment_atrakcije,container,false);
        listView=root.findViewById(R.id.listAtrakcije);
        atrakcije= MainActivity.dbHelper.getAllAtrakcije();
        atrakcijaAdapter=new AtrakcijaAdapter(atrakcije,this);
        listView.setAdapter(atrakcijaAdapter);
        listView.setOnItemClickListener(this);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        supportMapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Atrakcija atrakcija=atrakcije.get(i);
        AtrakcijaFragment.atrakcija=atrakcija;
        AtrakcijaFragment.omiljeno=false;
        Navigation.findNavController(root).navigate(R.id.action_nav_znamenitosti_to_atrakcijaFragment);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        for(Atrakcija atrakcija:atrakcije)
        {
            LatLng coordinates = new LatLng(atrakcija.getLatitude(),atrakcija.getLongitude());
            map.addMarker(new MarkerOptions().position(coordinates).title(atrakcija.getIme()));
        }
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(39.3260685,-4.837979)));

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String info = marker.getTitle();
                Atrakcija atrakcija =atrakcijaItem(info);
                AtrakcijaFragment.atrakcija=atrakcija;
                AtrakcijaFragment.omiljeno=false;
                Navigation.findNavController(root).navigate(R.id.action_nav_znamenitosti_to_atrakcijaFragment);
            }
        });
    }

    private Atrakcija atrakcijaItem(String ime)
    {
        for(Atrakcija a:atrakcije)
        {
            if(a.getIme().equals(ime))
            {
                return a;
            }
        }
        return null;
    }
}