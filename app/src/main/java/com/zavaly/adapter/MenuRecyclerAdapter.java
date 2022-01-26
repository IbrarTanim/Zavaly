package com.zavaly.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.zavaly.R;
import com.zavaly.models.MenuModelClass;

import java.util.ArrayList;
import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder> {

    private Context context;
    private List<MenuModelClass> menuList = new ArrayList<>();

    public MenuRecyclerAdapter(Context context, List<MenuModelClass> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        if (menuList != null) {

            holder.menuTitle.setText(menuList.get(position).getMenuTitle());
            holder.menuImage.setImageDrawable(context.getResources().getDrawable(menuList.get(position).getMenuImage()));

        }

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {

        private ImageView menuImage;
        private MaterialTextView menuTitle;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuImage = itemView.findViewById(R.id.home_menu_image);
            menuTitle = itemView.findViewById(R.id.home_menu_title);
        }
    }

}
