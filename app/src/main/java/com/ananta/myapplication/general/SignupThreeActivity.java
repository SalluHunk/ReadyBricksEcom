package com.ananta.myapplication.general;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.CustomerDashboard;
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

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;
import impl.AddressPresenterImpl;
import impl.SignupThreePresenterImpl;
import network.CityList;
import network.CountryList;
import network.DistrictList;
import network.SocialMedia;
import network.StateList;
import presenter.AddressPresenter;
import presenter.SignupStepPresenter;
import util.AlaBricks;
import view.AddressView;
import view.SignupStepThreeView;

public class SignupThreeActivity extends AppCompatActivity implements AddressView, SignupStepThreeView {

    MaterialSpinner spiCountry,spiState,spiCity;
    MaterialEditText edAddressTwo,edAddress,edLandmark;
    private LoadingButton btnProceed;
    private ImageView imgBack;
    AddressPresenter addressPresenter;
    String signupId="";
    ProgressDialog progressDialog;

    ArrayList<CountryList> coLists=new ArrayList<>();
    ArrayList<StateList> stLists=new ArrayList<>();
    ArrayList<DistrictList> districtLists=new ArrayList<>();

    ArrayList<CityList> ciLists=new ArrayList<>();

    ArrayList<StateList> filterStateLists=new ArrayList<>();
    ArrayList<CityList> filterCityLists=new ArrayList<>();
    ArrayList<DistrictList> filterDistrictLists=new ArrayList<>();

