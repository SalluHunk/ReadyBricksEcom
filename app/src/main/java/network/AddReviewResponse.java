package network;

import com.google.gson.annotations.SerializedName;

public class AddReviewResponse {

    @SerializedName("OdId")
    public String orderId;

    @SerializedName("ProductId")
    public String productId;

    @SerializedName("UserId")
    public String userId;

    @SerializedName("TransporterId")
    public String transId;

    @SerializedName("TransReview")
    public String transReview;

    @SerializedName("TransDescription")
    public String transDesc;

    @SerializedName("ManufactureId")
    public String manuId;

    @SerializedName("ManuReview")
    public String manuReview;

    @SerializedName("ManuDescription")
    public String manuDesc;

    @SerializedName("success")
    public int success;

    public AddReviewResponse(String orderId,String productId,String userId,String transId,String transReview,String transDesc,String manuId,String manuReview,String manuDesc)
    {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.transDesc = transDesc;
        this.transId = transId;
        this.transReview = transReview;
        this.manuId = manuId;
        this.manuDesc = manuDesc;
        this.manuReview = manuReview;
    }

    public int getSuccess() {
        return success;
    }
}
