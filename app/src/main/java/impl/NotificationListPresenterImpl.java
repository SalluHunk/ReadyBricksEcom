package impl;

import network.APIClient;
import network.APIInterface;
import network.NotificationListResponse;
import presenter.NotificationListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.NotificationListView;

public class NotificationListPresenterImpl implements NotificationListPresenter {
    APIInterface apiInterface;
    NotificationListView notificationListView;

    public NotificationListPresenterImpl(NotificationListView notificationListView)
    {
        this.notificationListView = notificationListView;
    }

    @Override
    public void listNotification(String roleId) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        NotificationListResponse notificationListResponse = new NotificationListResponse(roleId);
        Call<NotificationListResponse> notificationListResponseCall = apiInterface.listNotifications(notificationListResponse);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse notificationListResponse1 = response.body();
                if(notificationListResponse1!=null)
                {
                    if(notificationListResponse1.getSuccess()==1)
                    {
                        notificationListView.onSuccessNotification(notificationListResponse1.getNotificationData());
                    }
                    else
                    {
                        notificationListView.onFailedNotification();
                    }
                }
                else
                {
                    notificationListView.onFailedNotification();
                }
            }

            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {

            }
        });
    }
}
