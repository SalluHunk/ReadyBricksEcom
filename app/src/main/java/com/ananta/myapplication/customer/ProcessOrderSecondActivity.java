package com.ananta.myapplication.customer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.general.MainActivity;
import com.codesgood.views.JustifiedTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import impl.AddOrderPresenterImpl;
import presenter.ProcessOrderPresenter;
import util.AlaBricks;
import util.CircleCheckBox;
import view.ProcessOrderView;

public class ProcessOrderSecondActivity extends AppCompatActivity implements ProcessOrderView {
    private MaterialEditText edDate;
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog datePickerDialog;
    private Button btnConfirm;
    String selectedDate="";
    ProcessOrderPresenter processOrderPresenter;
    SharedPreferences sharedPreferences;
    private ImageView imgBack;
    String cartTotal="";
    private TextView txtPrice;
    private TextView txtPriceText;
    private Typeface typefaceBold,typefaceRegular;
    private TextView txtAddress;
    private JustifiedTextView txtAddressDesc;
    private TextView txtChange;
    private CircleCheckBox rdCredit;
    private CircleCheckBox rdDebit;
    private CircleCheckBox rdNet;
    private CircleCheckBox rdWallet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_order_second);

        Intent intent = getIntent();
        cartTotal = intent.getExtras().getString("cartTotal");

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        processOrderPresenter = new AddOrderPresenterImpl(this);
        edDate = (MaterialEditText)findViewById(R.id.edDate);
        btnConfirm =(Button)findViewById(R.id.btnConfirm);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        txtPrice = (TextView)findViewById(R.id.txtPrice);
        txtPriceText = (TextView)findViewById(R.id.txtPriceText);
        txtAddress = (TextView)findViewById(R.id.txtAddress);
        txtAddressDesc = (JustifiedTextView)findViewById(R.id.txtAddressDesc);
        txtChange = (TextView)findViewById(R.id.txtChange);

        rdCredit = (CircleCheckBox)findViewById(R.id.rdCredit);
        rdDebit =  (CircleCheckBox)findViewById(R.id.rdDebit);
        rdNet = (CircleCheckBox)findViewById(R.id.rdNet);
        rdWallet = (CircleCheckBox)findViewById(R.id.rdWallet);


        txtPrice.setText(cartTotal);



        rdCredit.setEnabled(false);
        rdDebit.setEnabled(false);
        rdNet.setEnabled(false);



        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtAddress.setTypeface(typefaceRegular);
        txtPrice.setTypeface(typefaceRegular);
        txtPriceText.setTypeface(typefaceBold);
        txtAddressDesc.setTypeface(typefaceRegular);
        txtChange.setTypeface(typefaceBold);

        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.orderProcess = null;
                finish();
            }
        });
        String address="";
        address = AlaBricks.orderProcess.getAddress1()+", "+AlaBricks.orderProcess.getLandmark()+", "+AlaBricks.orderProcess.getAddress2();


        txtAddressDesc.setText(address+"\n"+AlaBricks.orderProcess.getCityName()+", "+AlaBricks.orderProcess.getDistrictName()+", "+AlaBricks.orderProcess.getStateName()+", "+AlaBricks.orderProcess.getCountryName());

       date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                selectedDate = sdf.format(myCalendar.getTime());
                edDate.setText(selectedDate);
                Toast.makeText(ProcessOrderSecondActivity.this, ""+sdf.format(myCalendar.getTime()), Toast.LENGTH_SHORT).show();
            }

        };

        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 datePickerDialog = new DatePickerDialog(ProcessOrderSecondActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                 datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
                 datePickerDialog.show();


            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedDate.equals(""))
                {
                    Toast.makeText(ProcessOrderSecondActivity.this, "Please Select Delivery Date", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String isSame="0";
                    if(AlaBricks.orderProcess.isSame)
                    {
                        isSame="1";
                    }
                    processOrderPresenter.placeOrder(sharedPreferences.getString(AlaBricks.sharedUserId,""),isSame,AlaBricks.orderProcess.getAddress1()+" | "+AlaBricks.orderProcess.getAddress2(),AlaBricks.orderProcess.getLandmark(),AlaBricks.orderProcess.getCountry(),AlaBricks.orderProcess.getState(),AlaBricks.orderProcess.getCity(),selectedDate,AlaBricks.orderProcess.getDistrict());
                }
            }
        });


    }

    @Override
    public void onSuccessProcessOrder(String OrderId) {
    //    FBToast.successToast(ProcessOrderSecondActivity.this,"Order has been placed successfully.",FBToast.LENGTH_LONG);
        new AlertDialog.Builder(ProcessOrderSecondActivity.this)
                .setTitle("Order Placed Successfully")
                .setMessage("Order has been placed with Order ID: #"+OrderId+". We will contact you soon.")
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ProcessOrderSecondActivity.this,CustomerDashboard.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .show();

    }

    @Override
    public void onFailedProcessOrder() {

    }

    @Override
    public void onBackPressed() {
        AlaBricks.orderProcess = null;
        finish();
    }
}
