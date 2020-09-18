package network;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Password")
    public String password;

    public ForgotPasswordResponse(String userId,String password)
    {
        this.userId = userId;
        this.password = password;
    }

    public int getSuccess() {
        return success;
    }
}
