package com.ananta.myapplication.transporter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ananta.myapplication.R;
import com.ananta.myapplication.general.SignupThreeActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.share.Share;
import com.tfb.fbtoast.FBToast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.ganfra.materialspinner.MaterialSpinner;
import impl.AssignOrderPresenterImpl;
import impl.TrackingOrderPresenterImpl;
import impl.VehicleAndDriverPresenterImpl;
import network.TrackingData;
import network.VehicleAndDriverData;
import presenter.AssignDriverPresenter;
import presenter.TrackOrderPresenter;
import presenter.VehicleDriverPresenter;
import util.AlaBricks;
import view.AssignDriverView;
import view.TrackOrderView;
import view.VehicleDriverView;

public class SingleOrderDetailsTransporter extends AppCompatActivity implements VehicleDriverView, AssignDriverView, TrackOrderView {

    TrackOrderPresenter trackOrderPresenter;
    TextView txtOrderId,txtProductName,txtOrderDetails,txtStatus,txtProductTotalText,txtProductTotal,txtEstimatedDateText,txtEstimatedDate;
    ImageView imgBack,imgProduct;
    LinearLayout linDesc,linAdditional;
    private Typeface typefaceBold,typefaceRegular;
    private TextView txtDateFirst,txtOrderPlaced,txtTextFirst;
    VehicleDriverPresenter vehicleDriverPresenter;
    SharedPreferences sharedPreferences;
    VehicleAndDriverData vehicleAndDriverData;
    ArrayList<String> arrDriver =new ArrayList<>();
    ArrayList<String> arrVehicle = new ArrayList<>();
    private TextView txtCustPhone,txtHeader,txtManuPhone,txtManuAddress,txtManuName,txtManuDetails,txtCustomerDetails,txtCustName,txtCustAddress;
    MaterialSpinner spiTruck,spiDriver;
    Button btnSubmit;
    AssignDriverPresenter assignDriverPresenter;
    String driverId="",vehicleId="";
    private CardView cardAssign;
    private TextView txtDriverPhone,txtTransDetails,txtDriverName,txtTruckNumber;
    private CardView cardTransporter;
    private Button btnTrackOrder;
    private Date orderDate=null;
    private TextView txtOrderDateText,txtOrderDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_trans_order_details);

        trackOrderPresenter = new TrackingOrderPresenterImpl(this);
        assignDriverPresenter = new AssignOrderPresenterImpl(this);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        vehicleDriverPresenter = new VehicleAndDriverPresenterImpl(this);
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
        linAdditional = (LinearLayout)findViewById(R.id.linAdditional);
        cardAssign = (CardView)findViewById(R.id.cardAssign);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        spiTruck = (MaterialSpinner)findViewById(R.id.spiTruck);
        spiDriver = (MaterialSpinner)findViewById(R.id.spiDriver);
        txtHeader = (TextView)findViewById(R.id.txtHeader);
        txtManuPhone = (TextView)findViewById(R.id.txtManuPhone);
        txtManuAddress =  (TextView)findViewById(R.id.txtManuAddress);
        txtManuName = (TextView)findViewById(R.id.txtManuName);
        txtManuDetails = (TextView)findViewById(R.id.txtManuDetails);
        txtCustomerDetails = (TextView)findViewById(R.id.txtCustomerDetails);
        txtCustName = (TextView)findViewById(R.id.txtCustName);
        txtCustAddress = (TextView)findViewById(R.id.txtCustAddress);
        txtCustPhone = (TextView)findViewById(R.id.txtCustPhone);
        cardTransporter = (CardView)findViewById(R.id.cardTransporter);
        txtTransDetails = (TextView)findViewById(R.id.txtTransDetails);
        txtDriverName = (TextView)findViewById(R.id.txtDriverName);
        txtTruckNumber = (TextView)findViewById(R.id.txtTruckNumber);
        txtDriverPhone = (TextView)findViewById(R.id.txtDriverPhone);
        btnTrackOrder = (Button)findViewById(R.id.btnTrackOrder);

        txtOrderDateText = (TextView)findViewById(R.id.txtOrderDateText);
        txtOrderDate = (TextView)findViewById(R.id.txtOrderDate);


        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtOrderId.setTypeface(typefaceRegular);
        txtProductName.setTypeface(typefaceBold);
        txtOrderDetails.setTypeface(typefaceBold);
        txtStatus.setTypeface(typefaceBold);
        txtEstimatedDate.setTypeface(typefaceBold);
        txtProductTotal.setTypeface(typefaceBold);
        txtEstimatedDateText.setTypeface(typefaceRegular);
        txtProductTotalText.setTypeface(typefaceRegular);

        txtHeader.setTypeface(typefaceBold);
        txtManuName.setTypeface(typefaceRegular);
        txtManuPhone.setTypeface(typefaceRegular);
        txtManuAddress.setTypeface(typefaceRegular);

        txtCustAddress.setTypeface(typefaceRegular);
        txtCustPhone.setTypeface(typefaceRegular);
        txtCustName.setTypeface(typefaceRegular);
        txtManuDetails.setTypeface(typefaceBold);
        txtCustomerDetails.setTypeface(typefaceBold);

        txtTruckNumber.setTypeface(typefaceRegular);
        txtDriverPhone.setTypeface(typefaceRegular);
        txtDriverName.setTypeface(typefaceRegular);
        txtOrderDateText.setTypeface(typefaceRegular);
        txtOrderDate.setTypeface(typefaceBold);

        txtTransDetails.setTypeface(typefaceBold);

        linDesc.setVisibility(View.VISIBLE);
        txtOrderDetails.setTextColor(getResources().getColor(R.color.colorMain));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtOrderId.setText("ID: "+ AlaBricks.orderData.getOrderId());
        txtProductName.setText(AlaBricks.orderData.getProductName());
        txtProductTotal.setText("₹ "+(Float.parseFloat(AlaBricks.orderData.getQuantity()) *Float.parseFloat(AlaBricks.orderData.getPrice())));
        RequestOptions options = new RequestOptions();
        options.centerCrop();

        if(AlaBricks.orderData.getOrderStatus().equals("2") || AlaBricks.orderData.getOrderStatus().equals("3"))
        {
            String repCustAddress = AlaBricks.orderData.getCustomerAddress().replace("|",",");
            String repManuAddress = AlaBricks.orderData.getManufactureAddress().replace("\\|","");
            txtCustName.setText(AlaBricks.orderData.getCustomerFirstName()+" "+AlaBricks.orderData.getCustomerLastName());
            txtCustPhone.setText(AlaBricks.orderData.getCustomerMobileNumber());
            txtCustAddress.setText(repCustAddress+"\n"+AlaBricks.orderData.getCustomerCityName()+","+AlaBricks.orderData.getCustomerDistrictName()+","+AlaBricks.orderData.getCustomerStateName()+","+AlaBricks.orderData.getCountryName());

            txtManuName.setText(AlaBricks.orderData.getManuName());
            txtManuPhone.setText(AlaBricks.orderData.getManufacturerMobileNumber());
            txtManuAddress.setText(repManuAddress+"\n"+AlaBricks.orderData.getManufactureCityName()+","+AlaBricks.orderData.getCustomerDistrictName()+","+AlaBricks.orderData.getManufactureStateName()+","+AlaBricks.orderData.getManufactureCountryName());
        }

        if(AlaBricks.orderData.getOrderStatus().equals("4"))
        {
            cardAssign.setVisibility(View.GONE);
            btnTrackOrder.setVisibility(View.GONE);
            cardTransporter.setVisibility(View.GONE);

        }
        if(AlaBricks.orderData.getOrderStatus().equals("6"))
        {
            cardAssign.setVisibility(View.GONE);
        }

        if(AlaBricks.orderData.getOrderStatus().equals("3"))
        {
            cardAssign.setVisibility(View.GONE);
            txtDriverName.setText(AlaBricks.orderData.getDriverFirstName()+" "+AlaBricks.orderData.getDriverLastName());
            txtDriverPhone.setText(AlaBricks.orderData.getDriverMobileNumber());
            txtTruckNumber.setText(AlaBricks.orderData.getVehicleNo());
        }

        if(AlaBricks.orderData.getOrderStatus().equals("2"))
        {
            btnTrackOrder.setVisibility(View.GONE);
            cardTransporter.setVisibility(View.GONE);
        }

        btnTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackOrderPresenter.trackOrder(AlaBricks.orderData.getOrderId());
            }
        });
        Glide.with(SingleOrderDetailsTransporter.this)
                .load(AlaBricks.imagePath +AlaBricks.orderData.getProductImage()).apply(options).into(imgProduct);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date newDate= null;
        try {

            newDate = simpleDateFormat.parse(AlaBricks.orderData.getDeliveryDate());
            orderDate = simpleDateFormat.parse(AlaBricks.orderData.getCreatedAt());
            simpleDateFormat= new SimpleDateFormat("dd MMMM, yyyy");
            txtOrderDate.setText(simpleDateFormat.format(orderDate));
            txtEstimatedDate.setText(simpleDateFormat.format(newDate));
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

        vehicleDriverPresenter.listVehicleDriver(sharedPreferences.getString(AlaBricks.sharedUserId,""));

        spiTruck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    vehicleId = vehicleAndDriverData.getVehicleData().get(position).getVehicleId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spiDriver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    driverId = vehicleAndDriverData.getDriverData().get(position).getDriverId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(driverId.equals(""))
                {
                    FBToast.errorToast(SingleOrderDetailsTransporter.this,"Please Select Driver",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(vehicleId.equals(""))
                    {
                        FBToast.errorToast(SingleOrderDetailsTransporter.this,"Please Select Vehicle",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        assignDriverPresenter.assignDriver(AlaBricks.orderData.getOrderId(),vehicleId,driverId);
                    }
                }
            }
        });


       }

    @Override
    public void onSuccessVehicleDriver(VehicleAndDriverData vehicleAndDriverData) {
        this.vehicleAndDriverData = vehicleAndDriverData;
        if(vehicleAndDriverData.getDriverData().size()>0)
        {
            for (int i=0;i<vehicleAndDriverData.getDriverData().size();i++)
            {
                arrDriver.add(vehicleAndDriverData.getDriverData().get(i).getFirstName()+" "+vehicleAndDriverData.getDriverData().get(i).getLastName());
            }
        }
        if(vehicleAndDriverData.getVehicleData().size()>0)
        {
            for (int i=0;i<vehicleAndDriverData.getVehicleData().size();i++)
            {
                arrVehicle.add(vehicleAndDriverData.getVehicleData().get(i).getVehicleNo());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SingleOrderDetailsTransporter.this, android.R.layout.simple_spinner_item,arrDriver);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiDriver.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SingleOrderDetailsTransporter.this, android.R.layout.simple_spinner_item,arrVehicle);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiTruck.setAdapter(adapter2);

    }

    @Override
    public void onFailedVehicleDriver() {

    }

    @Override
    public void onSuccessDriverAssign() {
        FBToast.successToast(SingleOrderDetailsTransporter.this,"Order has been successfully assigned",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedDriverAssign() {

    }

    @Override
    public void onSuccessTracking(TrackingData trackingData) {
        Intent intent  =new Intent(SingleOrderDetailsTransporter.this,TrackingOrderActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailedTracking() {
        FBToast.errorToast(SingleOrderDetailsTransporter.this,"Tracking has not been started yet",FBToast.LENGTH_LONG);
    }
}
