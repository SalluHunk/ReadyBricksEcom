package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddressResponse {

    @SerializedName("success")
    public String success;

    @SerializedName("country")
    public ArrayList<CountryList> countryLists;

    @SerializedName("State")
    public ArrayList<StateList> stateLists;

    @SerializedName("city")
    public ArrayList<CityList> cityLists;

    @SerializedName("District")
    public ArrayList<DistrictList> districtLists;


    public ArrayList<DistrictList> getDistrictLists() {
        return districtLists;
    }

    public String getSuccess() {
        return success;
    }

    public ArrayList<CityList> getCityLists() {
        return cityLists;
    }

    public ArrayList<CountryList> getCountryLists() {
        return countryLists;
    }

    public ArrayList<StateList> getStateLists() {
        return stateLists;
    }
}
