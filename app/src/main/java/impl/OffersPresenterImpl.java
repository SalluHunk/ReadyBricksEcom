package impl;

import network.APIClient;
import network.APIInterface;
import network.OffersResponse;
import presenter.OffersPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.OffersView;

public class OffersPresenterImpl implements OffersPresenter {
    APIInterface apiInterface;
    OffersView offersView;

    public OffersPresenterImpl(OffersView offersView)
    {
        this.offersView = offersView;
    }
    @Override
    public void listBanners() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        OffersResponse offersResponse = new OffersResponse();
        Call<OffersResponse> offersResponseCall = apiInterface.listOffers(offersResponse);
        offersResponseCall.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                OffersResponse offersResponse1 = response.body();
                if(offersResponse1!=null)
                {
                    if(offersResponse1.getSuccess()==1)
                    {
                        offersView.onSuccessOffersView(offersResponse1);
                    }
                    else
                    {
                        offersView.onFailedOffersView();
                    }
                }
                else
                {
                    offersView.onFailedOffersView();
                }
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {

            }
        });
    }
}
