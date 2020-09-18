package network;

import com.google.gson.annotations.SerializedName;

public class UserProfileData {

    @SerializedName("CityName")
    public String cityName;

    @SerializedName("DName")
    public String districtName;

    @SerializedName("CName")
    public String countryName;

    @SerializedName("SName")
    public String stateName;

    @SerializedName("CountryId")
    public String countryId;

    @SerializedName("StateId")
    public String stateId;

    @SerializedName("DistrictId")
    public String districtId;

    @SerializedName("CityId")
    public String cityId;

    @SerializedName("CompanyName")
    public String companyName;

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("MobileNumber")
    public String mobile;

    @SerializedName("Address")
    public String address;

    @SerializedName("Landmark")
    public String landmark;

    @SerializedName("Email")
    public String email;

    @SerializedName("GSTIN")
    public String gst;

    @SerializedName("VatNumber")
    public String vat;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getGst() {
        return gst;
    }

    public String getVat() {
        return vat;
    }

    public String getMobile() {
        return mobile;
    }
}
