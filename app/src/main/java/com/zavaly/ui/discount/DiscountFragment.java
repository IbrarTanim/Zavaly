package com.zavaly.ui.discount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zavaly.databinding.DiscountFragmentBinding;

public class DiscountFragment extends Fragment {

    private DiscountViewModel mViewModel;
    private Context context;
    private DiscountFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DiscountFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(DiscountViewModel.class);
        mViewModel.viewModelInit(context);

        return binding.getRoot();
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