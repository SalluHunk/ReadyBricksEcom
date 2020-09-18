package impl;

import network.APIClient;
import network.APIInterface;
import network.VehicleDriverResponse;
import presenter.VehicleDriverPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.VehicleDriverView;

public class VehicleAndDriverPresenterImpl implements VehicleDriverPresenter {
    APIInterface apiInterface;
    VehicleDriverView vehicleDriverView;
    public VehicleAndDriverPresenterImpl(VehicleDriverView vehicleDriverView)
    {
        this.vehicleDriverView = vehicleDriverView;
    }
    @Override
    public void listVehicleDriver(String userId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        VehicleDriverResponse vehicleDriverResponse = new VehicleDriverResponse(userId);
        Call<VehicleDriverResponse> vehicleDriverResponseCall = apiInterface.listVehicleandDriver(vehicleDriverResponse);
        vehicleDriverResponseCall.enqueue(new Callback<VehicleDriverResponse>() {
            @Override
            public void onResponse(Call<VehicleDriverResponse> call, Response<VehicleDriverResponse> response) {
                VehicleDriverResponse vehicleDriverResponse1 = response.body();
                if(vehicleDriverResponse1!=null)
                {
                    if(vehicleDriverResponse1.getSuccess()==1)
                    {
                        vehicleDriverView.onSuccessVehicleDriver(vehicleDriverResponse1.getVehicleAndDriverData());
                    }
                    else
                    {
                        vehicleDriverView.onFailedVehicleDriver();
                    }
                }
                else
                {
                    vehicleDriverView.onFailedVehicleDriver();
                }
            }

            @Override
            public void onFailure(Call<VehicleDriverResponse> call, Throwable t) {

            }
        });
    }
}
