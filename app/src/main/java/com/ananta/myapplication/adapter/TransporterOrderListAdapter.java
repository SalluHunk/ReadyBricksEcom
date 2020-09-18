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
import com.ananta.myapplication.manufacturer.SingleOrderDetailsManufacturer;
import com.ananta.myapplication.transporter.SingleOrderDetailsTransporter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import network.OrderData;
import util.AlaBricks;

public class TransporterOrderListAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;
    Context context;
    private Typeface typefaceRegular,typefaceBold;
    ArrayList<OrderData> orderData = new ArrayList<>();

    public TransporterOrderListAdapter(Context context, ArrayList<OrderData> orderDataArrayList)
    {
        this.context = context;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.orderData = orderDataArrayList;
    }

    @Override
    public int getCount() {
        return orderData.size();
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
        TextView txtProductId,txtProductName,txtProductPrice,txtStatus;
        ImageView imgProduct;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_order_list, null);

        holder.txtProductId = (TextView)rowView.findViewById(R.id.txtProdcutId);
        holder.txtProductName = (TextView)rowView.findViewById(R.id.txtProductName);
        holder.txtProductPrice = (TextView)rowView.findViewById(R.id.txtPrice);
        holder.txtStatus = (TextView)rowView.findViewById(R.id.txtStatus);
        holder.imgProduct = (ImageView)rowView.findViewById(R.id.imgProduct);


        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");


        holder.txtProductId.setText("#"+orderData.get(position).getOrderId());
        holder.txtProductId.setTypeface(typefaceRegular);

        holder.txtProductName.setText(orderData.get(position).getProductName());
        holder.txtProductName.setTypeface(typefaceBold);

        holder.txtProductPrice.setText("₹ "+Float.parseFloat(orderData.get(position).getPrice()) * Float.parseFloat(orderData.get(position).getQuantity()));
        holder.txtProductPrice.setTypeface(typefaceRegular);
        holder.txtStatus.setTypeface(typefaceBold);

        if(orderData.get(position).getOrderStatus().equals("0"))
        {
            holder.txtStatus.setText("PENDING");
        }
        if(orderData.get(position).getOrderStatus().equals("1"))
        {
            holder.txtStatus.setText("ACCEPTED");
        }
        if(orderData.get(position).getOrderStatus().equals("2"))
        {
            holder.txtStatus.setText("PROCESS");
        }
        if(orderData.get(position).getOrderStatus().equals("3"))
        {
            holder.txtStatus.setText("ASSIGNED");
        }
        if(orderData.get(position).getOrderStatus().equals("4"))
        {
            holder.txtStatus.setText("COMPLETED");
        }
        if(orderData.get(position).getOrderStatus().equals("5"))
        {
            holder.txtStatus.setText("REJECTED");
        }
        if(orderData.get(position).getOrderStatus().equals("6"))
        {
            holder.txtStatus.setText("TRANSIT");
        }
        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide.with(context)
                .load(AlaBricks.imagePath +orderData.get(position).getProductImage()).apply(options).into(holder.imgProduct);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.orderData = orderData.get(position);
                Intent intent = new Intent(context, SingleOrderDetailsTransporter.class);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
