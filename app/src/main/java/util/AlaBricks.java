package util;

import android.app.Application;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import items.Customer;
import items.Manufacturer;
import items.OrderProcess;
import network.CustomerProductData;
import network.DriverListData;
import network.ManufacturerData;
import network.NotificationData;
import network.OrderData;
import network.ProductData;
import network.VehicleListData;

public class AlaBricks extends Application {

 //   public static String url ="http://bricks.idglock.com/index.php/Api/";
    public static String url="http://readybrick.idglock.com/index.php/Api/";
    public static DriverListData driverListData;
    public static VehicleListData vehicleListData;
    public static Customer globalCustomer;
    public static Manufacturer manufacturer;
    public static String userType="";
    public static String signupType="";
    public static String sharedName="alabricks";
    public static String sharedUserId="userId";
    public static String publicEmail="";
    public static String sharedNews="newsletter";
    public static String sharedUserType="userType";
    public static String imagePath="http://readybrick.idglock.com/uploads/";
    public static ArrayList<CustomerProductData> customerProductData = new ArrayList<>();
    public static boolean isProductAdded=false;
    public static OrderProcess orderProcess;
    public static ProductData productData;

    public static String filterPrice="";
    public static String filterSortBy="";
    public static String filterManufacturer="";
    public static ArrayList<String> manufacturerId=new ArrayList<>();
    public static ArrayList<String> finalManufacturerId=new ArrayList<>();
    public static ArrayList<ManufacturerData> manufacturerData = new ArrayList<>();
    public static CustomerProductData singleCustomerProductData;
    public static OrderData orderData;
    public static String globalFirebaseToken="firebaseToken";
    public static NotificationData notificationData;
    public static String fromNotify="";

    public static boolean validatePhoneNumber(MaterialEditText editText)
    {
        boolean isValid = false;

        if(editText.getText().toString().trim().length()==10)
        {
            isValid = true;
        }
        return isValid;
    }
    public static boolean validatePassword(MaterialEditText editText)
    {
        boolean isValid = false;

        if(editText.getText().toString().trim().length()>=8)
        {
            isValid = true;
        }
        return isValid;
    }
    public static boolean validateBlankValidation(MaterialEditText materialEditText)
    {
        boolean isValid = false;

        if(materialEditText.getText().toString().trim().length()>0)
        {
            isValid = true;
        }
        return isValid;
    }
    public static String getStringFromEditText(MaterialEditText materialEditText)
    {
        String response="";
        if(materialEditText.getText().toString().trim().length()>0)
        {
            response = materialEditText.getText().toString().trim();
        }
        return response;
    }
    public static boolean validateOTP(MaterialEditText editText)
    {
        if(editText.getText().toString().trim().length()==6){
            return true;
        }
        else
        {
            return false;
        }
    }
}
