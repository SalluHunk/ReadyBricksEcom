package view;

import network.SocialMedia;

public interface SocialMediaValidationView {
    void onSuccessSocialMediaValidation(SocialMedia socialMedia);
    void onFailedSocialMediaValidation();
}
