package network;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {

    @SerializedName("success")
    public int success;

    @SerializedName("Role")
    public String role;

    @SerializedName("CompanyName")
    public String companyName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("MobileNumber")
    public String mobileNumber;

    @SerializedName("Password")
    public String password;

    @SerializedName("VatNo")
    public String vatNo;

    @SerializedName("GstNo")
    public String gstNo;

    @SerializedName("Email")
    public String email;

    @SerializedName("Type")
    public String type;

    @SerializedName("Image")
    public String imageName;

    @SerializedName("data")
    public String id;


    public SignupResponse(String role,String companyName,String firstName,String lastName,String password,String email,String mobileNumber,String vatNo,String gstNo,String type,String imageName)
    {
        this.role = role;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.vatNo = vatNo;
        this.gstNo = gstNo;
        this.type = type;
        this.imageName = imageName;
    }

    public String getId() {
        return id;
    }

    public int getSuccess() {
        return success;
    }
}
