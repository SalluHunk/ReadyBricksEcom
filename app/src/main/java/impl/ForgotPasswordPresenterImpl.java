package impl;

import network.APIClient;
import network.APIInterface;
import network.ForgotPasswordResponse;
import presenter.ForgotPasswordPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.ForgotPasswordView;

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter {
    ForgotPasswordView forgotPasswordView;
    APIInterface apiInterface;
    public ForgotPasswordPresenterImpl(ForgotPasswordView forgotPasswordView)
    {
        this.forgotPasswordView =forgotPasswordView;
    }
    @Override
    public void forgotPasswordUpdate(String userId, String password) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse(userId,password);
        Call<ForgotPasswordResponse> forgotPasswordResponseCall = apiInterface.forgotPasswordUpdate(forgotPasswordResponse);
        forgotPasswordResponseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                ForgotPasswordResponse forgotPasswordResponse1 = response.body();

                if(forgotPasswordResponse1!=null)
                {
                    if(forgotPasswordResponse1.getSuccess()==1)
                    {
                        forgotPasswordView.onSuccessForgotPassword();
                    }
                    else
                    {
                        forgotPasswordView.onFailedForgotPassword();
                    }

                }
                else
                {
                    forgotPasswordView.onFailedForgotPassword();
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

            }
        });
    }
}
