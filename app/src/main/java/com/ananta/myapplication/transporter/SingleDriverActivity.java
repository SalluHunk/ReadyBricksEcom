package com.ananta.myapplication.transporter;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

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

import impl.UpdateDriverPresenterImpl;
import presenter.UpdateDriverPresenter;
import util.AlaBricks;
import view.UpdateDriverView;

public class SingleDriverActivity extends AppCompatActivity implements UpdateDriverView {

    private TextView txtUpload,txtUploadLicense;
    MaterialEditText edFirstName,edLastName,edPhone;
    ImageView imgBack,imgUploadLicense,imgDriverPhoto;
    private LoadingButton btnUpdateDriver;
    private TextView txtReason;
    private String imgName="";
    private String imgNameTwo="";
    private Typeface typefaceBold,typefaceRegular;
    UpdateDriverPresenter updateDriverPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_driver);

        updateDriverPresenter = new UpdateDriverPresenterImpl(this);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        edFirstName = (MaterialEditText)findViewById(R.id.edFirstName);
        edLastName = (MaterialEditText)findViewById(R.id.edLastName);
        edPhone = (MaterialEditText)findViewById(R.id.edPhone);
        imgUploadLicense = (ImageView)findViewById(R.id.imgUploadLicense);
        imgDriverPhoto = (ImageView)findViewById(R.id.imgDriverPhoto);
        txtUploadLicense = (TextView)findViewById(R.id.txtUploadLicense);
        txtUpload  = (TextView)findViewById(R.id.txtUpload);
        btnUpdateDriver = (LoadingButton)findViewById(R.id.btnUpdateDriver);
        txtReason = (TextView)findViewById(R.id.txtReason);

        edFirstName.setText(AlaBricks.driverListData.getFirstName());
        edLastName.setText(AlaBricks.driverListData.getLastName());
        edPhone.setText(AlaBricks.driverListData.getDriverPhone());

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtUploadLicense.setTypeface(typefaceRegular);
        txtUpload.setTypeface(typefaceRegular);
        txtReason.setTypeface(typefaceBold);

        RequestOptions options = new RequestOptions();
        options.centerCrop();

        if(AlaBricks.driverListData.getDriverAccount().equals("0"))
        {
            btnUpdateDriver.setVisibility(View.GONE);
            txtReason.setText("Driver is under review.");
            edFirstName.setEnabled(false);
            edLastName.setEnabled(false);
            edPhone.setEnabled(false);
        }
        if(AlaBricks.driverListData.getIsEdited().equals("1"))
        {
            btnUpdateDriver.setVisibility(View.GONE);
            txtReason.setText("Driver is Edited and is under review.");
            edFirstName.setEnabled(false);
            edLastName.setEnabled(false);
            edPhone.setEnabled(false);
        }
        if(AlaBricks.driverListData.getDriverAccount().equals("2"))
        {
            txtReason.setTypeface(typefaceBold);
            txtReason.setVisibility(View.VISIBLE);
            txtReason.setText("Reason: "+AlaBricks.driverListData.getReason());
        }
        imgUploadLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AlaBricks.driverListData.getStatus().equals("0")) {

                } else {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 2);
                }
            }
        });

        imgDriverPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AlaBricks.driverListData.getStatus().equals("0")) {

                } else {

                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 3);
                }
            }
        });


        Glide.with(SingleDriverActivity.this)
                .load(AlaBricks.imagePath +AlaBricks.driverListData.getDriverImage()).apply(options).into(imgDriverPhoto);

        Glide.with(SingleDriverActivity.this)
                .load(AlaBricks.imagePath +AlaBricks.driverListData.getLicenseImage()).apply(options).into(imgUploadLicense);


        btnUpdateDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validateBlankValidation(edFirstName))
                {
                    FBToast.errorToast(SingleDriverActivity.this,"Please Type First Name",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edLastName))
                    {
                        FBToast.errorToast(SingleDriverActivity.this,"Please Type Last Name",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        if(!AlaBricks.validatePhoneNumber(edPhone))
                        {
                            FBToast.errorToast(SingleDriverActivity.this,"Invalid Phone Number",FBToast.LENGTH_LONG);
                        }
                        else
                        {
                            updateDriverPresenter.updateDriver(AlaBricks.driverListData.getDriverId(),AlaBricks.getStringFromEditText(edFirstName),AlaBricks.getStringFromEditText(edLastName),AlaBricks.getStringFromEditText(edPhone),imgName,imgNameTwo);
                        }
                    }
                }
            }
        });
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
            imgUploadLicense.setImageURI(selectedImageUri);

            File imageFile = new File(selectedImageUri.getPath());
            String fileName = imageFile.getName();

            try {
                //Getting the Bitmap from Gallery
//                    Bitmap bitmap=null;
                //                  bitmap= Bitmap.createScaledBitmap(bitmap,100,100,true);
                //Setting the Bitmap to ImageView
                Bitmap bitmap = decodeUri(SingleDriverActivity.this,selectedImageUri,250);
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

        if(requestCode==3 && resultCode==RESULT_OK)
        {
            if (imgNameTwo.length() > 0) {
                imgNameTwo = "";
            }


            Uri selectedImageUri = data.getData();
            imgDriverPhoto.setImageURI(selectedImageUri);

            File imageFile = new File(selectedImageUri.getPath());
            String fileName = imageFile.getName();

            try {
                //Getting the Bitmap from Gallery
//                    Bitmap bitmap=null;
                //                  bitmap= Bitmap.createScaledBitmap(bitmap,100,100,true);
                //Setting the Bitmap to ImageView
                Bitmap bitmap = decodeUri(SingleDriverActivity.this,selectedImageUri,250);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byte_arr = stream.toByteArray();

                imgNameTwo = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                Log.w("ImageFileLog",imgNameTwo);
                imgNameTwo = "data:image/jpeg;base64,"+imgNameTwo;
                //    mediaType="image";
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onSuccessUpdateDriver() {
        FBToast.successToast(SingleDriverActivity.this,"Information has been sent to the admin for Review. Kindly wait until it is approved.",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedUpdateDriver() {
        FBToast.errorToast(SingleDriverActivity.this,"Failed",FBToast.LENGTH_LONG);
    }

    @Override
    public void onMobileNumberExists() {
        FBToast.errorToast(SingleDriverActivity.this,"Mobile Number Already Exists",FBToast.LENGTH_LONG);

    }
}
