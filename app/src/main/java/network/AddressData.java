package network;

import com.google.gson.annotations.SerializedName;

public class AddressData{

    @SerializedName("CountryId")
    public String countryId;

    @SerializedName("StateId")
    public String stateId;

    @SerializedName("DistrictId")
    public String districtId;

    @SerializedName("CityId")
    public String cityId;

    @SerializedName("Address")
    public String address;

    @SerializedName("Landmark")
    public String landmark;


    public String getAddress() {
        return address;
    }

    public String getCityId() {
        return cityId;
    }

    public String getStateId() {
        return stateId;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getLandmark() {
        return landmark;
    }
}
