package view;

import network.OffersResponse;

public interface OffersView {
    void onSuccessOffersView(OffersResponse offersResponse);
    void onFailedOffersView();
}
