package impl;

import network.APIClient;
import network.APIInterface;
import network.UpdateVehicleResponse;
import presenter.UpdateVehiclePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.UpdateVehicleView;

public class UpdateVehiclePresenterImpl implements UpdateVehiclePresenter {
    APIInterface apiInterface;
    UpdateVehicleView updateVehicleView;
    public UpdateVehiclePresenterImpl(UpdateVehicleView updateVehicleView)
    {
        this.updateVehicleView = updateVehicleView;
    }
    @Override
    public void updateVehicle(String vehicleId, String rcNo, String vNo, String rcImage) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        UpdateVehicleResponse updateVehicleResponse = new UpdateVehicleResponse(vehicleId,rcNo,vNo,rcImage);
        Call<UpdateVehicleResponse> updateVehicleResponseCall = apiInterface.updateVehicle(updateVehicleResponse);
        updateVehicleResponseCall.enqueue(new Callback<UpdateVehicleResponse>() {
            @Override
            public void onResponse(Call<UpdateVehicleResponse> call, Response<UpdateVehicleResponse> response) {
                UpdateVehicleResponse updateVehicleResponse1 = response.body();
                if(updateVehicleResponse1!=null)
                {
                    if(updateVehicleResponse1.getSuccess()==1)
                    {
                        updateVehicleView.onSuccessUpdateVehicle();
                    }
                    else
                    {
                        updateVehicleView.onFailedUpdateVehicle();
                    }
                }
                else
                {
                    updateVehicleView.onFailedUpdateVehicle();
                }
            }

            @Override
            public void onFailure(Call<UpdateVehicleResponse> call, Throwable t) {

            }
        });
    }
}
