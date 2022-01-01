package com.zavaly.ui.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private HomeRepository homeRepository;

    public void viewModelInit(Context context) {

        homeRepository = new HomeRepository(context);
    }

    public MutableLiveData<List<String>> getSliders() {
        return homeRepository.getSliders();
    }


}