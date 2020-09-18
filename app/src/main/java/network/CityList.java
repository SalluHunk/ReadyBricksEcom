package network;

import com.google.gson.annotations.SerializedName;

public class CityList {

    @SerializedName("CityId")
    public String cityId;

    @SerializedName("CCountryId")
    public String countryId;

    @SerializedName("CStateId")
    public String stateId;

    @SerializedName("CDistrictId")
    public String districtId;

    @SerializedName("CName")
    public String cName;

    public String getDistrictId() {
        return districtId;
    }

    public String getStateId() {
        return stateId;
    }

    public String getcName() {
        return cName;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCountryId() {
        return countryId;
    }
}
