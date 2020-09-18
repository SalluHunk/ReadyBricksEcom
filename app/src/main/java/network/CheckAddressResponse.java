package network;

import com.google.gson.annotations.SerializedName;

public class CheckAddressResponse {

    @SerializedName("UserId")
    public String userId;

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public AddressData addressData;

    public CheckAddressResponse(String userId)
    {
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public String getUserId() {
        return userId;
    }

    public AddressData getAddressData() {
        return addressData;
    }
}
