package impl;

import network.APIClient;
import network.APIInterface;
import network.AddReviewResponse;
import presenter.AddOrderReviewPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AddOrderReviewView;

public class AddReviewPresenterImpl implements AddOrderReviewPresenter {
    APIInterface apiInterface;
    AddOrderReviewView addOrderReviewView;
    public AddReviewPresenterImpl(AddOrderReviewView addOrderReviewView)
    {
        this.addOrderReviewView = addOrderReviewView;
    }
    @Override
    public void addReview(String orderId, String productId, String userId, String transId, String transReview, String transDesc, String manuId, String manuReview, String manuDesc) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AddReviewResponse addReviewResponse = new AddReviewResponse(orderId,productId,userId,transId,transReview,transDesc,manuId,manuReview,manuDesc);
        Call<AddReviewResponse> addReviewResponseCall  =apiInterface.addReview(addReviewResponse);
        addReviewResponseCall.enqueue(new Callback<AddReviewResponse>() {
            @Override
            public void onResponse(Call<AddReviewResponse> call, Response<AddReviewResponse> response) {
                AddReviewResponse addReviewResponse1 = response.body();

                if(addReviewResponse1!=null)
                {
                    if(addReviewResponse1.getSuccess()==1)
                    {
                        addOrderReviewView.onSuccessOrderReview();
                    }
                    else
                    {
                        addOrderReviewView.onFailedOrderReview();
                    }
                }
                else
                {
                    addOrderReviewView.onFailedOrderReview();
                }
            }

            @Override
            public void onFailure(Call<AddReviewResponse> call, Throwable t) {

            }
        });
    }
}
