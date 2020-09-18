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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.manufacturer.ManufacturerAddProducts;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import impl.AddDriverPresenterImpl;
import presenter.AddDriverPresenter;
import util.AlaBricks;
import view.AddDriverView;

public class AddDriverActivity extends AppCompatActivity implements AddDriverView {
    private MaterialEditText edFirstName,edLastName,edPhone,edPassword;
    private ImageView imgBack,imgDriverPhoto,imgUploadLicense;
    LoadingButton btnProceed;
    String imgName="",imgNameTwo="";
    AddDriverPresenter addDriverPresenter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        addDriverPresenter = new AddDriverPresenterImpl(this);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        imgDriverPhoto = (ImageView)findViewById(R.id.imgDriverPhoto);
        imgUploadLicense = (ImageView)findViewById(R.id.imgUploadLicense);
        edPassword = (MaterialEditText)findViewById(R.id.edPassword);
        edPhone = (MaterialEditText)findViewById(R.id.edPhone);
        edLastName = (MaterialEditText)findViewById(R.id.edLastName);
        edFirstName = (MaterialEditText)findViewById(R.id.edFirstName);
        imgBack = (ImageView)findViewById(R.id.imgBack);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgUploadLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 2);
            }
        });

        imgDriverPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 3);
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validateBlankValidation(edFirstName))
                {
                    FBToast.errorToast(AddDriverActivity.this,"Please Enter First Name",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edLastName))
                    {

                        FBToast.errorToast(AddDriverActivity.this,"Please Enter Last Name",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        if(!AlaBricks.validatePhoneNumber(edPhone))
                        {

                            FBToast.errorToast(AddDriverActivity.this,"Invalid Phone Number",FBToast.LENGTH_LONG);
                        }
                        else
                        {
                            if(!AlaBricks.validatePassword(edPassword))
                            {

                                FBToast.errorToast(AddDriverActivity.this,"Invalid Password. Minimum characters should be 8.",FBToast.LENGTH_LONG);
                            }
                            else
                            {
                                if(imgName.equals(""))
                                {
                                    FBToast.errorToast(AddDriverActivity.this,"Please Upload Driving License",FBToast.LENGTH_LONG);

                                }
                                else
                                {
                                    if(imgNameTwo.equals(""))
                                    {
                                        FBToast.errorToast(AddDriverActivity.this,"Please Uplaod Driver Photo",FBToast.LENGTH_LONG);
                                    }
                                    else
                                    {
                                        addDriverPresenter.addDriver(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.getStringFromEditText(edFirstName),AlaBricks.getStringFromEditText(edLastName),AlaBricks.getStringFromEditText(edPhone),AlaBricks.getStringFromEditText(edPassword),imgName,imgNameTwo);
                                    }
                                }
                            }
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
                Bitmap bitmap = decodeUri(AddDriverActivity.this,selectedImageUri,250);
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
                Bitmap bitmap = decodeUri(AddDriverActivity.this,selectedImageUri,250);
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
    public void onSuccessAddDriver() {
        FBToast.successToast(AddDriverActivity.this,"Successfully Uploaded",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedAddDriver() {
        FBToast.errorToast(AddDriverActivity.this,"Failed",FBToast.LENGTH_LONG);
    }

    @Override
    public void onNoMobileValidationDriver() {
        FBToast.errorToast(AddDriverActivity.this,"Mobile Number Already Exists",FBToast.LENGTH_LONG);

    }
}
