package com.zavaly.ui.accountui.forgetpassword;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.recoverpass.RecoverResponse;

public class ForgetPasswordViewModel extends ViewModel {

    private ForgetPasswordRepository repository;

    public void viewModelInit(Context context) {

        repository = new ForgetPasswordRepository(context);

    }


    public MutableLiveData<RecoverResponse> recoverRequest(String phoneNumber) {

        return repository.recoverRequest(phoneNumber);

    }


    public MutableLiveData<RecoverResponse> recoverPassword(String phoneNumber, String password, String conPassword, String pin) {

        return repository.recoverPassword(phoneNumber, password, conPassword, pin);

    }
}