package com.zeydalcan.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeydalcan.retrofitjava.R;
import com.zeydalcan.retrofitjava.adapter.CryptoAdapter;
import com.zeydalcan.retrofitjava.model.CryptoModel;
import com.zeydalcan.retrofitjava.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;

    String BASE_URL="//https://raw.githubusercontent.com/atilsamancioglu/";
    ArrayList<CryptoModel> cryptoModelArrayList;
    RecyclerView recyclerView;
    CryptoAdapter cryptoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        //https://api.meteomatics.com/2023-08-28T00:00:00Z--2023-08-31T00:00:00Z:PT1H/t_2m:C/52.520551,13.461804/json

        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }

    private void loadData()
    {
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);

        Call<List<CryptoModel>> call=cryptoAPI.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()){
                   List<CryptoModel> responseList=response.body();
                   cryptoModelArrayList=new ArrayList<>(responseList);
                   recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                   cryptoAdapter=new CryptoAdapter(cryptoModelArrayList);
                   recyclerView.setAdapter(cryptoAdapter);




                }

            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


}