package com.ananta.myapplication.customer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.general.SignupThreeActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;
import impl.AddressPresenterImpl;
import impl.CheckAddressPresenterImpl;
import items.OrderProcess;
import network.AddressData;
import network.CityList;
import network.CountryList;
import network.DistrictList;
import network.StateList;
import presenter.AddressPresenter;
import presenter.CheckAddressPresenter;
import util.AlaBricks;
import util.CircleCheckBox;
import view.AddressView;
import view.CheckAddressView;

public class ProcessOrderFirstActivithy extends AppCompatActivity implements AddressView, CheckAddressView {
    private TextView txtConfirmation,txtAddress;
    private Typeface typefaceRegular,typefaceBold;
    private MaterialEditText edAddressTwo,edAddress,edLandmark;
    private MaterialSpinner spiCountry,spiCity,spiState;
    String cId="",sId="",cityId="";
    String strCityName="",strDistrictName="",strStateName="",strCountryName="";
    ArrayList<String> countryName = new ArrayList<>();
    ArrayList<String> countryId = new ArrayList<>();
    ArrayList<String> stateName = new ArrayList<>();
    ArrayList<String> stateId = new ArrayList<>();
    ArrayList<String> cityName = new ArrayList<>();
    ArrayList<String> cityarrId = new ArrayList<>();
    AddressPresenter addressPresenter;
    boolean isSame=false;
    boolean isLoadedAddress=false;
    ArrayList<StateList> filterStateLists=new ArrayList<>();
    ArrayList<CityList> filterCityLists=new ArrayList<>();
    ArrayList<DistrictList> filterDistrictLists=new ArrayList<>();

