package items;

public class Manufacturer {

    public String companyName, password, logo, email, phone, gst, vat;

    public Manufacturer(String companyName, String password, String logo) {
        this.companyName = companyName;
        this.password = password;
        this.logo = logo;
    }

    public String getVat() {
        return vat;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getGst() {
        return gst;
    }

    public String getLogo() {
        return logo;
    }

    public String getPhone() {
        return phone;
    }
}
