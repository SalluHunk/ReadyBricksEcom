package impl;

import network.APIClient;
import network.APIInterface;
import network.ProductListResponse;
import presenter.ManufacturerListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.ManufacturerListView;

public class ProductListPresenterImpl implements ManufacturerListPresenter {
    APIInterface apiInterface;
    ManufacturerListView manufacturerListView;

    public ProductListPresenterImpl(ManufacturerListView manufacturerListView)
    {
        this.manufacturerListView = manufacturerListView;
    }
    @Override
    public void onLoadProductList(String userId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final ProductListResponse productListResponse = new ProductListResponse(userId);
        Call<ProductListResponse> productListResponseCall = apiInterface.listProduct(productListResponse);

        productListResponseCall.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                ProductListResponse productListResponse1 = response.body();
                if(productListResponse1!=null)
                {
                    if(productListResponse1.getSuccess()==1)
                    {
                        manufacturerListView.onSuccessManufacturerList(productListResponse1.getProductData());
                    }
                    else
                    {
                        manufacturerListView.onFailedManufacturerList();
                    }
                }
                else
                {
                    manufacturerListView.onFailedManufacturerList();
                }
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {

            }
        });
    }
}
