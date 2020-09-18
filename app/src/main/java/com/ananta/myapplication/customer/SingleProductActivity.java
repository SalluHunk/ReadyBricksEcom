package com.ananta.myapplication.customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.ReviewListAdapter;
import com.ananta.myapplication.general.MainActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codesgood.views.JustifiedTextView;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.facebook.share.Share;
import com.tfb.fbtoast.FBToast;

import impl.AddToCartPresenterImpl;
import presenter.AddToCartPresenter;
import util.AlaBricks;
import view.AddToCartView;

public class SingleProductActivity extends AppCompatActivity implements AddToCartView {

    private Typeface typefaceBold,typefaceRegular;
    TextView txtProductId,txtProductName,txtManufacturerName,txtRatingTotal,txtDesc,txtAdditionalInfo,txtReview;
    ImageView imgBack,imgProduct;
    RatingBar ratingBar;
    private LinearLayout linDesc,linAdditional;
    Intent intent;
    int id=0;
    private LoadingButton btnAddProductToCart;
    private TextView txtPriceText,txtPrice;
    private JustifiedTextView txtMyDesc,txtMyAdditional;
    AddToCartPresenter addToCartPresenter;
    SharedPreferences sharedPreferences;
    String path="";
    private TextView txtOfferPrice;
    private LinearLayout linReview;
    private ListView lstReview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        addToCartPresenter = new AddToCartPresenterImpl(this);
        intent = getIntent();

        path = intent.getExtras().getString("path");
        id = intent.getIntExtra("clickId",0);

        imgBack = (ImageView)findViewById(R.id.imgBack);
        imgProduct = (ImageView)findViewById(R.id.imgProduct);
        txtProductId = (TextView)findViewById(R.id.txtProductId);
        txtProductName = (TextView)findViewById(R.id.txtProductName);
        txtManufacturerName = (TextView)findViewById(R.id.txtManufacturerName);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        txtRatingTotal = (TextView)findViewById(R.id.txtRatingTotal);
        txtDesc = (TextView)findViewById(R.id.txtDesc);
        txtAdditionalInfo = (TextView)findViewById(R.id.txtAdditionalInfo);
        txtReview = (TextView)findViewById(R.id.txtReview);
        txtMyDesc = (JustifiedTextView)findViewById(R.id.txtMyDesc);
        txtMyAdditional = (JustifiedTextView)findViewById(R.id.txtMyAdditional);
        txtPriceText = (TextView)findViewById(R.id.txtPriceText);
        txtPrice = (TextView)findViewById(R.id.txtPrice);
        btnAddProductToCart = (LoadingButton)findViewById(R.id.btnAddProductToCart);
        txtOfferPrice = (TextView)findViewById(R.id.txtOfferPrice);

        linAdditional = (LinearLayout)findViewById(R.id.linAdditional);
        linDesc = (LinearLayout)findViewById(R.id.linDesc);
        linReview = (LinearLayout)findViewById(R.id.linReview);

