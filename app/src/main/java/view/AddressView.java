package view;

import java.util.ArrayList;

import network.CityList;
import network.CountryList;
import network.DistrictList;
import network.StateList;

public interface AddressView {
    void onSuccessAddress(ArrayList<CountryList> countryLists, ArrayList<StateList> stateLists, ArrayList<CityList> cityLists, ArrayList<DistrictList> districtLists);
    void onFailedAddress();
}
