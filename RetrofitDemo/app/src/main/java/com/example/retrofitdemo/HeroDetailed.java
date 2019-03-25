package com.example.retrofitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HeroDetailed extends AppCompatActivity {

    Hero hero;
    private TextView Rname,Hname,team,Createby,Publisher,Bio;
    private ImageView profile;

    public HeroDetailed(){
        //BLANK
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detailed);

        Intent intent = getIntent();

        hero    = intent.getParcelableExtra("data");

        initViews();
        setData();


    }

    private void setData() {
        Rname.setText(hero.getRealname());
        Hname.setText(hero.getName());
        team.setText(hero.getTeam());
        Createby.setText(hero.getCreatedby());
        Publisher.setText(hero.getPublisher());
        Bio.setText(hero.getBio());
        Glide.with(this).load(hero.getImageurl()).into(profile);
    }


    private void initViews() {
        Rname       = findViewById(R.id.rName);
        Hname       = findViewById(R.id.hName);
        Createby    = findViewById(R.id.createdBy);
        Publisher   = findViewById(R.id.publisher);
        Bio         = findViewById(R.id.bio);
        profile     = findViewById(R.id.heroProfile);
        team        = findViewById(R.id.team);
    }


}
