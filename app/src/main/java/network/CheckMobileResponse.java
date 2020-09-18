package network;

import com.google.gson.annotations.SerializedName;

public class CheckMobileResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public SocialMedia socialMedia;

    @SerializedName("MobileNumber")
    public String mobileNumber;

    public CheckMobileResponse(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public int getSuccess() {
        return success;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }
}
