package com.ananta.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;

import com.ananta.myapplication.R;
import com.squareup.picasso.Picasso;
import com.tiagosantos.enchantedviewpager.EnchantedViewPager;
import com.tiagosantos.enchantedviewpager.EnchantedViewPagerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import network.OffersData;
import util.AlaBricks;

public class HomePagerAdapter extends EnchantedViewPagerAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    ArrayList<OffersData> offersData = new ArrayList<>();

    public HomePagerAdapter(Context context, ArrayList<OffersData> offersData) {
        super(offersData);
        this.offersData = offersData;
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View mCurrentView = inflater.inflate(R.layout.layout_home_banner, container, false);

        TextView tv_title, tv_desc, tv_total;
        ImageView iv_banner, iv_forward;
        Typeface typefaceRegular;
        Typeface typefaceBold;
        /*tv_total = mCurrentView.findViewById(R.id.tv_home_banner_total);
        */
            iv_banner = mCurrentView.findViewById(R.id.iv_home_banner);
     /*   iv_forward = mCurrentView.findViewById(R.id.iv3);
     */
/*
        typefaceRegular = Typeface.createFromAsset(mContext.getAssets(),"fonts/poppinsregular.otf");
        typefaceBold = Typeface.createFromAsset(mContext.getAssets(),"fonts/poppinsbold.otf");
*/

       /* iv_forward.setColorFilter(Color.WHITE);
       *//* tv_title.setText(arrayList.get(position).getTitle());
        tv_desc.setText(arrayList.get(position).getDesc());*/
     /*tv_total.setText(arrayList.get(position).getTotalSong() + " " + mContext.getString(R.string.songs));*/
        Picasso.get()
                .load(AlaBricks.imagePath+offersData.get(position).getBannerUrl())
                .placeholder(R.drawable.buttondrawable)
                 .into(iv_banner);

     /*   new LoadColor().execute(offersData.get(position).getBannerUrl());

     */   mCurrentView.setTag(EnchantedViewPager.ENCHANTED_VIEWPAGER_POSITION + position);
        container.addView(mCurrentView);

        return mCurrentView;
    }

    public class LoadColor extends AsyncTask<String, String, String> {

        Bitmap bitmap;
        LoadColor() {
        }

        @Override
        protected String doInBackground(String... strings) {
            bitmap = getBitmapFromURL(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@NonNull Palette palette) {
                        int defaultValue = 0x000000;
                        int vibrant = palette.getVibrantColor(defaultValue);

                    }
                });
                super.onPostExecute(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return offersData.size();
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            InputStream input;
            if(AlaBricks.url.contains("https://")) {
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                input = connection.getInputStream();
            } else {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                input = connection.getInputStream();
            }
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public GradientDrawable getGradientDrawable(int first, int second) {
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(15);
        gd.setColors(new int[]{first, second});
        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        gd.mutate();
        return gd;
    }
}