package com.example.upoznajspaniju.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.example.upoznajspaniju.model.entities.Grad;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradFragment extends Fragment implements OnMapReadyCallback {

    View root;
    ImageView photos;
    Button next;
    Button prev;
    TextView cityName;
    TextView description;
    YouTubePlayerView youTubePlayerView;
    GoogleMap map;
    int index=0;
    public static Grad grad;
    private final String apiKey = "1c7c6a770e8e6a64d4720b2e65fb3a7d";
    private final String countryCode = "ES";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(GradFragment.this).navigate(R.id.action_gradFragment_to_nav_gradovi);
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

        root = inflater.inflate(R.layout.fragment_grad, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);

        photos=root.findViewById(R.id.photos);
        next=root.findViewById(R.id.btnNext);
        prev=root.findViewById(R.id.btnPrevious);
        cityName=root.findViewById(R.id.nazivGrada);
        description=root.findViewById(R.id.opisGrada);
        youTubePlayerView=root.findViewById(R.id.video);
        supportMapFragment.getMapAsync(this);

        cityName.setText(grad.getNaziv());
        description.setText(grad.getOpis());
        if(checkConnection())
        {
            showWeather(grad);
            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String id = grad.getVideo();
                    youTubePlayer.loadVideo(id, 0);
                }
            });
            grad.setSlike(dodajSlike(grad.getNaziv()));
            Collections.shuffle(grad.getSlike());
            Picasso.with(this.getContext()).load(grad.getSlike().get(index)).into(photos);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index++;
                    if(index >= MainActivity.numberOfPhotos)
                        index = 0;
                    Picasso.with(root.getContext()).load(grad.getSlike().get(index)).into(photos);
                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index--;
                    if(index <= -1)
                        index = MainActivity.numberOfPhotos - 1;
                    Picasso.with(root.getContext()).load(grad.getSlike().get(index)).into(photos);
                }
            });
        }
        else
        {
            LinearLayout dugmad=root.findViewById(R.id.dugmad);
            dugmad.setVisibility(View.GONE);
            photos.setVisibility(View.GONE);
            youTubePlayerView.setVisibility(View.GONE);
            LinearLayout prognoza = root.findViewById(R.id.vremenskaPrognoza);
            prognoza.setVisibility(View.GONE);
        }

        if(PodesavanjaFragment.LANG_CURRENT.equals("aa")) {
            if (MainActivity.eng) {
                MainActivity.englishTranslator.translate(grad.getNaziv())
                        .addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                cityName.setText(s);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                cityName.setText("Error");
                            }
                        });
                MainActivity.englishTranslator.translate(grad.getOpis())
                        .addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                description.setText(s);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                description.setText("Error :(");
                            }
                        });

            }
        }
        else
        {
            cityName.setText(grad.getNaziv());
            description.setText(grad.getOpis());
        }
        return root;
    }

    private void showWeather(Grad grad)
    {
        String tempUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + grad.getNaziv() + ","+ countryCode + "&appid=" + apiKey;
        TextView vlaznost = root.findViewById(R.id.vlaznost);
        TextView vjetar = root.findViewById(R.id.vjetar);
        TextView min = root.findViewById(R.id.min);
        TextView max = root.findViewById(R.id.max);
        TextView trenutnaTemparatura = root.findViewById(R.id.trenutnaTemperatura);
        TextView opisVremena = root.findViewById(R.id.opisVremena);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject weather = jsonArray.getJSONObject(0);
                    String opis = weather.getString("main");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    double tempMin = jsonObjectMain.getDouble("temp_min") - 273.15;
                    double tempMax = jsonObjectMain.getDouble("temp_max") - 273.15;
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");

                    trenutnaTemparatura.setText((int)temp+" °C");
                    opisVremena.setText(opis);
                    vlaznost.setText(" "+humidity+" %");
                    vjetar.setText(" "+wind+" m/s");
                    max.setText((int) tempMax+" °C");
                    min.setText((int)tempMin+" °C");
                } catch (Exception e) {
                    LinearLayout prognoza = root.findViewById(R.id.vremenskaPrognoza);
                    prognoza.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(root.getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User-Agent", "Mozilla/5.0");
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        queue.add(stringRequest);
    }

    private boolean checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)(getActivity().getSystemService(Context.CONNECTIVITY_SERVICE));
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<String> dodajSlike(String grad)
    {
        List<String> slike=new ArrayList<>();
        if("Madrid".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.madrid));
        }
        else if("Barcelona".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.barcelona));
        }
        else if("Malaga".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.malaga));
        }
        else if("Valencia".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.valencia));
        }
        else if("Sevilla".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.sevilla));
        }
        else if("Granada".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.granada));
        }
        else if("Bilbao".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.bilbao));
        }
        else if("Cadiz".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.cadiz));
        }
        else if("Cordoba".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.cordoba));
        }
        else if("Zaragoza".equals(grad))
        {
            slike= Arrays.asList(getResources().getStringArray(R.array.zaragoza));
        }
        return slike;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        LatLng coordinates = new LatLng(grad.getLatitude(), grad.getLongitude());
        map.addMarker(new MarkerOptions().position(coordinates).title(grad.getNaziv()));
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(39.3260685,-4.837979)));


    }
}