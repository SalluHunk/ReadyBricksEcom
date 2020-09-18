package network;

import com.google.gson.annotations.SerializedName;

public class OrderData {

    @SerializedName("OdId")
    public String orderId;

    @SerializedName("PImage")
    public String productImage;

    @SerializedName("OdManuId")
    public String manuId;

    @SerializedName("OdTransId")
    public String transporterId;

    @SerializedName("OdProductId")
    public String productId;

    @SerializedName("OdQty")
    public String quantity;

    @SerializedName("IsReview")
    public String IsReview;

    @SerializedName("OdPrice")
    public String price;

    @SerializedName("AcceptedAt")
    public String acceptedTime;

    @SerializedName("OdOrderId")
    public String invoiceId;

    @SerializedName("OdStatus")
    public String orderStatus;

    @SerializedName("PName")
    public String productName;

    @SerializedName("manufactureCompanyName")
    public String manuName;

    @SerializedName("Created_At")
    public String createdAt;

    @SerializedName("UpdatedAt")
    public String updatedAt;

    @SerializedName("RejectedAt")
    public String rejectedAt;

    @SerializedName("CountryName")
    public String countryName;

    @SerializedName("DistrictName")
    public String districtName;

    @SerializedName("CityName")
    public String cityName;

    @SerializedName("StateName")
    public String stateName;

    @SerializedName("ProcessedAt")
    public String processedAt;

    @SerializedName("AssignedAt")
    public String assignedAt;

    @SerializedName("CompletedAt")
    public String completedAt;

    @SerializedName("CustomerFirstName")
    public String customerFirstName;

    @SerializedName("CustomerLastName")
    public String customerLastName;

    @SerializedName("CustomerAddress")
    public String customerAddress;

    @SerializedName("CustomerLandmark")
    public String customerLandmark;

    @SerializedName("CustomerCountryName")
    public String customerCountryName;

    @SerializedName("CustomerStateName")
    public String customerStateName;

    @SerializedName("CustomerDistrictName")
    public String customerDistrictName;

    @SerializedName("CustomerCityName")
    public String customerCityName;

    @SerializedName("CustomerMobileNumber")
    public String customerMobileNumber;

    @SerializedName("manufactureMobileNumber")
    public String manufacturerMobileNumber;

    @SerializedName("manufactureAddress")
    public String manufactureAddress;

    @SerializedName("manufactureLandmark")
    public String manufactureLandmark;

    @SerializedName("ManufactureCountryName")
    public String manufactureCountryName;

    @SerializedName("ManufactureStateName")
    public String manufactureStateName;

    @SerializedName("ManufactureDistrictName")
    public String manufactureDistrictName;

    @SerializedName("ManufactureCityName")
    public String manufactureCityName;

    @SerializedName("DMobileNumber")
    public String driverMobileNumber;

    @SerializedName("VNo")
    public String vehicleNo;

    @SerializedName("DFirstName")
    public String driverFirstName;

    @SerializedName("DLastName")
    public String driverLastName;

    @SerializedName("ODeliveryDate")
    public String deliveryDate;

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public String getRejectedAt() {
        return rejectedAt;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public String getDriverMobileNumber() {
        return driverMobileNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerCityName() {
        return customerCityName;
    }

    public String getCustomerCountryName() {
        return customerCountryName;
    }

    public String getCustomerDistrictName() {
        return customerDistrictName;
    }

    public String getCustomerLandmark() {
        return customerLandmark;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public String getCustomerStateName() {
        return customerStateName;
    }

    public String getManufactureAddress() {
        return manufactureAddress;
    }

    public String getManufactureCityName() {
        return manufactureCityName;
    }

    public String getManufactureCountryName() {
        return manufactureCountryName;
    }

    public String getManufactureDistrictName() {
        return manufactureDistrictName;
    }

    public String getManufactureLandmark() {
        return manufactureLandmark;
    }

    public String getManufacturerMobileNumber() {
        return manufacturerMobileNumber;
    }

    public String getManufactureStateName() {
        return manufactureStateName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getAssignedAt() {
        return assignedAt;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getManuName() {
        return manuName;
    }

    public String getManuId() {
        return manuId;
    }

    public String getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getAcceptedTime() {
        return acceptedTime;
    }

    public String getIsReview() {
        return IsReview;
    }
}
