package com.zavaly.ui.cart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.button.MaterialButton;
import com.zavaly.R;
import com.zavaly.adapter.CartViewRecyclerAdapter;
import com.zavaly.databinding.FragmentCartBinding;
import com.zavaly.models.Cart;
import com.zavaly.utils.ChildClickListener;
import com.zavaly.utils.Helper;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    private FragmentCartBinding binding;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);

        cartViewModel.initViewModel(context);

        Helper.showLoader(context, "");

        cartViewModel.getCartDetails().observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {

                if (!carts.isEmpty()) {

                    CartViewRecyclerAdapter adapter = new CartViewRecyclerAdapter(context, carts, new ChildClickListener() {
                        @Override
                        public void onChildClick(View view, int position) {

                            Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog_layout);
                            TextView warningText = dialog.findViewById(R.id.warning_text);
                            warningText.setText("Are you sure to remove?");
                            MaterialButton noBTN = dialog.findViewById(R.id.no_btn);
                            MaterialButton yesBTN = dialog.findViewById(R.id.yes_btn);


                            noBTN.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    dialog.dismiss();

                                }
                            });

                            yesBTN.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    CartRepository cartRepository = new CartRepository(context);
                                    cartRepository.deleteCartItem(String.valueOf(carts.get(position).getProductId()));

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            int responseCode = cartRepository.getRESPONSE_CODE();
                                            if (responseCode == 200) {

                                                Navigation.findNavController(view).navigate(CartFragmentDirections.actionNavigationCartSelf());
                                                dialog.dismiss();

                                            } else {

                                                Toasty.warning(context, "Remove failed.").show();
                                                dialog.dismiss();

                                            }

                                        }
                                    }, 1000);


                                }
                            });
                            dialog.show();

                        }
                    });
                    LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    binding.cartRv.setLayoutManager(manager);
                    binding.cartRv.setAdapter(adapter);

                    if (binding.cartRv.getVisibility() == View.GONE) {

                        binding.cartRv.setVisibility(View.VISIBLE);

                    }

                    setUpBottomLayout(carts);

                    if (binding.bottomLayout.getVisibility() == View.GONE) {

                        binding.bottomLayout.setVisibility(View.VISIBLE);

                    }

                } else {

                    Toasty.warning(context, "Cart is empty").show();

                }

            }
        });

        Helper.cancelLoader();


        return binding.getRoot();
    }

    private void setUpBottomLayout(List<Cart> carts) {

        double totalSubTotal = 0.0;
        double totalDiscount = 0.0;
        double totalGrandTotal = 0.0;

        for (Cart cart : carts) {

            double subTotal = Double.parseDouble(String.valueOf(cart.getSubtotal()));
            double total = Double.parseDouble(String.valueOf(cart.getTotal()));
            double discount = Double.parseDouble(String.valueOf(cart.getDiscount()));

            totalSubTotal = totalSubTotal + subTotal;
            totalGrandTotal = totalGrandTotal + total;
            totalDiscount = totalDiscount + discount;

        }

        binding.tvSubTotal.setText("Tk. " + totalSubTotal);
        binding.tvDiscount.setText("Tk. " + totalDiscount);
        binding.tvGrandTotal.setText("Tk. " + totalGrandTotal);

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