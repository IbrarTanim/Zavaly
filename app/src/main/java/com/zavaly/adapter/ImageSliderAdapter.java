package com.zavaly.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.enums.ZavalyEnums;

import java.util.ArrayList;
import java.util.List;


public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private Context context;
    private List<String> sliderImagesList = new ArrayList<>();
    private String type;

    public ImageSliderAdapter(Context context, List<String> sliderImagesList, String type) {
        this.context = context;
        this.sliderImagesList = sliderImagesList;
        this.type = type;
    }

    @Override
    public ImageSliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_slider, null);
        return new ImageSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageSliderViewHolder viewHolder, int position) {

        if (type.equals(String.valueOf(ZavalyEnums.SLIDER_NORMAL))) {

            try {

                String url = sliderImagesList.get(position);
                Glide.with(viewHolder.view)
                        .load(url)
                        .fitCenter()
                        .into(viewHolder.sliderImageView);
            } catch (Exception e) {
                //
            }

        } else {

            try {

                String url = BaseApiConstant.IMAGE_FETCH_URL + sliderImagesList.get(position);
                Glide.with(viewHolder.view)
                        .load(url)
                        .fitCenter()
                        .into(viewHolder.sliderImageView);
            } catch (Exception e) {
                //
            }

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
