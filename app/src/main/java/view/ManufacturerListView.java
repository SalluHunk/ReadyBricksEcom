package view;

import java.util.ArrayList;

import network.ProductData;

public interface ManufacturerListView {
    void onSuccessManufacturerList(ArrayList<ProductData> productData);
    void onFailedManufacturerList();
}
