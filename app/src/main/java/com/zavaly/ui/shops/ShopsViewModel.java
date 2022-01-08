package com.zavaly.ui.shops;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class ShopsViewModel extends ViewModel {


    private ShopsRepository shopsRepository;

    public void viewModelInit(Context context) {

        shopsRepository = new ShopsRepository(context);

    }

    public void getAllShops(RecyclerView recyclerView) {

        shopsRepository.getAllShops(recyclerView);

    }

}