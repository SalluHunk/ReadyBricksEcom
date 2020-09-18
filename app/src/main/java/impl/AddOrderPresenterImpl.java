package impl;

import network.APIClient;
import network.APIInterface;
import network.AddOrderResponse;
import presenter.ProcessOrderPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.ProcessOrderView;

public class AddOrderPresenterImpl implements ProcessOrderPresenter {
    APIInterface apiInterface;
    ProcessOrderView processOrderView;
    public AddOrderPresenterImpl(ProcessOrderView processOrderView)
    {
        this.processOrderView = processOrderView;
    }
    @Override
    public void placeOrder(String userId, String isSameAddress, String address, String landmark, String countryId, String stateId, String cityId, String date,String dId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AddOrderResponse addOrderResponse = new AddOrderResponse(userId,isSameAddress,address,landmark,countryId,stateId,cityId,date,dId);
        Call<AddOrderResponse> addOrderResponseCall = apiInterface.addOrder(addOrderResponse);
        addOrderResponseCall.enqueue(new Callback<AddOrderResponse>() {
            @Override
            public void onResponse(Call<AddOrderResponse> call, Response<AddOrderResponse> response) {
                AddOrderResponse addOrderResponse1 = response.body();
                if(addOrderResponse1!=null)
                {
                    if(addOrderResponse1.getSuccess()==1)
                    {
                        processOrderView.onSuccessProcessOrder(addOrderResponse1.getOrderId());
                    }
                    else
                    {
                        processOrderView.onFailedProcessOrder();
                    }
                }
                else
                {
                    processOrderView.onFailedProcessOrder();
                }
            }

            @Override
            public void onFailure(Call<AddOrderResponse> call, Throwable t) {

            }
        });
    }
}
