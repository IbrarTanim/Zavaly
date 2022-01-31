package com.zavaly.ui.mainui.discount;

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
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import com.zavaly.adapter.DiscountRecyclerAdapter;
import com.zavaly.databinding.DiscountFragmentBinding;
import com.zavaly.models.discountproducts.Datum;
import com.zavaly.utils.Helper;

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

        Helper.showLoader(context, "");


        mViewModel.getDiscountList(context);
        mViewModel.getListLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> products) {

                if (products != null) {

                    DiscountRecyclerAdapter adapter = new DiscountRecyclerAdapter(context);

                    adapter.submitList(products);

                    GridLayoutManager manager = new GridLayoutManager(context, 2);

                    binding.discountRv.setLayoutManager(manager);

                    binding.discountRv.setAdapter(adapter);

                    Helper.cancelLoader();

                }

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