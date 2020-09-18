package impl;

import network.APIClient;
import network.APIInterface;
import network.TransporterOrderListResponse;
import presenter.TransporterOrderListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TransporterOrderListView;

public class TransporterOrderListPresenterImpl implements TransporterOrderListPresenter {
    APIInterface apiInterface;
    TransporterOrderListView transporterOrderListView;
    public TransporterOrderListPresenterImpl(TransporterOrderListView transporterOrderListView)
    {
        this.transporterOrderListView = transporterOrderListView;

    }
    @Override
    public void listTransporterOrder(String userId, String status) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        TransporterOrderListResponse transporterOrderListResponse =  new TransporterOrderListResponse(userId,status);
        Call<TransporterOrderListResponse> transporterOrderListResponseCall = apiInterface.listTransportOrder(transporterOrderListResponse);
        transporterOrderListResponseCall.enqueue(new Callback<TransporterOrderListResponse>() {
            @Override
            public void onResponse(Call<TransporterOrderListResponse> call, Response<TransporterOrderListResponse> response) {
                TransporterOrderListResponse transporterOrderListResponse1 = response.body();
                if(transporterOrderListResponse1!=null)
                {
                    if(transporterOrderListResponse1.getSuccess()==1)
                    {
                        transporterOrderListView.onSuccessTransporterOrderList(transporterOrderListResponse1.getOrderData());
                    }
                    else
                    {
                        transporterOrderListView.onFailedTransporterOrderList();
                    }
                }
                else
                {
                    transporterOrderListView.onFailedTransporterOrderList();
                }
            }

            @Override
            public void onFailure(Call<TransporterOrderListResponse> call, Throwable t) {

            }
        });
    }
}
