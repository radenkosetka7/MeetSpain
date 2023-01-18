package com.example.upoznajspaniju.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.upoznajspaniju.MainActivity;
import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.entities.Atrakcija;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;


public class AtrakcijaFragment extends Fragment implements OnMapReadyCallback {


    public static Atrakcija atrakcija;
    View root;
    ImageView image;
    Button favourite;
    TextView title;
    TextView description;
    GoogleMap map;
    private boolean control=false;
    public static boolean omiljeno=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OnBackPressedCallback onBackPressedCallback;
        if(omiljeno) {
            onBackPressedCallback = new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    NavHostFragment.findNavController(AtrakcijaFragment.this).navigate(R.id.action_atrakcijaFragment_to_nav_omiljeno);
                }
            };
        }
        else
        {
            onBackPressedCallback=new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    NavHostFragment.findNavController(AtrakcijaFragment.this).navigate(R.id.action_atrakcijaFragment_to_nav_znamenitosti);
                }
            };
            requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

        }


        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);


        root = inflater.inflate(R.layout.fragment_atrakcija, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        image=root.findViewById(R.id.slikaAtrakcije);
        favourite=root.findViewById(R.id.btnFav);
        title=root.findViewById(R.id.nazivAtrakcije);
        description=root.findViewById(R.id.opisAtrakcije);
        supportMapFragment.getMapAsync(this);
        Picasso.with(this.getContext()).load(atrakcija.getSlika()).into(image);
        if(atrakcija.getOmiljena()==1)
        {
            control=true;
            setValueUkloni();
        }
        else
        {
            control=false;
        }
        if(omiljeno)
        {
            favourite.setVisibility(View.GONE);
        }

        if(PodesavanjaFragment.LANG_CURRENT.equals("aa")) {
            if (MainActivity.eng) {
                MainActivity.englishTranslator.translate(atrakcija.getIme())
                        .addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                title.setText(s);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                title.setText("Error");
                            }
                        });
                MainActivity.englishTranslator.translate(atrakcija.getOpis())
                        .addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                description.setText(s);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                description.setText("Error");
                            }
                        });

            }
        }
        else
        {
            title.setText(atrakcija.getIme());
            description.setText(atrakcija.getOpis());
        }

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!control)
                {
                    favourite.setText(getResources().getString(R.string.omiljenoUkloni));
                    MainActivity.dbHelper.updateAtrakcija(atrakcija.getId(),1);
                    control=true;
                }
                else
                {
                    favourite.setText(getResources().getString(R.string.omiljenoDodaj));
                    MainActivity.dbHelper.updateAtrakcija(atrakcija.getId(),0);
                    control=false;
                }
            }
        });
        return  root;

    }

    private void setValueUkloni()
    {
        favourite.setText(getResources().getString(R.string.omiljenoUkloni));
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        LatLng coordinates = new LatLng(atrakcija.getLatitude(), atrakcija.getLongitude());
        map.addMarker(new MarkerOptions().position(coordinates).title(atrakcija.getIme()));
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(39.3260685,-4.837979)));
    }
}