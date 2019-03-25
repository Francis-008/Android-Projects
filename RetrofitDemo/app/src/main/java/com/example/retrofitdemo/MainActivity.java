package com.example.retrofitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements HeroAdapter.HeroClick {

    private RecyclerView recyclerView;
    private HeroAdapter adapter;
    private List<Hero> hlist;
    private ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiServices = Common.getServices();




//
//        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl(ApiServices.BASE_URL)
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//
//        ApiServices apiServices = retrofit.create(ApiServices.class);

//        Call<List<Hero>>    call    = apiServices.getHeros();

        apiServices.getHeros().enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateDataList(List<Hero> heroes) {
        this.hlist  = heroes;
        recyclerView    = findViewById(R.id.recyclerView);
        adapter         = new HeroAdapter(heroes, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onHeroClick(int heroID) {
        HeroDetailed heroDetailed = new HeroDetailed();
        Hero hero = hlist.get(heroID);

        Intent intent = new Intent(MainActivity.this,HeroDetailed.class);
        intent.putExtra("data",hero);
        startActivity(intent);


    }
}
