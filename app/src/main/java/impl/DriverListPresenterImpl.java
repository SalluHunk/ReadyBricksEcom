package impl;

import com.ananta.myapplication.transporter.DriverListActivity;

import network.APIClient;
import network.APIInterface;
import network.DriverListResposne;
import presenter.DriverListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.DriverListView;

public class DriverListPresenterImpl implements DriverListPresenter {
    APIInterface apiInterface;
    DriverListView driverListView;
    public DriverListPresenterImpl(DriverListView driverListView)
    {
        this.driverListView = driverListView;
    }
    @Override
    public void listDrivers(String userId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        DriverListResposne driverListResposne = new DriverListResposne(userId);
        Call<DriverListResposne> driverListResposneCall = apiInterface.listDriver(driverListResposne);
        driverListResposneCall.enqueue(new Callback<DriverListResposne>() {
            @Override
            public void onResponse(Call<DriverListResposne> call, Response<DriverListResposne> response) {
                DriverListResposne driverListResposne1 = response.body();

                if(driverListResposne1!=null)
                {
                    if(driverListResposne1.getSuccess()==1)
                    {
                        driverListView.onSuccessDriverList(driverListResposne1.getDriverListData());
                    }
                    else
                    {
                        driverListView.onFailedDriverList();
                    }
                }
                else
                {
                    driverListView.onFailedDriverList();
                }
            }

            @Override
            public void onFailure(Call<DriverListResposne> call, Throwable t) {

            }
        });
    }
}
