package com.zavaly.ui.mainui.shops;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zavaly.models.shopsresponse.Datum;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ShopsViewModel extends ViewModel {


    private ShopsRepository shopsRepository;
    private LiveData<PagedList<Datum>> pagedShopsLiveData;
    private MutableLiveData<ShopsDataSource> dataSourceMutableLiveData;
    private Executor executor;

    public void viewModelInit(Context context) {

        shopsRepository = new ShopsRepository(context);

    }

    /*public void getAllShops(RecyclerView recyclerView) {

        shopsRepository.getAllShops(recyclerView);

    }*/

    public void getPagedShops(Context context) {

        ShopDataSourceFactory factory = new ShopDataSourceFactory(context);

        dataSourceMutableLiveData = factory.getLiveData();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .build();

        executor = Executors.newFixedThreadPool(5);

        pagedShopsLiveData = (new LivePagedListBuilder<Integer, Datum>(factory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<Datum>> getPagedShopsLiveData() {
        return pagedShopsLiveData;
    }
}