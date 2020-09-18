package network;

import com.google.gson.annotations.SerializedName;

public class SocialMediaValidation {

    @SerializedName("success")
    public int success;

    @SerializedName("Email")
    public String email;

    @SerializedName("data")
    public SocialMedia socialMedia;

    public SocialMediaValidation(String email)
    {
        this.email = email;
    }

    public int getSuccess() {
        return success;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }
}
