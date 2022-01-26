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

import com.zavaly.R;
import com.zavaly.databinding.CheckOutFragmentBinding;

public class CheckOutFragment extends Fragment {

    private CheckOutViewModel mViewModel;
    private CheckOutFragmentBinding binding;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CheckOutFragmentBinding.inflate(inflater, container, false);

        //initialize
        initializeViews();

        return binding.getRoot();
    }

    private void initializeViews() {

        binding.btnCheckoutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.mainLayout.getVisibility() == View.VISIBLE) {

                    binding.mainLayout.setVisibility(View.GONE);

                }

                if (binding.secondaryLayout.getVisibility() == View.GONE) {

                    binding.secondaryLayout.setVisibility(View.VISIBLE);

                }

                Animator animator = AnimatorInflater.loadAnimator(context, R.animator.checkout_animator);
                animator.setTarget(binding.ivGiphy);
                animator.setDuration(2000);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        //TODO

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