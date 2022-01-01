package com.zavaly.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.zavaly.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private FragmentAccountBinding binding;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);


        initialize();

        return binding.getRoot();
    }

    private void initialize() {

        binding.accRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(AccountFragmentDirections.actionNavigationAccountToNavigationCreateAccount());
            }
        });

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