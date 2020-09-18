package impl;

import network.APIClient;
import network.APIInterface;
import network.UpdateCartResponse;
import presenter.UpdateCartPresenter;
import presenter.UpdateProdcutPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.UpdateCartView;

public class UpdateCartPresenterImpl implements UpdateCartPresenter {
    APIInterface apiInterface;
    UpdateCartView updateCartView;
    public UpdateCartPresenterImpl(UpdateCartView updateCartView)
    {
        this.updateCartView = updateCartView;
    }

    @Override
    public void onUpdateCart(String cartId, String qty) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        UpdateCartResponse updateCartResponse = new UpdateCartResponse(cartId,qty);
        Call<UpdateCartResponse> updateCartResponseCall = apiInterface.updateCart(updateCartResponse);
        updateCartResponseCall.enqueue(new Callback<UpdateCartResponse>() {
            @Override
            public void onResponse(Call<UpdateCartResponse> call, Response<UpdateCartResponse> response) {
                UpdateCartResponse updateCartResponse1 = response.body();
                if(updateCartResponse1!=null)
                {
                    if(updateCartResponse1.getSuccess()==1)
                    {
                        updateCartView.onSuccessUpdateCart();
                    }
                    else
                    {
                        updateCartView.onFailedUpdateCart();
                    }
                }
                else
                {
                    updateCartView.onFailedUpdateCart();
                }
            }

            @Override
            public void onFailure(Call<UpdateCartResponse> call, Throwable t) {

            }
        });
    }
}
