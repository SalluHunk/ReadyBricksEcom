package view;

import network.UserProfileData;

public interface UserProfileView {
    void onSuccessProfileView(UserProfileData userProfileData);
    void onFailedProfileView();
}
