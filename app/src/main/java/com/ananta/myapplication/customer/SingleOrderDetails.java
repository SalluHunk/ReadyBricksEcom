package com.ananta.myapplication.customer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.manufacturer.SingleOrderDetailsManufacturer;
import com.ananta.myapplication.transporter.TrackingOrderActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tfb.fbtoast.FBToast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import impl.TrackingOrderPresenterImpl;
import network.TrackingData;
import presenter.TrackOrderPresenter;
import util.AlaBricks;
import view.TrackOrderView;

public class SingleOrderDetails extends AppCompatActivity implements TrackOrderView {

    TextView txtOrderId,txtProductName,txtOrderDetails,txtStatus,txtProductTotalText,txtProductTotal,txtEstimatedDateText,txtEstimatedDate;
    ImageView imgBack,imgProduct;
    LinearLayout linDesc,linAdditional;
    private Typeface typefaceBold,typefaceRegular;
    private TextView txtDateFirst,txtOrderPlaced,txtTextFirst;
    private TextView txtDateSecond,txtTextSecond,txtOrderAccepted;
    private ImageView imgSecond;
    private LinearLayout linAccept;
    private LinearLayout linAcceptLine;
    private LinearLayout linProcessLine,linProcess;
    TextView txtDateThird,txtTextThird,txtOrderProcessed;
    private ImageView imgThird;
    private TextView txtDateFour,txtTextFour,txtOrderAssign,txtTrackOrder,txtDateFive,txtTextFive,txtOrderCompleted;
    private ImageView imgFour;
    private LinearLayout linAssigned,linAssignedLine,linCompletedLine,linCompleted;
    TrackOrderPresenter trackOrderPresenter;
    private LinearLayout linRejectedLine,linRejected;
    TextView txtDateSix,txtTextSix,txtOrderRejected;
    private TextView txtAddReview;
    private TextView txtOrderDate,txtOrderDateText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_details);

        trackOrderPresenter = new TrackingOrderPresenterImpl(this);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        imgProduct  = (ImageView)findViewById(R.id.imgProduct);
        txtOrderId = (TextView)findViewById(R.id.txtOrderId);
        txtProductName = (TextView)findViewById(R.id.txtProductName);
        txtOrderDetails = (TextView)findViewById(R.id.txtOrderDetails);
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        linDesc = (LinearLayout)findViewById(R.id.linDesc);
        txtProductTotalText = (TextView)findViewById(R.id.txtProductTotalText);
        txtProductTotal =  (TextView)findViewById(R.id.txtProductTotal);
        txtEstimatedDateText = (TextView)findViewById(R.id.txtEstimatedDateText);
        txtEstimatedDate = (TextView)findViewById(R.id.txtEstimatedDate);
        txtOrderDateText = (TextView)findViewById(R.id.txtOrderDateText);
        txtOrderDate = (TextView)findViewById(R.id.txtOrderDate);


        linAdditional = (LinearLayout)findViewById(R.id.linAdditional);
        txtOrderPlaced =  (TextView)findViewById(R.id.txtOrderPlaced);
        txtTextFirst = (TextView)findViewById(R.id.txtTextFirst);
        txtDateFirst = (TextView)findViewById(R.id.txtDateFirst);
        txtAddReview = (TextView)findViewById(R.id.txtAddReview);

        txtDateSecond = (TextView)findViewById(R.id.txtDateSecond);
        txtTextSecond = (TextView)findViewById(R.id.txtTextSecond);
        txtOrderAccepted = (TextView)findViewById(R.id.txtOrderAccepted);
        imgSecond = (ImageView)findViewById(R.id.imgSecond);

        linAccept = (LinearLayout)findViewById(R.id.linAccept);
        linAcceptLine = (LinearLayout)findViewById(R.id.linAcceptLine);

        txtDateThird = (TextView)findViewById(R.id.txtDateThird);
        txtTextThird = (TextView)findViewById(R.id.txtTextThird);
        txtOrderProcessed = (TextView)findViewById(R.id.txtOrderProcessed);
        imgThird = (ImageView)findViewById(R.id.imgThird);
        linProcess = (LinearLayout)findViewById(R.id.linProcess);
        linProcessLine = (LinearLayout)findViewById(R.id.linProcessLine);

        txtDateFour = (TextView)findViewById(R.id.txtDateFour);
        txtTextFour = (TextView)findViewById(R.id.txtTextFour);
        txtOrderAssign = (TextView)findViewById(R.id.txtOrderAssign);
        imgFour = (ImageView)findViewById(R.id.imgFour);
        linAssigned = (LinearLayout)findViewById(R.id.linAssigned);
        linAssignedLine = (LinearLayout)findViewById(R.id.linAssignedLine);
        txtTrackOrder = (TextView)findViewById(R.id.txtTrackOrder);


        linCompletedLine  =(LinearLayout)findViewById(R.id.linCompletedLine);
        linCompleted=  (LinearLayout)findViewById(R.id.linCompleted);
        txtDateFive = (TextView)findViewById(R.id.txtDateFive);
        txtTextFive = (TextView)findViewById(R.id.txtTextFive);
        txtOrderCompleted = (TextView)findViewById(R.id.txtOrderCompleted);

        txtDateSix = (TextView)findViewById(R.id.txtDateSix);
        txtTextSix =  (TextView)findViewById(R.id.txtTextSix);
        txtOrderRejected = (TextView)findViewById(R.id.txtOrderRejected);
        linRejected = (LinearLayout)findViewById(R.id.linRejected);
        linRejectedLine = (LinearLayout)findViewById(R.id.linRejectedLine);


        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtDateSecond.setTypeface(typefaceRegular);
        txtTextSecond.setTypeface(typefaceRegular);
        txtOrderAccepted.setTypeface(typefaceBold);
        txtOrderId.setTypeface(typefaceRegular);
        txtProductName.setTypeface(typefaceBold);
        txtOrderDetails.setTypeface(typefaceBold);
        txtStatus.setTypeface(typefaceBold);
        txtEstimatedDate.setTypeface(typefaceBold);
        txtProductTotal.setTypeface(typefaceBold);
        txtEstimatedDateText.setTypeface(typefaceRegular);
        txtOrderDateText.setTypeface(typefaceRegular);
        txtOrderDate.setTypeface(typefaceBold);

        txtProductTotalText.setTypeface(typefaceRegular);
        txtTextFirst.setTypeface(typefaceRegular);
        txtOrderPlaced.setTypeface(typefaceBold);
        txtDateFirst.setTypeface(typefaceRegular);

        txtDateSecond.setTypeface(typefaceRegular);
        txtDateThird.setTypeface(typefaceRegular);
        txtTextSecond.setTypeface(typefaceRegular);
        txtTextThird.setTypeface(typefaceRegular);
        txtOrderAccepted.setTypeface(typefaceBold);
        txtOrderProcessed.setTypeface(typefaceBold);

        txtTrackOrder.setTypeface(typefaceBold);
        txtOrderAssign.setTypeface(typefaceBold);
        txtTextFour.setTypeface(typefaceRegular);
        txtDateFour.setTypeface(typefaceRegular);

        txtDateFive.setTypeface(typefaceRegular);
        txtTextFive.setTypeface(typefaceRegular);
        txtOrderCompleted.setTypeface(typefaceBold);

        txtDateSix.setTypeface(typefaceRegular);
        txtTextSix.setTypeface(typefaceRegular);
        txtOrderRejected.setTypeface(typefaceBold);

        txtAddReview.setTypeface(typefaceBold);

        linDesc.setVisibility(View.VISIBLE);
        txtOrderDetails.setTextColor(getResources().getColor(R.color.colorMain));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleOrderDetails.this,AddReviewActivity.class);
                startActivity(intent);
            }
        });

        txtOrderId.setText("ID: "+ AlaBricks.orderData.getOrderId());
        txtProductName.setText(AlaBricks.orderData.getProductName());
        txtProductTotal.setText("₹ "+(Float.parseFloat(AlaBricks.orderData.getQuantity()) *Float.parseFloat(AlaBricks.orderData.getPrice())));

        if(AlaBricks.orderData.getOrderStatus().equals("1"))
        {
            linAccept.setVisibility(View.VISIBLE);
            linAcceptLine.setVisibility(View.VISIBLE);
        }

        if(AlaBricks.orderData.getOrderStatus().equals("2"))
        {
            linAccept.setVisibility(View.VISIBLE);
            linAcceptLine.setVisibility(View.VISIBLE);
          /*  linProcess.setVisibility(View.VISIBLE);
            linProcessLine.setVisibility(View.VISIBLE);*/
        }
        if(AlaBricks.orderData.getOrderStatus().equals("3"))
        {
            linAccept.setVisibility(View.VISIBLE);
            linAcceptLine.setVisibility(View.VISIBLE);
          /*  linProcess.setVisibility(View.VISIBLE);
            linProcessLine.setVisibility(View.VISIBLE);
            linAssigned.setVisibility(View.VISIBLE);
            linAssignedLine.setVisibility(View.VISIBLE);*/
        }

        if(AlaBricks.orderData.getOrderStatus().equals("4"))
        {
            linAccept.setVisibility(View.VISIBLE);
            linAcceptLine.setVisibility(View.VISIBLE);
           /* linProcess.setVisibility(View.VISIBLE);
            linProcessLine.setVisibility(View.VISIBLE);
            */
            linAssigned.setVisibility(View.VISIBLE);
            linAssignedLine.setVisibility(View.VISIBLE);
            txtTrackOrder.setVisibility(View.GONE);
            linCompleted.setVisibility(View.VISIBLE);
            linCompletedLine.setVisibility(View.VISIBLE);
            if(AlaBricks.orderData.getIsReview().equals("1"))
            {
                txtAddReview.setVisibility(View.GONE);
            }
            else {
                txtAddReview.setVisibility(View.VISIBLE);
            }
        }


        if(AlaBricks.orderData.getOrderStatus().equals("6"))
        {
            linAccept.setVisibility(View.VISIBLE);
            linAcceptLine.setVisibility(View.VISIBLE);
           /* linProcess.setVisibility(View.VISIBLE);
            linProcessLine.setVisibility(View.VISIBLE);
            */
            linAssigned.setVisibility(View.VISIBLE);
            linAssignedLine.setVisibility(View.VISIBLE);
            txtTrackOrder.setVisibility(View.VISIBLE);
        }

        if(AlaBricks.orderData.getOrderStatus().equals("5"))
        {
            linRejected.setVisibility(View.VISIBLE);
            linRejectedLine.setVisibility(View.VISIBLE);
        }
        txtTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackOrderPresenter.trackOrder(AlaBricks.orderData.getOrderId());
            }
        });


        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide.with(SingleOrderDetails.this)
                .load(AlaBricks.imagePath +AlaBricks.orderData.getProductImage()).apply(options).into(imgProduct);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date orderDate=null,newDate= null,placedDate=null,acceptedDate=null,processedDate=null,assigendDate=null,completedDate=null,rejectedDate=null;
        try {
            orderDate = simpleDateFormat.parse(AlaBricks.orderData.getCreatedAt());
            newDate = simpleDateFormat.parse(AlaBricks.orderData.getDeliveryDate());
            placedDate = simpleDateFormat.parse(AlaBricks.orderData.getCreatedAt());
            acceptedDate = simpleDateFormat.parse(AlaBricks.orderData.getAcceptedTime());
            processedDate = simpleDateFormat.parse(AlaBricks.orderData.getAssignedAt());
            assigendDate = simpleDateFormat.parse(AlaBricks.orderData.getProcessedAt());
            completedDate = simpleDateFormat.parse(AlaBricks.orderData.getCompletedAt());
            rejectedDate = simpleDateFormat.parse(AlaBricks.orderData.getRejectedAt());

            simpleDateFormat= new SimpleDateFormat(" dd MMMM, yyyy");
            txtOrderDate.setText(simpleDateFormat.format(orderDate));
            txtEstimatedDate.setText(simpleDateFormat.format(newDate));
            txtDateFirst.setText(simpleDateFormat.format(placedDate));
            if(AlaBricks.orderData.getOrderStatus().equals("1"))
            {
                txtDateSecond.setText(simpleDateFormat.format(acceptedDate));
            }
            if(AlaBricks.orderData.getOrderStatus().equals("2"))
            {
                txtDateSecond.setText(simpleDateFormat.format(acceptedDate));
                txtDateThird.setText(simpleDateFormat.format(processedDate));
            }
            if(AlaBricks.orderData.getOrderStatus().equals("3"))
            {
                txtDateSecond.setText(simpleDateFormat.format(acceptedDate));
                txtDateThird.setText(simpleDateFormat.format(processedDate));
                txtDateFour.setText(simpleDateFormat.format(assigendDate));
            }
            if(AlaBricks.orderData.getOrderStatus().equals("4"))
            {
                txtDateSecond.setText(simpleDateFormat.format(acceptedDate));
                txtDateThird.setText(simpleDateFormat.format(processedDate));
                txtDateFour.setText(simpleDateFormat.format(assigendDate));
                txtDateFive.setText(simpleDateFormat.format(completedDate));
            }

            if(AlaBricks.orderData.getOrderStatus().equals("6"))
            {
                txtDateSecond.setText(simpleDateFormat.format(acceptedDate));
                txtDateThird.setText(simpleDateFormat.format(processedDate));
                txtDateFour.setText(simpleDateFormat.format(assigendDate));
            }

            if(AlaBricks.orderData.getOrderStatus().equals("5"))
            {
                txtDateSix.setText(simpleDateFormat.format(rejectedDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linAdditional.setVisibility(View.VISIBLE);
                linDesc.setVisibility(View.GONE);
                txtStatus.setTextColor(getResources().getColor(R.color.colorMain));
                txtOrderDetails.setTextColor(Color.BLACK);
            }
        });

        txtOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linDesc.setVisibility(View.VISIBLE);
                linAdditional.setVisibility(View.GONE);
                txtOrderDetails.setTextColor(getResources().getColor(R.color.colorMain));
                txtStatus.setTextColor(Color.BLACK);
            }
        });
    }


    @Override
    public void onSuccessTracking(TrackingData trackingData) {
        Intent intent  =new Intent(SingleOrderDetails.this, TrackingOrderActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailedTracking() {
        FBToast.errorToast(SingleOrderDetails.this,"Tracking has not been started yet",FBToast.LENGTH_LONG);
    }
}
