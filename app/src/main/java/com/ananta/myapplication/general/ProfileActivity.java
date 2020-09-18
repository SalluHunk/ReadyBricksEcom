package com.ananta.myapplication.general;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.ProcessOrderFirstActivithy;
import com.ananta.myapplication.customer.ProcessOrderSecondActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;
import impl.AddressPresenterImpl;
import impl.CheckAddressPresenterImpl;
import impl.UpdateProfilePresenterImpl;
import impl.UserProfilePresenterImpl;
import items.OrderProcess;
import network.AddressData;
import network.CityList;
import network.CountryList;
import network.DistrictList;
import network.StateList;
import network.UserProfileData;
import presenter.AddressPresenter;
import presenter.CheckAddressPresenter;
import presenter.UpdateProfilePresenter;
import presenter.UserProfilePresenter;
import util.AlaBricks;
import view.AddressView;
import view.CheckAddressView;
import view.UpdateProfileView;
import view.UserProfileView;

public class ProfileActivity extends AppCompatActivity implements UserProfileView, AddressView, CheckAddressView, UpdateProfileView {

    boolean isCompany =false;
    UpdateProfilePresenter updateProfilePresenter;
    UserProfilePresenter userProfilePresenter;
    SharedPreferences sharedPreferences;
    private MaterialSpinner spiCity,spiCountry,spiState,spiDistrict;
    private MaterialEditText edFirstName,edLastName,edEmail,edPhone,edAddress,edAddressTwo,edLandmark;
    String cId="",sId="",cityId="";
    ArrayList<String> countryName = new ArrayList<>();
    ArrayList<String> countryId = new ArrayList<>();
    ArrayList<String> stateName = new ArrayList<>();
    ArrayList<String> stateId = new ArrayList<>();
    ArrayList<String> cityName = new ArrayList<>();
    ArrayList<String> cityarrId = new ArrayList<>();

    ArrayList<StateList> filterStateLists=new ArrayList<>();
    ArrayList<CityList> filterCityLists=new ArrayList<>();
    ArrayList<DistrictList> filterDistrictLists=new ArrayList<>();

    ArrayList<CountryList> coLists=new ArrayList<>();
    ArrayList<StateList> stLists=new ArrayList<>();
    ArrayList<CityList> ciLists=new ArrayList<>();
    ArrayList<DistrictList> districtLists=new ArrayList<>();

    private String dId="";
    private ArrayList<String> districtName= new ArrayList<>();
    ArrayList<String> districtId = new ArrayList<>();
    CheckAddressPresenter checkAddressPresenter;
    private boolean isLoadedAddress=false;
    UserProfileData userProfileData;
    AddressPresenter addressPresenter;
    private Button btnSubmit;
    private MaterialEditText edCompanyName;
    private MaterialEditText edGST,edVAT;
    private ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        updateProfilePresenter = new UpdateProfilePresenterImpl(this);
        checkAddressPresenter = new CheckAddressPresenterImpl(this);
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        userProfilePresenter= new UserProfilePresenterImpl(this);
        addressPresenter = new AddressPresenterImpl(this);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        imgBack = (ImageView)findViewById(R.id.imgBack);

        edFirstName = (MaterialEditText)findViewById(R.id.edFirstName);
        edLastName = (MaterialEditText)findViewById(R.id.edLastName);
        edEmail = (MaterialEditText)findViewById(R.id.edEmail);
        edPhone = (MaterialEditText)findViewById(R.id.edPhone);
        edAddress = (MaterialEditText)findViewById(R.id.edAddress);
        edLandmark  = (MaterialEditText)findViewById(R.id.edLandmark);
        edAddressTwo  = (MaterialEditText)findViewById(R.id.edAddressTwo);
        edCompanyName = (MaterialEditText)findViewById(R.id.edCompanyName);
        edVAT = (MaterialEditText)findViewById(R.id.edVAT);
        edGST = (MaterialEditText)findViewById(R.id.edGST);


        spiCountry  = (MaterialSpinner)findViewById(R.id.spiCountry);
        spiState = (MaterialSpinner)findViewById(R.id.spiState);
        spiDistrict = (MaterialSpinner)findViewById(R.id.spiDistrict);
        spiCity = (MaterialSpinner)findViewById(R.id.spiCity);

        adapters();

