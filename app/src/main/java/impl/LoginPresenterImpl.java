package impl;

import android.content.Context;
import android.content.SharedPreferences;

import network.APIClient;
import network.APIInterface;
import network.LoginRespnse;
import presenter.LoginPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.AlaBricks;
import view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
     APIInterface apiInterface;
     LoginView loginView;
     SharedPreferences sharedPreferences;
     Context context;
     public LoginPresenterImpl(LoginView loginView,Context context)
     {
         this.loginView =  loginView;
         this.context =  context;
     }
    @Override
    public void login(String phone, String password) {
         sharedPreferences = context.getSharedPreferences(AlaBricks.sharedName,Context.MODE_PRIVATE);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        final LoginRespnse loginRespnse = new LoginRespnse(phone,password,sharedPreferences.getString(AlaBricks.globalFirebaseToken,""));
        Call<LoginRespnse> loginRespnseCall = apiInterface.login(loginRespnse);
        loginRespnseCall.enqueue(new Callback<LoginRespnse>() {
            @Override
            public void onResponse(Call<LoginRespnse> call, Response<LoginRespnse> response) {
                LoginRespnse loginRespnse1 = response.body();
                if(loginRespnse1!=null)
                {
                    if(loginRespnse1.getSuccess()==1)
                    {
                        loginView.onSuccessLogin(loginRespnse1.getSocialMedia());
                    }
                    if(loginRespnse1.getSuccess()==0)
                    {
                        loginView.onFailedLogin();
                    }
                    if(loginRespnse1.getSuccess()==2)
                    {
                        loginView.onAccountNotVerified();
                    }
                    if(loginRespnse1.getSuccess()==3)
                    {
                        loginView.onRejected(loginRespnse1.getMessage());
                    }
                    if(loginRespnse1.getSuccess()==4)
                    {
                        loginView.onNoAccountFound();
                    }

                }
                else
                {
                    loginView.onFailedLogin();
                }
            }

            @Override
            public void onFailure(Call<LoginRespnse> call, Throwable t) {

            }
        });
    }
}
