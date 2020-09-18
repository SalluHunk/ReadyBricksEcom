package impl;

import network.APIClient;
import network.APIInterface;
import network.UpdateProductResponse;
import presenter.UpdateProdcutPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.UpdateProductView;

public class ProductPresenterImpl implements UpdateProdcutPresenter {
    APIInterface apiInterface;
    UpdateProductView updateProductView;

    public ProductPresenterImpl(UpdateProductView updateProductView)
    {
        this.updateProductView = updateProductView;
    }
    @Override
    public void updateProduct(String ProductId, String Name, String minDeliveryDays, String price, String description, String additionalInfo, String image,String isImage) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        UpdateProductResponse updateProductResponse = new UpdateProductResponse(ProductId,Name,minDeliveryDays,price,description,additionalInfo,image,isImage);
        Call<UpdateProductResponse> updateProductResponseCall = apiInterface.updateProduct(updateProductResponse);
        updateProductResponseCall.enqueue(new Callback<UpdateProductResponse>() {
            @Override
            public void onResponse(Call<UpdateProductResponse> call, Response<UpdateProductResponse> response) {
                UpdateProductResponse updateProductResponse1 = response.body();

                if(updateProductResponse1!=null)
                {
                    if(updateProductResponse1.getSuccess()==1)
                    {
                        updateProductView.onSuccessProductUpdate();
                    }
                    else
                    {
                        updateProductView.onFailedProductUpdate();
                    }
                }
                else
                {
                    updateProductView.onFailedProductUpdate();
                }
            }

            @Override
            public void onFailure(Call<UpdateProductResponse> call, Throwable t) {

            }
        });
    }
}
