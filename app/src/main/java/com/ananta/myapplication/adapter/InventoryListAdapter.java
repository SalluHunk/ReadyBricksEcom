package com.ananta.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ananta.myapplication.R;
import com.ananta.myapplication.manufacturer.EditProductActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import network.ProductData;
import util.AlaBricks;

public class InventoryListAdapter extends BaseAdapter {

    ArrayList<ProductData> productData  = new ArrayList<ProductData>();
    private static LayoutInflater inflater=null;
    Context context;
    private Typeface typefaceRegular,typefaceBold;

    public InventoryListAdapter(Context context, ArrayList<ProductData> employeeOrderDataArrayList)
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
        TextView txtProductName,txtProductStock;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_inventory_list, null);

        holder.txtProductName = (TextView)rowView.findViewById(R.id.txtProductName);
        holder.txtProductStock = (TextView)rowView.findViewById(R.id.txtStock);

        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");

        holder.txtProductName.setText(productData.get(position).getProductName());
        holder.txtProductStock.setText(productData.get(position).getProductStock());

        holder.txtProductName.setTypeface(typefaceRegular);
        holder.txtProductStock.setTypeface(typefaceBold);

        RequestOptions options = new RequestOptions();
        options.centerCrop();

        return rowView;
    }
}
