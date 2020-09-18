package impl;

import network.APIClient;
import network.APIInterface;
import network.ChangePasswordResponse;
import presenter.ChangePasswordPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.ChangePasswordView;

public class ChangePasswordPresenterImpl implements ChangePasswordPresenter {
    APIInterface apiInterface;
    ChangePasswordView changePasswordView;
    public ChangePasswordPresenterImpl(ChangePasswordView changePasswordView)
    {
        this.changePasswordView = changePasswordView;
    }
    @Override
    public void changePassword(String userId, String oldPassword, String newPassword) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse(userId,oldPassword,newPassword);
        Call<ChangePasswordResponse> changePasswordResponseCall = apiInterface.changePassword(changePasswordResponse);
        changePasswordResponseCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                ChangePasswordResponse changePasswordResponse1 = response.body();
                if(changePasswordResponse1!=null)
                {
                    if(changePasswordResponse1.getSuccess()==1)
                    {
                        changePasswordView.onSuccessChangePassword();
                    }
                    else if(changePasswordResponse1.getSuccess()==2)
                    {
                        changePasswordView.onNoMatchPassword();
                    }
                    else
                    {
                        changePasswordView.onFailedChangePassword();
                    }
                }
                else
                {
                    changePasswordView.onFailedChangePassword();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

            }
        });
    }
}
