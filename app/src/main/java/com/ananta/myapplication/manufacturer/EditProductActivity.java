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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import impl.AddProductPresenterImpl;
import impl.ProductPresenterImpl;
import presenter.AddProductPresenter;
import presenter.UpdateProdcutPresenter;
import util.AlaBricks;
import view.UpdateProductView;

public class EditProductActivity extends AppCompatActivity implements UpdateProductView {
    private MaterialEditText edAvailibility,edProductName,edDescription,edAdditioanlInfo,edPrice;
    private ImageView imgUploadProduct,imgBack;
    LoadingButton btnProceed;
    TextView txtUpload;
    private Typeface typefaceRegular,typefaceBold;
    private String imgName="";
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    private TextView txtMessage;
    UpdateProdcutPresenter updateProdcutPresenter;
    String isImage="0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_edit_product);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        updateProdcutPresenter = new ProductPresenterImpl(this);
        initUi();
        clickListener();
        progressDialog = new ProgressDialog(EditProductActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        setupData();


    }

    public void initUi()
    {
        edProductName= (MaterialEditText)findViewById(R.id.edProductName);
        edDescription = (MaterialEditText)findViewById(R.id.edDescription);
        edAdditioanlInfo = (MaterialEditText)findViewById(R.id.edAdditioanlInfo);
        edPrice = (MaterialEditText)findViewById(R.id.edPrice);
        edAvailibility = (MaterialEditText)findViewById(R.id.edAvailibility);
        txtMessage = (TextView)findViewById(R.id.txtMessage);

        imgBack = (ImageView)findViewById(R.id.imgBack);
        imgUploadProduct = (ImageView)findViewById(R.id.imgUploadProduct);

        txtUpload = (TextView)findViewById(R.id.txtUpload);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");
    }

    public void clickListener()
    {
        imgUploadProduct.setOnClickListener(new View.OnClickListener() {
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

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!AlaBricks.validateBlankValidation(edProductName))
                {
                    FBToast.errorToast(EditProductActivity.this, "Please Enter Product Name", FBToast.LENGTH_SHORT);
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edDescription))
                    {
                        FBToast.errorToast(EditProductActivity.this, "Please Enter Product Description", FBToast.LENGTH_SHORT);
                    }
                    else
                    {
                        if(!AlaBricks.validateBlankValidation(edPrice))
                        {
                            FBToast.errorToast(EditProductActivity.this, "Please Enter Price", FBToast.LENGTH_SHORT);
                        }
                        else
                        {
                            if(!AlaBricks.validateBlankValidation(edAvailibility))
                            {
                                FBToast.errorToast(EditProductActivity.this, "Please Enter Days Availibility", FBToast.LENGTH_SHORT);
                            }
                            else
                            {
                                progressDialog.show();
                                updateProdcutPresenter.updateProduct(AlaBricks.productData.getProductId(),AlaBricks.getStringFromEditText(edProductName),AlaBricks.getStringFromEditText(edAvailibility),AlaBricks.getStringFromEditText(edPrice),AlaBricks.getStringFromEditText(edDescription),AlaBricks.getStringFromEditText(edAdditioanlInfo),imgName,isImage);
                            }
                        }
                    }
                }
            }
        });
    }

    public void setupData()
    {
        edProductName.setText(AlaBricks.productData.getProductName());
        edDescription.setText(AlaBricks.productData.getProductDescription());
        edAdditioanlInfo.setText(AlaBricks.productData.getProductAdditionalInfo());
        edPrice.setText(AlaBricks.productData.getProductPrice());
        edAvailibility.setText(AlaBricks.productData.getMinDays());
        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide.with(EditProductActivity.this)
                .load(AlaBricks.imagePath +AlaBricks.productData.getProductImage()).apply(options).into(imgUploadProduct);

        if(AlaBricks.productData.getProductEdit().equals("1"))
        {
            edPrice.setEnabled(false);
            edDescription.setEnabled(false);
            edAdditioanlInfo.setEnabled(false);
            edProductName.setEnabled(false);
            edAvailibility.setEnabled(false);
            btnProceed.setVisibility(View.GONE);
            txtMessage.setText("Product is Edited and is under review.");
            txtMessage.setTypeface(typefaceBold);
            txtMessage.setVisibility(View.VISIBLE);
            imgUploadProduct.setClickable(false);
        }

        if(AlaBricks.productData.getProductAccount().equals("0"))
        {
            edPrice.setEnabled(false);
            edDescription.setEnabled(false);
            edAdditioanlInfo.setEnabled(false);
            edProductName.setEnabled(false);
            edAvailibility.setEnabled(false);
            btnProceed.setVisibility(View.GONE);
            txtMessage.setText("Product is under review.");
            txtMessage.setVisibility(View.VISIBLE);
            txtMessage.setTypeface(typefaceBold);
            imgUploadProduct.setClickable(false);
        }
        if(AlaBricks.productData.getProductAccount().equals("2"))
        {
            txtMessage.setTypeface(typefaceBold);
            txtMessage.setVisibility(View.VISIBLE);
            txtMessage.setText("Reason: "+AlaBricks.productData.getReason());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("REQUEST CODE",requestCode+" is the request code");
        if(requestCode==2 && resultCode==RESULT_OK)
        {
            isImage = "1";
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
                Bitmap bitmap = decodeUri(EditProductActivity.this,selectedImageUri,250);
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
    public void onSuccessProductUpdate() {
        FBToast.successToast(EditProductActivity.this,"Product has been updated",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedProductUpdate() {

    }
}
