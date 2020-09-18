package view;

import network.AddressData;

public interface CheckAddressView
{
    void onSuccessAddress(AddressData addressData);
    void onFailedAddress();
}
