package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.models.discountproducts.Datum;

import org.json.JSONArray;
import org.json.JSONException;

public class DiscountRecyclerAdapter extends PagedListAdapter<Datum, DiscountRecyclerAdapter.ProductsViewHolder> {


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
    private Context context;

    public DiscountRecyclerAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_rv, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        Datum product = getItem(position);

        if (product != null) {

            holder.productNameTV.setText(product.getTitle());

            holder.productNewPriceTV.setText(String.valueOf(product.getPrice()));

            String imageNameList = product.getImage();
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
