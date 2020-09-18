package view;

import java.util.ArrayList;

import network.VehicleListData;

public interface TruckListView {
    void onSuccessTruckList(ArrayList<VehicleListData> vehicleListData);
    void onFailedTruckList();
}
