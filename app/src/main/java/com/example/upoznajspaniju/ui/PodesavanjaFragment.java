package com.example.upoznajspaniju.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.upoznajspaniju.MainActivity;
import com.example.upoznajspaniju.R;


public class PodesavanjaFragment extends Fragment {


    View root;
    EditText number;
    ImageButton eng;
    ImageButton srb;
    Switch aSwitch;
    Button save;
    public static String LANG_CURRENT = "cu-rRS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(PodesavanjaFragment.this).navigate(R.id.action_nav_podesavanja_to_nav_home);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
        root=inflater.inflate(R.layout.fragment_podesvanja, container, false);
        number=root.findViewById(R.id.editText);
        eng=root.findViewById(R.id.btnEN);
        srb=root.findViewById(R.id.btnRS);
        aSwitch=root.findViewById(R.id.switchButton);
        save=root.findViewById(R.id.btnSacuvaj);
        number.setText(String.valueOf(MainActivity.numberOfPhotos));
        aSwitch.setChecked(MainActivity.memory);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer n = 5;
                try{
                    n = Integer.parseInt(number.getText().toString());
                }catch (Exception e){
                    Toast.makeText(root.getContext(), "Neispravan broj", Toast.LENGTH_SHORT).show();
                }
                if(n > 10 || n <= 0) {
                    Toast.makeText(root.getContext(), "Neispravan broj", Toast.LENGTH_SHORT).show();
                    number.setText("");
                }
                else {
                    MainActivity.numberOfPhotos = n;
                    number.setText("");
                    SharedPreferences sharedPreferences = root.getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("numberOfPhotos", n);
                    editor.commit();
                }
                Boolean cache = aSwitch.isChecked();
                if(!cache)
                {
                    MainActivity.dbHelper.deleteNovosti();
                }
                MainActivity.memory = cache;
                SharedPreferences sharedPreferences = root.getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("cacheMemory", cache);
                editor.commit();
                startActivity(new Intent(root.getContext(), MainActivity.class));
            }
        });

        srb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLang(root.getContext(), "cu");
                startActivity(new Intent(root.getContext(), MainActivity.class));
            }
        });
        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLang(root.getContext(), "aa");
                startActivity(new Intent(root.getContext(), MainActivity.class));
            }
        });
        return root;
    }

    private void changeLang(Context context, String s) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Language", s);
        editor.apply();
    }
}