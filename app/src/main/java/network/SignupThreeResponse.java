package network;

import com.google.gson.annotations.SerializedName;

public class SignupThreeResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Address")
    public String address;

    @SerializedName("CountryId")
    public String countryId;

    @SerializedName("StateId")
    public String stateId;

    @SerializedName("CityId")
    public String cityId;

    @SerializedName("Landmark")
    public String landmark;

    @SerializedName("DistrictId")
    public String dId;

    @SerializedName("data")
    SocialMedia socialMedia;


    public SignupThreeResponse(String userId,String address,String countryId,String stateId,String cityId,String landmark,String dId)
    {
        this.dId = dId;
        this.userId = userId;
        this.address = address;
        this.countryId = countryId;
        this.stateId = stateId;
        this.cityId = cityId;
        this.landmark = landmark;
    }

    public int getSuccess() {
        return success;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }
}
