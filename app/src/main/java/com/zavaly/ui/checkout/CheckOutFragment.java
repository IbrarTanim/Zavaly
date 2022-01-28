package com.zavaly.ui.checkout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
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
import androidx.navigation.Navigation;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.zavaly.R;
import com.zavaly.cache.ZavalyRoomDatabase;
import com.zavaly.cache.entities.LoginCache;
import com.zavaly.databinding.CheckOutFragmentBinding;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CheckOutFragment extends Fragment {

    private CheckOutViewModel mViewModel;
    private CheckOutFragmentBinding binding;
    private Context context;
    private AwesomeValidation validation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CheckOutFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(CheckOutViewModel.class);
        mViewModel.viewModelInit(context);

        validation = new AwesomeValidation(ValidationStyle.BASIC);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        //initialize
        initializeViews();

    }

    private void initializeViews() {

        binding.btnCheckoutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setUpValidation();

                if (validation.validate()) {

                    List<LoginCache> loginCacheList = ZavalyRoomDatabase.getINSTANCE(context).loginCacheDao().getLoggedInfo();

                    if (loginCacheList.isEmpty()) {

                        Toasty.info(context, "Please login to proceed..").show();

                        //TODO

                    } else {

                        String email = "";
                        String guestId = new SharedPreferencesUtils(context).getString(String.valueOf(ZavalyEnums.KEY_GUEST));
                        String userId = String.valueOf(loginCacheList.get(0).getUserId());
                        String name = String.valueOf(binding.etShippingName.getText());
                        String contact = String.valueOf(binding.etShippingPhone.getText());
                        email = String.valueOf(binding.etShippingEmail.getText());
                        String address = String.valueOf(binding.etShippingAdress.getText());

                        HashMap<String, String> params = new HashMap<>();
                        params.put("guest_id", guestId);
                        params.put("user_id", userId);
                        params.put("shipping_name", name);
                        params.put("shipping_phone", contact);
                        params.put("shipping_email", email);
                        params.put("shipping_address", address);

                        mViewModel.checkoutConfirm(params).observe(getViewLifecycleOwner(), new Observer<Integer>() {
                            @Override
                            public void onChanged(Integer integer) {

                                if (integer == 200) {

                                    if (binding.mainLayout.getVisibility() == View.VISIBLE) {

                                        binding.mainLayout.setVisibility(View.GONE);

                                    }

                                    if (binding.secondaryLayout.getVisibility() == View.GONE) {

                                        binding.secondaryLayout.setVisibility(View.VISIBLE);

                                    }

                                    Animator animator = AnimatorInflater.loadAnimator(context, R.animator.checkout_animator);
                                    animator.setTarget(binding.ivGiphy);
                                    animator.setDuration(1000);
                                    animator.addListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animator) {

                                            //TODO
                                            Navigation.findNavController(view).navigate(CheckOutFragmentDirections.actionNavigationCheckoutToNavigationHome());

                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animator) {

                                        }
                                    });
                                    animator.start();

                                }

                            }
                        });

                    }

                }


            }
        });

    }

    private void setUpValidation() {

        String phoneCustom = ".{11,}";
        validation.addValidation(getActivity(), binding.etShippingName.getId(), RegexTemplate.NOT_EMPTY, R.string.warning_empty);
        validation.addValidation(getActivity(), binding.etShippingAdress.getId(), RegexTemplate.NOT_EMPTY, R.string.warning_empty);
        validation.addValidation(getActivity(), binding.etShippingPhone.getId(), RegexTemplate.NOT_EMPTY, R.string.warning_empty);
        validation.addValidation(getActivity(), binding.etShippingPhone.getId(), phoneCustom, R.string.warning_not_valid);

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