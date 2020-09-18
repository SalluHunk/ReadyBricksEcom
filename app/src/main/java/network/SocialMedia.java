package network;

import com.google.gson.annotations.SerializedName;

public class SocialMedia {

    @SerializedName("Role")
    public String role;

    @SerializedName("Email")
    public String email;

    @SerializedName("MobileNumber")
    public String MobileNumber;

    @SerializedName("UserId")
    public String UserId;

    @SerializedName("IsNewsletter")
    public String isNewsLetter;

    public String getIsNewsLetter() {
        return isNewsLetter;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return UserId;
    }
}
