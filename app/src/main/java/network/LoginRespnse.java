package network;

import com.google.gson.annotations.SerializedName;

public class LoginRespnse {

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    SocialMedia socialMedia;

    @SerializedName("msg")
    public String message;

    @SerializedName("token")
    public String token;

    public LoginRespnse(String email,String password,String token)
    {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public int getSuccess() {
        return success;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public String getMessage() {
        return message;
    }
}
