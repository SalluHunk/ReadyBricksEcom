package impl;

import network.APIClient;
import network.APIInterface;
import network.AddProductResponse;
import presenter.AddProductPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AddProductView;

public class AddProductPresenterImpl implements AddProductPresenter {
    APIInterface apiInterface;
    AddProductView addProductView;
    public AddProductPresenterImpl(AddProductView addProductView)
    {
        this.addProductView = addProductView;
    }
    @Override
    public void addProduct(String userId, String productName, String description, String addtionalInfo, String price, String avail, String uploadImage) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AddProductResponse addProductResponse = new AddProductResponse(userId,productName,description,addtionalInfo,price,avail,uploadImage);
        Call<AddProductResponse> addProductResponseCall = apiInterface.addProduct(addProductResponse);
        addProductResponseCall.enqueue(new Callback<AddProductResponse>() {
            @Override
            public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                AddProductResponse addProductResponse1 = response.body();
                if(addProductResponse1!=null)
                {
                    if(addProductResponse1.getSuccess()==1)
                    {
                        addProductView.onSuccessProductAdd();
                    }
                    else
                    {
                        addProductView.onFailedProductAdd();
                    }
                }
                else
                {
                    addProductView.onFailedProductAdd();
                }
            }

            @Override
            public void onFailure(Call<AddProductResponse> call, Throwable t) {

            }
        });
    }
}
