package com.zavaly.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.zavaly.R;
import com.zavaly.cache.ZavalyRoomDatabase;
import com.zavaly.cache.entities.LoginCache;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.databinding.FragmentAccountBinding;
import com.zavaly.models.login.LoginResponse;

import java.util.HashMap;
import java.util.List;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private FragmentAccountBinding binding;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);

        accountViewModel.viewModelInit(context);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize() {

        List<LoginCache> loginCaches = ZavalyRoomDatabase.getINSTANCE(context).loginCacheDao().getLoggedInfo();

        if (!loginCaches.isEmpty()) {

            if (binding.loginLayout.getVisibility() == View.VISIBLE) {

                binding.loginLayout.setVisibility(View.GONE);

            }

            if (binding.showProfileLayout.getVisibility() == View.GONE) {

                binding.showProfileLayout.setVisibility(View.VISIBLE);

            }

            if (binding.accRegisterBtn.getVisibility() == View.VISIBLE) {

                binding.accRegisterBtn.setVisibility(View.GONE);

            }

            String customerName = loginCaches.get(0).getUserName();
            String customerPhone = loginCaches.get(0).getUserPhone();
            String customerAvater = loginCaches.get(0).getUserAvater();

            if (!customerName.equals("null")) {

                binding.accShowName.setText(customerName);

            }

            if (!customerPhone.equals("null")) {

                binding.accShowPhone.setText(customerPhone);

            }

            if (!customerAvater.equals("null")) {

                String url = BaseApiConstant.IMAGE_FETCH_URL + customerAvater;

                Glide.with(context)
                        .load(url)
                        .placeholder(context.getResources().getDrawable(R.drawable.zavalylogo))
                        .into(binding.accAccountImage);

            }

        }


        /**-----------------------------------------------------------------------**/
        binding.accRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(AccountFragmentDirections.actionNavigationAccountToNavigationCreateAccount());

            }
        });
        /**-----------------------------------------------------------------------**/

        /**-----------------------------------------------------------------------**/
        binding.accSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = String.valueOf(binding.accPhoneEdt.getText());
                String password = String.valueOf(binding.accPasswordEdt.getText());

                if (phone.isEmpty()) {

                    binding.accPhoneEdt.setError("Empty");

                } else if (password.isEmpty()) {

                    binding.accPasswordEdt.setError("Empty");

                } else {

                    HashMap<String, String> loginMap = new HashMap<>();
                    loginMap.put("phone", phone);
                    loginMap.put("password", password);

                    accountViewModel.userLogin(loginMap).observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse loginResponse) {

                            if (loginResponse != null) {

                                if (loginResponse.getSuccess()) {

                                    binding.accPhoneEdt.setText("");
                                    binding.accPasswordEdt.setText("");

                                    if (binding.loginLayout.getVisibility() == View.VISIBLE) {

                                        binding.loginLayout.setVisibility(View.GONE);
                                    }

                                    if (binding.showProfileLayout.getVisibility() == View.GONE) {

                                        binding.showProfileLayout.setVisibility(View.VISIBLE);

                                    }

                                    if (binding.accRegisterBtn.getVisibility() == View.VISIBLE) {

                                        binding.accRegisterBtn.setVisibility(View.GONE);

                                    }

                                    binding.accShowName.setText(loginResponse.getUser().getName());
                                    binding.accShowPhone.setText(loginResponse.getUser().getPhone());


                                    /**Save To Room Database**/
                                    String name = loginResponse.getUser().getName();
                                    String phone = loginResponse.getUser().getPhone();
                                    String token = loginResponse.getToken();
                                    String avater;
                                    try {
                                        avater = String.valueOf(loginResponse.getUser().getAvater());
                                    } catch (NullPointerException ne) {

                                        avater = "";

                                    }

                                    LoginCache loginCache = new LoginCache(name, phone, token, avater);
                                    ;
                                    ZavalyRoomDatabase.getINSTANCE(context).loginCacheDao().insert(loginCache);

                                }

                            }

                        }
                    });

                }

            }
        });
        /**-----------------------------------------------------------------------**/

        /**-----------------------------------------------------------------------**/
        binding.accShowLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.loginLayout.getVisibility() == View.GONE) {

                    binding.loginLayout.setVisibility(View.VISIBLE);

                }

                if (binding.showProfileLayout.getVisibility() == View.VISIBLE) {

                    binding.showProfileLayout.setVisibility(View.GONE);

                }

                if (binding.accRegisterBtn.getVisibility() == View.GONE) {

                    binding.accRegisterBtn.setVisibility(View.VISIBLE);

                }

                ZavalyRoomDatabase.getINSTANCE(context).loginCacheDao().deleteLoggedInfo();

            }
        });
        /**-----------------------------------------------------------------------**/

        /**-----------------------------------------------------------------------**/

        binding.accForgetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(AccountFragmentDirections.actionNavigationAccountToNavigationForgetPassword());

            }
        });

        /**-----------------------------------------------------------------------**/

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