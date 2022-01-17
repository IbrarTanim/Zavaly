package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.models.Cart;
import com.zavaly.utils.ChildClickListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CartViewRecyclerAdapter extends RecyclerView.Adapter<CartViewRecyclerAdapter.CartViewViewHolder> {

    public ChildClickListener clickListener;
    private Context context;
    private List<Cart> cartList = new ArrayList<>();

    public CartViewRecyclerAdapter(Context context, List<Cart> cartList, ChildClickListener clickListener) {
        this.context = context;
        this.cartList = cartList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CartViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_product_rv, parent, false);
        return new CartViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewViewHolder holder, int position) {

        if (cartList != null) {

            int productId = cartList.get(position).getProductId();

            /**
             * Product Image
             * */
            try {

                JSONArray imageArray = new JSONArray(cartList.get(position).getImage());
                String productLogo = imageArray.getString(0);

                String imageUrl = BaseApiConstant.IMAGE_FETCH_URL + productLogo;
                Glide.with(context)
                        .load(imageUrl)
                        .into(holder.cartProductImage);

            } catch (NullPointerException | JSONException ne) {

            }

            /**
             * Product Name
             * */

            try {

                holder.cartProductName.setText(cartList.get(position).getTitle());

            } catch (Exception e) {

            }


            /**
             * Product Color
             * */

            try {

                if (cartList.get(position).getColor() != null) {
                    holder.cartProductColorName.setText(cartList.get(position).getColor() + " |");
                }


            } catch (Exception e) {

            }

            /**
             * Product Size
             * */

            try {

                if (cartList.get(position).getSize() != null) {
                    holder.cartProductSizeName.setText(" " + cartList.get(position).getSize() + " |");
                }

            } catch (Exception e) {

            }

            /**
             * Product Model
             * */

            try {

                //holder.cartProductColorName.setText(" " + cartList.get(position).getSize() + " |");

            } catch (Exception e) {

            }

            /**
             * Product total
             * */

            try {

                holder.cartTotalPrice.setText(String.valueOf(cartList.get(position).getTotal()) + " Tk");

            } catch (Exception e) {

            }


            holder.cartEditBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toasty.warning(context, "Under construction").show();

                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView cartProductImage;
        private TextView cartProductName, cartProductColorName, cartProductSizeName, cartProductModelName, cartTotalPrice;
        private AppCompatImageButton cartEditBTN, cartDeleteBTN;

        public CartViewViewHolder(@NonNull View itemView) {
            super(itemView);

            cartProductImage = itemView.findViewById(R.id.cart_product_image);
            cartProductName = itemView.findViewById(R.id.cart_product_name);
            cartProductColorName = itemView.findViewById(R.id.cart_color_name);
            cartProductSizeName = itemView.findViewById(R.id.cart_size_name);
            cartProductModelName = itemView.findViewById(R.id.cart_model_name);
            cartTotalPrice = itemView.findViewById(R.id.cart_total_price);
            cartEditBTN = itemView.findViewById(R.id.cart_edit_btn);
            cartDeleteBTN = itemView.findViewById(R.id.cart_delete_btn);

            cartDeleteBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onChildClick(view, getAdapterPosition());
                }
            });

        }
    }
}
