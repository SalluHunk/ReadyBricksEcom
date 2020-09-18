package impl;

import network.APIClient;
import network.APIInterface;
import network.DeleteCartResponse;
import presenter.DeleteCartPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.DeleteCartItemView;

public class DeleteCartPresenterImpl implements DeleteCartPresenter {
    APIInterface apiInterface;
    DeleteCartItemView deleteCartItemView;
    public DeleteCartPresenterImpl(DeleteCartItemView deleteCartItemView)
    {
        this.deleteCartItemView = deleteCartItemView;
    }
    @Override
    public void onDelete(String cartId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final DeleteCartResponse deleteCartResponse = new DeleteCartResponse(cartId);
        Call<DeleteCartResponse> deleteCartResponseCall = apiInterface.deleteCartItem(deleteCartResponse);

        deleteCartResponseCall.enqueue(new Callback<DeleteCartResponse>() {
            @Override
            public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                DeleteCartResponse deleteCartResponse1 = response.body();

                if(deleteCartResponse1!=null)
                {
                    if(deleteCartResponse1.getSuccess()==1)
                    {
                        deleteCartItemView.onSuccesDeleteCart();
                    }
                    else
                    {
                        deleteCartItemView.onFailedDeleteCart();
                    }
                }
                else
                {
                    deleteCartItemView.onFailedDeleteCart();
                }
            }

            @Override
            public void onFailure(Call<DeleteCartResponse> call, Throwable t) {

            }
        });
    }
}
