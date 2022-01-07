package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.models.specificcategory.Category;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubCategoryListAdapter extends RecyclerView.Adapter<SubCategoryListAdapter.SubCategoryViewHolder> {


    private Context context;
    private List<Category> subCategoryList = new ArrayList<>();

    public SubCategoryListAdapter(Context context, List<Category> subCategoryList) {
        this.context = context;
        this.subCategoryList = subCategoryList;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_menu, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {

        if (subCategoryList != null) {

            String subCatName = subCategoryList.get(position).getName();
            holder.rowSubCatName.setText(subCatName);
            String subCatImage = subCategoryList.get(position).getImage();
            if (!subCatImage.isEmpty()) {

                String url = BaseApiConstant.IMAGE_FETCH_URL + subCatImage;
                Glide.with(context)
                        .load(url)
                        .into(holder.rowSubCatImage);

            }

        }

    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    static class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        CircleImageView rowSubCatImage;
        MaterialTextView rowSubCatName;

        public SubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            rowSubCatImage = itemView.findViewById(R.id.home_menu_image);
            rowSubCatName = itemView.findViewById(R.id.home_menu_title);
        }
    }
}
