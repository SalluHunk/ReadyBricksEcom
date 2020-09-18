package impl;

import network.APIClient;
import network.APIInterface;
import network.TrackingOrderResponse;
import presenter.TrackOrderPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TrackOrderView;

public class TrackingOrderPresenterImpl implements TrackOrderPresenter {
    APIInterface apiInterface;
    TrackOrderView trackOrderView;
    public TrackingOrderPresenterImpl(TrackOrderView trackOrderView)
    {
        this.trackOrderView = trackOrderView;
    }
    @Override
    public void trackOrder(String orderId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        TrackingOrderResponse trackingOrderResponse = new TrackingOrderResponse(orderId);
        Call<TrackingOrderResponse> trackingOrderResponseCall = apiInterface.orderTrack(trackingOrderResponse);
        trackingOrderResponseCall.enqueue(new Callback<TrackingOrderResponse>() {
            @Override
            public void onResponse(Call<TrackingOrderResponse> call, Response<TrackingOrderResponse> response) {
                TrackingOrderResponse trackingOrderResponse1 = response.body();
                if(trackingOrderResponse1!=null)
                {
                    if(trackingOrderResponse1.getSuccess()==1)
                    {
                        trackOrderView.onSuccessTracking(trackingOrderResponse1.getTrackingData());
                    }
                    else
                    {
                        trackOrderView.onFailedTracking();
                    }
                }
                else
                {
                    trackOrderView.onFailedTracking();
                }
            }

            @Override
            public void onFailure(Call<TrackingOrderResponse> call, Throwable t) {

            }
        });
    }
}
