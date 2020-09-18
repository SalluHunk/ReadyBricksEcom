package impl;

import network.APIClient;
import network.APIInterface;
import network.NewsletterResponse;
import presenter.NewsletterPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.NewsletterView;

public class NewsletterPresenterImpl implements NewsletterPresenter {
    APIInterface apiInterface;
    NewsletterView newsletterView;
    public NewsletterPresenterImpl(NewsletterView newsletterView)
    {
        this.newsletterView = newsletterView;
    }
    @Override
    public void updateNewsletter(String userId, String status) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        NewsletterResponse newsletterResponse = new NewsletterResponse(userId,status);
        Call<NewsletterResponse> newsletterResponseCall = apiInterface.updateNewsletter(newsletterResponse);
        newsletterResponseCall.enqueue(new Callback<NewsletterResponse>() {
            @Override
            public void onResponse(Call<NewsletterResponse> call, Response<NewsletterResponse> response) {
                NewsletterResponse newsletterResponse1 = response.body();

                if(newsletterResponse1!=null)
                {
                    if(newsletterResponse1.getSuccess()==1)
                    {
                        newsletterView.onSuccessNewsletter();
                    }
                    else
                    {
                        newsletterView.onFailedNewsletter();
                    }
                }
                else
                {
                    newsletterView.onFailedNewsletter();
                }
            }

            @Override
            public void onFailure(Call<NewsletterResponse> call, Throwable t) {

            }
        });
    }
}
