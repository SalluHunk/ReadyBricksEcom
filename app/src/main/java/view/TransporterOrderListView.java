package view;

import java.util.ArrayList;

import network.OrderData;

public interface TransporterOrderListView {
    void onSuccessTransporterOrderList(ArrayList<OrderData> orderDataArrayList);
    void onFailedTransporterOrderList();
}
