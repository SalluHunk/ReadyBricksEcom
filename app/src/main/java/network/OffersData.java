package network;

import com.google.gson.annotations.SerializedName;

public class OffersData {


    @SerializedName("OImage")
    public String bannerUrl;

    public String getBannerUrl() {
        return bannerUrl;
    }


    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
