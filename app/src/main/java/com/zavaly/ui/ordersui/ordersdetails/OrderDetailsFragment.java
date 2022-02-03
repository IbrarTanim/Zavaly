package com.zavaly.ui.ordersui.ordersdetails;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zavaly.adapter.OrderDetailsAdapter;
import com.zavaly.constants.NetworkConstants;
import com.zavaly.databinding.OrderDetailsFragmentBinding;
import com.zavaly.models.ordersdetails.OrderDetailsResponse;
import com.zavaly.utils.Helper;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.dmoral.toasty.Toasty;

public class OrderDetailsFragment extends Fragment {

    private OrderDetailsFragmentBinding binding;
    private OrderDetailsViewModel viewModel;
    private Context context;
    private ExecutorService executor;
    private Handler handler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = OrderDetailsFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(OrderDetailsViewModel.class);

        /**
         * Creating Background Thread
         * Getting Data On Background
         * */
        String orderCode = OrderDetailsFragmentArgs.fromBundle(getArguments()).getOrderCode();

        executor = Executors.newFixedThreadPool(3);
        handler = new Handler(Looper.getMainLooper());
        Helper.showLoader(context, "");

        executor.execute(new Runnable() {
            @Override
            public void run() {

                viewModel.initViewModel(context, orderCode);


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (executor.isShutdown()) {
                            Log.e("Thread", "run: shutdown");
                        } else {
                            executor.shutdown();
                            Log.e("Thread", "run: not shutdown");
                        }

                        viewModel.getResponseLiveData().observe(getViewLifecycleOwner(), new Observer<OrderDetailsResponse>() {
                            @Override
                            public void onChanged(OrderDetailsResponse orderDetailsResponse) {

                                if (orderDetailsResponse != null) {

                                    if (binding.noDataIv.getVisibility() == View.VISIBLE) {
                                        binding.noDataIv.setVisibility(View.GONE);
                                    }

                                    if (binding.mainLayout.getVisibility() == View.GONE) {
                                        binding.mainLayout.setVisibility(View.VISIBLE);
                                    }

                                    try {
                                        binding.deliveryDetailsAddressTextview.setText(orderDetailsResponse.getOrder().getShippingAddress());
                                    } catch (Exception e) {
                                        //skip
                                    }
                                    try {
                                        binding.subTotal.setText(String.valueOf(orderDetailsResponse.getOrder().getSubtotal()));
                                    } catch (Exception e) {
                                        //skip
                                    }
                                    try {
                                        binding.discount.setText(String.valueOf(orderDetailsResponse.getOrder().getTotalDiscount()));
                                    } catch (Exception e) {
                                        //skip
                                    }
                                    try {
                                        binding.total.setText(String.valueOf(orderDetailsResponse.getOrder().getTotal()));
                                    } catch (Exception e) {
                                        //skip
                                    }

                                    if (orderDetailsResponse.getOrderDetails() != null || !orderDetailsResponse.getOrderDetails().isEmpty()) {

                                        OrderDetailsAdapter adapter = new OrderDetailsAdapter(context, orderDetailsResponse.getOrderDetails());
                                        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                                        binding.detailsProductRv.setLayoutManager(manager);
                                        binding.detailsProductRv.setAdapter(adapter);

                                    }

                                }

                            }
                        });

                        viewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                            @Override
                            public void onChanged(Integer integer) {

                                if (Objects.equals(integer, NetworkConstants.noInternet)) {

                                    if (binding.noDataIv.getVisibility() == View.GONE) {
                                        binding.noDataIv.setVisibility(View.VISIBLE);
                                    }
                                    Toasty.warning(context, NetworkConstants.warningNoInternet).show();
                                    Helper.cancelLoader();

                                } else if (Objects.equals(integer, NetworkConstants.notAuthorized)) {

                                    if (binding.noDataIv.getVisibility() == View.GONE) {
                                        binding.noDataIv.setVisibility(View.VISIBLE);
                                    }
                                    Toasty.warning(context, NetworkConstants.warningNotAuthorized).show();
                                    Helper.cancelLoader();

                                } else if (Objects.equals(integer, NetworkConstants.serverConnectionFailed)) {

                                    if (binding.noDataIv.getVisibility() == View.GONE) {
                                        binding.noDataIv.setVisibility(View.VISIBLE);
                                    }
                                    Toasty.warning(context, NetworkConstants.warningSCF).show();
                                    Helper.cancelLoader();

                                } else if (Objects.equals(integer, NetworkConstants.noData)) {

                                    if (binding.noDataIv.getVisibility() == View.GONE) {
                                        binding.noDataIv.setVisibility(View.VISIBLE);
                                    }
                                    Toasty.warning(context, NetworkConstants.warningND).show();
                                    Helper.cancelLoader();

                                }

                            }
                        });

                    }
                });

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

        if (executor.isShutdown()) {
            Log.e("Thread", "run: shutdown");
        } else {
            executor.shutdown();
            Log.e("Thread", "run: not shutdown");
        }
    }
}