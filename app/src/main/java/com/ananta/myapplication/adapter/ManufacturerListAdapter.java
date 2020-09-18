package com.ananta.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ananta.myapplication.R;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import network.ProductData;
import util.AlaBricks;
import util.CircleCheckBox;

public class ManufacturerListAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;
    Context context;
    private Typeface typefaceRegular,typefaceBold;

    public ManufacturerListAdapter(Context context)
    {
        this.context = context;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return AlaBricks.manufacturerData.size();
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
        TextView txtManufacturerName;
        CircleCheckBox chkSame;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_manufacturer_list, null);

        holder.txtManufacturerName = (TextView)rowView.findViewById(R.id.txtManufacturerName);
        holder.chkSame = (CircleCheckBox)rowView.findViewById(R.id.chkSame);


        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");


        holder.txtManufacturerName.setText(AlaBricks.manufacturerData.get(position).getManuName());
        holder.txtManufacturerName.setTypeface(typefaceRegular);


        RequestOptions options = new RequestOptions();
        options.centerCrop();

        if(AlaBricks.manufacturerId.contains(AlaBricks.manufacturerData.get(position).getManuId()))
        {
            holder.chkSame.setChecked(true);
        }

        holder.chkSame.setOnCheckedChangeListener(new CircleCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CircleCheckBox view, boolean isChecked) {
                if(isChecked)
                {
                    AlaBricks.manufacturerId.add(AlaBricks.manufacturerData.get(position).getManuId());
                }
                else
                {
                    AlaBricks.manufacturerId.remove(AlaBricks.manufacturerData.get(position).getManuId());
                }
            }
        });

        return rowView;
    }
}
