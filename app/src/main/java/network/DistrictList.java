package network;

import com.google.gson.annotations.SerializedName;

public class DistrictList {

    @SerializedName("DistrictId")
    public String districtId;

    @SerializedName("DCountryId")
    public String dCountryId;

    @SerializedName("DStateId")
    public String dStateId;

    @SerializedName("DName")
    public String dName;

    public String getdCountryId() {
        return dCountryId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getdName() {
        return dName;
    }

    public String getdStateId() {
        return dStateId;
    }
}
