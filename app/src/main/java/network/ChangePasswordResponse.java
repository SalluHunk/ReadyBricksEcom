package network;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;

public class ChangePasswordResponse {

    @SerializedName("UserId")
    public String userId;

    @SerializedName("CurrentPassword")
    public String currentPassword;

    @SerializedName("NewPassword")
    public String newPassword;

    @SerializedName("success")
    public int success;

    public ChangePasswordResponse(String userId,String currentPassword,String newPassword)
    {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }
}
