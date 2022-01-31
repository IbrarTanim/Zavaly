package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.R;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.allcategorydetails.Datum;
import com.zavaly.ui.mainui.home.HomeFragmentDirections;
import com.zavaly.utils.RecyclerTouchListener;

public class HomeAllCategoriesRecyclerAdapter extends PagedListAdapter<Datum, HomeAllCategoriesRecyclerAdapter.HomeAllCategoryViewHolder> {

    private Context context;

    private static DiffUtil.ItemCallback<Datum> DIFF_CALLBACK = new DiffUtil.ItemCallback<Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return true;
        }
    };

    public HomeAllCategoriesRecyclerAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home_all_products_rv, parent, false);
        return new HomeAllCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAllCategoryViewHolder holder, int position) {

        Datum categoryItem = getItem(position);

        if (categoryItem != null) {

            if (!categoryItem.getProducts().isEmpty()) {


                holder.brandNameTV.setText(categoryItem.getName());

                int catId = categoryItem.getId();

                holder.brandViewAllTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavigationHomeToNavigationViewCategoryProducts2(catId));

                    }
                });

                ProductsRecyclerAdapter productsRecyclerAdapter = new ProductsRecyclerAdapter(context, categoryItem.getProducts(), null, null, null, String.valueOf(ZavalyEnums.List_All_Cat));
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.allBrandProductRV.setLayoutManager(manager);
                holder.allBrandProductRV.setAdapter(productsRecyclerAdapter);

                holder.allBrandProductRV.addOnItemTouchListener(new RecyclerTouchListener(context, holder.allBrandProductRV, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        int productId = categoryItem.getProducts().get(position).getId();
                        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavigationHomeToNavigationProductDetails(productId));

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


            } else {

                holder.parentRowMain.setVisibility(View.GONE);

            }

        }

    }




    /*public void updateList(List<Datum> datumList){

        allCategoryList = datumList;
        notifyDataSetChanged();

    }*/

    static class HomeAllCategoryViewHolder extends RecyclerView.ViewHolder {

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
