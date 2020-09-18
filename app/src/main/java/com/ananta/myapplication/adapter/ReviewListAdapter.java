package com.ananta.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ananta.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import items.SendDataToCartFragment;
import network.CartData;
import network.ReviewData;
import util.AlaBricks;
import util.SendDataFromCartListToCartFragment;

public class ReviewListAdapter extends BaseAdapter {

    ArrayList<ReviewData> reviewDataArrayList  = new ArrayList<ReviewData>();
    private static LayoutInflater inflater=null;
    Context context;
    private Typeface typefaceRegular,typefaceBold;
    public ReviewListAdapter(Context context, ArrayList<ReviewData> reviewData)
    {
        this.context = context;
        this.reviewDataArrayList = reviewData;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return reviewDataArrayList.size();
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
        TextView txtName,txtDesd;
        CircleImageView imgProfile;
        RatingBar ratingBar;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_review, null);

        holder.imgProfile = (CircleImageView) rowView.findViewById(R.id.imgProfile);
        holder.txtName = (TextView)rowView.findViewById(R.id.txtName);
        holder.txtDesd = (TextView)rowView.findViewById(R.id.txtDesc);
        holder.ratingBar = (RatingBar)rowView.findViewById(R.id.ratingBar);

        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");

        holder.txtName.setText(reviewDataArrayList.get(position).getFirstName()+" "+reviewDataArrayList.get(position).getLastName());
        holder.txtDesd.setText(reviewDataArrayList.get(position).getDesc());

        holder.txtName.setTypeface(typefaceBold);
        holder.txtDesd.setTypeface(typefaceRegular);
        holder.ratingBar.setRating(Float.parseFloat(reviewDataArrayList.get(position).getRating()));

        RequestOptions options = new RequestOptions();
        options.centerCrop();


        return rowView;
    }
}
