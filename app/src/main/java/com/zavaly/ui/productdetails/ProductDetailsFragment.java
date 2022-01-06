package com.zavaly.ui.productdetails;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.zavaly.adapter.ImageSliderAdapter;
import com.zavaly.databinding.ProductDetailsFragmentBinding;
import com.zavaly.models.productdetails.ProductDetailsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsFragment extends Fragment {

    private ProductDetailsViewModel mViewModel;
    private ProductDetailsFragmentBinding binding;
    private Context context;

    private List<String> imageList;
    private ImageSliderAdapter imageSliderAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProductDetailsFragmentBinding.inflate(inflater, container, false);

        imageList =
                new ArrayList<>();

        mViewModel =
                new ViewModelProvider(this).get(ProductDetailsViewModel.class);
        mViewModel.viewModelInit(context);

        int productId = ProductDetailsFragmentArgs.fromBundle(getArguments()).getProductId();
        mViewModel.getDetails(productId).observe(getViewLifecycleOwner(), new Observer<ProductDetailsResponse>() {
            @Override
            public void onChanged(ProductDetailsResponse productDetailsResponse) {

                if (productDetailsResponse != null) {

                    try {
                        JSONArray jsonArray = new JSONArray(productDetailsResponse.getProduct().getImage());

                        for (int i = 0; i < jsonArray.length(); i++) {

                            String imageName = jsonArray.getString(i);
                            if (!imageName.isEmpty()) {

                                imageList.add(imageName);

                            }

                        }
                        setUpImageSlider(imageList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    binding.tvProductName.setText(productDetailsResponse.getProduct().getTitle());
                    binding.tvProductPrice.setText(productDetailsResponse.getProduct().getPrice());

                    JSONArray colorsArray = null;
                    try {
                        colorsArray = new JSONArray(productDetailsResponse.getProduct().getColor());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (colorsArray.length() >= 0) {

                        if (binding.colorDropDown.getVisibility() == View.GONE) {

                            binding.colorDropDown.setVisibility(View.VISIBLE);

                        }

                        List<String> colors = new ArrayList<>();
                        for (int c = 0; c < colorsArray.length(); c++) {

                            try {
                                JSONObject object = new JSONObject(colorsArray.getString(c));
                                colors.add(object.getString("name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ArrayAdapter<String> colorsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, colors);
                        //districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        binding.colorDropDown.setAdapter(colorsAdapter);

                    } else {

                        if (binding.colorDropDown.getVisibility() == View.VISIBLE) {

                            binding.colorDropDown.setVisibility(View.GONE);

                        }

                    }


                    JSONArray sizeArray = null;
                    try {
                        sizeArray = new JSONArray(productDetailsResponse.getProduct().getSize());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (sizeArray.length() >= 0) {

                        if (binding.sizeDropDown.getVisibility() == View.GONE) {

                            binding.sizeDropDown.setVisibility(View.VISIBLE);

                        }

                        List<String> sizes = new ArrayList<>();
                        for (int s = 0; s < sizeArray.length(); s++) {

                            try {
                                sizes.add(sizeArray.getString(s));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ArrayAdapter<String> sizesAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, sizes);
                        //districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        binding.sizeDropDown.setAdapter(sizesAdapter);

                    } else {

                        if (binding.sizeDropDown.getVisibility() == View.VISIBLE) {

                            binding.sizeDropDown.setVisibility(View.GONE);

                        }

                    }


                    JSONArray modelArray = null;
                    try {
                        modelArray = new JSONArray(productDetailsResponse.getProduct().getModel());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (modelArray.length() >= 0) {

                        if (binding.modelsDropDown.getVisibility() == View.GONE) {

                            binding.modelsDropDown.setVisibility(View.VISIBLE);

                        }

                        List<String> models = new ArrayList<>();
                        for (int m = 0; m < modelArray.length(); m++) {

                            try {
                                JSONObject object = new JSONObject(modelArray.getString(m));
                                models.add(object.getString("name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        ArrayAdapter<String> modelsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, models);
                        //districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        binding.modelsDropDown.setAdapter(modelsAdapter);

                    } else {

                        if (binding.modelsDropDown.getVisibility() == View.VISIBLE) {

                            binding.modelsDropDown.setVisibility(View.GONE);

                        }

                    }

                }

            }
        });

        return binding.getRoot();
    }

    private void setUpImageSlider(List<String> listImages) {

        SliderView sliderView = binding.imageSlider;
        imageSliderAdapter = new ImageSliderAdapter(listImages, context);
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