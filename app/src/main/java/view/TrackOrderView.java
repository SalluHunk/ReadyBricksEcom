package view;

import network.TrackingData;

public interface TrackOrderView {
    void onSuccessTracking(TrackingData trackingData);
    void onFailedTracking();
}
