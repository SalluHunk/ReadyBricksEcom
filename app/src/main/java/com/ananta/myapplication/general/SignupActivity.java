package com.ananta.myapplication.general;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import fr.ganfra.materialspinner.MaterialSpinner;
import items.Customer;
import items.Manufacturer;
import util.AlaBricks;

public class SignupActivity extends AppCompatActivity {
    String[] ITEMS = {"Customer", "Manufacturer", "Transporter"};
    private MaterialSpinner spiUserType;
    TextView txtSignup;
    MaterialEditText edCompanyName,edFirstName,edLastName,edPassword;
    private LoadingButton btnProceed;
    Typeface typefaceBold,typefaceRegular;
    private ImageView imgBack;
    private ImageView imgUpload;
    private TextView txtUpload;
    private String imgName="";
    private GoogleSignInClient mGoogleSignInClient;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private MaterialEditText edCompanyNameForCustomer;
    String path="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Intent intent = getIntent();
        path = intent.getExtras().getString("path");

        AlaBricks.globalCustomer = null;
        AlaBricks.manufacturer = null;
        initUi();

        clickListeners();
    }
    public void initUi()
    {
        imgBack = (ImageView)findViewById(R.id.imgBack);

        spiUserType = (MaterialSpinner)findViewById(R.id.spiUserType);
        txtSignup = (TextView)findViewById(R.id.txtSignup);
        edCompanyName = (MaterialEditText) findViewById(R.id.edCompanyName);
        edFirstName = (MaterialEditText)findViewById(R.id.edFirstName);
        edLastName = (MaterialEditText)findViewById(R.id.edLastName);
        edPassword = (MaterialEditText)findViewById(R.id.edPassword);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        edCompanyNameForCustomer = (MaterialEditText)findViewById(R.id.edCompanyNameForCustomer);
        txtUpload = (TextView)findViewById(R.id.txtUpload);
        imgUpload = (ImageView)findViewById(R.id.imgUpload);


        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        btnProceed.setTypeface(typefaceBold);
        txtSignup.setTypeface(typefaceBold);
        txtUpload.setTypeface(typefaceRegular);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiUserType = (MaterialSpinner) findViewById(R.id.spiUserType);
        spiUserType.setAdapter(adapter);

        spiUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    if(position==0)
                    {
                        AlaBricks.userType = "2";
                        edFirstName.setVisibility(View.VISIBLE);
                        edLastName.setVisibility(View.VISIBLE);
                        edCompanyName.setVisibility(View.GONE);
                        edCompanyNameForCustomer.setVisibility(View.VISIBLE);
                  //      edCompanyName.setHint("Company Name(Optional)");
                        imgUpload.setVisibility(View.GONE);
                        txtUpload.setVisibility(View.GONE);
                        imgName="";
                        imgUpload.setImageResource(R.drawable.upload);
                        btnProceed.setVisibility(View.VISIBLE);
                        edPassword.setVisibility(View.VISIBLE);
                    }

                    else if(position==1)
                    {
                        AlaBricks.userType = "3";
                        edFirstName.setVisibility(View.GONE);
                        edLastName.setVisibility(View.GONE);
                        edCompanyName.setVisibility(View.VISIBLE);
                        edCompanyNameForCustomer.setVisibility(View.GONE);
                  //      edCompanyName.setHint("Company Name");
                        imgUpload.setVisibility(View.VISIBLE);
                        txtUpload.setVisibility(View.VISIBLE);
                        edFirstName.setText("");
                        edLastName.setText("");
                        btnProceed.setVisibility(View.VISIBLE);
                        edPassword.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        AlaBricks.userType = "4";
                        edFirstName.setVisibility(View.GONE);
                        edLastName.setVisibility(View.GONE);
                        edCompanyName.setVisibility(View.VISIBLE);
                        edCompanyNameForCustomer.setVisibility(View.GONE);
                 //       edCompanyName.setHint("Company Name");
                        imgUpload.setVisibility(View.VISIBLE);
                        txtUpload.setVisibility(View.VISIBLE);
                        edFirstName.setText("");
                        edLastName.setText("");
                        btnProceed.setVisibility(View.VISIBLE);
                        edPassword.setVisibility(View.VISIBLE);
                    }

                }
                else
                {
                    AlaBricks.userType="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void clickListeners()
    {

        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 2);

            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(AlaBricks.userType.equals("")) {
                    FBToast.errorToast(SignupActivity.this, getString(R.string.txt_user_error), FBToast.LENGTH_SHORT);
                }
                else
                {
                    if(AlaBricks.userType.equals("2"))
                    {
                        if (!AlaBricks.validateBlankValidation(edFirstName)) {
                            FBToast.errorToast(SignupActivity.this, getString(R.string.txt_firstname_error), FBToast.LENGTH_SHORT);
                        } else {
                            if (!AlaBricks.validateBlankValidation(edLastName)) {
                                FBToast.errorToast(SignupActivity.this, getString(R.string.txt_lastname_error), FBToast.LENGTH_SHORT);
                            } else {
                                if (!AlaBricks.validatePassword(edPassword)) {
                                    FBToast.errorToast(SignupActivity.this, getString(R.string.txt_password_error), FBToast.LENGTH_SHORT);
                                } else {
                                    Customer customer = new Customer(AlaBricks.getStringFromEditText(edFirstName), AlaBricks.getStringFromEditText(edLastName), AlaBricks.getStringFromEditText(edCompanyNameForCustomer), AlaBricks.getStringFromEditText(edPassword));
                                    AlaBricks.globalCustomer = customer;
                                    Intent intent = new Intent(SignupActivity.this, SignupTwoActivity.class);
                                    intent.putExtra("path",path);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                    else if(AlaBricks.userType.equals("3") || AlaBricks.userType.equals("4"))
                    {
                        if(!AlaBricks.validateBlankValidation(edCompanyName))
                        {
                            FBToast.errorToast(SignupActivity.this, getString(R.string.txt_company_error), FBToast.LENGTH_SHORT);
                        }
                        else
                        {
                            if(!AlaBricks.validatePassword(edPassword))
                            {
                                FBToast.errorToast(SignupActivity.this, getString(R.string.txt_password_error), FBToast.LENGTH_SHORT);
                            }
                            else
                            {
                                if(imgName.equals(""))
                                {
                                    FBToast.errorToast(SignupActivity.this, getString(R.string.txt_logo_error), FBToast.LENGTH_SHORT);
                                }
                                else
                                {
                                    Manufacturer manufacturer =  new Manufacturer(AlaBricks.getStringFromEditText(edCompanyName),AlaBricks.getStringFromEditText(edPassword),imgName);
                                    Intent intent = new Intent(SignupActivity.this, SignupTwoActivity.class);
                                    intent.putExtra("path",path);
                                    startActivity(intent);
                                    AlaBricks.manufacturer = manufacturer;
                                }
                            }
                        }
                    }
                    else
                    {

                    }
                }


            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(SignupActivity.this, gso);

                new GraphRequest(com.facebook.AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                        .Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse) {
                        LoginManager.getInstance().logOut();
                    }
                }).executeAsync();

                LoginManager.getInstance().logOut();

                mGoogleSignInClient.signOut();

                editor.putString(AlaBricks.sharedUserId,"");
                editor.putString(AlaBricks.sharedUserType,"");
                editor.putString(AlaBricks.sharedName,"");
                editor.commit();

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        if(AlaBricks.globalCustomer!=null && AlaBricks.userType.equals("2"))
        {
            edCompanyName.setText(AlaBricks.globalCustomer.getCompanyDetails());
            edFirstName.setText(AlaBricks.globalCustomer.getFirstName());
            edLastName.setText(AlaBricks.globalCustomer.getLastName());
            edPassword.setText(AlaBricks.globalCustomer.getPassword());
        }
        super.onResume();
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
                imgUpload.setImageURI(selectedImageUri);

                File imageFile = new File(selectedImageUri.getPath());
            String fileName = imageFile.getName();

            try {
                //Getting the Bitmap from Gallery
//                    Bitmap bitmap=null;
                //                  bitmap= Bitmap.createScaledBitmap(bitmap,100,100,true);
                //Setting the Bitmap to ImageView
                Bitmap bitmap = decodeUri(SignupActivity.this,selectedImageUri,250);
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
    public void onBackPressed() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(SignupActivity.this, gso);

        new GraphRequest(com.facebook.AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();

        LoginManager.getInstance().logOut();

        mGoogleSignInClient.signOut();

        editor.putString(AlaBricks.sharedUserId,"");
        editor.putString(AlaBricks.sharedUserType,"");
        editor.putString(AlaBricks.sharedName,"");
        editor.commit();

        finish();
    }
}
