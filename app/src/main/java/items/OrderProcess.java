package items;

public class OrderProcess {

    public String address1,address2,landmark,city,state,district,country,date;
    public boolean isSame;
    String countryName;
    String stateName;
    String districtName;
    String cityName;

    public OrderProcess(String address1,String address2,String landmark,String city,String state,String district,String country,boolean isSame,String date,String countryName,String stateName,String districtName,String cityName)
    {
        this.district = district;
        this.address1 = address1;
        this.address2 = address2;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.country = country;
        this.isSame = isSame;
        this.date = date;
        this.countryName = countryName;
        this.stateName = stateName;
        this.districtName= districtName;
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getDistrict() {
        return district;
    }

    public String getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getLandmark() {
        return landmark;
    }

    public boolean isSame() {
        return isSame;
    }

}
