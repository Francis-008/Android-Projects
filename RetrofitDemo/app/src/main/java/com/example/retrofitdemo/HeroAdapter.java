package com.example.retrofitdemo;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    private List<Hero> heroList;
    private Context context;
    private LayoutInflater inflater;
    private HeroClick mheroClick;

    public HeroAdapter(List<Hero> heroes, Context context, HeroClick heroClick){

        this.context     = context;
        this.heroList    = heroes;
        inflater         = LayoutInflater.from(context);
        this.mheroClick  = heroClick;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.hero,viewGroup,false);

        return new ViewHolder(view,mheroClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hero    h  = heroList.get(position);
        holder.realName.setText(h.getRealname());
        holder.heroName.setText(h.getName());
        Glide.with(context).load(h.getImageurl()).into(holder.heroImage);
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView realName,heroName;
        ImageView  heroImage;

        public ViewHolder(@NonNull View itemView, final HeroClick click) {
            super(itemView);
            realName    = itemView.findViewById(R.id.realName);
            heroName    = itemView.findViewById(R.id.heroName);
            heroImage   = itemView.findViewById(R.id.heroImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onHeroClick(getAdapterPosition());
                }
            });
        }
    }


    public interface HeroClick{
        void onHeroClick(int heroID);
    }
}
