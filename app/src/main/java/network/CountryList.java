package network;

import com.google.gson.annotations.SerializedName;

public class CountryList {

    @SerializedName("CId")
    public String cId;

    @SerializedName("CName")
    public String cName;

    public String getcId() {
        return cId;
    }

    public String getcName() {
        return cName;
    }
}
