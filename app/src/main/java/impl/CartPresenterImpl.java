package impl;

import network.APIClient;
import network.APIInterface;
import network.CartResponse;
import presenter.CartPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.CartView;

public class CartPresenterImpl implements CartPresenter {
    APIInterface apiInterface;
    CartView cartView;
    public CartPresenterImpl(CartView cartView)
    {
        this.cartView = cartView;
    }
    @Override
    public void displayCart(String userId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        CartResponse cartResponse = new CartResponse(userId);
        Call<CartResponse> cartResponseCall = apiInterface.displayCart(cartResponse);
        cartResponseCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                CartResponse cartResponse1 = response.body();
                if(cartResponse1!=null)
                {
                    if(cartResponse1.getSuccess()==1)
                    {
                        cartView.onSuccessCartDisplay(cartResponse1.getCartData());
                    }
                    else
                    {
                        cartView.onFailedCartDisplay();
                    }
                }
                else
                {
                    cartView.onFailedCartDisplay();
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
    }
}