        lstReview = (ListView)findViewById(R.id.lstReview);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddProductToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddProductToCart.startLoading();
                if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
                {
                    btnAddProductToCart.loadingFailed();
                    FBToast.errorToast(SingleProductActivity.this,"Please Register or Login at first",FBToast.LENGTH_LONG);
                    Intent intent = new Intent(SingleProductActivity.this, MainActivity.class);
                    intent.putExtra("path","special");
                    startActivityForResult(intent,100);
                }
                else
                {
                    String price="",offer="";
                    if(AlaBricks.customerProductData.get(id).getOfferPrice().equals(""))
                    {
                        price = AlaBricks.customerProductData.get(id).getProductPrice();
                        offer = "0";
                    }
                    else
                    {
                        price = AlaBricks.customerProductData.get(id).getOfferPrice();
                        offer="1";
                    }
                    addToCartPresenter.addToCart(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.customerProductData.get(id).getProductId(),price,"1",offer);
                }

            }
        });

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtPriceText.setTypeface(typefaceRegular);
        txtPrice.setTypeface(typefaceBold);
        btnAddProductToCart.setTypeface(typefaceRegular);
        txtMyDesc.setTypeface(typefaceRegular);

        txtReview.setTypeface(typefaceBold);
        txtAdditionalInfo.setTypeface(typefaceBold);
        txtDesc.setTypeface(typefaceBold);
        txtOfferPrice.setTypeface(typefaceBold);
        txtRatingTotal.setTypeface(typefaceRegular);
        txtManufacturerName.setTypeface(typefaceRegular);
        txtProductId.setTypeface(typefaceRegular);

        txtProductName.setTypeface(typefaceBold);

        linDesc.setVisibility(View.VISIBLE);
        txtDesc.setTextColor(Color.parseColor("#F44343"));
        RequestOptions options = new RequestOptions();
        options.centerCrop();


        if(path.equals("special"))
        {
            Glide.with(SingleProductActivity.this)
                    .load(AlaBricks.imagePath +AlaBricks.singleCustomerProductData.getProductImage()).apply(options).into(imgProduct);


            txtProductId.setText("ID: "+ AlaBricks.singleCustomerProductData.getProductId());
            txtProductName.setText(AlaBricks.singleCustomerProductData.getProductName());
            txtMyDesc.setText(AlaBricks.singleCustomerProductData.getProductDescription());
            txtMyAdditional.setText(AlaBricks.singleCustomerProductData.getProductAdditionalInfo());
            if(AlaBricks.singleCustomerProductData.getOfferPrice().equals(""))
            {
                txtPrice.setText("₹ "+AlaBricks.singleCustomerProductData.getProductPrice());
            }
            else
            {
                txtPrice.setText("₹ "+AlaBricks.singleCustomerProductData.getProductPrice());
                txtOfferPrice.setVisibility(View.VISIBLE);
                txtOfferPrice.setText("₹ "+AlaBricks.singleCustomerProductData.getOfferPrice());
                txtPrice.setPaintFlags(txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtPrice.setTextColor(Color.LTGRAY);
            }
            txtManufacturerName.setText(AlaBricks.singleCustomerProductData.getCompanyName());
            String price="",offer="";
            if(AlaBricks.customerProductData.get(id).getOfferPrice().equals(""))
            {
                price = AlaBricks.customerProductData.get(id).getProductPrice();
                offer = "0";
            }
            else
            {
                price = AlaBricks.customerProductData.get(id).getOfferPrice();
                offer="1";
            }
            addToCartPresenter.addToCart(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.singleCustomerProductData.getProductId(),price,"1",offer);

        }
        else
        {

            Glide.with(SingleProductActivity.this)
                    .load(AlaBricks.imagePath +AlaBricks.customerProductData.get(id).getProductImage()).apply(options).into(imgProduct);


            txtProductId.setText("ID: "+ AlaBricks.customerProductData.get(id).getProductId());
            txtProductName.setText(AlaBricks.customerProductData.get(id).getProductName());
            txtMyDesc.setText(AlaBricks.customerProductData.get(id).getProductDescription());
            txtMyAdditional.setText(AlaBricks.customerProductData.get(id).getProductAdditionalInfo());
            if(AlaBricks.singleCustomerProductData.getOfferPrice().equals(""))
            {
                txtPrice.setText("₹ "+AlaBricks.singleCustomerProductData.getProductPrice());
            }
            else
            {
                txtPrice.setText("₹ "+AlaBricks.singleCustomerProductData.getProductPrice());
                txtOfferPrice.setVisibility(View.VISIBLE);
                txtOfferPrice.setText("₹ "+AlaBricks.singleCustomerProductData.getOfferPrice());
                txtPrice.setPaintFlags(txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtPrice.setTextColor(Color.LTGRAY);
            }
            txtManufacturerName.setText(AlaBricks.customerProductData.get(id).getCompanyName());

        }

        txtDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txtDesc.setTextColor(Color.parseColor("#F44343"));
                txtAdditionalInfo.setTextColor(Color.BLACK);
                txtReview.setTextColor(Color.BLACK);
                linDesc.setVisibility(View.VISIBLE);
                linAdditional.setVisibility(View.GONE);
                linReview.setVisibility(View.GONE);
            }
        });



        txtAdditionalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtAdditionalInfo.setTextColor(Color.parseColor("#F44343"));
                txtDesc.setTextColor(Color.BLACK);
                txtReview.setTextColor(Color.BLACK);
                linDesc.setVisibility(View.GONE);
                linAdditional.setVisibility(View.VISIBLE);
                linReview.setVisibility(View.GONE);
            }
        });

        txtReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtReview.setTextColor(Color.parseColor("#F44343"));
                txtDesc.setTextColor(Color.BLACK);
                txtAdditionalInfo.setTextColor(Color.BLACK);
                linDesc.setVisibility(View.GONE);
                linAdditional.setVisibility(View.GONE);
                linReview.setVisibility(View.VISIBLE);
            }
        });

        if(!AlaBricks.singleCustomerProductData.getAvgRating().equals("0"))
        {
            ReviewListAdapter reviewListAdapter =new ReviewListAdapter(SingleProductActivity.this,AlaBricks.singleCustomerProductData.getReviewData());
            lstReview.setAdapter(reviewListAdapter);
        }


        if(AlaBricks.singleCustomerProductData.getAvgRating().equals("0"))
        {

        }
        else
        {
            txtRatingTotal.setText("("+AlaBricks.singleCustomerProductData.getReviewData().size()+")");
        }
        ratingBar.setRating(Float.parseFloat(AlaBricks.singleCustomerProductData.getAvgRating()));
    }

    @Override
    public void onSuccessAddtoCart() {
        btnAddProductToCart.loadingSuccessful();
        btnAddProductToCart.reset();
        AlaBricks.isProductAdded = true;
        FBToast.successToast(SingleProductActivity.this,"Product Added to Cart",FBToast.LENGTH_LONG);
        if(path.equals("special"))
        {
            Intent intent = new Intent(SingleProductActivity.this,CustomerDashboard.class);
            startActivity(intent);
            finish();
        }
        else
        {
            finish();
        }
    }

    @Override
    public void onFailedAddtoCart() {
        btnAddProductToCart.loadingFailed();
        FBToast.errorToast(SingleProductActivity.this,"Technical Error",FBToast.LENGTH_LONG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
        {
            if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
            {
               FBToast.errorToast(SingleProductActivity.this,"You are not logged in",FBToast.LENGTH_LONG);
            }
            else
            {
                String price="",offer="";
                if(AlaBricks.customerProductData.get(id).getOfferPrice().equals(""))
                {
                    price = AlaBricks.customerProductData.get(id).getProductPrice();
                    offer = "0";
                }
                else
                {
                    price = AlaBricks.customerProductData.get(id).getOfferPrice();
                    offer="1";
                }
                addToCartPresenter.addToCart(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.customerProductData.get(id).getProductId(),price,"1",offer);
            }
        }
    }
}
