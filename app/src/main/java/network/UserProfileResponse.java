package network;

import com.google.gson.annotations.SerializedName;

public class UserProfileResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Role")
    public String role;

    @SerializedName("data")
    public UserProfileData userProfileData;


    public UserProfileResponse(String userId,String role)
    {
        this.role = role;
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public UserProfileData getUserProfileData() {
        return userProfileData;
    }
}
