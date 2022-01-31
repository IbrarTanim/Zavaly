package com.zavaly.ui.ordersui.orders;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zavaly.adapter.OrdersAdapter;
import com.zavaly.databinding.OrdersFragmentBinding;
import com.zavaly.models.orders.OrdersResponse;
import com.zavaly.utils.ChildClickListener;

public class OrdersFragment extends Fragment {

    private OrdersViewModel mViewModel;
    private Context context;
    private OrdersFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrdersFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(OrdersViewModel.class);

        mViewModel.initViewModel(context);
        mViewModel.getOrdersLiveData().observe(getViewLifecycleOwner(), new Observer<OrdersResponse>() {
            @Override
            public void onChanged(OrdersResponse ordersResponse) {

                if (ordersResponse != null) {

                    if (binding.ordersRv.getVisibility() == View.GONE) {

                        binding.ordersRv.setVisibility(View.VISIBLE);

                    }

                    OrdersAdapter adapter = new OrdersAdapter(context, ordersResponse.getOrders(), new ChildClickListener() {
                        @Override
                        public void onChildClick(View view, int position) {

                        }
                    });

                    LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

                    binding.ordersRv.setLayoutManager(manager);
                    binding.ordersRv.setAdapter(adapter);


                } else {

                    //skip
                    if (binding.noDataIv.getVisibility() == View.GONE) {

                        binding.noDataIv.setVisibility(View.VISIBLE);

                    }

                }

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