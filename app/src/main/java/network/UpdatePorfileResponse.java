package network;

import com.google.gson.annotations.SerializedName;

public class UpdatePorfileResponse {

    @SerializedName("UserId")
    public String userId;

    @SerializedName("CompanyName")
    public String companyName;

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("VatNo")
    public String vatNo;

    @SerializedName("GstNo")
    public String gstNo;

    @SerializedName("Address")
    public String address;

    @SerializedName("Landmark")
    public String landmark;

    @SerializedName("CountryId")
    public String countryId;

    @SerializedName("StateId")
    public String stateId;

    @SerializedName("DistrictId")
    public String districtId;

    @SerializedName("CityId")
    public String cityId;

    @SerializedName("success")
    public int success;

    public UpdatePorfileResponse(String userId,String companyName,String firstName,String lastName,String address,String landmark,String vatNo,String gstNo,String countryId,String stateId,String districtId,String cityId)
    {
        this.userId = userId;
        this.companyName =companyName;
        this.firstName  =firstName;
        this.lastName =lastName;
        this.address = address;
        this.landmark = landmark;
        this.vatNo =vatNo;
        this.gstNo = gstNo;
        this.countryId=  countryId;
        this.stateId = stateId;
        this.cityId = cityId;
        this.districtId = districtId;
    }

    public int getSuccess() {
        return success;
    }
}
