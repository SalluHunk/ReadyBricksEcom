package com.ananta.myapplication.customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import impl.AddReviewPresenterImpl;
import presenter.AddOrderReviewPresenter;
import util.AlaBricks;
import view.AddOrderReviewView;

public class AddReviewActivity extends AppCompatActivity implements AddOrderReviewView {

    private ImageView imgBack;
    private LoadingButton btnProceed;
    private RatingBar ratingTransporter,ratingManufacturer;
    MaterialEditText edTranspoterReview,edManuReview;
    TextView txtTransporterReview,txtManuReview;
    String rManu="",rTransporter="";
    AddOrderReviewPresenter addOrderReviewPresenter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        addOrderReviewPresenter = new AddReviewPresenterImpl(this);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        ratingTransporter = (RatingBar)findViewById(R.id.ratingTransporter);
        edTranspoterReview = (MaterialEditText)findViewById(R.id.edTranspoterReview);
        txtTransporterReview = (TextView)findViewById(R.id.txtTransporterReview);
        ratingManufacturer = (RatingBar)findViewById(R.id.ratingManufacturer);
        edManuReview = (MaterialEditText)findViewById(R.id.edManuReview);
        txtManuReview = (TextView)findViewById(R.id.txtManuReview);
        imgBack = (ImageView)findViewById(R.id.imgBack);

        ratingManufacturer.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rManu = ""+ rating;
            }
        });

        ratingTransporter.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rTransporter = "" + rating;
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validateBlankValidation(edManuReview))
                {
                    FBToast.errorToast(AddReviewActivity.this,"Please Enter Manufacturer Review",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edTranspoterReview))
                    {
                        FBToast.errorToast(AddReviewActivity.this,"Please Enter Transporter Review",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        if(rManu.equals(""))
                        {
                            FBToast.errorToast(AddReviewActivity.this,"Please Add Manufacturer Ratings",FBToast.LENGTH_LONG);
                        }
                        else
                        {

                            if(rTransporter.equals(""))
                            {
                                FBToast.errorToast(AddReviewActivity.this,"Please Add Transporter Ratings",FBToast.LENGTH_LONG);
                            }
                            else
                            {
                                addOrderReviewPresenter.addReview(AlaBricks.orderData.getOrderId(),AlaBricks.orderData.getProductId(),sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.orderData.getTransporterId(),rTransporter,AlaBricks.getStringFromEditText(edTranspoterReview),AlaBricks.orderData.getManuId(),rManu,AlaBricks.getStringFromEditText(edManuReview));
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onSuccessOrderReview() {
        FBToast.successToast(AddReviewActivity.this,"Review has been added",FBToast.LENGTH_LONG);
        Intent intent = new Intent(AddReviewActivity.this,CustomerDashboard.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void onFailedOrderReview() {

    }
}
