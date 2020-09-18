package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OffersResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("data")
    public ArrayList<OffersData> offersData;

    public int getSuccess() {
        return success;
    }

    public ArrayList<OffersData> getOffersData() {
        return offersData;
    }
}
