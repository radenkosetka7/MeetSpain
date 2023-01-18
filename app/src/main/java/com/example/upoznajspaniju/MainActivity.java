package com.example.upoznajspaniju;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.upoznajspaniju.model.adapters.NovostAdapter;
import com.example.upoznajspaniju.model.entities.Novost;
import com.example.upoznajspaniju.model.util.DBHelper;
import com.example.upoznajspaniju.model.util.TestData;
import com.example.upoznajspaniju.ui.NovostiFragment;
import com.example.upoznajspaniju.ui.PodesavanjaFragment;
import com.example.upoznajspaniju.wrapper.MyWrapper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.upoznajspaniju.databinding.ActivityMainBinding;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private SharedPreferences data;
    public static Translator englishTranslator;
    public static DBHelper dbHelper;
    public static int numberOfPhotos=5;
    public static Boolean memory = false;
    public static Boolean eng = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper=new DBHelper(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // dbHelper.deleteItems(); on the first run uncomment this two lines of code, and then comment them again
        //TestData testData=new TestData();
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_znamenitosti,R.id.nav_novosti,R.id.nav_omiljeno,R.id.nav_gradovi,R.id.nav_podesavanja)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
      /*  if(checkConnection())
        {
            novosti.clear();
            getNovosti();
        }*/
        data = getSharedPreferences("Data", MODE_PRIVATE);
        numberOfPhotos = data.getInt("numberOfPhotos", 5);
        memory = data.getBoolean("cacheMemory", false);

        TranslatorOptions translatorOptions = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.CROATIAN)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build();
        englishTranslator = Translation.getClient(translatorOptions);
        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
        englishTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        eng = true;
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                eng = false;
                            }
                        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void attachBaseContext(Context newBase){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        PodesavanjaFragment.LANG_CURRENT = preferences.getString("Language", "cu-rRS");
        super.attachBaseContext(MyWrapper.wrap(newBase, PodesavanjaFragment.LANG_CURRENT));
    }

}