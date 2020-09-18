package impl;

import network.APIClient;
import network.APIInterface;
import network.SignupResponse;
import presenter.SignupPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.SignupView;

public class SignupPresenterImpl implements SignupPresenter {
    APIInterface apiInterface;
    SignupView signupView;

    public SignupPresenterImpl(SignupView signupView)
    {
        this.signupView = signupView;
    }
    @Override
    public void signup(String role, String companyName, String firstName, String lastName, String password, String email, String phone, String vat, String gst, String type,String imageName) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        SignupResponse signupResponse = new SignupResponse(role,companyName,firstName,lastName,password,email,phone,vat,gst,type,imageName);

        Call<SignupResponse> signupResponseCall = apiInterface.signup(signupResponse);

        signupResponseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse signupResponse1 = response.body();

                if(signupResponse1!=null)
                {
                   if(signupResponse1.getSuccess()==1)
                   {
                       signupView.onSuccessSignup(signupResponse1.getId());
                   }
                   else if(signupResponse1.getSuccess()==2)
                   {
                        signupView.onFailedSignupEmail();
                   }
                   else
                   {
                       signupView.onFailedSignup();
                   }
                }
                else
                {
                    signupView.onFailedSignup();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

            }
        });

    }
}
