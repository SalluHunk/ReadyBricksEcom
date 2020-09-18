package impl;

import network.APIClient;
import network.APIInterface;
import network.ContactUsResponse;
import presenter.ContactUsPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.ContactUsView;

public class AddContactPresenterImpl implements ContactUsPresenter {
    APIInterface apiInterface;
    ContactUsView contactUsView;
    public AddContactPresenterImpl(ContactUsView contactUsView)
    {
        this.contactUsView = contactUsView;
    }
    @Override
    public void addContact(String userId, String title, String desc) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        ContactUsResponse contactUsResponse = new ContactUsResponse(userId,title,desc);
        Call<ContactUsResponse> contactUsResponseCall = apiInterface.addContact(contactUsResponse);
        contactUsResponseCall.enqueue(new Callback<ContactUsResponse>() {
            @Override
            public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                ContactUsResponse contactUsResponse1 = response.body();

                if(contactUsResponse1!=null)
                {
                    if(contactUsResponse1.getSuccess()==1)
                    {
                        contactUsView.onSuccessContactUs();
                    }
                    else
                    {
                        contactUsView.onFailedContactUs();
                    }
                }
                else
                {
                    contactUsView.onFailedContactUs();
                }
            }

            @Override
            public void onFailure(Call<ContactUsResponse> call, Throwable t) {

            }
        });
    }
}
