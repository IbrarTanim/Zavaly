package com.zavaly.ui.mainui.shops;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zavaly.adapter.ShopsRecyclerAdapter;
import com.zavaly.databinding.ShopsFragmentBinding;
import com.zavaly.models.shopsresponse.Datum;
import com.zavaly.utils.Helper;
import com.zavaly.utils.RecyclerTouchListener;

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

        mViewModel.getPagedShops(context);

        Helper.showLoader(context, "");

        mViewModel.getPagedShopsLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {

                if (data != null) {

                    ShopsRecyclerAdapter adapter = new ShopsRecyclerAdapter(context);
                    adapter.submitList(data);
                    LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    binding.shopsRv.setLayoutManager(manager);
                    binding.shopsRv.setAdapter(adapter);

                    binding.shopsRv.addOnItemTouchListener(new RecyclerTouchListener(context, binding.shopsRv, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {


                        }

                        @Override
                        public void onLongClick(View view, int position) {

                        }
                    }));

                    Helper.cancelLoader();

                }

            }
        });

        Helper.cancelLoader();
        //mViewModel.getAllShops(binding.shopsRv);

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