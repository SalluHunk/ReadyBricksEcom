package impl;

import network.APIClient;
import network.APIInterface;
import network.AddInventoryResponse;
import presenter.AddInventoryPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AddInventoryView;

public class AddInventoryPresenterImpl implements AddInventoryPresenter {
    APIInterface apiInterface;
    AddInventoryView addInventoryView;
    public AddInventoryPresenterImpl(AddInventoryView addInventoryView)
    {
        this.addInventoryView = addInventoryView;
    }
    @Override
    public void addStock(String productId, String stock) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AddInventoryResponse addInventoryResponse = new AddInventoryResponse(productId,stock);
        Call<AddInventoryResponse> addInventoryResponseCall = apiInterface.addStock(addInventoryResponse);
        addInventoryResponseCall.enqueue(new Callback<AddInventoryResponse>() {
            @Override
            public void onResponse(Call<AddInventoryResponse> call, Response<AddInventoryResponse> response) {
                AddInventoryResponse addInventoryResponse1 = response.body();
                if(addInventoryResponse1!=null)
                {
                    if(addInventoryResponse1.getStock()==1)
                    {
                        addInventoryView.onSuccessAddInventory();
                    }
                    else
                    {
                        addInventoryView.onFailedAddInventory();
                    }
                }
                else
                {
                    addInventoryView.onFailedAddInventory();
                }
            }

            @Override
            public void onFailure(Call<AddInventoryResponse> call, Throwable t) {

            }
        });
    }
}
