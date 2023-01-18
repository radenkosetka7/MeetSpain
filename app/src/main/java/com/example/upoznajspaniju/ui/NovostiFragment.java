package com.example.upoznajspaniju.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.upoznajspaniju.MainActivity;
import com.example.upoznajspaniju.R;
import com.example.upoznajspaniju.model.adapters.GradAdapter;
import com.example.upoznajspaniju.model.adapters.NovostAdapter;
import com.example.upoznajspaniju.model.entities.Grad;
import com.example.upoznajspaniju.model.entities.Novost;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovostiFragment extends Fragment implements AdapterView.OnItemClickListener {


    View root;
    private List<Novost> novosti=new ArrayList<>();
    ListView listView;
    NovostAdapter novostAdapter;
    private static final String url = "https://newsdata.io/api/1/news?apikey=pub_157407afec14518ebe36d87350a47107f454c&country=es";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(NovostiFragment.this).navigate(R.id.action_nav_novosti_to_nav_home);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
        root = inflater.inflate(R.layout.fragment_novosti, container, false);
        listView = root.findViewById(R.id.listNovosti);
        if(checkConnection(this)){
            getAllNovosti(this);
        }
        else
        {
            novosti = MainActivity.dbHelper.getAllNovosti();
            novostAdapter=new NovostAdapter(MainActivity.dbHelper.getAllNovosti(), this);
            listView.setAdapter(novostAdapter);
            listView.setOnItemClickListener(this::onItemClick);
        }
        return root;
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Novost novost = novosti.get(i);
        NovostFragment.novost=novost;
        Navigation.findNavController(root).navigate(R.id.action_nav_novosti_to_novostFragment);
    }

    private void getAllNovosti(Fragment fragment)
    {
        if(!MainActivity.memory)
        {
            MainActivity.dbHelper.deleteNovosti();
            novosti.clear();
        }
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonRespone = new JSONObject(response);
                    JSONArray jsonArray = jsonRespone.getJSONArray("results");
                    for(int i = 0; i < jsonArray.length(); i ++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String naslov = jsonObject.getString("title");
                        String opis = jsonObject.getString("content");
                        String url = jsonObject.getString("link");
                        String urlSlika = jsonObject.getString("image_url");

                        if(naslov != null && opis != null && url != null && urlSlika != null){
                            Novost novost = new Novost(naslov,urlSlika, opis, url);
                            novosti.add(novost);
                            if(MainActivity.memory)
                            {
                                MainActivity.dbHelper.insertNovost(novost);
                            }
                        }
                    }
                    NovostAdapter adapterNovost = new NovostAdapter(novosti, fragment);
                    ListView listView = root.findViewById(R.id.listNovosti);
                    listView.setAdapter(adapterNovost);
                    listView.setOnItemClickListener(NovostiFragment.this::onItemClick);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("User-Agent", "Mozilla/5.0");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

    private boolean checkConnection(Fragment fragment){
        ConnectivityManager connectivityManager = (ConnectivityManager)fragment.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else
        {
            return false;
        }
    }

}