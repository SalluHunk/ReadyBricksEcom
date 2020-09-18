package network;

import com.google.gson.annotations.SerializedName;

public class StateList {

    @SerializedName("StateId")
    public String stateId;


    @SerializedName("SCountryId")
    public String cId;

    @SerializedName("SName")
    public String sName;

    public String getcId() {
        return cId;
    }

    public String getsName() {
        return sName;
    }

    public String getStateId() {
        return stateId;
    }
}
