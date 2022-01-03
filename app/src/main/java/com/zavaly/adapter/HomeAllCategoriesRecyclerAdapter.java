package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.R;
import com.zavaly.models.allcategorydetails.Datum;

import java.util.ArrayList;
import java.util.List;

public class HomeAllCategoriesRecyclerAdapter extends RecyclerView.Adapter<HomeAllCategoriesRecyclerAdapter.HomeAllCategoryViewHolder> {

    private Context context;
    private List<Datum> allCategoryList = new ArrayList<>();

    public HomeAllCategoriesRecyclerAdapter(Context context, List<Datum> allCategoryList) {
        this.context = context;
        this.allCategoryList = allCategoryList;
    }

    @NonNull
    @Override
    public HomeAllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home_all_products_rv, parent, false);
        return new HomeAllCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAllCategoryViewHolder holder, int position) {

        if (allCategoryList != null) {

            if (!allCategoryList.get(position).getProducts().isEmpty()) {


                holder.brandNameTV.setText(allCategoryList.get(position).getName());

                ProductsRecyclerAdapter productsRecyclerAdapter = new ProductsRecyclerAdapter(context, allCategoryList.get(position).getProducts());
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.allBrandProductRV.setLayoutManager(manager);
                holder.allBrandProductRV.setAdapter(productsRecyclerAdapter);


            } else {

                holder.parentRowMain.setVisibility(View.GONE);

            }

        }

    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    class HomeAllCategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView brandNameTV;
        private TextView brandViewAllTV;
        private RecyclerView allBrandProductRV;
        private LinearLayout parentRowMain;

        public HomeAllCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            brandNameTV = itemView.findViewById(R.id.home_parent_brand_name_tv);
            brandViewAllTV = itemView.findViewById(R.id.home_parent_all_tv);
            allBrandProductRV = itemView.findViewById(R.id.home_parent_child_rv);
            parentRowMain = itemView.findViewById(R.id.parent_row_main);
        }
    }

}
