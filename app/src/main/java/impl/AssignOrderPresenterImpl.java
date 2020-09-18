package impl;

import network.APIClient;
import network.APIInterface;
import network.AssignDriverResponse;
import presenter.AssignDriverPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AssignDriverView;

public class AssignOrderPresenterImpl implements AssignDriverPresenter {
    APIInterface apiInterface;
    AssignDriverView assignDriverView;
    public AssignOrderPresenterImpl(AssignDriverView assignDriverView)
    {
        this.assignDriverView = assignDriverView;
    }
    @Override
    public void assignDriver(String OdId, String VehicleId, String DriverId) {
        apiInterface  = APIClient.getClient().create(APIInterface.class);
        AssignDriverResponse assignDriverResponse = new AssignDriverResponse(OdId,VehicleId,DriverId);
        Call<AssignDriverResponse> assignDriverResponseCall = apiInterface.assignDriver(assignDriverResponse);
        assignDriverResponseCall.enqueue(new Callback<AssignDriverResponse>() {
            @Override
            public void onResponse(Call<AssignDriverResponse> call, Response<AssignDriverResponse> response) {
                AssignDriverResponse assignDriverResponse1 = response.body();
                if(assignDriverResponse1!=null)
                {
                    if(assignDriverResponse1.getSuccess()==1)
                    {
                        assignDriverView.onSuccessDriverAssign();
                    }
                    else
                    {
                        assignDriverView.onFailedDriverAssign();
                    }
                }
                else
                {
                    assignDriverView.onFailedDriverAssign();
                }
            }

            @Override
            public void onFailure(Call<AssignDriverResponse> call, Throwable t) {

            }
        });
    }
}
