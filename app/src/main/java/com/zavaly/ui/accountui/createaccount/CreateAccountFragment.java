package com.zavaly.ui.accountui.createaccount;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.zavaly.R;
import com.zavaly.databinding.CreateAccountFragmentBinding;
import com.zavaly.models.register.RegisterResponse;
import com.zavaly.models.registerverify.RegisterVerifyResponse;

public class CreateAccountFragment extends Fragment {

    private CreateAccountViewModel mViewModel;
    private Context context;
    private CreateAccountFragmentBinding binding;
    private String phoneNumber;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CreateAccountFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(CreateAccountViewModel.class);
        mViewModel.viewModelInit(context);

        initialize();

        return binding.getRoot();
    }

    private void initialize() {

        binding.regSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(CreateAccountFragmentDirections.actionNavigationCreateAccountToNavigationAccount());
            }
        });

        binding.regRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String customerName = String.valueOf(binding.regName.getText());
                phoneNumber = String.valueOf(binding.regPhone.getText());
                String password = String.valueOf(binding.regPassword.getText());
                String confirmPassword = String.valueOf(binding.regConfirmPassword.getText());

                if (validate(customerName, phoneNumber, password, confirmPassword)) {

                    mViewModel.registerCustomer(customerName, phoneNumber, password, confirmPassword).observe(getViewLifecycleOwner(), new Observer<RegisterResponse>() {
                        @Override
                        public void onChanged(RegisterResponse registerResponse) {

                            if (registerResponse.getSuccess()) {

                                //dialog start
                                Dialog dialog = new Dialog(context);
                                dialog.setContentView(R.layout.verify_phone_dialog);
                                TextInputEditText verifyDialogEdt = dialog.findViewById(R.id.verifyDialogEdt);
                                AppCompatButton enterBtn = dialog.findViewById(R.id.dialog_enter);
                                AppCompatButton cancelBtn = dialog.findViewById(R.id.dialog_cancel);

                                enterBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String verificationCode = String.valueOf(verifyDialogEdt.getText());
                                        if (!verificationCode.isEmpty()) {

                                            mViewModel.verifyRegisterAccount(verificationCode, phoneNumber).observe(getViewLifecycleOwner(), new Observer<RegisterVerifyResponse>() {
                                                @Override
                                                public void onChanged(RegisterVerifyResponse registerVerifyResponse) {

                                                    NavHostFragment.findNavController(CreateAccountFragment.this).navigate(CreateAccountFragmentDirections.actionNavigationCreateAccountToNavigationAccount());

                                                }
                                            });

                                        }

                                    }
                                });

                                cancelBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                                //dialog end

                            }

                        }
                    });

                }
            }
        });

    }

    private boolean validate(String customerName, String phoneNumber, String password, String confirmPassword) {

        boolean valid = true;

        if (customerName.isEmpty()) {

            binding.regName.setError("Empty");
            valid = false;

        } else if (phoneNumber.length() != 11) {

            binding.regPhone.setError("Invalid");
            valid = false;

        } else if (password.length() < 8) {

            binding.regPassword.setError("At least 8 character.");
            valid = false;

        } else if (confirmPassword.length() < 8) {

            binding.regConfirmPassword.setError("At least 8 character.");
            valid = false;

        } else if (!password.equals(confirmPassword)) {

            binding.regConfirmPassword.setError("Mismatch");
            valid = false;

        }

        return valid;

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