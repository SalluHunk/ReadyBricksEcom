package com.ananta.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ananta.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import network.NotificationData;
import util.AlaBricks;

public class NotificationAdapter extends BaseAdapter {

    ArrayList<NotificationData> notificationData  = new ArrayList<NotificationData>();

    private static LayoutInflater inflater=null;
    Context context;
    public NotificationAdapter(Context context, ArrayList<NotificationData> notificationData)
    {
        this.context = context;
        this.notificationData = notificationData;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notificationData.size();
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
        TextView txtTitle,txtDesc;
        ImageView imgList;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        if(!notificationData.get(position).getNotificationImage().equals(""))
        {
            rowView = inflater.inflate(R.layout.custom_list_notification, null);
            holder.txtTitle = (TextView)rowView.findViewById(R.id.txtTitle);
            holder.imgList = (ImageView)rowView.findViewById(R.id.imgList);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");

            holder.txtTitle.setTypeface(typeface);

            holder.txtTitle.setText(notificationData.get(position).getNotificationTitle());

            RequestOptions options = new RequestOptions();
            options.centerCrop();


            if(!notificationData.get(position).getNotificationImage().equals(""))
            {
                Glide.with(context)
                        .load(AlaBricks.imagePath+notificationData.get(position).getNotificationImage()).apply(options).into(holder.imgList);

            }
            else
            {
                holder.imgList.setImageResource(R.drawable.logo);
            }
        }
        else
        {
            rowView = inflater.inflate(R.layout.custom_list_notification_title, null);
            holder.txtTitle = (TextView)rowView.findViewById(R.id.txtTitle);
            holder.txtDesc = (TextView)rowView.findViewById(R.id.txtDesc);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
            Typeface typeface1 = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");

            holder.txtTitle.setTypeface(typeface);
            holder.txtDesc.setTypeface(typeface1);

            holder.txtTitle.setText(notificationData.get(position).getNotificationTitle());
            holder.txtDesc.setText(notificationData.get(position).getNotificationDescription());
            RequestOptions options = new RequestOptions();
            options.centerCrop();
        }



        return rowView;

    }

}
