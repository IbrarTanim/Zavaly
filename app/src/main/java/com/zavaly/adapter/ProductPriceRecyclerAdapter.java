package com.zavaly.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.R;
import com.zavaly.models.pricemodel.PriceDetailsObject;

import java.util.ArrayList;
import java.util.List;

public class ProductPriceRecyclerAdapter extends RecyclerView.Adapter<ProductPriceRecyclerAdapter.ProductPriceViewHolder> {

    private Context context;

    private List<PriceDetailsObject> priceDetailsObjectList = new ArrayList<>();

    public ProductPriceRecyclerAdapter(Context context, List<PriceDetailsObject> priceDetailsObjectList) {
        this.context = context;
        this.priceDetailsObjectList = priceDetailsObjectList;
    }

    @NonNull
    @Override
    public ProductPriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_price, parent, false);
        return new ProductPriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductPriceViewHolder holder, int position) {

        if (priceDetailsObjectList != null) {

            if (priceDetailsObjectList.get(position).getQuantityDisPrice() != null) {

                if (holder.productDisPrice.getVisibility() == View.GONE) {

                    holder.productDisPrice.setVisibility(View.VISIBLE);

                }
                String concateDisPrice = "TK " + priceDetailsObjectList.get(position).getQuantityPrice();
                holder.productDisPrice.setText(concateDisPrice);
                holder.productDisPrice.setPaintFlags(holder.productDisPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                double actualPrice = Double.parseDouble(priceDetailsObjectList.get(position).getQuantityPrice());
                double disCountAmount = Double.parseDouble(priceDetailsObjectList.get(position).getQuantityDisPrice());
                double discountedPrice = actualPrice - disCountAmount;
                holder.productPrice.setText(String.valueOf(discountedPrice));

            } else {

                holder.productPrice.setText(priceDetailsObjectList.get(position).getQuantityPrice());

            }

            holder.productQuantity.setText(priceDetailsObjectList.get(position).getQuantityRange());

        }

    }

    @Override
    public int getItemCount() {
        return priceDetailsObjectList.size();
    }

    static class ProductPriceViewHolder extends RecyclerView.ViewHolder {

        private TextView productPrice, productDisPrice, productQuantity;

        public ProductPriceViewHolder(@NonNull View itemView) {
            super(itemView);

            productPrice = itemView.findViewById(R.id.tv_product_price);
            productDisPrice = itemView.findViewById(R.id.tv_dis_product_price);
            productQuantity = itemView.findViewById(R.id.tv_quantity);
        }
    }
}
