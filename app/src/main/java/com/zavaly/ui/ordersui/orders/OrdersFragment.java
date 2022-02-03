package com.zavaly.ui.ordersui.orders;

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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zavaly.adapter.OrdersAdapter;
import com.zavaly.constants.NetworkConstants;
import com.zavaly.databinding.OrdersFragmentBinding;
import com.zavaly.models.orders.OrdersResponse;
import com.zavaly.utils.ChildClickListener;
import com.zavaly.utils.Helper;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.dmoral.toasty.Toasty;

public class OrdersFragment extends Fragment {

    private OrdersViewModel mViewModel;
    private Context context;
    private OrdersFragmentBinding binding;
    private ExecutorService executor;
    private Handler handler;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrdersFragmentBinding.inflate(inflater, container, false);

        mViewModel =
                new ViewModelProvider(this).get(OrdersViewModel.class);

        /**
         *
         * set up executor
         * handler
         *
         * */
        executor = Executors.newFixedThreadPool(3);
        handler = new Handler(Looper.getMainLooper());

        Helper.showLoader(context, "");
        executor.execute(new Runnable() {
            @Override
            public void run() {

                mViewModel.initViewModel(context);

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (executor.isShutdown()) {
                            Log.e("Thread", "run: shutdown");
                        } else {
                            executor.shutdown();
                            Log.e("Thread", "run: not shutdown");
                        }

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

                                            String orderCode = ordersResponse.getOrders().get(position).getOrderCode();
                                            NavHostFragment.findNavController(OrdersFragment.this).navigate(OrdersFragmentDirections.actionNavigationOrdersToNavigationOrderDetails(orderCode));

                                        }
                                    });

                                    LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

                                    binding.ordersRv.setLayoutManager(manager);
                                    binding.ordersRv.setAdapter(adapter);
                                    Helper.cancelLoader();


                                }

                            }
                        });


                        mViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
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