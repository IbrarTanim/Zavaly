package com.zavaly.ui.mainui.discount;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.models.discountproducts.Datum;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiscountViewModel extends ViewModel {

    private DiscountRepository repository;
    private MutableLiveData<DiscountDataSource> dataSourceMutableLiveData;
    private LiveData<PagedList<Datum>> listLiveData;
    private Executor executor;

    public void viewModelInit(Context context) {
        repository = new DiscountRepository(context);
    }

    public void getList(RecyclerView recyclerView) {
        repository.getList(recyclerView);
    }

    public void getDiscountList(Context context) {

        DiscountDataSourceFactory factory = new DiscountDataSourceFactory(context);

        dataSourceMutableLiveData = factory.getDiscountDataSourceMutableLiveData();

        PagedList.Config pagedConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(25)
                .build();

        executor = Executors.newFixedThreadPool(5);

        listLiveData = (new LivePagedListBuilder<Integer, Datum>(factory, pagedConfig))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<Datum>> getListLiveData() {
        return listLiveData;
    }
}