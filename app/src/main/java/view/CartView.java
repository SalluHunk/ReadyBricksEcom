package view;

import java.util.ArrayList;

import network.CartData;

public interface CartView {
    void onSuccessCartDisplay(ArrayList<CartData> cartDataArrayList);
    void onFailedCartDisplay();
}