        addressPresenter.loadAddresses();


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCompany)
                {
                    if(!AlaBricks.validateBlankValidation(edCompanyName))
                    {
                        FBToast.errorToast(ProfileActivity.this,"Please Enter Company Name",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        if(!AlaBricks.validateBlankValidation(edVAT))
                        {
                            FBToast.errorToast(ProfileActivity.this,"Please Enter VAT",FBToast.LENGTH_LONG);
                        }
                        else
                        {
                            if(!AlaBricks.validateBlankValidation(edGST))
                            {
                                FBToast.errorToast(ProfileActivity.this,"Please Enter GST",FBToast.LENGTH_LONG);
                            }
                            else
                            {
                                if(!AlaBricks.validateBlankValidation(edAddress))
                                {
                                    FBToast.errorToast(ProfileActivity.this,"Please Enter Address",FBToast.LENGTH_LONG);
                                }
                                else
                                {
                                    if(!AlaBricks.validateBlankValidation(edLandmark))
                                    {
                                        FBToast.errorToast(ProfileActivity.this,"Please Enter Landmark",FBToast.LENGTH_LONG);
                                    }
                                    else
                                    {
                                        if(cId.equals(""))
                                        {
                                            Toast.makeText(ProfileActivity.this, "Please Select Country", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            if(sId.equals(""))
                                            {
                                                Toast.makeText(ProfileActivity.this, "Please Select State", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                if(cityId.equals(""))
                                                {
                                                    Toast.makeText(ProfileActivity.this, "Please Select City", Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    if(dId.equals(""))
                                                    {
                                                        Toast.makeText(ProfileActivity.this, "Please Select District", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else
                                                    {
                                                        updateProfilePresenter.updateProfile(sharedPreferences.getString(AlaBricks.sharedName,""),AlaBricks.getStringFromEditText(edCompanyName),"","",AlaBricks.getStringFromEditText(edVAT),AlaBricks.getStringFromEditText(edGST),AlaBricks.getStringFromEditText(edAddress)+" | "+AlaBricks.getStringFromEditText(edAddressTwo),AlaBricks.getStringFromEditText(edLandmark),cId,sId,dId,cityId);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                else
                {
                    if(!AlaBricks.validateBlankValidation(edFirstName))
                    {
                        FBToast.errorToast(ProfileActivity.this,"Please Enter First Name",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        if(!AlaBricks.validateBlankValidation(edLastName))
                        {
                            FBToast.errorToast(ProfileActivity.this,"Please Enter First Name",FBToast.LENGTH_LONG);
                        }
                        else
                        {
                            if(!AlaBricks.validateBlankValidation(edAddress))
                            {
                                FBToast.errorToast(ProfileActivity.this,"Please Enter Address",FBToast.LENGTH_LONG);
                            }
                            else
                            {
                                if(!AlaBricks.validateBlankValidation(edLandmark))
                                {
                                    FBToast.errorToast(ProfileActivity.this,"Please Enter Landmark",FBToast.LENGTH_LONG);
                                }
                                else
                                {
                                    if(cId.equals(""))
                                    {
                                        Toast.makeText(ProfileActivity.this, "Please Select Country", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        if(sId.equals(""))
                                        {
                                            Toast.makeText(ProfileActivity.this, "Please Select State", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            if(cityId.equals(""))
                                            {
                                                Toast.makeText(ProfileActivity.this, "Please Select City", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                if(dId.equals(""))
                                                {
                                                    Toast.makeText(ProfileActivity.this, "Please Select District", Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    updateProfilePresenter.updateProfile(sharedPreferences.getString(AlaBricks.sharedUserId,""),"",AlaBricks.getStringFromEditText(edFirstName),AlaBricks.getStringFromEditText(edLastName),"","",AlaBricks.getStringFromEditText(edAddress)+" | "+AlaBricks.getStringFromEditText(edAddressTwo),AlaBricks.getStringFromEditText(edLandmark),cId,sId,dId,cityId);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onSuccessProfileView(UserProfileData userProfileData) {
        isLoadedAddress = true;
        if(userProfileData.getCompanyName().equals(""))
        {
            isCompany = false;
            edCompanyName.setVisibility(View.GONE);
            edVAT.setVisibility(View.GONE);
            edGST.setVisibility(View.GONE);
        }
        else
        {
            isCompany = true;
            edFirstName.setVisibility(View.GONE);
            edLastName.setVisibility(View.GONE);

        }
        edEmail.setEnabled(false);
        edPhone.setEnabled(false);



        this.userProfileData = userProfileData;
        String[] address = userProfileData.getAddress().split("\\|");
        edFirstName.setText(userProfileData.getFirstName());
        edLastName.setText(userProfileData.getLastName());
        edPhone.setText(userProfileData.getMobile());
        edEmail.setText(userProfileData.getEmail());
        edAddress.setText(address[0]);
        edLandmark.setText(userProfileData.getLandmark());

        if(address.length==2)
            edAddressTwo.setText(address[1]);

        cId = userProfileData.getCountryId();
        int countryIndex = countryId.indexOf(cId);
        spiCountry.setSelection(countryIndex+1);

    }

    @Override
    public void onFailedProfileView() {

    }

    @Override
    public void onSuccessAddress(ArrayList<CountryList> countryLists, ArrayList<StateList> stateLists, ArrayList<CityList> cityLists, ArrayList<DistrictList> districtLists) {
        this.coLists = countryLists;
        this.stLists = stateLists;
        this.ciLists = cityLists;
        this.districtLists = districtLists;

        for (int i=0;i<countryLists.size();i++)
        {
            countryName.add(countryLists.get(i).getcName());
            countryId.add(countryLists.get(i).getcId());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfileActivity.this, android.R.layout.simple_spinner_item,countryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiCountry.setAdapter(adapter);
        SharedPreferences sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        userProfilePresenter.displayUserProfile(sharedPreferences.getString(AlaBricks.sharedUserId,""),sharedPreferences.getString(AlaBricks.sharedUserType,""));

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
                        stateId.clear();
                        for (int i=0;i<filterStateLists.size();i++)
                        {
                            stateName.add(filterStateLists.get(i).getsName());
                            stateId.add(filterStateLists.get(i).getStateId());
                        }
                        if(!isLoadedAddress)
                        {
                            sId = "";
                        }
                        else
                        {
                            sId =  userProfileData.getStateId();
                            int stateIndex = stateId.indexOf(sId);
                            spiState.setSelection(stateIndex+1);
                        }
                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(ProfileActivity.this, android.R.layout.simple_spinner_item,stateName);
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
                        districtId.clear();
                        for (int i=0;i<filterDistrictLists.size();i++)
                        {
                            districtId.add(filterDistrictLists.get(i).getDistrictId());
                            districtName.add(filterDistrictLists.get(i).getdName());
                        }

                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(ProfileActivity.this, android.R.layout.simple_spinner_item,districtName);
                        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spiDistrict.setAdapter(adapterState);
                        if(!isLoadedAddress)
                        {
                            dId="";
                        }
                        else
                        {
                            dId = userProfileData.getDistrictId();
                            int districtIndex = districtId.indexOf(dId);
                            spiDistrict.setSelection(districtIndex+1);
                        }
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
                        cityarrId.clear();
                        for (int i=0;i<filterCityLists.size();i++)
                        {
                            cityName.add(filterCityLists.get(i).getcName());
                            cityarrId.add(filterCityLists.get(i).getCityId());
                        }

                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(ProfileActivity.this, android.R.layout.simple_spinner_item,cityName);
                        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spiCity.setAdapter(adapterState);


                        if(!isLoadedAddress)
                        {
                            cityId="";
                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(isLoadedAddress)
                                {
                                    cityId = userProfileData.getCityId();
                                    int cityIndex= cityarrId.indexOf(cityId);
                                    spiCity.setSelection(cityIndex+1);
                                    isLoadedAddress=false;
                                }
                            }
                        }, 2000);

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
    public void onSuccessAddress(AddressData addressData) {
       // progressDialog.dismiss();


   /*     final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                sId =  newAddressData.getStateId();
                int stateIndex = stateId.indexOf(sId);
                spiState.setSelection(stateIndex+1);
            }
        }, 2000);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                dId = newAddressData.getDistrictId();
                int districtIndex = districtId.indexOf(dId);
                spiDistrict.setSelection(districtIndex+1);
            }
        }, 2000);

        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                cityId = newAddressData.getCityId();
                int cityIndex= cityarrId.indexOf(cityId);
                spiCity.setSelection(cityIndex+1);
            }
        }, 2000);


*/

    }

    @Override
    public void onFailedAddress() {
      //  progressDialog.dismiss();
    }


    @Override
    public void onSuccessUpdateProfile() {
        FBToast.successToast(ProfileActivity.this,"Profile has been activated successfully",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedUpdateProfile() {

    }
}
