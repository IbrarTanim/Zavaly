package com.zavaly.ui.ordersui.ordersdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zavaly.databinding.OrderDetailsFragmentBinding;

public class OrderDetailsFragment extends Fragment {

    private OrderDetailsFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = OrderDetailsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}