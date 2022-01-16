package com.zavaly.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;

import java.util.ArrayList;
import java.util.List;

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ProductImagesViewHolder> {

    private Context context;
    private List<String> images = new ArrayList<>();
    private int isSelected;

    public ProductImagesAdapter(Context context, List<String> images, int isSelected) {
        this.context = context;
        this.images = images;
        this.isSelected = isSelected;
    }

    @NonNull
    @Override
    public ProductImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_images_rv, parent, false);
        return new ProductImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImagesViewHolder holder, int position) {

        if (images != null) {

            try {

                if (position == isSelected) {
                    holder.mainLayout.setBackground(AppCompatResources.getDrawable(context, R.drawable.primary_stroke));
                    String url = BaseApiConstant.IMAGE_FETCH_URL + images.get(position);
                    Glide.with(context)
                            .load(url)
                            .fitCenter()
                            .into(holder.productIV);
                } else {
                    String url = BaseApiConstant.IMAGE_FETCH_URL + images.get(position);
                    Glide.with(context)
                            .load(url)
                            .fitCenter()
                            .into(holder.productIV);
                }

            } catch (Exception e) {
                //
            }

        }

    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
        notifyDataSetChanged();
    }

    static class ProductImagesViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mainLayout;
        private AppCompatImageView productIV;

        public ProductImagesViewHolder(@NonNull View itemView) {
            super(itemView);

            mainLayout = itemView.findViewById(R.id.item_main_layout);
            productIV = itemView.findViewById(R.id.item_image_view);
        }
    }
}
