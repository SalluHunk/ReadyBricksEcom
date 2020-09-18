package view;

import java.util.ArrayList;

import network.DriverListData;

public interface DriverListView {
    void onSuccessDriverList(ArrayList<DriverListData> driverListData);
    void onFailedDriverList();
}
