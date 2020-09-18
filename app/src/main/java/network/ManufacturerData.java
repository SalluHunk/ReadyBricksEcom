package network;

import com.google.gson.annotations.SerializedName;

public class ManufacturerData {

    @SerializedName("UserId")
    public String manuId;

    @SerializedName("CompanyName")
    public String manuName;

    public String getManuId() {
        return manuId;
    }

    public String getManuName() {
        return manuName;
    }
}