    String cId="",sId="",dId="",cityId="";
    ArrayList<String> countryName = new ArrayList<>();
    ArrayList<String> stateName = new ArrayList<>();
    ArrayList<String> districtName = new ArrayList<>();
    ArrayList<String> cityName = new ArrayList<>();
    private TextView txtSignup;
    private Typeface typefaceRegular,typefaceBold;
    SignupStepPresenter signupStepPresenter;
    private GoogleSignInClient mGoogleSignInClient;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private MaterialSpinner spiDistrict;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_three);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        progressDialog = new ProgressDialog(SignupThreeActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        txtSignup = (TextView)findViewById(R.id.txtSignup);

        signupStepPresenter = new SignupThreePresenterImpl(this);
        addressPresenter = new AddressPresenterImpl(this);
        spiCountry = (MaterialSpinner)findViewById(R.id.spiCountry);
        spiState = (MaterialSpinner)findViewById(R.id.spiState);
        spiCity = (MaterialSpinner)findViewById(R.id.spiCity);
        spiDistrict = (MaterialSpinner)findViewById(R.id.spiDistrict);

        edAddressTwo = (MaterialEditText)findViewById(R.id.edAddressTwo);
        edAddress = (MaterialEditText)findViewById(R.id.edAddress);
        edLandmark = (MaterialEditText)findViewById(R.id.edLandmark);

        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        imgBack = (ImageView)findViewById(R.id.imgBack);


        signupId = getIntent().getStringExtra("signupId");

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtSignup.setTypeface(typefaceBold);


        addressPresenter.loadAddresses();

        adapters();

        clickListeners();




    }

    public void clickListeners()
    {
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cId.equals(""))
                {
                    FBToast.errorToast(SignupThreeActivity.this,getString(R.string.txt_country_error),FBToast.LENGTH_SHORT);
                }
                else
                {
                    if(sId.equals(""))
                    {
                        FBToast.errorToast(SignupThreeActivity.this,getString(R.string.txt_state_error),FBToast.LENGTH_SHORT);
                    }
                    else
                    {
                        if(cityId.equals(""))
                        {
                            FBToast.errorToast(SignupThreeActivity.this,getString(R.string.txt_city_error),FBToast.LENGTH_SHORT);
                        }
                        else
                        {
                            if(dId.equals(""))
                            {
                                FBToast.errorToast(SignupThreeActivity.this,"Please Select District",FBToast.LENGTH_SHORT);
                            }
                            else
                            {
                                if(!AlaBricks.validateBlankValidation(edAddress))
                                {
                                    FBToast.errorToast(SignupThreeActivity.this,getString(R.string.txt_address_error),FBToast.LENGTH_SHORT);
                                }
                                else
                                {
                                    if(!AlaBricks.validateBlankValidation(edLandmark))
                                    {
                                        FBToast.errorToast(SignupThreeActivity.this,getString(R.string.txt_landmark_error),FBToast.LENGTH_SHORT);
                                    }
                                    else
                                    {
                                        progressDialog.show();
                                        signupStepPresenter.signupLast(signupId,AlaBricks.getStringFromEditText(edAddress)+" | "+AlaBricks.getStringFromEditText(edAddressTwo),AlaBricks.getStringFromEditText(edLandmark),cId,sId,cityId,dId);
                                    }
                                }
                            }
                        }
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
                mGoogleSignInClient = GoogleSignIn.getClient(SignupThreeActivity.this, gso);

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

                Intent intent = new Intent(SignupThreeActivity.this,MainActivity.class);
                intent.putExtra("path","normal");
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    @Override
    public void onSuccessAddress(ArrayList<CountryList> countryLists, ArrayList<StateList> stateLists, ArrayList<CityList> cityLists,ArrayList<DistrictList> districtLists) {

        this.coLists = countryLists;
        this.stLists = stateLists;
        this.ciLists = cityLists;
        this.districtLists = districtLists;

        for (int i=0;i<countryLists.size();i++)
        {
            countryName.add(countryLists.get(i).getcName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupThreeActivity.this, android.R.layout.simple_spinner_item,countryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiCountry.setAdapter(adapter);
    }

    @Override
    public void onFailedAddress() {

    }

    public void adapters()
    {

        spiCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    cId = coLists.get(position).getcId();
                    filterStateLists.clear();
                    for (int i=0;i<stLists.size();i++)
                    {
                        if(stLists.get(i).getcId().equals(cId))
                        {
                            filterStateLists.add(stLists.get(i));
                        }
                    }

                    if(filterStateLists.size()>0)
                    {
                        stateName.clear();
                        for (int i=0;i<filterStateLists.size();i++)
                        {
                            stateName.add(filterStateLists.get(i).getsName());
                        }
                        sId = "";
                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(SignupThreeActivity.this, android.R.layout.simple_spinner_item,stateName);
                        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spiState.setAdapter(adapterState);
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spiState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    sId = filterStateLists.get(position).getStateId();
                    filterDistrictLists.clear();
                    for (int i=0;i<districtLists.size();i++)
                    {
                        if(districtLists.get(i).getdStateId().equals(sId))
                        {
                            filterDistrictLists.add(districtLists.get(i));
                        }
                    }

                    if(filterDistrictLists.size()>0)
                    {
                        districtName.clear();
                        for (int i=0;i<filterDistrictLists.size();i++)
                        {
                            districtName.add(filterDistrictLists.get(i).getdName());
                        }
                        dId="";
                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(SignupThreeActivity.this, android.R.layout.simple_spinner_item,districtName);
                        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spiDistrict.setAdapter(adapterState);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spiDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    dId = filterDistrictLists.get(position).getDistrictId();
                    filterCityLists.clear();
                    for (int i=0;i<ciLists.size();i++)
                    {
                        if(ciLists.get(i).getDistrictId().equals(dId))
                        {
                            filterCityLists.add(ciLists.get(i));
                        }
                    }

                    if(filterCityLists.size()>0)
                    {
                        cityName.clear();
                        for (int i=0;i<filterCityLists.size();i++)
                        {
                            cityName.add(filterCityLists.get(i).getcName());
                        }
                        cityId="";
                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(SignupThreeActivity.this, android.R.layout.simple_spinner_item,cityName);
                        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spiCity.setAdapter(adapterState);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spiCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    cityId = filterCityLists.get(position).getCityId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(SignupThreeActivity.this,MainActivity.class);
                        intent.putExtra("path","normal");
                        startActivity(intent);
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onSuccessSignupStepThree(SocialMedia socialMedia) {
        progressDialog.dismiss();
        AlaBricks.globalCustomer = null;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(SignupThreeActivity.this, gso);

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
        editor.commit();

        if(socialMedia.getRole().equals("2"))
        {
            editor.putString(AlaBricks.sharedUserId,socialMedia.getUserId());
            editor.putString(AlaBricks.sharedUserType,socialMedia.getRole());
            editor.commit();
            FBToast.successToast(SignupThreeActivity.this,"You are successfully Signup As a Customer",FBToast.LENGTH_LONG);
            Intent intent = new Intent(SignupThreeActivity.this, CustomerDashboard.class);
            startActivity(intent);
            finishAffinity();
        }
        else {
            if(socialMedia.getRole().equals("3"))
            {
                FBToast.successToast(SignupThreeActivity.this,"You are successfully Signup As a Manufacturer. Your Account is under Verification.",FBToast.LENGTH_LONG);
            }
            if(socialMedia.getRole().equals("4"))
            {
                FBToast.successToast(SignupThreeActivity.this,"You are successfully Signup As a Transporter.  Your Account is under Verification.",FBToast.LENGTH_LONG);
            }
            Intent intent = new Intent(SignupThreeActivity.this, MainActivity.class);
            intent.putExtra("path","normal");
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    public void onFailedSignupStepThree() {

    }
}
