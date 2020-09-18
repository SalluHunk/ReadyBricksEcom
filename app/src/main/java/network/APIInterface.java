package network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {


    @POST("loginwithsocial")
    Call<SocialMediaValidation> loginWithSocial(@Body SocialMediaValidation socialMediaValidation);

    @POST("getallddl")
    Call<AddressResponse> addressList(@Body AddressResponse addressResponse);

    @POST("getaddress")
    Call<CheckAddressResponse> checkAddress(@Body CheckAddressResponse checkAddressResponse);

    @POST("gettransporterorders")
    Call<TransporterOrderListResponse> listTransportOrder(@Body TransporterOrderListResponse transporterOrderListResponse);

    @POST("signup")
    Call<SignupResponse> signup(@Body SignupResponse signupResponse);

    @POST("signup3")
    Call<SignupThreeResponse> signupLast(@Body SignupThreeResponse signupThreeResponse);

    @POST("login")
    Call<LoginRespnse> login(@Body LoginRespnse loginRespnse);

    @POST("addproduct")
    Call<AddProductResponse> addProduct(@Body AddProductResponse addProductResponse);

    @POST("getproduct")
    Call<ProductListResponse> listProduct(@Body ProductListResponse productListResponse);

    @POST("updateproductstockstatus")
    Call<AddInventoryResponse> addStock(@Body AddInventoryResponse addInventoryResponse);

    @POST("getallproduct")
    Call<CustomerProductResponse> listProducts(@Body CustomerProductResponse customerProductResponse);

    @POST("addtocart")
    Call<AddToCartResponse> addToCart(@Body AddToCartResponse addToCartResponse);

    @POST("getcartbyuserid")
    Call<CartResponse> displayCart(@Body CartResponse cartResponse);

    @POST("addorder")
    Call<AddOrderResponse> addOrder(@Body AddOrderResponse cartResponse);

    @POST("updateproduct")
    Call<UpdateProductResponse> updateProduct(@Body UpdateProductResponse updateProductResponse);

    @POST("updatecartbyid")
    Call<UpdateCartResponse> updateCart(@Body UpdateCartResponse updateCartResponse);

    @POST("adddriver")
    Call<AddDriverResposne> addDriver(@Body AddDriverResposne addDriverResposne);

    @POST("addvehicle")
    Call<AddTruckResponse> addDriver(@Body AddTruckResponse addTruckResponse);

    @POST("deletecartbyid")
    Call<DeleteCartResponse> deleteCartItem(@Body DeleteCartResponse deleteCartResponse);

    @POST("getcustomerorders")
    Call<OrderListResponse> listOrders(@Body OrderListResponse orderListResponse);

    @POST("getmanufactureorders")
    Call<ManuOrderListResponse> orderListManufacturer(@Body ManuOrderListResponse manuOrderListResponse);

    @POST("getallvahicledriver")
    Call<VehicleDriverResponse> listVehicleandDriver(@Body VehicleDriverResponse vehicleDriverResponse);

    @POST("assignorder")
    Call<AssignDriverResponse> assignDriver(@Body AssignDriverResponse assignDriverResponse);

    @POST("gettransporterdriver")
    Call<DriverListResposne> listDriver(@Body DriverListResposne driverListResposne);

    @POST("updatetransporterdriver")
    Call<UpdateDriverResponse> updateDriver(@Body UpdateDriverResponse updateDriverResponse);

    @POST("gettransportervehicle")
    Call<TruckListResponse> listTrucks(@Body TruckListResponse truckListResponse);

    @POST("updatetransportervehicle")
    Call<UpdateVehicleResponse> updateVehicle(@Body UpdateVehicleResponse updateVehicleResponse);

    @POST("trackorder")
    Call<TrackingOrderResponse> orderTrack(@Body TrackingOrderResponse trackingOrderResponse);

    @POST("checkmobilenumber")
    Call<CheckMobileResponse> checkMobile(@Body CheckMobileResponse checkMobileResponse);

    @POST("forgotpassword")
    Call<ForgotPasswordResponse> forgotPasswordUpdate(@Body ForgotPasswordResponse forgotPasswordResponse);

    @POST("changepassword")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordResponse changePasswordResponse);

    @POST("contactus")
    Call<ContactUsResponse> addContact(@Body ContactUsResponse contactUsResponse);

    @POST("newsletter")
    Call<NewsletterResponse> updateNewsletter(@Body NewsletterResponse newsletterResponse);

    @POST("getuserdetail")
    Call<UserProfileResponse> displayUserProfile(@Body UserProfileResponse userProfileResponse);

    @POST("updatecustomer")
    Call<UpdatePorfileResponse> updateProfile(@Body UpdatePorfileResponse updatePorfileResponse);

    @POST("getoffers")
    Call<OffersResponse> listOffers(@Body OffersResponse offersResponse);

    @POST("getnotification")
    Call<NotificationListResponse> listNotifications(@Body NotificationListResponse notificationListResponse);

    @POST("addordereview")
    Call<AddReviewResponse> addReview(@Body AddReviewResponse addReviewResponse);
}




