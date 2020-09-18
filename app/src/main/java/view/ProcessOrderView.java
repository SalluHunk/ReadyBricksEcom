package view;

public interface ProcessOrderView {
    void onSuccessProcessOrder(String orderId);
    void onFailedProcessOrder();
}
