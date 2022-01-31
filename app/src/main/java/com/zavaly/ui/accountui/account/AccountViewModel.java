package com.zavaly.ui.accountui.account;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.login.LoginResponse;

import java.util.HashMap;

public class AccountViewModel extends ViewModel {

    private AccountRepository accountRepository;

    public void viewModelInit(Context context) {

        accountRepository = new AccountRepository(context);

    }

    public MutableLiveData<LoginResponse> userLogin(HashMap<String, String> loginMap) {

        return accountRepository.userLogin(loginMap);

    }
}