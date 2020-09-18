package network;

import com.google.gson.annotations.SerializedName;

public class DriverData {

    @SerializedName("DId")
    public String driverId;

    @SerializedName("DFirstName")
    public String firstName;

    @SerializedName("DLastName")
    public String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDriverId() {
        return driverId;
    }
}
