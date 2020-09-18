package network;

import com.google.gson.annotations.SerializedName;

public class UpdateDriverResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("DriverId")
    public String driverId;

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("MobileNumber")
    public String mobileNumber;

    @SerializedName("Password")
    public String password;

    @SerializedName("LicenceImage")
    public String licenseImage;

    @SerializedName("DriverPhoto")
    public String driverPhoto;

    public UpdateDriverResponse(String driverId,String firstName,String lastName,String mobileNumber,String password,String licenseImage,String driverPhoto)
    {
        this.driverId = driverId;
        this.driverPhoto = driverPhoto;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.licenseImage = licenseImage;
        this.password = "";
    }

    public int getSuccess() {
        return success;
    }
}
