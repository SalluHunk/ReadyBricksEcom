package network;

import com.google.gson.annotations.SerializedName;

public class ContactUsResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("Title")
    public String title;

    @SerializedName("Description")
    public String desc;


    public ContactUsResponse(String userId,String title,String desc)
    {
        this.userId = userId;
        this.title = title;
        this.desc = desc;
    }

    public int getSuccess() {
        return success;
    }
}
