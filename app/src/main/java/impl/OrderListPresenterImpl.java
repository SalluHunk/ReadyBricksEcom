package impl;

import network.APIClient;
import network.APIInterface;
import network.OrderListResponse;
import presenter.OrderListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.OrderListView;

public class OrderListPresenterImpl implements OrderListPresenter {
    APIInterface apiInterface;
    OrderListView orderListView;
    public OrderListPresenterImpl(OrderListView orderListView)
    {
        this.orderListView = orderListView;
    }
    @Override
    public void listOrder(String userId, String status) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        OrderListResponse orderListResponse =  new OrderListResponse(userId,status);
        Call<OrderListResponse> orderListResponseCall = apiInterface.listOrders(orderListResponse);
        orderListResponseCall.enqueue(new Callback<OrderListResponse>() {
            @Override
            public void onResponse(Call<OrderListResponse> call, Response<OrderListResponse> response) {
                OrderListResponse orderListResponse1 = response.body();

                if(orderListResponse1!=null)
                {
                    if(orderListResponse1.getSuccess()==1)
                    {
                        orderListView.onSuccessOrderList(orderListResponse1.getOrderData());
                    }
                    else
                    {
                        orderListView.onFailedOrderList();
                    }
                }
                else
                {
                    orderListView.onFailedOrderList();
                }
            }

            @Override
            public void onFailure(Call<OrderListResponse> call, Throwable t) {

            }
        });
    }
}
