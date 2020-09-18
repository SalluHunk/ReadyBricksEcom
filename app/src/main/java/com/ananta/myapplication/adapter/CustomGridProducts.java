package com.ananta.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.SingleProductActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import items.BuyButtonCallBack;
import network.CustomerProductData;
import util.AlaBricks;

public class CustomGridProducts extends BaseAdapter {
    Context context;
    ArrayList<CustomerProductData> customerProductDataArrayList;
    private Typeface typefaceRegular,typefaceBold;
    BuyButtonCallBack buyButtonCallBack;
    public CustomGridProducts(Context context, ArrayList<CustomerProductData> customerProductData,BuyButtonCallBack buyButtonCallBack)
    {
        this.context = context;
        this.customerProductDataArrayList = customerProductData;
        this.buyButtonCallBack = buyButtonCallBack;
    }
    @Override
    public int getCount() {
        return customerProductDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View grid;
        if(convertView==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = layoutInflater.inflate(R.layout.custom_grid,null);
        }
        else
        {
            grid = convertView;
        }
        ImageView imageView = (ImageView)grid.findViewById(R.id.imgProduct);
        TextView txtView = (TextView)grid.findViewById(R.id.txtProductName);
        TextView txtManufacturerName = (TextView)grid.findViewById(R.id.txtManufacturerName);
        RatingBar ratingBar = (RatingBar)grid.findViewById(R.id.ratingBar);
        TextView txtRatingTotal = (TextView)grid.findViewById(R.id.txtRatingTotal);
        TextView txtPrice = (TextView)grid.findViewById(R.id.txtPrice);
        Button btnBuy = (Button)grid.findViewById(R.id.btnBuy);
        LinearLayout linOffer = (LinearLayout)grid.findViewById(R.id.linOffer);
        TextView txtOffer = (TextView)grid.findViewById(R.id.txtOffer);

        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");

        txtView.setTypeface(typefaceBold);
        txtManufacturerName.setTypeface(typefaceRegular);
        txtRatingTotal.setTypeface(typefaceRegular);
        txtPrice.setTypeface(typefaceBold);
        btnBuy.setTypeface(typefaceRegular);
        txtPrice.setTypeface(typefaceRegular);
        if(customerProductDataArrayList.get(position).getAvgRating().equals("0"))
        {

        }
        else
        {
            txtRatingTotal.setText("("+customerProductDataArrayList.get(position).getReviewData().size()+")");
        }
        ratingBar.setRating(Float.parseFloat(customerProductDataArrayList.get(position).getAvgRating()));
        txtView.setText(customerProductDataArrayList.get(position).getProductName());
        txtManufacturerName.setText(customerProductDataArrayList.get(position).getCompanyName());
        txtPrice.setText("₹ "+customerProductDataArrayList.get(position).getProductPrice()+"/ Bricks");
        RequestOptions options = new RequestOptions();
        options.centerCrop();

        if(!customerProductDataArrayList.get(position).getOfferPrice().equals(""))
        {
            float off = (Float.parseFloat(customerProductDataArrayList.get(position).getOfferPrice())*100)/ Float.parseFloat(customerProductDataArrayList.get(position).getProductPrice());
            float finaoff  = 100 - off;
            txtPrice.setText("₹ "+customerProductDataArrayList.get(position).getOfferPrice()+"/ Bricks");
            String of = String.format("%.0f", finaoff);
            txtOffer.setText(""+of+"% OFF");
            linOffer.setVisibility(View.VISIBLE);
        }
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price="",offer="";
                if(!customerProductDataArrayList.get(position).getOfferPrice().equals(""))
                {
                    price = customerProductDataArrayList.get(position).getOfferPrice();
                    offer = "1";
                }
                else
                {
                    price = customerProductDataArrayList.get(position).getProductPrice();
                    offer = "0";
                }
                buyButtonCallBack.onClickBuy(customerProductDataArrayList.get(position).getProductId(),price,"1",offer);
            }
        });

        Glide.with(context)
                .load(AlaBricks.imagePath +customerProductDataArrayList.get(position).getProductImage()).apply(options).into(imageView);

        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(context, SingleProductActivity.class);
                intent.putExtra("clickId",position);
                intent.putExtra("path","normal");
                AlaBricks.singleCustomerProductData = customerProductDataArrayList.get(position);
                context.startActivity(intent);
            }
        });
        return grid;
    }
}
