package impl;

import network.APIClient;
import network.APIInterface;
import network.TruckListResponse;
import presenter.TruckListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TruckListView;

public class TruckListPresenterImpl implements TruckListPresenter {
    APIInterface apiInterface;
    TruckListView truckListView;
    public TruckListPresenterImpl(TruckListView truckListView)
    {
        this.truckListView = truckListView;
    }
    @Override
    public void listTrucks(String userId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        TruckListResponse truckListResponse = new TruckListResponse(userId);
        Call<TruckListResponse> truckListResponseCall = apiInterface.listTrucks(truckListResponse);
        truckListResponseCall.enqueue(new Callback<TruckListResponse>() {
            @Override
            public void onResponse(Call<TruckListResponse> call, Response<TruckListResponse> response) {
                TruckListResponse truckListResponse1 = response.body();
                if(truckListResponse1!=null)
                {
                    if(truckListResponse1.getSuccess()==1)
                    {
                        truckListView.onSuccessTruckList(truckListResponse1.getVehicleListData());
                    }
                    else
                    {
                        truckListView.onFailedTruckList();
                    }
                }
                else
                {
                    truckListView.onFailedTruckList();
                }
            }

            @Override
            public void onFailure(Call<TruckListResponse> call, Throwable t) {

            }
        });
    }
}
