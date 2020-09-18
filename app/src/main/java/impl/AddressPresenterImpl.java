package impl;

import network.APIClient;
import network.APIInterface;
import network.AddressResponse;
import presenter.AddressPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AddressView;

public class AddressPresenterImpl implements AddressPresenter {
    APIInterface apiInterface;
    AddressView addressView;
    public AddressPresenterImpl(AddressView addressView)
    {
        this.addressView = addressView;
    }
    @Override
    public void loadAddresses() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final AddressResponse addressResponse = new AddressResponse();
        Call<AddressResponse> addressResponseCall = apiInterface.addressList(addressResponse);
        addressResponseCall.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                AddressResponse addressResponse1 = response.body();

                if(addressResponse1!=null)
                {
                    if(addressResponse1.getSuccess().equals("true"))
                    {
                        addressView.onSuccessAddress(addressResponse1.getCountryLists(),addressResponse1.getStateLists(),addressResponse1.getCityLists(),addressResponse1.getDistrictLists());
                    }
                    else
                    {
                        addressView.onFailedAddress();
                    }
                }
                else
                {
                    addressView.onFailedAddress();
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {

            }
        });
    }
}
