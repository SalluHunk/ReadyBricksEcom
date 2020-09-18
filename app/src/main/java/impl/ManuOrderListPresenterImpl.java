package impl;

import network.APIClient;
import network.APIInterface;
import network.ManuOrderListResponse;
import presenter.ManuOrderListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.ManuOrderListView;

public class ManuOrderListPresenterImpl implements ManuOrderListPresenter {
    APIInterface apiInterface;
    ManuOrderListView manuOrderListView;
    public ManuOrderListPresenterImpl(ManuOrderListView manuOrderListView)
    {
        this.manuOrderListView = manuOrderListView;
    }
    @Override
    public void orderListManufacturer(String userId, String status) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final ManuOrderListResponse manuOrderListResponse = new ManuOrderListResponse(userId,status);
        Call<ManuOrderListResponse> manuOrderListResponseCall = apiInterface.orderListManufacturer(manuOrderListResponse);
        manuOrderListResponseCall.enqueue(new Callback<ManuOrderListResponse>() {
            @Override
            public void onResponse(Call<ManuOrderListResponse> call, Response<ManuOrderListResponse> response) {
                ManuOrderListResponse manuOrderListResponse1 = response.body();

                if(manuOrderListResponse1!=null)
                {
                    if(manuOrderListResponse1.getSuccess()==1)
                    {
                        manuOrderListView.onSuccessOrderList(manuOrderListResponse1.getOrderData());
                    }
                    else
                    {
                        manuOrderListView.onFailedSuccessOrderList();
                    }
                }
                else
                {
                    manuOrderListView.onFailedSuccessOrderList();
                }
            }

            @Override
            public void onFailure(Call<ManuOrderListResponse> call, Throwable t) {

            }
        });
    }
}
