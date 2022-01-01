package com.zavaly.ui.allcategories;

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
import androidx.recyclerview.widget.GridLayoutManager;

import com.zavaly.adapter.CategoryListAdapter;
import com.zavaly.databinding.AllCategoriesFragmentBinding;
import com.zavaly.models.Cat;

import java.util.List;

public class AllCategoriesFragment extends Fragment {

    private AllCategoriesViewModel mViewModel;
    private AllCategoriesFragmentBinding binding;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AllCategoriesFragmentBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(AllCategoriesViewModel.class);
        mViewModel.viewModelInit(context);
        mViewModel.getAllCat().observe(getViewLifecycleOwner(), new Observer<List<Cat>>() {
            @Override
            public void onChanged(List<Cat> cats) {

                CategoryListAdapter categoryListAdapter = new CategoryListAdapter(context, cats);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                binding.allCategoriesRv.setLayoutManager(gridLayoutManager);
                binding.allCategoriesRv.setAdapter(categoryListAdapter);

            }
        });

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