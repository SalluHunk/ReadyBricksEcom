package com.ananta.myapplication.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import impl.OffersPresenterImpl;
import ir.apend.slider.model.Slide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.CustomGridProducts;
import com.ananta.myapplication.adapter.HomePagerAdapter;
import com.ananta.myapplication.general.MainActivity;
import com.tfb.fbtoast.FBToast;
import com.tiagosantos.enchantedviewpager.EnchantedViewPager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import impl.AddToCartPresenterImpl;
import impl.CustomerListProductPresenterImpl;
import ir.apend.slider.ui.Slider;
import items.BuyButtonCallBack;
import network.CustomerProductData;
import network.OffersData;
import network.OffersResponse;
import presenter.CustomerListProductPresenter;
import presenter.OffersPresenter;
import util.AlaBricks;
import util.ExpandableHeightGridView;
import view.AddToCartView;
import view.CustomerListProductView;
import view.OffersView;

public class ProdcutFragment extends Fragment implements CustomerListProductView, BuyButtonCallBack, AddToCartView, OffersView {

    private TextView txtOffers,txtResults,txtFilter;
    private Typeface typefaceBold,typefaceRegular;
    private GridView grid;
    CustomerListProductPresenter customerListProductPresenter;
    LinearLayout linFilter;
    private AddToCartPresenterImpl addToCartPresenter;
    private SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    private EnchantedViewPager enchantedViewPager;
    OffersPresenter offersPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);


        offersPresenter = new OffersPresenterImpl(this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);



        enchantedViewPager = view.findViewById(R.id.viewPager_home);
        enchantedViewPager.useAlpha();
        enchantedViewPager.useScale();



        addToCartPresenter = new AddToCartPresenterImpl(this);
        sharedPreferences = getContext().getSharedPreferences(AlaBricks.sharedName,Context.MODE_PRIVATE);
        txtOffers = (TextView)view.findViewById(R.id.txtOffers);
        txtResults = (TextView)view.findViewById(R.id.txtResults);
        txtFilter = (TextView)view.findViewById(R.id.txtFilter);
        grid = (GridView)view.findViewById(R.id.grid);
        linFilter = (LinearLayout)view.findViewById(R.id.linFilter);
      /*  grid.setExpanded(true);
      */  typefaceBold = Typeface.createFromAsset(getContext().getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getContext().getAssets(),"regularfont.otf");

        txtFilter.setTypeface(typefaceBold);
        txtResults.setTypeface(typefaceBold);
        txtOffers.setTypeface(typefaceBold);

        linFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),FilterActivity.class);
                startActivity(intent);
            }
        });

        offersPresenter.listBanners();

        return view;
    }

    @Override
    public void onResume() {
        grid.setAdapter(null);
        customerListProductPresenter = new CustomerListProductPresenterImpl(this);
        customerListProductPresenter.listProducts();

        super.onResume();
    }

    @Override
    public void onSuccessCustomerProductList(ArrayList<CustomerProductData> customerProductData) {
        AlaBricks.customerProductData = customerProductData;
        CustomGridProducts customGridProducts = new CustomGridProducts(getContext(),AlaBricks.customerProductData,this);
        grid.setAdapter(customGridProducts);
        setDynamicHeight(grid);
    }

    @Override
    public void onFailedCustomerProductList() {
        FBToast.errorToast(getContext(),"No Products Found",FBToast.LENGTH_LONG);
    }

    @Override
    public void onClickBuy(String itemId, String price, String qty,String offer) {
        if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
        {
            FBToast.errorToast(getContext(),"Please Register or Login at first",FBToast.LENGTH_LONG);
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("path","normal2");
            startActivity(intent);
        }
        else
        {
            progressDialog.show();
            addToCartPresenter.addToCart(sharedPreferences.getString(AlaBricks.sharedUserId,""),itemId,price,qty,offer);
        }
    }

    @Override
    public void onSuccessAddtoCart() {
        progressDialog.dismiss();
        FBToast.successToast(getContext(),"Product Added to Cart",FBToast.LENGTH_LONG);
    }

    @Override
    public void onFailedAddtoCart() {
        progressDialog.dismiss();
        FBToast.errorToast(getContext(),"Technical Error while adding product to the cart",FBToast.LENGTH_LONG);
    }

    @Override
    public void onSuccessOffersView(OffersResponse offersResponse) {

        if (offersResponse.getOffersData().size()>0)
        {
            HomePagerAdapter homePagerAdapter  = new HomePagerAdapter(getContext(),offersResponse.getOffersData());
            homePagerAdapter.enableCarrousel();
            enchantedViewPager.setAdapter(homePagerAdapter);

        }
    }

    @Override
    public void onFailedOffersView() {

    }

    private void setDynamicHeight(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = gridViewAdapter.getCount();
        int rows = 0;

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > 2 && items%2==0){
            x = items/2;
            rows = (int) (x);
            totalHeight *= rows;
        }
        if( items > 2 && items%2!=0){
            x = items/2;
            rows = (int) (x+1);
            totalHeight *= rows;
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight+60;
        gridView.setLayoutParams(params);
    }
}
