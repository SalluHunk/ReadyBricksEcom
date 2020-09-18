package com.ananta.myapplication.transporter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import impl.AddTruckPresenterImpl;
import presenter.AddTruckPresenter;
import util.AlaBricks;
import view.AddTruckView;

public class AddTruckActivity extends AppCompatActivity implements AddTruckView {

    private ImageView imgBack,imgRcBook;
    private MaterialEditText edPlateNo,edRcNo;
    TextView txtRcBookPhoto;
    LoadingButton btnProceed;
    private String imgNameTwo="";
    AddTruckPresenter addTruckPresenter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truck);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        addTruckPresenter = new AddTruckPresenterImpl(this);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        txtRcBookPhoto = (TextView)findViewById(R.id.txtRcBookPhoto);
        imgRcBook = (ImageView)findViewById(R.id.imgRcBook);
        edRcNo = (MaterialEditText)findViewById(R.id.edRcNo);
        edPlateNo = (MaterialEditText)findViewById(R.id.edPlateNo);
        imgBack = (ImageView)findViewById(R.id.imgBack);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgRcBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 3);
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!AlaBricks.validateBlankValidation(edRcNo))
                {
                    FBToast.errorToast(AddTruckActivity.this,"Please Enter RC Book Number",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edPlateNo))
                    {
                        FBToast.errorToast(AddTruckActivity.this,"Please Enter Plate Number",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        if(imgNameTwo.equals(""))
                        {
                            FBToast.errorToast(AddTruckActivity.this,"Please Upload RC Book Image",FBToast.LENGTH_LONG);
                        }
                        else
                        {
                            addTruckPresenter.addTruck(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.getStringFromEditText(edRcNo),AlaBricks.getStringFromEditText(edPlateNo),imgNameTwo);
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

        if(requestCode==3 && resultCode==RESULT_OK)
        {
            if (imgNameTwo.length() > 0) {
                imgNameTwo = "";
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
                Bitmap bitmap = decodeUri(AddTruckActivity.this,selectedImageUri,250);
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
    public void onSuccessTruckUpload() {
        FBToast.successToast(AddTruckActivity.this,"Successfully Uploaded",FBToast.LENGTH_LONG);
        finish();

    }

    @Override
    public void onFailedTruckUpload() {
        FBToast.errorToast(AddTruckActivity.this,"Failed",FBToast.LENGTH_LONG);

    }
}
