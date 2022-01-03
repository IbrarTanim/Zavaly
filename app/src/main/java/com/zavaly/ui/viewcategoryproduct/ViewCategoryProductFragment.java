package com.zavaly.ui.viewcategoryproduct;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zavaly.databinding.ViewCategoryProductFragmentBinding;

public class ViewCategoryProductFragment extends Fragment {

    private ViewCategoryProductViewModel mViewModel;
    private ViewCategoryProductFragmentBinding binding;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =
                ViewCategoryProductFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(ViewCategoryProductViewModel.class);

        mViewModel.viewModelInit(context);

        int categoryId = ViewCategoryProductFragmentArgs.fromBundle(getArguments()).getCategoryId();

        mViewModel.getCategoryProducts(categoryId, binding.subCategoryListRv, binding.mainCategoryProductsRv);

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