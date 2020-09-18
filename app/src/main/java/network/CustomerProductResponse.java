package network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerProductResponse {

    @SerializedName("data")
    public ArrayList<CustomerProductData> customerProductDataArrayList;

    @SerializedName("success")
    public int success;

    @SerializedName("manufacturer")
    public ArrayList<ManufacturerData> manufacturerData;

    @SerializedName("Manufacture")
    public String manufacture;

    @SerializedName("Price")
    public String price;

    @SerializedName("Sorting")
    public String sort;

    public CustomerProductResponse(String manufacture,String pri,String sort)
    {
        this.manufacture = manufacture;
        this.price = pri;
        this.sort = sort;
    }

    public int getSuccess() {
        return success;
    }

    public ArrayList<ManufacturerData> getManufacturerData() {
        return manufacturerData;
    }

    public ArrayList<CustomerProductData> getCustomerProductDataArrayList() {
        return customerProductDataArrayList;
    }
}
