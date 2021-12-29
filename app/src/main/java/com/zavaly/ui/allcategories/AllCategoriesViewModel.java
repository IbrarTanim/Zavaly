package com.zavaly.ui.allcategories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.Cat;

import java.util.List;

public class AllCategoriesViewModel extends ViewModel {

    private AllCategoriesRepository repository;

    public void viewModelInit(Context context) {
        repository = new AllCategoriesRepository(context);
    }

    public MutableLiveData<List<Cat>> getAllCat() {
        return repository.getCategories();
    }
}