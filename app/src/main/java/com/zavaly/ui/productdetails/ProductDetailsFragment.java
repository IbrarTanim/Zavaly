package com.zavaly.ui.productdetails;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.zavaly.adapter.ImageSliderAdapter;
import com.zavaly.adapter.ProductImagesAdapter;
import com.zavaly.adapter.ProductPriceRecyclerAdapter;
import com.zavaly.constants.BaseApiConstant;
import com.zavaly.databinding.ProductDetailsFragmentBinding;
import com.zavaly.models.pricemodel.PriceDetailsObject;
import com.zavaly.models.productdetails.ProductDetailsResponse;
import com.zavaly.utils.Helper;
import com.zavaly.utils.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ProductDetailsFragment extends Fragment {

    private ProductDetailsViewModel mViewModel;
    private ProductDetailsFragmentBinding binding;
    private Context context;

    private List<String> imageList;
    private ImageSliderAdapter imageSliderAdapter;

    //variables
    private int productId;
    private int cartQuantity = 1;
    private String cartDiscount = "";
    private String cartPrice = "";
    private String cartColor = "";
    private String cartSize = "";
    private String cartModel = "";

    private List<Integer> cartQuantityList = new ArrayList<>();
    private List<Double> cartPriceList = new ArrayList<>();

    private long GUEST_ID;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProductDetailsFragmentBinding.inflate(inflater, container, false);

        GUEST_ID = Helper.generateRandom(12);

        Log.e("***********", String.valueOf(GUEST_ID));

        imageList =
                new ArrayList<>();

        mViewModel =
                new ViewModelProvider(this).get(ProductDetailsViewModel.class);
        mViewModel.viewModelInit(context);

        productId = ProductDetailsFragmentArgs.fromBundle(getArguments()).getProductId();

        initialize();

        Helper.showLoader(context, "");

        mViewModel.getDetails(productId).observe(getViewLifecycleOwner(), new Observer<ProductDetailsResponse>() {
            @Override
            public void onChanged(ProductDetailsResponse productDetailsResponse) {

                if (productDetailsResponse != null) {

                    setUpViews(productDetailsResponse);

                }

            }
        });

        Helper.cancelLoader();

        return binding.getRoot();
    }

    private void initialize() {

        // set quantity to edit text
        binding.etQuantity.setText(String.valueOf(cartQuantity));
        binding.etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!String.valueOf(charSequence).isEmpty()) {
                    cartQuantity = Integer.parseInt(String.valueOf(charSequence));
                } else {
                    cartQuantity = 0;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /**
         * Quantity Minus
         * */
        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cartQuantity > 1) {

                    cartQuantity = cartQuantity - 1;
                    binding.etQuantity.setText(String.valueOf(cartQuantity));

                }

            }
        });

        /**
         * Quantity Plus
         * */
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cartQuantity = cartQuantity + 1;
                binding.etQuantity.setText(String.valueOf(cartQuantity));


            }
        });


        /**
         * Add to cart BTN
         * */
        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateInformation()) {

                    if (!cartQuantityList.isEmpty()) {

                        for (int i = 0; i < cartQuantityList.size(); i++) {

                            if (cartQuantity < cartQuantityList.get(i)) {

                                try {
                                    cartPrice = String.valueOf(cartPriceList.get(i));
                                } catch (Exception e) {

                                }


                            }

                        }

                    }

                    HashMap<String, String> params = new HashMap<>();
                    params.put("product_id", String.valueOf(productId));
                    params.put("price", cartPrice);
                    params.put("quantity", String.valueOf(cartQuantity));
                    params.put("discount", cartDiscount);
                    params.put("size", cartSize);
                    params.put("color", cartColor);
                    params.put("guest_id", String.valueOf(GUEST_ID));

                    Log.e("Cart----", params.toString());

                    mViewModel.addToCart(params);

                }

            }
        });
    }

    private boolean validateInformation() {

        boolean valid = true;

        if (binding.colorDropDownLayout.getVisibility() == View.VISIBLE) {

            cartColor = String.valueOf(binding.colorDropDown.getText());

            if (cartColor.isEmpty()) {

                Toasty.warning(context, "Please select color.").show();
                valid = false;

            }

        }
        if (binding.sizeDropDownLayout.getVisibility() == View.VISIBLE) {

            cartSize = String.valueOf(binding.sizeDropDown.getText());

            if (cartSize.isEmpty()) {

                Toasty.warning(context, "Please select size.").show();
                valid = false;

            }

        }
        if (cartQuantity == 0) {

            Toasty.warning(context, "Please increase quantity.").show();
            valid = false;

        }

        return valid;

    }


    private void setUpViews(ProductDetailsResponse productDetailsResponse) {

        /**
         * Image Load
         * */

        try {
            JSONArray jsonArray = new JSONArray(productDetailsResponse.getProduct().getImage());

            for (int i = 0; i < jsonArray.length(); i++) {

                String imageName = jsonArray.getString(i);
                if (!imageName.isEmpty()) {

                    imageList.add(imageName);

                }

            }

            if (imageList != null) {

                try {


                    String url = BaseApiConstant.IMAGE_FETCH_URL + imageList.get(0);
                    Glide.with(context)
                            .load(url)
                            .into(binding.productImage);


                } catch (Exception e) {
                    //
                }

                ProductImagesAdapter imagesAdapter = new ProductImagesAdapter(context, imageList, 0);
                LinearLayoutManager imageManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                binding.productImageRv.setLayoutManager(imageManager);
                binding.productImageRv.setAdapter(imagesAdapter);

                binding.productImageRv.addOnItemTouchListener(new RecyclerTouchListener(context, binding.productImageRv, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        ProductImagesAdapter imagesAdapter = new ProductImagesAdapter(context, imageList, position);
                        LinearLayoutManager imageManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                        binding.productImageRv.setLayoutManager(imageManager);
                        binding.productImageRv.setAdapter(imagesAdapter);

                        try {
                            String url = BaseApiConstant.IMAGE_FETCH_URL + imageList.get(position);
                            Glide.with(context)
                                    .load(url)
                                    .fitCenter()
                                    .into(binding.productImage);

                        } catch (Exception e) {
                            //
                        }

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * Product name
         * */

        binding.tvProductName.setText(productDetailsResponse.getProduct().getTitle());

        /**
         * Product Price
         * */

        if (productDetailsResponse.getProduct().getCustomQty() != null) {

            List<PriceDetailsObject> priceDetails = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(productDetailsResponse.getProduct().getCustomQty());
                if (array.length() > 0) {

                    for (int i = 0; i < array.length(); i++) {

                        String productQuantity = array.getString(i);
                        Log.e("productQuantity", productQuantity);
                        cartQuantityList.add(Integer.parseInt(productQuantity));
                        JSONArray priceArray = new JSONArray(productDetailsResponse.getProduct().getCustomQtyPrice());
                        String productPrice = priceArray.getString(i);
                        cartPriceList.add(Double.parseDouble(productPrice));
                        Log.e("productPrice", productPrice);

                        PriceDetailsObject priceDetailsObject;
                        if (productDetailsResponse.getProduct().getDisprice() != null || !productDetailsResponse.getProduct().getDisprice().equals("0")) {

                            priceDetailsObject = new PriceDetailsObject(productQuantity, String.valueOf(productDetailsResponse.getProduct().getDisprice()), productPrice);

                        } else {

                            priceDetailsObject = new PriceDetailsObject(productQuantity, null, productPrice);

                        }
                        priceDetails.add(priceDetailsObject);

                    }

                }
            } catch (JSONException e) {
                Log.e("JSON Exception", e.getMessage());
            }

            if (!priceDetails.isEmpty()) {

                //cartPrice = priceDetails.get(0).getQuantityPrice();
                if (priceDetails.get(0).getQuantityDisPrice() != null) {
                    cartDiscount = priceDetails.get(0).getQuantityDisPrice();
                }
                ProductPriceRecyclerAdapter adapter = new ProductPriceRecyclerAdapter(context, priceDetails);
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                binding.productPriceRv.setLayoutManager(manager);
                binding.productPriceRv.setAdapter(adapter);

            }


        } else {

            if (productDetailsResponse.getProduct().getPrice() != null) {

                List<PriceDetailsObject> priceList = new ArrayList<>();
                String price = productDetailsResponse.getProduct().getPrice();

                PriceDetailsObject priceDetailsObject;
                if (productDetailsResponse.getProduct().getDisprice() != null && Double.parseDouble(String.valueOf(productDetailsResponse.getProduct().getDisprice())) != 0) {

                    priceDetailsObject = new PriceDetailsObject("1", String.valueOf(productDetailsResponse.getProduct().getDisprice()), price);

                } else {

                    priceDetailsObject = new PriceDetailsObject("1", null, price);

                }
                priceList.add(priceDetailsObject);

                if (!priceList.isEmpty()) {

                    cartPrice = priceList.get(0).getQuantityPrice();
                    if (priceList.get(0).getQuantityDisPrice() != null) {
                        cartDiscount = priceList.get(0).getQuantityDisPrice();
                    }
                    ProductPriceRecyclerAdapter adapter = new ProductPriceRecyclerAdapter(context, priceList);
                    LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                    binding.productPriceRv.setLayoutManager(manager);
                    binding.productPriceRv.setAdapter(adapter);

                }

            }

        }


        /**
         * Product color
         * */

        JSONArray colorsArray = null;
        try {
            colorsArray = new JSONArray(productDetailsResponse.getProduct().getColor());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (colorsArray.length() > 0) {

            if (binding.colorDropDownLayout.getVisibility() == View.GONE) {

                binding.colorDropDownLayout.setVisibility(View.VISIBLE);

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

            binding.colorDropDown.setAdapter(colorsAdapter);

        } else {

            if (binding.colorDropDownLayout.getVisibility() == View.VISIBLE) {

                binding.colorDropDownLayout.setVisibility(View.GONE);

            }

        }


        /**
         * Product Size
         * */

        JSONArray sizeArray = null;
        try {
            sizeArray = new JSONArray(productDetailsResponse.getProduct().getSize());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sizeArray.length() > 0) {

            if (binding.sizeDropDownLayout.getVisibility() == View.GONE) {

                binding.sizeDropDownLayout.setVisibility(View.VISIBLE);

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
            binding.sizeDropDown.setAdapter(sizesAdapter);

        } else {

            if (binding.sizeDropDownLayout.getVisibility() == View.VISIBLE) {

                binding.sizeDropDownLayout.setVisibility(View.GONE);

            }

        }


        /**
         * Product Model
         * */

        JSONArray modelArray = null;
        try {
            modelArray = new JSONArray(productDetailsResponse.getProduct().getModel());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (modelArray.length() > 0) {

            if (binding.modelsDropDownLayout.getVisibility() == View.GONE) {

                binding.modelsDropDownLayout.setVisibility(View.VISIBLE);

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
            binding.modelsDropDown.setAdapter(modelsAdapter);

        } else {

            if (binding.modelsDropDownLayout.getVisibility() == View.VISIBLE) {

                binding.modelsDropDownLayout.setVisibility(View.GONE);

            }

        }

        if (sizeArray.length() < 1 && colorsArray.length() < 1 && modelArray.length() < 1) {

            if (binding.variantLayout.getVisibility() == View.VISIBLE) {

                binding.variantLayout.setVisibility(View.GONE);

            }

        } else {

            if (binding.variantLayout.getVisibility() == View.GONE) {

                binding.variantLayout.setVisibility(View.VISIBLE);

            }

        }

        /**
         * Product Details
         * */

        if (productDetailsResponse.getProduct().getDetails() != null) {

            if (binding.detailsLayout.getVisibility() == View.GONE) {

                binding.detailsLayout.setVisibility(View.VISIBLE);

            }

            binding.tvDescription.setText(String.valueOf(productDetailsResponse.getProduct().getDetails()));

        }

    }


    /*private void setUpImageSlider(List<String> listImages) {

        SliderView sliderView = binding.imageSlider;
        imageSliderAdapter = new ImageSliderAdapter(context, listImages, String.valueOf(ZavalyEnums.SLIDER_PRODUCT));
        sliderView.setSliderAdapter(imageSliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(2); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }*/

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