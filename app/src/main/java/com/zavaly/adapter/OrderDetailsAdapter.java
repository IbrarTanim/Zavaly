package com.zavaly.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.models.ordersdetails.OrderDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.DetailsViewHolder> {

    private Context context;
    private List<OrderDetail> products;

    public OrderDetailsAdapter(Context context, List<OrderDetail> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_details_products, parent, false);
        return new DetailsViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {

        if (products != null) {

            try {

                JSONObject jsonObject = new JSONObject(products.get(position).getProduct());

                try {
                    String title = jsonObject.getString("title");
                    holder.productTitle.setText(title);
                } catch (Exception ne) {
                    //skip
                }

                try {
                    JSONArray imageArray = new JSONArray(jsonObject.getString("image"));
                    String image = BaseApiConstant.IMAGE_FETCH_URL + imageArray.get(0);
                    Glide.with(context)
                            .load(image)
                            .override(80, 80)
                            .into(holder.productImage);

                } catch (Exception ne) {
                    //skip
                    Log.e("TAG", ne.getMessage());
                }

                try {
                    String color = products.get(position).getColor();
                    holder.productColor.setText(color);
                } catch (NullPointerException ne) {
                    //skip
                }


                try {
                    String size = products.get(position).getSize();
                    holder.productSize.setText(size);
                    holder.barTV.setVisibility(View.VISIBLE);
                } catch (NullPointerException ne) {
                    //skip
                }


                try {
                    int quantity = products.get(position).getQuantity();
                    holder.productQuantity.setText(String.valueOf(quantity));
                } catch (Exception ne) {
                    //skip
                }


                try {
                    double total = products.get(position).getTotal();
                    holder.productPrice.setText("TK. " + total);
                } catch (Exception ne) {
                    //skip
                }


            } catch (Exception ne) {
                //skip
            }

        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class DetailsViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private MaterialTextView productTitle, productColor, productSize, productQuantity, productPrice, barTV;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image_view);
            productTitle = itemView.findViewById(R.id.product_title);
            productColor = itemView.findViewById(R.id.product_color);
            productSize = itemView.findViewById(R.id.product_size);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productPrice = itemView.findViewById(R.id.product_price);
            barTV = itemView.findViewById(R.id.product_size_color_bar);

        }
    }
}
