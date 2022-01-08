package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.models.shopsresponse.Datum;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsRecyclerAdapter extends RecyclerView.Adapter<ShopsRecyclerAdapter.ShopsViewHolder> {

    private Context context;
    private List<Datum> allShops = new ArrayList<>();

    public ShopsRecyclerAdapter(Context context, List<Datum> allShops) {
        this.context = context;
        this.allShops = allShops;
    }

    @NonNull
    @Override
    public ShopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shops_rv, parent, false);
        return new ShopsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopsViewHolder holder, int position) {

        if (allShops != null) {

            try {
                String shopName = allShops.get(position).getShopname();
                holder.shopName.setText(shopName);
            } catch (NullPointerException ne) {

            }
            try {
                String shopAddress = allShops.get(position).getShopaddress();
                holder.shopAddress.setText(shopAddress);
            } catch (NullPointerException ne) {

            }
            try {
                String shopContact = allShops.get(position).getShopcontact();
                holder.shopContact.setText(shopContact);
            } catch (NullPointerException ne) {

            }
            try {

                String shopLogo = String.valueOf(allShops.get(position).getShoplogo());

                String imageUrl = BaseApiConstant.IMAGE_FETCH_URL + shopLogo;
                Glide.with(context)
                        .load(imageUrl)
                        .into(holder.shopImage);

            } catch (NullPointerException ne) {

            }


        }

    }

    @Override
    public int getItemCount() {
        return allShops.size();
    }

    static class ShopsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView shopImage;
        TextView shopName, shopAddress, shopContact;

        public ShopsViewHolder(@NonNull View itemView) {
            super(itemView);

            shopImage = itemView.findViewById(R.id.row_shop_logo);
            shopName = itemView.findViewById(R.id.row_shop_name);
            shopAddress = itemView.findViewById(R.id.row_shop_address);
            shopContact = itemView.findViewById(R.id.row_shop_contact);
        }
    }
}
