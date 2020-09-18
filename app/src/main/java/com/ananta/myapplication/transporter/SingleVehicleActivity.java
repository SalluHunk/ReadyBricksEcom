package com.ananta.myapplication.transporter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import impl.AddTruckPresenterImpl;
import impl.UpdateVehiclePresenterImpl;
import presenter.AddTruckPresenter;
import presenter.UpdateVehiclePresenter;
import util.AlaBricks;
import view.UpdateVehicleView;

public class SingleVehicleActivity extends AppCompatActivity implements UpdateVehicleView {
    private ImageView imgBack,imgRcBook;
    private MaterialEditText edPlateNo,edRcNo;
    TextView txtRcBookPhoto;
    LoadingButton btnProceed;
    private String imgNameTwo="";
    private SharedPreferences sharedPreferences;
    private TextView txtReason;
    private Typeface typefaceRegular,typefaceBold;
    UpdateVehiclePresenter vehiclePresenter;
    private String imgName="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truck);

        vehiclePresenter = new UpdateVehiclePresenterImpl(this);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        txtRcBookPhoto = (TextView)findViewById(R.id.txtRcBookPhoto);
        imgRcBook = (ImageView)findViewById(R.id.imgRcBook);
        edRcNo = (MaterialEditText)findViewById(R.id.edRcNo);
        edPlateNo = (MaterialEditText)findViewById(R.id.edPlateNo);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        txtReason = (TextView)findViewById(R.id.txtReason);


        imgRcBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 2);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edPlateNo.setText(AlaBricks.vehicleListData.getVehicleNo());
        edRcNo.setText(AlaBricks.vehicleListData.getVehicleRcNo());

        RequestOptions options = new RequestOptions();
        options.centerCrop();

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtReason.setTypeface(typefaceBold);
        txtRcBookPhoto.setTypeface(typefaceRegular);

        Glide.with(SingleVehicleActivity.this)
                .load(AlaBricks.imagePath +AlaBricks.vehicleListData.getRcImage()).apply(options).into(imgRcBook);

        if(AlaBricks.vehicleListData.getIsAccount().equals("0"))
        {
            btnProceed.setVisibility(View.GONE);
            txtReason.setText("Truck is under review.");
            edPlateNo.setEnabled(false);
            edRcNo.setEnabled(false);
        }
        if(AlaBricks.vehicleListData.getIsEdit().equals("1"))
        {
            btnProceed.setVisibility(View.GONE);
            txtReason.setText("Truck is Edited and is under review.");
            edPlateNo.setEnabled(false);
            edRcNo.setEnabled(false);
        }
        if(AlaBricks.vehicleListData.getIsAccount().equals("2"))
        {
            txtReason.setTypeface(typefaceBold);
            txtReason.setVisibility(View.VISIBLE);
            txtReason.setText("Reason: "+AlaBricks.vehicleListData.getReason());
        }

        btnProceed.setText("Update Truck");
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validateBlankValidation(edPlateNo))
                {
                    FBToast.errorToast(SingleVehicleActivity.this,"Please Add Plate Number",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edRcNo))
                    {
                        FBToast.errorToast(SingleVehicleActivity.this,"Please Add RC No",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        vehiclePresenter.updateVehicle(AlaBricks.vehicleListData.getVehicleId(),AlaBricks.getStringFromEditText(edRcNo),AlaBricks.getStringFromEditText(edPlateNo),imgName);
                    }
                }
            }
        });

    }

    @Override
    public void onSuccessUpdateVehicle() {
        FBToast.successToast(SingleVehicleActivity.this,"Vehicle Details has been updated. Admin will confirm it.",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedUpdateVehicle() {
        FBToast.errorToast(SingleVehicleActivity.this,"Failed",FBToast.LENGTH_LONG);
    }


    public static Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;
        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory
                .Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("REQUEST CODE",requestCode+" is the request code");
        if(requestCode==2 && resultCode==RESULT_OK)
        {
            if (imgName.length() > 0) {
                imgName = "";
            }


            Uri selectedImageUri = data.getData();
            imgRcBook.setImageURI(selectedImageUri);

            File imageFile = new File(selectedImageUri.getPath());
            String fileName = imageFile.getName();

            try {
                //Getting the Bitmap from Gallery
//                    Bitmap bitmap=null;
                //                  bitmap= Bitmap.createScaledBitmap(bitmap,100,100,true);
                //Setting the Bitmap to ImageView
                Bitmap bitmap = decodeUri(SingleVehicleActivity.this,selectedImageUri,250);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byte_arr = stream.toByteArray();

                imgName = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                Log.w("ImageFileLog",imgName);
                imgName = "data:image/jpeg;base64,"+imgName;
                //    mediaType="image";
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
