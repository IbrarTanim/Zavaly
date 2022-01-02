package com.zavaly.ui.forgetpassword;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.zavaly.databinding.ForgetPasswordFragmentBinding;
import com.zavaly.models.recoverpass.RecoverResponse;
import com.zavaly.utils.RandomVariableUtils;

import es.dmoral.toasty.Toasty;

public class ForgetPasswordFragment extends Fragment {

    private ForgetPasswordViewModel mViewModel;
    private ForgetPasswordFragmentBinding binding;
    private Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ForgetPasswordFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(ForgetPasswordViewModel.class);
        mViewModel.viewModelInit(context);

        initialize();

        return binding.getRoot();
    }

    private void initialize() {

        binding.sendVerificationCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RandomVariableUtils.forgetPhoneNumber = String.valueOf(binding.sendPhoneEdt.getText());

                if (!RandomVariableUtils.forgetPhoneNumber.isEmpty()) {

                    mViewModel.recoverRequest(RandomVariableUtils.forgetPhoneNumber).observe(getViewLifecycleOwner(), new Observer<RecoverResponse>() {
                        @Override
                        public void onChanged(RecoverResponse recoverResponse) {

                            if (recoverResponse != null) {

                                if (recoverResponse.getSuccess()) {

                                    Toasty.success(context, "Verification code sent.").show();

                                    if (binding.resetPassLayout.getVisibility() == View.GONE) {

                                        binding.resetPassLayout.setVisibility(View.VISIBLE);

                                    }

                                    if (binding.sendVerificationCodeLayout.getVisibility() == View.VISIBLE) {

                                        binding.sendVerificationCodeLayout.setVisibility(View.GONE);

                                    }

                                } else {

                                    Toasty.error(context, "Phone number not valid!").show();

                                }

                            }

                        }
                    });

                }

            }
        });


        binding.resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = String.valueOf(binding.passEdt.getText());
                String conPass = String.valueOf(binding.conPassEdt.getText());
                String pin = String.valueOf(binding.codeEdt.getText());

                if (password.length() < 8) {

                    binding.passEdt.setError("Minimum 8 characters needed.");

                } else if (!password.equals(conPass)) {

                    binding.conPassEdt.setError("Mismatch");

                } else if (pin.isEmpty()) {

                    binding.codeEdt.setError("Empty");

                } else {

                    mViewModel.recoverPassword(RandomVariableUtils.forgetPhoneNumber, password, conPass, pin).observe(getViewLifecycleOwner(), new Observer<RecoverResponse>() {
                        @Override
                        public void onChanged(RecoverResponse recoverResponse) {

                            if (recoverResponse != null) {

                                if (recoverResponse.getSuccess()) {

                                    Toasty.success(context, "Password changed successfully").show();
                                    NavHostFragment.findNavController(ForgetPasswordFragment.this).navigate(ForgetPasswordFragmentDirections.actionNavigationForgetPasswordToNavigationAccount());

                                } else {

                                    Toasty.error(context, "Information not valid.").show();

                                }

                            }

                        }
                    });

                }

            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}