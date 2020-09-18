package view;

import java.lang.reflect.Array;
import java.util.ArrayList;

import network.CustomerProductData;

public interface CustomerListProductView {
    void onSuccessCustomerProductList(ArrayList<CustomerProductData> customerProductData);
    void onFailedCustomerProductList();
}
