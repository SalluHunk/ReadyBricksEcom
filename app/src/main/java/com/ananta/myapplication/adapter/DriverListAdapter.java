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
import com.ananta.myapplication.customer.SingleOrderDetails;
import com.ananta.myapplication.transporter.SingleDriverActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import network.DriverListData;
import network.OrderData;
import util.AlaBricks;

public class DriverListAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;
    Context context;
    private Typeface typefaceRegular,typefaceBold;
    ArrayList<DriverListData> orderData = new ArrayList<>();

    public DriverListAdapter(Context context, ArrayList<DriverListData> orderDataArrayList)
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
        TextView txtProductName,txtProductPrice,txtStatus;
        ImageView imgProduct;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_driver_list, null);

        holder.txtProductName = (TextView)rowView.findViewById(R.id.txtDriverName);
        holder.txtProductPrice = (TextView)rowView.findViewById(R.id.txtDriverPhone);
        holder.txtStatus = (TextView)rowView.findViewById(R.id.txtStatus);
        holder.imgProduct = (ImageView)rowView.findViewById(R.id.imgDriver);


        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");



        holder.txtProductName.setText(orderData.get(position).getFirstName()+" "+orderData.get(position).getLastName());
        holder.txtProductName.setTypeface(typefaceBold);

        holder.txtProductPrice.setText(orderData.get(position).getDriverPhone());
        holder.txtProductPrice.setTypeface(typefaceRegular);
        holder.txtStatus.setTypeface(typefaceBold);

      /*  if(orderData.get(position).getStatus().equals("0"))
        {
            holder.txtStatus.setText("PENDING");
        }
        if(orderData.get(position).getStatus().equals("1"))
        {
            holder.txtStatus.setText("ACCEPTED");
        }
        if(orderData.get(position).getStatus().equals("2"))
        {
            holder.txtStatus.setText("PROCESS");
        }*/

        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide.with(context)
                .load(AlaBricks.imagePath +orderData.get(position).getDriverImage()).apply(options).into(holder.imgProduct);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.driverListData = orderData.get(position);
                Intent intent = new Intent(context, SingleDriverActivity.class);
                context.startActivity(intent);
            }
        });

        if(orderData.get(position).getStatus().equals("0"))
        {
            holder.txtStatus.setText("PENDING");
        }
        if(orderData.get(position).getStatus().equals("1") && orderData.get(position).getDriverAccount().equals("1"))
        {
            holder.txtStatus.setText("APPROVED");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusGreen));
        }
        if(orderData.get(position).getDriverAccount().equals("1") && orderData.get(position).getStatus().equals("0"))
        {
            holder.txtStatus.setText("DEACTIVATED");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusRed));
        }
        if(orderData.get(position).getDriverAccount().equals("2"))
        {
            holder.txtStatus.setText("REJECTED");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusRed));
        }
        if(orderData.get(position).getIsEdited().equals("1"))
        {
            holder.txtStatus.setText("EDITED");
            holder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorStatusRed));
        }
        return rowView;
    }
}
