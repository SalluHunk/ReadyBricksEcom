package network;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Role")
    public String userType;

    public String getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }
}
