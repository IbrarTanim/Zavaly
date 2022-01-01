package com.zavaly.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.zavaly.R;

import java.util.ArrayList;
import java.util.List;


public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private List<String> sliderImagesList = new ArrayList<>();
    private Context context;

    public ImageSliderAdapter(List<String> sliderImagesList, Context context) {
        this.sliderImagesList = sliderImagesList;
        this.context = context;
    }

    @Override
    public ImageSliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_slider, null);
        return new ImageSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageSliderViewHolder viewHolder, int position) {
        try {

            String url = sliderImagesList.get(position);
            Glide.with(viewHolder.view)
                    .load(url)
                    .fitCenter()
                    .into(viewHolder.sliderImageView);
        } catch (Exception e) {
            //
        }

    }

    @Override
    public int getCount() {
        return sliderImagesList.size();
    }

    static class ImageSliderViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView sliderImageView;
        View view;

        public ImageSliderViewHolder(View itemView) {
            super(itemView);
            sliderImageView = itemView.findViewById(R.id.row_image_slider_iv);
            this.view = itemView;
        }
    }
}
