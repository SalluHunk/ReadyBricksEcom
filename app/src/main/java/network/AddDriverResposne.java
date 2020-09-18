package network;

import com.google.gson.annotations.SerializedName;

public class AddDriverResposne {

    @SerializedName("TransporterId")
    public String transporterId;

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("MobileNumber")
    public String phone;

    @SerializedName("Password")
    public String password;

    @SerializedName("LicenceImage")
    public String licenseImage;

    @SerializedName("DriverPhoto")
    public String driverPhoto;

    @SerializedName("success")
    public int success;


    public AddDriverResposne(String transporterId,String firstName,String lastName,String phone,String password,String licenseImage,String driverPhoto)
    {
        this.transporterId= transporterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.licenseImage = licenseImage;
        this.driverPhoto = driverPhoto;
    }
    public int getSuccess() {
        return success;
    }
}
