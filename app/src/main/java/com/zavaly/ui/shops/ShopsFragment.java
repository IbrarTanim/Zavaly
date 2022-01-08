package com.zavaly.ui.shops;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zavaly.databinding.ShopsFragmentBinding;

public class ShopsFragment extends Fragment {

    private ShopsViewModel mViewModel;
    private Context context;
    private ShopsFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ShopsFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(ShopsViewModel.class);
        mViewModel.viewModelInit(context);

        mViewModel.getAllShops(binding.shopsRv);

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