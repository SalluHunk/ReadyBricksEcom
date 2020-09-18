package impl;

import network.APIClient;
import network.APIInterface;
import network.AddToCartResponse;
import presenter.AddToCartPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AddToCartView;

public class AddToCartPresenterImpl implements AddToCartPresenter {
    APIInterface apiInterface;
    AddToCartView addToCartView;
    public AddToCartPresenterImpl(AddToCartView addToCartView)
    {
        this.addToCartView = addToCartView;
    }
    @Override
    public void addToCart(String userId, String productId, String price, String qty,String offer) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AddToCartResponse addToCartResponse =new AddToCartResponse(productId,userId,qty,price,offer);
        Call<AddToCartResponse> addToCartResponseCall = apiInterface.addToCart(addToCartResponse);
        addToCartResponseCall.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                AddToCartResponse addToCartResponse1 = response.body();

                if(addToCartResponse1!=null)
                {
                   if(addToCartResponse1.getSuccess()==1)
                   {
                       addToCartView.onSuccessAddtoCart();
                   }
                   else
                   {
                       addToCartView.onFailedAddtoCart();
                   }
                }
                else
                {
                    addToCartView.onFailedAddtoCart();
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {

            }
        });
    }
}
