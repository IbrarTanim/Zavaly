package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.models.Cat;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoriesViewHolder> {

    private Context context;
    private List<Cat> categories = new ArrayList<>();

    public CategoryListAdapter(Context context, List<Cat> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categories_rv, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        if (!categories.isEmpty()) {
            holder.categoryNameTV.setText(categories.get(position).getName());
            try {
                String imageURl = categories.get(position).getImage();
                if (!imageURl.isEmpty()) {
                    //String productImg = "http://alifmart.com.bd/" + imageURl;
                    String productImg = BaseApiConstant.IMAGE_FETCH_URL + imageURl;
                    Glide.with(context)
                            .load(productImg)
                            .fitCenter()
                            .into(holder.categoryIV);
                }

            } catch (Exception e) {

            }
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIV;
        private TextView categoryNameTV;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIV = itemView.findViewById(R.id.row_category_iv);
            categoryNameTV = itemView.findViewById(R.id.row_category_name_tv);
        }
    }
}
