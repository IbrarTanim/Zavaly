package com.zavaly.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.zavaly.R;
import com.zavaly.adapter.HomeAllCategoriesRecyclerAdapter;
import com.zavaly.adapter.ImageSliderAdapter;
import com.zavaly.adapter.MenuRecyclerAdapter;
import com.zavaly.databinding.FragmentHomeBinding;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.MenuModelClass;
import com.zavaly.models.allcategorydetails.Datum;
import com.zavaly.utils.Helper;
import com.zavaly.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ImageSliderAdapter imageSliderAdapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        homeViewModel.viewModelInit(context);
        homeViewModel.getSliders().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                setUpImageSlider(strings);

            }
        });

        //homeViewModel.getAllProducts(binding.homeAllProductsRv);
        Helper.showLoader(context, "");
        homeViewModel.getAllCategory(context);
        homeViewModel.getCategoryPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {

                HomeAllCategoriesRecyclerAdapter adapter = new HomeAllCategoriesRecyclerAdapter(context);
                adapter.submitList(data);
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                binding.homeAllProductsRv.setLayoutManager(manager);
                binding.homeAllProductsRv.setAdapter(adapter);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Helper.cancelLoader();
                    }
                }, 2000);


            }
        });

        setUpMenu();

        return root;
    }

    private void setUpMenu() {

        List<MenuModelClass> menuModelClassList = new ArrayList<>();
        MenuModelClass model1 = new MenuModelClass(1, "Categories", R.drawable.categories_32);
        MenuModelClass model2 = new MenuModelClass(2, "Shops", R.drawable.online_shop_32);
        MenuModelClass model3 = new MenuModelClass(3, "Agents", R.drawable.seller_32);
        MenuModelClass model4 = new MenuModelClass(4, "Campaign", R.drawable.bullhorn_32);
        MenuModelClass model5 = new MenuModelClass(5, "Discount", R.drawable.discounts);
        menuModelClassList.add(model1);
        menuModelClassList.add(model2);
        menuModelClassList.add(model3);
        menuModelClassList.add(model4);
        menuModelClassList.add(model5);

        MenuRecyclerAdapter menuRecyclerAdapter = new MenuRecyclerAdapter(context, menuModelClassList);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        binding.homeMenusRv.setLayoutManager(manager);
        binding.homeMenusRv.setAdapter(menuRecyclerAdapter);

        binding.homeMenusRv.addOnItemTouchListener(new RecyclerTouchListener(context, binding.homeMenusRv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                int menuId = menuModelClassList.get(position).getMenuId();

                switch (menuId) {

                    case 1:
                        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavigationHomeToNavigationAllCategories());
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    private void setUpImageSlider(List<String> listImages) {

        SliderView sliderView = binding.imageSlider;
        imageSliderAdapter = new ImageSliderAdapter(context, listImages, String.valueOf(ZavalyEnums.SLIDER_NORMAL));
        sliderView.setSliderAdapter(imageSliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(2); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}