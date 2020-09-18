package com.ananta.myapplication.manufacturer;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import impl.AddProductPresenterImpl;
import presenter.AddProductPresenter;
import util.AlaBricks;
import view.AddProductView;

public class ManufacturerAddProducts extends AppCompatActivity implements AddProductView {

    private MaterialEditText edAvailibility,edProductName,edDescription,edAdditioanlInfo,edPrice;
    private ImageView imgUploadProduct,imgBack;
    LoadingButton btnProceed;
    TextView txtUpload;
    private Typeface typefaceRegular,typefaceBold;
    private String imgName="";
    AddProductPresenter addProductPresenter;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_add_product);
        addProductPresenter =  new AddProductPresenterImpl(this);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        initUi();
        clickListener();
        progressDialog = new ProgressDialog(ManufacturerAddProducts.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);



    }

    public void initUi()
    {
        edProductName= (MaterialEditText)findViewById(R.id.edProductName);
        edDescription = (MaterialEditText)findViewById(R.id.edDescription);
        edAdditioanlInfo = (MaterialEditText)findViewById(R.id.edAdditioanlInfo);
        edPrice = (MaterialEditText)findViewById(R.id.edPrice);
        edAvailibility = (MaterialEditText)findViewById(R.id.edAvailibility);

        imgBack = (ImageView)findViewById(R.id.imgBack);
        imgUploadProduct = (ImageView)findViewById(R.id.imgUploadProduct);

        txtUpload = (TextView)findViewById(R.id.txtUpload);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");
    }

    public void clickListener()
    {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

            imgUploadProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 2);
                }
            });

            btnProceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!AlaBricks.validateBlankValidation(edProductName))
                    {
                        FBToast.errorToast(ManufacturerAddProducts.this, "Please Enter Product Name", FBToast.LENGTH_SHORT);
                    }
                    else
                    {
                        if(!AlaBricks.validateBlankValidation(edDescription))
                        {
                            FBToast.errorToast(ManufacturerAddProducts.this, "Please Enter Product Description", FBToast.LENGTH_SHORT);
                        }
                        else
                        {
                            if(!AlaBricks.validateBlankValidation(edPrice))
                            {
                                FBToast.errorToast(ManufacturerAddProducts.this, "Please Enter Price", FBToast.LENGTH_SHORT);
                            }
                            else
                            {
                                if(!AlaBricks.validateBlankValidation(edAvailibility))
                                {
                                    FBToast.errorToast(ManufacturerAddProducts.this, "Please Enter Days Availibility", FBToast.LENGTH_SHORT);
                                }
                                else
                                {
                                    if(imgName.equals(""))
                                    {
                                        FBToast.errorToast(ManufacturerAddProducts.this, "Please Upload Product Image", FBToast.LENGTH_SHORT);
                                    }
                                    else
                                    {
                                        progressDialog.show();
                                        addProductPresenter.addProduct(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.getStringFromEditText(edProductName),AlaBricks.getStringFromEditText(edDescription),AlaBricks.getStringFromEditText(edAdditioanlInfo),AlaBricks.getStringFromEditText(edPrice),AlaBricks.getStringFromEditText(edAvailibility),imgName);
                                    }
                                }
                            }
                        }
                    }
                }
            });
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
            imgUploadProduct.setImageURI(selectedImageUri);

            File imageFile = new File(selectedImageUri.getPath());
            String fileName = imageFile.getName();

            try {
                //Getting the Bitmap from Gallery
//                    Bitmap bitmap=null;
                //                  bitmap= Bitmap.createScaledBitmap(bitmap,100,100,true);
                //Setting the Bitmap to ImageView
                Bitmap bitmap = decodeUri(ManufacturerAddProducts.this,selectedImageUri,250);
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
    public void onSuccessProductAdd() {
        progressDialog.dismiss();
       FBToast.successToast(ManufacturerAddProducts.this,"Product Added",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedProductAdd() {
        progressDialog.dismiss();
        FBToast.errorToast(ManufacturerAddProducts.this, "Failed", FBToast.LENGTH_SHORT);
    }
}
