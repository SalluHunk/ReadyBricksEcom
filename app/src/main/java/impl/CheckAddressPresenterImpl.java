package impl;

import network.APIClient;
import network.APIInterface;
import network.CheckAddressResponse;
import presenter.CheckAddressPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.CheckAddressView;

public class CheckAddressPresenterImpl implements CheckAddressPresenter {
    APIInterface apiInterface;
    CheckAddressView checkAddressView;

    public CheckAddressPresenterImpl(CheckAddressView checkAddressView)
    {
        this.checkAddressView = checkAddressView;
    }
    @Override
    public void checkAddress(String userId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        CheckAddressResponse checkAddressResponse = new CheckAddressResponse(userId);
        Call<CheckAddressResponse> checkAddressResponseCall  = apiInterface.checkAddress(checkAddressResponse);
        checkAddressResponseCall.enqueue(new Callback<CheckAddressResponse>() {
            @Override
            public void onResponse(Call<CheckAddressResponse> call, Response<CheckAddressResponse> response) {
                CheckAddressResponse checkAddressResponse1 = response.body();

                if(checkAddressResponse1!=null)
                {
                    if(checkAddressResponse1.getSuccess()==1)
                    {
                        checkAddressView.onSuccessAddress(checkAddressResponse1.getAddressData());
                    }
                    else
                    {
                        checkAddressView.onFailedAddress();
                    }
                }
                else
                {
                    checkAddressView.onFailedAddress();
                }
            }

            @Override
            public void onFailure(Call<CheckAddressResponse> call, Throwable t) {

            }
        });
    }
}
