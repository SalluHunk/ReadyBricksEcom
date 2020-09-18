package view;

import java.util.ArrayList;

import network.OrderData;

public interface ManuOrderListView {
    void onSuccessOrderList(ArrayList<OrderData> orderDataArrayList);
    void onFailedSuccessOrderList();
}
