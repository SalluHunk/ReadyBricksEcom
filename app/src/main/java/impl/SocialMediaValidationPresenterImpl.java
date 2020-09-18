package impl;

import network.APIClient;
import network.APIInterface;
import network.SocialMediaValidation;
import presenter.SocialMediaValidationPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.SocialMediaValidationView;

public class SocialMediaValidationPresenterImpl implements SocialMediaValidationPresenter {
    public SocialMediaValidationView socialMediaValidationView;
    APIInterface apiInterface;
    public SocialMediaValidationPresenterImpl(SocialMediaValidationView socialMediaValidationView)
    {
        this.socialMediaValidationView = socialMediaValidationView;
    }
    @Override
    public void validateEmail(String email) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final SocialMediaValidation socialMediaValidation = new SocialMediaValidation(email);
        Call<SocialMediaValidation> socialMediaValidationCall = apiInterface.loginWithSocial(socialMediaValidation);
        socialMediaValidationCall.enqueue(new Callback<SocialMediaValidation>() {
            @Override
            public void onResponse(Call<SocialMediaValidation> call, Response<SocialMediaValidation> response) {
                SocialMediaValidation socialMediaValidation1 = response.body();

                if(socialMediaValidation1!=null)
                {
                    if(socialMediaValidation1.getSuccess()==1)
                    {
                        socialMediaValidationView.onSuccessSocialMediaValidation(socialMediaValidation1.getSocialMedia());
                    }
                    else
                    {
                        socialMediaValidationView.onFailedSocialMediaValidation();
                    }
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<SocialMediaValidation> call, Throwable t) {

            }
        });

    }
}
