package items;

public class Customer {

    String firstName,lastName,companyDetails,mobileNumber,emailAddress,gstin,vat,country,city,state,address,password;

    public Customer(String firstName,String lastName,String companyName,String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyDetails = companyName;
        this.password = password;
    }
    public Customer(String firstName,String lastName,String companyName,String password,String mobileNumber,String emailAddress,String gstin,String vat)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyDetails = companyName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.gstin = gstin;
        this.vat = vat;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompanyDetails() {
        return companyDetails;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getGstin() {
        return gstin;
    }

    public String getState() {
        return state;
    }

    public String getVat() {
        return vat;
    }
}
