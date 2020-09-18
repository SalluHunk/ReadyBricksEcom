package view;

import network.SocialMedia;

public interface LoginView {

     void onSuccessLogin(SocialMedia socialMedia);
     void onFailedLogin();
     void onAccountNotVerified();
     void onRejected(String message);
     void onNoAccountFound();
}
