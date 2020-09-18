package network;

import com.google.gson.annotations.SerializedName;

public class NewsletterResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("IsNewsletter")
    public String status;

    public NewsletterResponse(String userId,String status)
    {
        this.status  = status;
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }
}
