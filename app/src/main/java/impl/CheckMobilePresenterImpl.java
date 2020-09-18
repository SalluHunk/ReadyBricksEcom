package impl;

import network.APIClient;
import network.APIInterface;
import network.CheckMobileResponse;
import network.SocialMedia;
import presenter.CheckMobilePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.CheckMobileView;

public class CheckMobilePresenterImpl implements CheckMobilePresenter {
    CheckMobileView checkMobileView;
    APIInterface apiInterface;
    public CheckMobilePresenterImpl(CheckMobileView checkMobileView)
    {
        this.checkMobileView =checkMobileView;
    }
    @Override
    public void checkPhone(String mobileNumber) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        CheckMobileResponse checkMobileResponse = new CheckMobileResponse(mobileNumber);
        Call<CheckMobileResponse> checkMobileResponseCall = apiInterface.checkMobile(checkMobileResponse);
        checkMobileResponseCall.enqueue(new Callback<CheckMobileResponse>() {
            @Override
            public void onResponse(Call<CheckMobileResponse> call, Response<CheckMobileResponse> response) {
                CheckMobileResponse checkMobileResponse1 = response.body();
                if(checkMobileResponse1!=null)
                {
                    if(checkMobileResponse1.getSuccess()==1)
                    {
                        checkMobileView.onSuccessCheckMobileView(checkMobileResponse1.getSocialMedia());
                    }
                    else
                    {
                        checkMobileView.onFailedCheckMobileView();
                    }
                }
                else
                {
                    checkMobileView.onFailedCheckMobileView();
                }
            }

            @Override
            public void onFailure(Call<CheckMobileResponse> call, Throwable t) {

            }
        });
    }
}
