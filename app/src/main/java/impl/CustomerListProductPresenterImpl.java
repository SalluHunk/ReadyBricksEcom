package impl;

import network.APIClient;
import network.APIInterface;
import network.CustomerProductResponse;
import presenter.CustomerListProductPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.AlaBricks;
import view.CustomerListProductView;

public class CustomerListProductPresenterImpl implements CustomerListProductPresenter {
    APIInterface apiInterface;
    CustomerListProductView customerListProductView;
    public CustomerListProductPresenterImpl(CustomerListProductView customerListProductView)
    {
        this.customerListProductView = customerListProductView;
    }
    @Override
    public void listProducts() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        CustomerProductResponse customerProductResponse = new CustomerProductResponse(AlaBricks.filterManufacturer,AlaBricks.filterPrice,AlaBricks.filterSortBy);
        Call<CustomerProductResponse> customerProductResponseCall = apiInterface.listProducts(customerProductResponse);
        customerProductResponseCall.enqueue(new Callback<CustomerProductResponse>() {
            @Override
            public void onResponse(Call<CustomerProductResponse> call, Response<CustomerProductResponse> response) {
                CustomerProductResponse customerProductResponse1 = response.body();
                if(customerProductResponse1!=null)
                {
                    if(customerProductResponse1.getSuccess()==1)
                    {
                        AlaBricks.manufacturerData = customerProductResponse1.getManufacturerData();
                        customerListProductView.onSuccessCustomerProductList(customerProductResponse1.getCustomerProductDataArrayList());
                    }
                    else
                    {
                        customerListProductView.onFailedCustomerProductList();
                    }
                }
                else
                {
                    customerListProductView.onFailedCustomerProductList();
                }
            }

            @Override
            public void onFailure(Call<CustomerProductResponse> call, Throwable t) {

            }
        });
    }
}
