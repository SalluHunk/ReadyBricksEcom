package impl;

import network.APIClient;
import network.APIInterface;
import network.UpdateDriverResponse;
import presenter.UpdateDriverPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.UpdateDriverView;

public class UpdateDriverPresenterImpl implements UpdateDriverPresenter {
    APIInterface apiInterface;
    UpdateDriverView updateDriverView;
    public UpdateDriverPresenterImpl(UpdateDriverView updateDriverView)
    {
        this.updateDriverView = updateDriverView;
    }
    @Override
    public void updateDriver(String driverId, String firstName, String lastName, String mobileNumber, String licenseImage, String driverPhoto) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final UpdateDriverResponse updateDriverResponse = new UpdateDriverResponse(driverId,firstName,lastName,mobileNumber,"",licenseImage,driverPhoto);
        Call<UpdateDriverResponse> updateDriverResponseCall = apiInterface.updateDriver(updateDriverResponse);
        updateDriverResponseCall.enqueue(new Callback<UpdateDriverResponse>() {
            @Override
            public void onResponse(Call<UpdateDriverResponse> call, Response<UpdateDriverResponse> response) {
                UpdateDriverResponse driverResponse = response.body();
                if(driverResponse!=null)
                {
                    if(driverResponse.getSuccess()==1)
                    {
                        updateDriverView.onSuccessUpdateDriver();
                    }
                    else if(driverResponse.getSuccess()==2)
                    {
                        updateDriverView.onMobileNumberExists();
                    }
                    else
                    {
                        updateDriverView.onFailedUpdateDriver();
                    }
                }
                else
                {
                    updateDriverView.onFailedUpdateDriver();
                }
            }

            @Override
            public void onFailure(Call<UpdateDriverResponse> call, Throwable t) {

            }
        });
    }
}
