package network;

import com.google.gson.annotations.SerializedName;

public class DriverListData {

    @SerializedName("DId")
    public String driverId;

    @SerializedName("Reason")
    public String reason;

    @SerializedName("DFirstName")
    public String firstName;

    @SerializedName("DLastName")
    public String lastName;

    @SerializedName("DMobileNumber")
    public String driverPhone;

    @SerializedName("DImage")
    public String driverImage;

    @SerializedName("DLicenceImage")
    public String licenseImage;

    @SerializedName("DStatus")
    public String status;

    @SerializedName("DPassword")
    public String password;

    @SerializedName("IsEdited")
    public String isEdited;

    @SerializedName("IsAccount")
    public String driverAccount;

    public String getDriverAccount() {
        return driverAccount;
    }

    public String getIsEdited() {
        return isEdited;
    }

    public String getPassword() {
        return password;
    }

    public String getLicenseImage() {
        return licenseImage;
    }

    public String getStatus() {
        return status;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getReason() {
        return reason;
    }
}
