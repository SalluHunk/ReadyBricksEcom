package network;

import com.google.gson.annotations.SerializedName;

import java.sql.Driver;
import java.util.ArrayList;

public class VehicleAndDriverData {

    @SerializedName("vehicle")
    ArrayList<VehicleData> vehicleData;

    @SerializedName("driver")
    ArrayList<DriverData> driverData;

    public ArrayList<DriverData> getDriverData() {
        return driverData;
    }

    public ArrayList<VehicleData> getVehicleData() {
        return vehicleData;
    }
}
