package impl;

import network.APIClient;
import network.APIInterface;
import network.AddTruckResponse;
import presenter.AddTruckPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AddTruckView;

public class AddTruckPresenterImpl implements AddTruckPresenter {

    APIInterface apiInterface;
    AddTruckView addTruckView;
    public AddTruckPresenterImpl(AddTruckView addTruckView)
    {
        this.addTruckView = addTruckView;
    }
    @Override
    public void addTruck(String TransporterId, String rcNo, String vNo, String rcImage) {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        AddTruckResponse addTruckResponse = new AddTruckResponse(TransporterId,rcNo,vNo,rcImage);

        Call<AddTruckResponse> addTruckResponseCall  = apiInterface.addDriver(addTruckResponse);

        addTruckResponseCall.enqueue(new Callback<AddTruckResponse>() {
            @Override
            public void onResponse(Call<AddTruckResponse> call, Response<AddTruckResponse> response) {
                AddTruckResponse addTruckResponse1 = response.body();

                if(addTruckResponse1!=null)
                {
                    if(addTruckResponse1.getSuccess()==1)
                    {
                        addTruckView.onSuccessTruckUpload();
                    }
                    else
                    {
                        addTruckView.onFailedTruckUpload();
                    }
                }
                else
                {
                    addTruckView.onFailedTruckUpload();
                }
            }

            @Override
            public void onFailure(Call<AddTruckResponse> call, Throwable t) {

            }
        });

    }
}
