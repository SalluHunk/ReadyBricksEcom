package network;

import com.google.gson.annotations.SerializedName;

public class TrackingData {

    @SerializedName("Latitude")
    public String lat;

    @SerializedName("Longitude")
    public String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
