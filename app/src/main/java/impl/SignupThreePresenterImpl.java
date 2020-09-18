package impl;

import network.APIClient;
import network.APIInterface;
import network.SignupThreeResponse;
import presenter.SignupStepPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.SignupStepThreeView;

public class SignupThreePresenterImpl implements SignupStepPresenter {
    public APIInterface apiInterface;
    public SignupStepThreeView signupStepThreeView;
    public SignupThreePresenterImpl(SignupStepThreeView signupStepThreeView)
    {
        this.signupStepThreeView = signupStepThreeView;
    }
    @Override
    public void signupLast(String UserId, String Address, String Landmark, String CountryId, String StateId, String CityId,String dId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final SignupThreeResponse signupThreeResponse  = new SignupThreeResponse(UserId,Address,CountryId,StateId,CityId,Landmark,dId);
        Call<SignupThreeResponse> signupThreeResponseCall = apiInterface.signupLast(signupThreeResponse);

        signupThreeResponseCall.enqueue(new Callback<SignupThreeResponse>() {
            @Override
            public void onResponse(Call<SignupThreeResponse> call, Response<SignupThreeResponse> response) {
                SignupThreeResponse signupThreeResponse1 = response.body();

                if(signupThreeResponse1!=null)
                {
                    if(signupThreeResponse1.getSuccess()==1)
                    {
                        signupStepThreeView.onSuccessSignupStepThree(signupThreeResponse1.getSocialMedia());
                    }
                    else
                    {
                        signupStepThreeView.onFailedSignupStepThree();
                    }
                }
                else
                {
                    signupStepThreeView.onFailedSignupStepThree();
                }
            }

            @Override
            public void onFailure(Call<SignupThreeResponse> call, Throwable t) {

            }
        });

    }
}
