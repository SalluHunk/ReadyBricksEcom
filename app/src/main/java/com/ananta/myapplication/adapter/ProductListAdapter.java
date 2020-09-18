package com.ananta.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ananta.myapplication.R;
import com.ananta.myapplication.manufacturer.EditProductActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import network.ProductData;
import util.AlaBricks;

public class ProductListAdapter extends BaseAdapter {

    ArrayList<ProductData> productData  = new ArrayList<ProductData>();
    private static LayoutInflater inflater=null;
    Context context;
    private Typeface typefaceRegular,typefaceBold;

    public ProductListAdapter(Context context, ArrayList<ProductData> employeeOrderDataArrayList)
    {
        this.context = context;
        this.productData = employeeOrderDataArrayList;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView txtProductName,txtProductStock,txtStatus;
        ImageView imgProduct;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_list_manu_product, null);

        holder.txtProductName = (TextView)rowView.findViewById(R.id.txtProductName);
        holder.txtProductStock = (TextView)rowView.findViewById(R.id.txtProductStock);
        holder.txtStatus = (TextView)rowView.findViewById(R.id.txtStatus);

        holder.imgProduct = (ImageView)rowView.findViewById(R.id.imgProduct);

        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");


        holder.txtProductName.setText(productData.get(position).getProductName());
        holder.txtProductStock.setText("Stock: "+productData.get(position).getProductStock());
        if(productData.get(position).getProductAccount().equals("0"))
        {
            holder.txtStatus.setText("PENDING");
        }
        if(productData.get(position).getProductAccount().equals("1") && productData.get(position).getProductStatus().equals("1"))
        {
            holder.txtStatus.setText("ACTIVE");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusGreen));
        }
        if(productData.get(position).getProductAccount().equals("1") && productData.get(position).getProductStatus().equals("0"))
        {
            holder.txtStatus.setText("DEACTIVATED");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusRed));
        }
        if(productData.get(position).getProductAccount().equals("2"))
        {
            holder.txtStatus.setText("REJECTED");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusRed));
        }
        holder.txtProductName.setTypeface(typefaceBold);
        holder.txtProductStock.setTypeface(typefaceRegular);
        holder.txtStatus.setTypeface(typefaceBold);

        if(productData.get(position).getProductEdit().equals("1"))
        {
            holder.txtStatus.setText("EDITED");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusRed));
        }


        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide.with(context)
                .load(AlaBricks.imagePath +productData.get(position).getProductImage()).apply(options).into(holder.imgProduct);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.productData = productData.get(position);
                Intent intent = new Intent(context, EditProductActivity.class);
                context.startActivity(intent);
            }
        });

        return rowView;

    }


}
