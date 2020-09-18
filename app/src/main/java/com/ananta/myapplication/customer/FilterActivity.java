package com.ananta.myapplication.customer;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ananta.myapplication.R;

import org.w3c.dom.Text;

import util.AlaBricks;

public class FilterActivity extends AppCompatActivity {

    private SeekBar seekBarPrice;
    TextView txtZero,txtTwenty,txtPrice;
    private Typeface typefaceRegular,typefaceBold;
    private TextView txtManu,txtApply,txtClear,txtLowToHigh,txtHightoLow,txtPopular,txtSortBy,txtManuTwo;
    LinearLayout linApply,linClear;
    CardView linManu;
    String price="";
    String sortBy="";
    private ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        txtZero = (TextView)findViewById(R.id.txtZero);
        txtTwenty = (TextView)findViewById(R.id.txtTwenty);
        txtPrice = (TextView)findViewById(R.id.txtPrice);
        seekBarPrice = (SeekBar)findViewById(R.id.seekBarPrice);
        imgBack  =(ImageView)findViewById(R.id.imgBack);


        txtApply = (TextView)findViewById(R.id.txtApply);
        linApply = (LinearLayout)findViewById(R.id.linApply);
        txtClear = (TextView)findViewById(R.id.txtClear);
        linClear = (LinearLayout)findViewById(R.id.linClear);
        txtLowToHigh = (TextView)findViewById(R.id.txtLowToHigh);
        txtHightoLow = (TextView)findViewById(R.id.txtHightoLow);
        txtPopular = (TextView)findViewById(R.id.txtPopular);
        txtSortBy = (TextView)findViewById(R.id.txtSortBy);
        txtManuTwo = (TextView)findViewById(R.id.txtManuTwo);
        linManu = (CardView)findViewById(R.id.linManu);
        txtManu = (TextView)findViewById(R.id.txtManu);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtZero.setTypeface(typefaceRegular);
        txtTwenty.setTypeface(typefaceRegular);
        txtPrice.setTypeface(typefaceBold);
        txtApply.setTypeface(typefaceBold);
        txtClear.setTypeface(typefaceBold);
        txtLowToHigh.setTypeface(typefaceRegular);
        txtHightoLow.setTypeface(typefaceRegular);
        txtPopular.setTypeface(typefaceRegular);
        txtSortBy.setTypeface(typefaceBold);
        txtManuTwo.setTypeface(typefaceRegular);
        txtManu.setTypeface(typefaceBold);


        if(AlaBricks.filterSortBy.equals("1"))
        {
            txtPopular.setTypeface(typefaceBold);
            sortBy="1";
        }

        if(AlaBricks.filterSortBy.equals("2"))
        {
            txtHightoLow.setTypeface(typefaceBold);
            sortBy="2";
        }

        if(AlaBricks.filterSortBy.equals("3"))
        {
            txtLowToHigh.setTypeface(typefaceBold);
            sortBy="3";
        }

        if(!AlaBricks.filterPrice.equals(""))
        {
            seekBarPrice.setProgress(Integer.parseInt(AlaBricks.filterPrice));
            price = AlaBricks.filterPrice;
        }

        txtPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPopular.setTypeface(typefaceBold);
                txtHightoLow.setTypeface(typefaceRegular);
                txtLowToHigh.setTypeface(typefaceRegular);
                sortBy="1";
            }
        });

        txtHightoLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtHightoLow.setTypeface(typefaceBold);
                txtPopular.setTypeface(typefaceRegular);
                txtLowToHigh.setTypeface(typefaceRegular);
                sortBy="2";
            }
        });

        txtLowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtLowToHigh.setTypeface(typefaceBold);
                txtPopular.setTypeface(typefaceRegular);
                txtHightoLow.setTypeface(typefaceRegular);
                sortBy="3";
            }
        });

        linApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.filterSortBy = sortBy;
                AlaBricks.filterPrice = price;
                AlaBricks.filterManufacturer="";
                if(AlaBricks.finalManufacturerId.size()>0){
                    for (int i=0;i<AlaBricks.finalManufacturerId.size();i++)
                    {
                        if(i==0)
                        {
                            AlaBricks.filterManufacturer= AlaBricks.finalManufacturerId.get(0);
                        }
                        else
                        {
                            AlaBricks.filterManufacturer= AlaBricks.filterManufacturer+","+AlaBricks.finalManufacturerId.get(i);
                        }
                    }
                }
      //          Toast.makeText(FilterActivity.this, ""+AlaBricks.filterManufacturer, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        linManu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this,ManufacturerListActivity.class);
                startActivity(intent);
            }
        });


        linClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.filterPrice="";
                AlaBricks.filterSortBy ="";
                AlaBricks.filterManufacturer="";
                AlaBricks.finalManufacturerId.clear();
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
  //              Toast.makeText(FilterActivity.this, ""+progress, Toast.LENGTH_SHORT).show();
                 price= ""+progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
