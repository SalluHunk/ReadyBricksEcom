package impl;

import network.APIClient;
import network.APIInterface;
import network.UpdatePorfileResponse;
import presenter.UpdateProfilePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.UpdateProfileView;

public class UpdateProfilePresenterImpl implements UpdateProfilePresenter {
    UpdateProfileView updateProfileView;
    APIInterface apiInterface;
    public UpdateProfilePresenterImpl(UpdateProfileView updateProfileView)
    {
        this.updateProfileView =updateProfileView;
    }
    @Override
    public void updateProfile(String userId, String companyName, String firstName, String lastName, String vatNo, String gstNo, String address, String landmark, String countryId, String stateId, String districtId, String cityId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        UpdatePorfileResponse updatePorfileResponse =new UpdatePorfileResponse(userId,companyName,firstName,lastName,address,landmark,vatNo,gstNo,countryId,stateId,districtId,cityId);
        Call<UpdatePorfileResponse> updatePorfileResponseCall = apiInterface.updateProfile(updatePorfileResponse);
        updatePorfileResponseCall.enqueue(new Callback<UpdatePorfileResponse>() {
            @Override
            public void onResponse(Call<UpdatePorfileResponse> call, Response<UpdatePorfileResponse> response) {
                UpdatePorfileResponse updatePorfileResponse1 = response.body();
                if(updatePorfileResponse1!=null)
                {
                    if(updatePorfileResponse1.getSuccess()==1)
                    {
                        updateProfileView.onSuccessUpdateProfile();
                    }
                    else
                    {
                        updateProfileView.onFailedUpdateProfile();
                    }
                }
                else
                {
                    updateProfileView.onFailedUpdateProfile();
                }
            }

            @Override
            public void onFailure(Call<UpdatePorfileResponse> call, Throwable t) {

            }
        });
    }
}
