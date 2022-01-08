package com.zavaly.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zavaly.models.allcategorydetails.Datum;
import com.zavaly.models.searchresponse.Product;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeViewModel extends ViewModel {

    private HomeRepository homeRepository;
    private LiveData<PagedList<Datum>> categoryPagedList;
    private LiveData<PagedList<Product>> searchProductList;
    private MutableLiveData<CategoryPageDataSource> liveDataSource;
    private MutableLiveData<SearchResultDataSource> liveData;
    private Executor executors;

    public void viewModelInit(Context context) {

        homeRepository = new HomeRepository(context);
    }

    public MutableLiveData<List<String>> getSliders() {
        return homeRepository.getSliders();
    }

    /*public void getAllProducts(RecyclerView recyclerView) {
        homeRepository.getAllProducts(recyclerView);
    }*/

    public void getAllCategory(Context context) {

        CategoryPageDataSourceFactory factory = new CategoryPageDataSourceFactory(context);

        liveDataSource = factory.getLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(10)
                .build();

        executors = Executors.newFixedThreadPool(5);

        categoryPagedList = (new LivePagedListBuilder<Integer, Datum>(factory, pagedListConfig))
                .setFetchExecutor(executors)
                .build();
    }

    public LiveData<PagedList<Datum>> getCategoryPagedList() {
        return categoryPagedList;
    }

    public void getSearchProduct(Context context, String searchText) {

        SearchResultDataSourceFactory factory = new SearchResultDataSourceFactory(context, searchText);

        liveData = factory.getLiveData();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .build();

        executors = Executors.newFixedThreadPool(5);

        searchProductList = (new LivePagedListBuilder<Integer, Product>(factory, pagedListConfig))
                .setFetchExecutor(executors)
                .build();
    }

    public LiveData<PagedList<Product>> getSearchProductList() {
        return searchProductList;
    }
}