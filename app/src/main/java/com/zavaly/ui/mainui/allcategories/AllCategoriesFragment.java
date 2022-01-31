package com.zavaly.ui.mainui.allcategories;

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
import androidx.recyclerview.widget.GridLayoutManager;

import com.zavaly.adapter.CategoryListAdapter;
import com.zavaly.databinding.AllCategoriesFragmentBinding;
import com.zavaly.models.Cat;
import com.zavaly.utils.Helper;
import com.zavaly.utils.RecyclerTouchListener;

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

        Helper.showLoader(context, "");
        mViewModel.getAllCat().observe(getViewLifecycleOwner(), new Observer<List<Cat>>() {
            @Override
            public void onChanged(List<Cat> cats) {

                CategoryListAdapter categoryListAdapter = new CategoryListAdapter(context, cats);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                binding.allCategoriesRv.setLayoutManager(gridLayoutManager);
                binding.allCategoriesRv.setAdapter(categoryListAdapter);

                binding.allCategoriesRv.addOnItemTouchListener(new RecyclerTouchListener(context, binding.allCategoriesRv, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        int catId = cats.get(position).getId();
                        Navigation.findNavController(view).navigate(AllCategoriesFragmentDirections.actionNavigationAllCategoriesToNavigationViewCategoryProducts(catId));

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

                Helper.cancelLoader();

            }
        });

        Helper.cancelLoader();

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