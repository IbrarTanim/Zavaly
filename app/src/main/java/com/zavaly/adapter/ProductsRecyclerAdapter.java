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
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.allcategorydetails.Product__1;
import com.zavaly.models.specificcategory.Product;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.ProductsViewHolder> {

    private Context context;
    private List<Product__1> allProductsList = new ArrayList<>();
    private List<Product> specificProducts = new ArrayList<>();
    private String listType;


    public ProductsRecyclerAdapter(Context context, List<Product__1> allProductsList, List<Product> specificProducts, String listType) {
        this.context = context;
        this.allProductsList = allProductsList;
        this.specificProducts = specificProducts;
        this.listType = listType;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_rv, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        if (listType.equals(String.valueOf(ZavalyEnums.List_All_Cat))) {

            if (allProductsList != null) {

                holder.productNameTV.setText(allProductsList.get(position).getTitle());

                holder.productNewPriceTV.setText(allProductsList.get(position).getPrice());

                String imageNameList = allProductsList.get(position).getImage();
                try {
                    JSONArray jsonArray = new JSONArray(imageNameList);
                    String imageName = jsonArray.getString(0);
                    if (!imageName.isEmpty()) {

                        String url = BaseApiConstant.IMAGE_FETCH_URL + imageName;
                        Glide.with(context)
                                .load(url)
                                .into(holder.productPhotoIV);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } else if (listType.equals(String.valueOf(ZavalyEnums.List_Solo_Cat))) {

            if (specificProducts != null) {

                holder.productNameTV.setText(specificProducts.get(position).getTitle());

                holder.productNewPriceTV.setText(specificProducts.get(position).getPrice());

                String imageNameList = specificProducts.get(position).getImage();
                try {
                    JSONArray jsonArray = new JSONArray(imageNameList);
                    String imageName = jsonArray.getString(0);
                    if (!imageName.isEmpty()) {

                        String url = BaseApiConstant.IMAGE_FETCH_URL + imageName;
                        Glide.with(context)
                                .load(url)
                                .into(holder.productPhotoIV);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    @Override
    public int getItemCount() {
        if (listType.equals(String.valueOf(ZavalyEnums.List_All_Cat))) {
            return allProductsList.size();
        } else if (listType.equals(String.valueOf(ZavalyEnums.List_Solo_Cat))) {
            return specificProducts.size();
        } else {
            return 0;
        }

    }

    static class ProductsViewHolder extends RecyclerView.ViewHolder {

        private ImageView productPhotoIV;
        private TextView productNameTV, productOldPriceTV, productNewPriceTV, productDiscountTV, productCrossPriceTV;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            productPhotoIV = itemView.findViewById(R.id.home_child_product_iv);
            productNameTV = itemView.findViewById(R.id.home_child_product_name_tv);
            productOldPriceTV = itemView.findViewById(R.id.home_child_product_old_price_tv);
            productNewPriceTV = itemView.findViewById(R.id.home_child_product_new_price_tv);
            productDiscountTV = itemView.findViewById(R.id.home_child_product_discount_tv);
            productCrossPriceTV = itemView.findViewById(R.id.home_child_product_cross_price_tv);
        }
    }
}
