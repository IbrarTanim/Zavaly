package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.R;
import com.zavaly.models.allcategorydetails.Product__1;

import java.util.ArrayList;
import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.ProductsViewHolder> {

    private Context context;
    private List<Product__1> allProductsList = new ArrayList<>();


    public ProductsRecyclerAdapter(Context context, List<Product__1> allProductsList) {
        this.context = context;
        this.allProductsList = allProductsList;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_rv, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        if (allProductsList != null) {

            holder.productNameTV.setText(allProductsList.get(position).getTitle());

            /*try {
                //String productImg = "http://alifmart.com.bd/" + allProductsForSingleCategoryList.get(position).getThumbnailImages().get(0).getFileName();
                String productImg = BaseApiConstant.IMAGE_FETCH_URL + ;
                //"https://alifmart.com.bd/"
                Glide.with(context)
                        .load(productImg)
                        .fitCenter()
                        .placeholder(context.getResources().getDrawable(R.drawable.alif_mart_logo_blue_back))
                        .into(holder.productPhotoIV);
            } catch (Exception ne) {
                //To DO
                holder.productPhotoIV.setImageDrawable(context.getResources().getDrawable(R.drawable.alif_mart_logo_blue_back));
            }*/

            //holder.productNewPriceTV.setText(String.valueOf(allProductsForSingleCategoryList.get(position).getUnitPrice()));
            //holder.productDiscountTV.setText(allProductsForSingleBrandList.get(position).getDiscount());
            //String productUnitPrice, String discountAmount, String discountType, String discountStartDate, String discountEndDate, TextView productPriceTV, TextView detailsProductCrossPrice

            holder.productNewPriceTV.setText(allProductsList.get(position).getPrice());

        }

    }

    @Override
    public int getItemCount() {
        return allProductsList.size();
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
