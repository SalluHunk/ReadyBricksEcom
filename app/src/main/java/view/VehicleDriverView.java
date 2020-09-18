package view;

import network.VehicleAndDriverData;

public interface VehicleDriverView {
    void onSuccessVehicleDriver(VehicleAndDriverData vehicleAndDriverData);
    void onFailedVehicleDriver();
}
