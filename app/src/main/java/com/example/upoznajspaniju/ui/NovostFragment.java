package com.example.upoznajspaniju.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.entities.Novost;
import com.squareup.picasso.Picasso;


public class NovostFragment extends Fragment {

    View root;
    TextView naslov;
    ImageView slika;
    TextView opis;
    TextView url;
    public static Novost novost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(NovostFragment.this).navigate(R.id.action_novostFragment_to_nav_novosti);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
        root=inflater.inflate(R.layout.fragment_novost,container,false);
        naslov=root.findViewById(R.id.novostNaslov);
        slika=root.findViewById(R.id.novostSlika);
        opis=root.findViewById(R.id.opisNovosti);
        url=root.findViewById(R.id.urlNovosti);
        naslov.setText(novost.getNaslov());
        if("null".equals(novost.getSadrzaj()))
        {
            opis.setText("");
        }
        else
        {
            opis.setText(novost.getSadrzaj());
        }
        url.setText(novost.getUrl());
        Picasso.with(root.getContext()).load(novost.getSlika()).into(slika);
        return root;
    }
}