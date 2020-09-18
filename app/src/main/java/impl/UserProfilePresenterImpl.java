package impl;

import network.APIClient;
import network.APIInterface;
import network.UserProfileResponse;
import presenter.UserProfilePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.UserProfileView;

public class UserProfilePresenterImpl implements UserProfilePresenter {
    UserProfileView userProfileView;
    APIInterface apiInterface;
    public UserProfilePresenterImpl(UserProfileView userProfileView)
    {
        this.userProfileView = userProfileView;
    }
    @Override
    public void displayUserProfile(String userId, String type) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final UserProfileResponse userProfileResponse  =new UserProfileResponse(userId,type);
        Call<UserProfileResponse> userProfileResponseCall = apiInterface.displayUserProfile(userProfileResponse);
        userProfileResponseCall.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                UserProfileResponse userProfileResponse1 = response.body();
                if(userProfileResponse1!=null)
                {

                    if(userProfileResponse1.getSuccess()==1)
                    {
                        userProfileView.onSuccessProfileView(userProfileResponse1.getUserProfileData());
                    }
                    else
                    {
                        userProfileView.onFailedProfileView();
                    }
                }
                else
                {
                    userProfileView.onFailedProfileView();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {

            }
        });
    }
}