    ArrayList<CountryList> coLists=new ArrayList<>();
    ArrayList<StateList> stLists=new ArrayList<>();
    ArrayList<CityList> ciLists=new ArrayList<>();
    ArrayList<DistrictList> districtLists=new ArrayList<>();
    private CircleCheckBox chkSame;
    private Button btnBack;
    private Button btnNext;
    private ImageView imgBack;
    private String cartTotal="";
    private MaterialSpinner spiDistrict;
    private String dId="";
    private ArrayList<String> districtName= new ArrayList<>();
    ArrayList<String> districtId = new ArrayList<>();
    AddressData newAddressData;
    CheckAddressPresenter checkAddressPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_order_first);

        progressDialog = new ProgressDialog(ProcessOrderFirstActivithy.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        checkAddressPresenter = new CheckAddressPresenterImpl(this);
        Intent intent = getIntent();
        cartTotal = intent.getExtras().getString("cartTotal");

   /*     txtSameEmail  = (TextView)findViewById(R.id.txtSameEmail);
   */     txtAddress = (TextView)findViewById(R.id.txtAddress);
        txtConfirmation = (TextView)findViewById(R.id.txtConfirmation);
        addressPresenter = new AddressPresenterImpl(this);
        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");
    /*    chkSame = (CircleCheckBox)findViewById(R.id.chkSame);
    */    btnBack = (Button)findViewById(R.id.btnBack);
        btnNext = (Button)findViewById(R.id.btnNext);
        imgBack = (ImageView)findViewById(R.id.imgBack);


  /*      txtSameEmail.setTypeface(typefaceRegular);
  */      txtAddress.setTypeface(typefaceRegular);
        txtConfirmation.setTypeface(typefaceRegular);

        edAddress = (MaterialEditText)findViewById(R.id.edAddress);
        edLandmark = (MaterialEditText)findViewById(R.id.edLandmark);
        edAddressTwo = (MaterialEditText)findViewById(R.id.edAddressTwo);

        spiCity = (MaterialSpinner)findViewById(R.id.spiCity);
        spiState = (MaterialSpinner)findViewById(R.id.spiState);
        spiCountry = (MaterialSpinner)findViewById(R.id.spiCountry);
        spiDistrict = (MaterialSpinner)findViewById(R.id.spiDistrict);
        progressDialog.show();
        addressPresenter.loadAddresses();

      /*  chkSame.setChecked(true);
      */

        /*chkSame.setOnCheckedChangeListener(new CircleCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CircleCheckBox view, boolean isChecked) {
                if(isChecked)
                {
                    disableAllFields();
                    isSame = true;
                }
                else
                {
                    enableAllFields();
                    isSame = false;
                }
            }
        });
*/
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.orderProcess=null;
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(isSame)
                {
                    OrderProcess orderProcess =new OrderProcess("","","","","","",isSame,"");
                    AlaBricks.orderProcess = orderProcess;
                    Intent intent = new Intent(ProcessOrderFirstActivithy.this,ProcessOrderSecondActivity.class);
                    intent.putExtra("cartTotal",cartTotal);
                    startActivity(intent);

                }
                else
                {*/
                    if(!AlaBricks.validateBlankValidation(edAddress))
                    {
                        Toast.makeText(ProcessOrderFirstActivithy.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(!AlaBricks.validateBlankValidation(edLandmark))
                        {
                            Toast.makeText(ProcessOrderFirstActivithy.this, "Please Enter Landmark", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(cId.equals(""))
                            {
                                Toast.makeText(ProcessOrderFirstActivithy.this, "Please Select Country", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(sId.equals(""))
                                {
                                    Toast.makeText(ProcessOrderFirstActivithy.this, "Please Select State", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    if(cityId.equals(""))
                                    {
                                        Toast.makeText(ProcessOrderFirstActivithy.this, "Please Select City", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        if(dId.equals(""))
                                        {
                                            Toast.makeText(ProcessOrderFirstActivithy.this, "Please Select District", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {

                                            strCityName = spiCity.getSelectedItem().toString();
                                            OrderProcess orderProcess =new OrderProcess(AlaBricks.getStringFromEditText(edAddress),AlaBricks.getStringFromEditText(edAddressTwo),AlaBricks.getStringFromEditText(edLandmark),cityId,sId,dId,cId,isSame,"",strCountryName,strStateName,strDistrictName,strCityName);
                                            AlaBricks.orderProcess = orderProcess;
                                            Intent intent = new Intent(ProcessOrderFirstActivithy.this,ProcessOrderSecondActivity.class);
                                            intent.putExtra("cartTotal",cartTotal);
                                            startActivity(intent);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.orderProcess =null;
                finish();
            }
        });

        adapters();

    }
    public void enableAllFields()
    {
        spiCity.setEnabled(true);
        spiState.setEnabled(true);
        spiCountry.setEnabled(true);
        edAddress.setEnabled(true);
        edAddressTwo.setEnabled(true);
        edLandmark.setEnabled(true);
    }
    public void disableAllFields()
    {
        spiCity.setEnabled(false);
        spiState.setEnabled(false);
        spiCountry.setEnabled(false);
        edAddress.setEnabled(false);
        edAddressTwo.setEnabled(false);
        edLandmark.setEnabled(false);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProcessOrderFirstActivithy.this, android.R.layout.simple_spinner_item,countryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiCountry.setAdapter(adapter);
        SharedPreferences sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        checkAddressPresenter.checkAddress(sharedPreferences.getString(AlaBricks.sharedUserId,""));
    }

    public void adapters()
    {

        spiCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0)
                {
                    cId = coLists.get(position).getcId();
                    strCountryName = coLists.get(position).getcName();
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
                            sId =  newAddressData.getStateId();
                            int stateIndex = stateId.indexOf(sId);
                            spiState.setSelection(stateIndex+1);
                        }
                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(ProcessOrderFirstActivithy.this, android.R.layout.simple_spinner_item,stateName);
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
                    strStateName = filterStateLists.get(position).getsName();

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

                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(ProcessOrderFirstActivithy.this, android.R.layout.simple_spinner_item,districtName);
                        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spiDistrict.setAdapter(adapterState);
                        if(!isLoadedAddress)
                        {
                            dId="";
                        }
                        else
                        {
                            dId = newAddressData.getDistrictId();
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
                    strDistrictName = filterDistrictLists.get(position).getdName();

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

                        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(ProcessOrderFirstActivithy.this, android.R.layout.simple_spinner_item,cityName);
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
                                    cityId = newAddressData.getCityId();
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
                    strCityName = filterCityLists.get(position).getcName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onSuccessAddress(AddressData addressData) {
        progressDialog.dismiss();
        isLoadedAddress = true;
        newAddressData = addressData;
        edLandmark.setText(addressData.getLandmark());
        String[] address =  addressData.getAddress().split("\\|");
        edAddress.setText(address[0]);
        if(address.length==2)
             edAddressTwo.setText(address[1]);

        cId = addressData.getCountryId();
        int countryIndex = countryId.indexOf(cId);
        spiCountry.setSelection(countryIndex+1);

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
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        AlaBricks.orderProcess = null;
        finish();
    }
}

