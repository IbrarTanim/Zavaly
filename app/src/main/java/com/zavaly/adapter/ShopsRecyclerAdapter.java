package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.zavaly.R;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.models.shopsresponse.Datum;
import com.zavaly.ui.mainui.shops.ShopsFragmentDirections;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsRecyclerAdapter extends PagedListAdapter<Datum, ShopsRecyclerAdapter.ShopsViewHolder> {

    private Context context;

    private static final DiffUtil.ItemCallback<Datum> DIFF_UTIL = new DiffUtil.ItemCallback<Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return true;
        }
    };

    public ShopsRecyclerAdapter(Context context) {
        super(DIFF_UTIL);
        this.context = context;
    }


    @NonNull
    @Override
    public ShopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shops_rv, parent, false);
        return new ShopsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopsViewHolder holder, int position) {

        Datum datum = getItem(position);

        if (datum != null) {

            try {
                String shopName = datum.getShopname();
                holder.shopName.setText(shopName);
            } catch (NullPointerException ne) {

            }
            try {
                String shopAddress = datum.getShopaddress();
                holder.shopAddress.setText(shopAddress);
            } catch (NullPointerException ne) {

            }
            try {
                String shopContact = datum.getShopcontact();
                holder.shopContact.setText(shopContact);
            } catch (NullPointerException ne) {

            }
            try {

                String shopLogo = String.valueOf(datum.getShoplogo());

                String imageUrl = BaseApiConstant.IMAGE_FETCH_URL + shopLogo;
                Glide.with(context)
                        .load(imageUrl)
                        .into(holder.shopImage);

            } catch (NullPointerException ne) {

            }

            holder.shopLiveChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Navigation.findNavController(view).navigate(ShopsFragmentDirections.actionNavigationShopsToNavigationChat());

                }
            });


        }

    }


    static class ShopsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView shopImage;
        TextView shopName, shopAddress, shopContact;
        MaterialButton shopLiveChat;

        public ShopsViewHolder(@NonNull View itemView) {
            super(itemView);

            shopImage = itemView.findViewById(R.id.row_shop_logo);
            shopName = itemView.findViewById(R.id.row_shop_name);
            shopAddress = itemView.findViewById(R.id.row_shop_address);
            shopContact = itemView.findViewById(R.id.row_shop_contact);
            shopLiveChat = itemView.findViewById(R.id.shop_live_chat);
        }
    }
}
