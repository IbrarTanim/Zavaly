package com.zavaly.ui.createaccount;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.register.RegisterResponse;
import com.zavaly.models.registerverify.RegisterVerifyResponse;

public class CreateAccountViewModel extends ViewModel {

    private CreateAccountRepository repository;

    public void viewModelInit(Context context) {
        repository = new CreateAccountRepository(context);
    }

    public MutableLiveData<RegisterResponse> registerCustomer(String customerName, String phoneNumber, String password, String confirmPassword) {
        return repository.registerCustomer(customerName, phoneNumber, password, confirmPassword);
    }

    public MutableLiveData<RegisterVerifyResponse> verifyRegisterAccount(String pin, String phone) {
        return repository.verifyRegisterAccount(pin, phone);
    }
}