package com.example.upoznajspaniju.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.upoznajspaniju.MainActivity;
import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.databinding.FragmentHomeBinding;
import com.example.upoznajspaniju.model.entities.Drzava;
import com.example.upoznajspaniju.model.entities.Grad;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View root;
    ImageView flag;
    ImageView capital;
    TextView description2;
    TextView description1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root=inflater.inflate(R.layout.fragment_home,container,false);
        flag=root.findViewById(R.id.zastava);
        capital=root.findViewById(R.id.glavniGrad);
        description2=root.findViewById(R.id.opis2);
        description1=root.findViewById(R.id.opis1);
        return root;
    }

}