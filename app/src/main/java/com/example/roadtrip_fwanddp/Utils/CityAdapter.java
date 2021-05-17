package com.example.roadtrip_fwanddp.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadtrip_fwanddp.Model.PopulationCities;
import com.example.roadtrip_fwanddp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    ArrayList<PopulationCities> cities = new ArrayList<>();


    @NonNull
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.search_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new CityViewHolder(view);
    }

    public void setCities(ArrayList<PopulationCities> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityViewHolder holder, int position) {
        holder.bindView(cities.get(position));
    }

    @Override
    public int getItemCount() {
        if (cities == null) return 0;
        return cities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.state_id) TextView mState;
        @Bind(R.id.city_id) TextView mCity;
        @Bind(R.id.population_id) TextView mPopulation;
        Context mContext;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindView(PopulationCities pop){
            mState.setText(pop.getState());
            mCity.setText(pop.getCity());
            mPopulation.setText(pop.getPopulation());
        }
    }
}
