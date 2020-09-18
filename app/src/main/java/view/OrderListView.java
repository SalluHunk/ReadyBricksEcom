package view;

import java.util.ArrayList;

import network.OrderData;

public interface OrderListView  {
    void onSuccessOrderList(ArrayList<OrderData> orderDataArrayList);
    void onFailedOrderList();
}
