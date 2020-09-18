package impl;

import network.APIClient;
import network.APIInterface;
import network.AddDriverResposne;
import presenter.AddDriverPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AddDriverView;

public class AddDriverPresenterImpl implements AddDriverPresenter {
    APIInterface apiInterface;
    AddDriverView addDriverView;
    public AddDriverPresenterImpl(AddDriverView addDriverView)
    {
        this.addDriverView = addDriverView;
    }
    @Override
    public void addDriver(String transportId, String firstName, String lastName, String phone, String password, String licenseImage, String driverPhoto) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AddDriverResposne addDriverResposne = new AddDriverResposne(transportId,firstName,lastName,phone,password,licenseImage,driverPhoto);
        Call<AddDriverResposne> addDriverResposneCall = apiInterface.addDriver(addDriverResposne);
        addDriverResposneCall.enqueue(new Callback<AddDriverResposne>() {
            @Override
            public void onResponse(Call<AddDriverResposne> call, Response<AddDriverResposne> response) {
                AddDriverResposne addDriverResposne1 = response.body();

                if (addDriverResposne1!=null)
                {
                    if(addDriverResposne1.getSuccess()==1)
                    {
                        addDriverView.onSuccessAddDriver();
                    }
                    else if(addDriverResposne1.getSuccess()==2)
                    {
                        addDriverView.onNoMobileValidationDriver();
                    }
                    else
                    {
                        addDriverView.onFailedAddDriver();
                    }
                }
                else
                {
                    addDriverView.onFailedAddDriver();
                }
            }

            @Override
            public void onFailure(Call<AddDriverResposne> call, Throwable t) {

            }
        });
    }
}
