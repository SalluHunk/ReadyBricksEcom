package com.ananta.myapplication.general;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ananta.myapplication.R;
import com.ananta.myapplication.transporter.TransporterDashboard;
import com.ananta.myapplication.customer.CustomerDashboard;
import com.ananta.myapplication.manufacturer.ManufacturerDashboard;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import impl.LoginPresenterImpl;
import impl.SocialMediaValidationPresenterImpl;
import network.SocialMedia;
import presenter.LoginPresenter;
import presenter.SocialMediaValidationPresenter;
import util.AlaBricks;
import view.LoginView;
import view.SocialMediaValidationView;

public class MainActivity extends AppCompatActivity implements SocialMediaValidationView, LoginView {

    Typeface typefaceBold,typefaceRegular;
    private TextView txtGoogle,txtWelcome,txtSignin,txtSkip,txtForgot,txtNewUser,txtSignup,txtOr,txtFacebook;
    LinearLayout linFacebook,linGoogle,linSignup;
    MaterialEditText edPhone,edPassword;
    LoadingButton btnProceed;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;
    SocialMediaValidationPresenter socialMediaValidationPresenter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String userType="";
    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;
    String path="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isLogin = checkLogin();

        Intent intent  =getIntent();
        path = intent.getExtras().getString("path");
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        setContentView(R.layout.activity_main);

        loginPresenter = new LoginPresenterImpl(this,MainActivity.this);
        socialMediaValidationPresenter = new SocialMediaValidationPresenterImpl(this);
        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        initUi();
        clickListeners();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        editor = sharedPreferences.edit();


       /* if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(this, "Normal sized screen", Toast.LENGTH_LONG).show();
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(this, "Small sized screen", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Screen size is neither large, normal or small", Toast.LENGTH_LONG).show();
        }

        //Determine density
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if (density == DisplayMetrics.DENSITY_HIGH) {
            Toast.makeText(this, "DENSITY_HIGH... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        }
        else if (density == DisplayMetrics.DENSITY_MEDIUM) {
            Toast.makeText(this, "DENSITY_MEDIUM... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        }
        else if (density == DisplayMetrics.DENSITY_LOW) {
            Toast.makeText(this, "DENSITY_LOW... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Density is neither HIGH, MEDIUM OR LOW.  Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        }*/


    }
    public void initUi()
    {
        txtWelcome = (TextView)findViewById(R.id.txtWelcome);
        txtSignin = (TextView)findViewById(R.id.txtSignin);
        txtSkip = (TextView)findViewById(R.id.txtSkip);
        txtForgot = (TextView)findViewById(R.id.txtForgot);
        txtNewUser = (TextView)findViewById(R.id.txtNewUser);
        txtSignup = (TextView)findViewById(R.id.txtSignup);
        txtOr = (TextView)findViewById(R.id.txtOr);
        txtFacebook = (TextView)findViewById(R.id.txtFacebook);
        txtGoogle = (TextView)findViewById(R.id.txtGoogle);

        linFacebook = (LinearLayout)findViewById(R.id.linFacebook);
        linGoogle = (LinearLayout)findViewById(R.id.linGoogle);
        linSignup = (LinearLayout)findViewById(R.id.linSignup);


        edPhone =  (MaterialEditText)findViewById(R.id.edPhone);
        edPassword = (MaterialEditText)findViewById(R.id.edPassword);

        btnProceed = (LoadingButton) findViewById(R.id.btnProceed);
        callbackManager = CallbackManager.Factory.create();

        if(path.equals("special") || path.equals("normal2"))
            txtSkip.setVisibility(View.GONE);

        setupFonts();



    }
    public void setupFonts()
    {
        txtWelcome.setTypeface(typefaceBold);
        txtSignin.setTypeface(typefaceRegular);
        txtSkip.setTypeface(typefaceRegular);
        txtForgot.setTypeface(typefaceRegular);
        txtNewUser.setTypeface(typefaceRegular);
        txtSignup.setTypeface(typefaceRegular);
        txtOr.setTypeface(typefaceRegular);
        txtFacebook.setTypeface(typefaceRegular);
        txtGoogle.setTypeface(typefaceRegular);
        btnProceed.setTypeface(typefaceBold);
    }
    public void clickListeners()
    {
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProceed.startLoading();
                if(!AlaBricks.validatePhoneNumber(edPhone))
                {
                    btnProceed.loadingFailed();
                    FBToast.errorToast(MainActivity.this,getString(R.string.txt_phone_error),FBToast.LENGTH_SHORT);
                }
                else
                {
                    if(!AlaBricks.validatePassword(edPassword))
                    {
                        btnProceed.loadingFailed();
                        FBToast.errorToast(MainActivity.this,getString(R.string.txt_password_error),FBToast.LENGTH_SHORT);
                    }
                    else
                    {
                       // progressDialog.show();
                        loginPresenter.login(AlaBricks.getStringFromEditText(edPhone),AlaBricks.getStringFromEditText(edPassword));
                    }
                }
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgotPasswordStepOne.class);
                startActivity(intent);
            }
        });

        linFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.signupType="Facebook";
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email","public_profile"));
                facebookSetup();
            }
        });

        linGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.signupType="Google";
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 100);
            }
        });

        linSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlaBricks.signupType="Normal";
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
            }
        });

        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent customerDashboard  = new Intent(MainActivity.this, CustomerDashboard.class);
                startActivity(customerDashboard);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 100) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String email="";
            email = account.getEmail();
            progressDialog.show();
            socialMediaValidationPresenter.validateEmail(email);
            AlaBricks.publicEmail= email;

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
     //       Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("GoogleError", "signInResult:failed code=" + e.getStatusCode());
           // updateUI(null);
        }
    }

    public void facebookSetup()
    {

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());

                                        // Application code
                                        String email = "",name="",id="",url="";
                                        try {
                                            email = object.getString("email");
                                            AlaBricks.publicEmail = email;
                                            name = object.getString("name");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Log.w("FacebookDetails",email);
                                        progressDialog.show();
                                        socialMediaValidationPresenter.validateEmail(email);
                                       //     Toast.makeText(NewWelcomeActivity.this, ""+email+" "+name, Toast.LENGTH_SHORT).show();
                                        //loginOrSignup();
                                        // 01/31/1980 format
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "name,email");
                        request.setParameters(parameters);
                        request.executeAsync();
                        //           Toast.makeText(NewWelcomeActivity.this, "Login Success", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code

                        Toast.makeText(MainActivity.this, "Login Failed"+exception, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("FacebookKey", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("FacebookKey", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("FacebookKey", "printHashKey()", e);
        }
    }


    @Override
    public void onSuccessSocialMediaValidation(SocialMedia socialMedia) {
        progressDialog.dismiss();
        FBToast.successToast(MainActivity.this,"You are successfully logged in",FBToast.LENGTH_SHORT);
        editor.putString(AlaBricks.sharedUserId,socialMedia.getUserId());
        editor.putString(AlaBricks.sharedUserType,socialMedia.getRole());
        editor.putInt(AlaBricks.sharedNews,Integer.parseInt(socialMedia.getIsNewsLetter()));
        editor.commit();

        if (path.equals("special") || path.equals("login"))
        {
            if(socialMedia.getRole().equals("2"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Customer",FBToast.LENGTH_SHORT);
                finish();
            }
            else if(socialMedia.getRole().equals("3"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Manufacturer",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this, ManufacturerDashboard.class);
                startActivity(intent);
                finishAffinity();
            }
            else
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Transporter",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this, TransporterDashboard.class);
                startActivity(intent);
                finishAffinity();

            }
        }
        else
        {
            if(socialMedia.getRole().equals("2"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Customer",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this,CustomerDashboard.class);
                startActivity(intent);
                finishAffinity();

            }
            else if(socialMedia.getRole().equals("3"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Manufacturer",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this, ManufacturerDashboard.class);
                startActivity(intent);
                finishAffinity();

            }
            else
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Transporter",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this, TransporterDashboard.class);
                startActivity(intent);
                finishAffinity();

            }
        }

    }

    @Override
    public void onFailedSocialMediaValidation() {
        progressDialog.dismiss();
       // AlaBricks.userType=userType;

        Intent intent= new Intent(MainActivity.this,SignupActivity.class);
        intent.putExtra("path",path);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
         AlaBricks.globalCustomer = null;
         finish();
    }
    public boolean checkLogin()
    {
        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public String returnUserType()
    {
        String type="";
        type = sharedPreferences.getString(AlaBricks.sharedUserType,"");
        return type;
    }
    @Override
    public void onSuccessLogin(SocialMedia socialMedia) {
        btnProceed.loadingSuccessful();
        btnProceed.reset();
        editor.putString(AlaBricks.sharedUserId,socialMedia.getUserId());
        editor.putString(AlaBricks.sharedUserType,socialMedia.getRole());
        editor.putInt(AlaBricks.sharedNews,Integer.parseInt(socialMedia.getIsNewsLetter()));
        editor.commit();
        progressDialog.dismiss();
        if (path.equals("special"))
        {
            if(socialMedia.getRole().equals("2"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Customer",FBToast.LENGTH_SHORT);
                finish();
            }
            else if(socialMedia.getRole().equals("3"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Manufacturer",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this,ManufacturerDashboard.class);
                startActivity(intent);
                finishAffinity();
            }
            else
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Transporter",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this,TransporterDashboard.class);
                startActivity(intent);
                finishAffinity();
            }
        }
        else
        {

            if(socialMedia.getRole().equals("2"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Customer",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this,CustomerDashboard.class);
                startActivity(intent);
                finishAffinity();

            }
            else if(socialMedia.getRole().equals("3"))
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Manufacturer",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this,ManufacturerDashboard.class);
                startActivity(intent);
                finishAffinity();

            }
            else
            {
                FBToast.successToast(MainActivity.this,"You are successfully logged in as a Transporter",FBToast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this,TransporterDashboard.class);
                startActivity(intent);
                finishAffinity();

            }
        }
    }

    @Override
    public void onFailedLogin() {
        btnProceed.loadingFailed();
        progressDialog.dismiss();
        FBToast.errorToast(MainActivity.this,"Invalid Password",FBToast.LENGTH_SHORT);

    }

    @Override
    public void onAccountNotVerified() {
        btnProceed.loadingFailed();
        progressDialog.dismiss();
        FBToast.errorToast(MainActivity.this,"Account is not verified yet.",FBToast.LENGTH_SHORT);
    }

    @Override
    public void onRejected(String message) {
        btnProceed.loadingFailed();
        progressDialog.dismiss();
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Message From Admin")
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .show();
    }

    @Override
    public void onNoAccountFound() {
        progressDialog.dismiss();
        btnProceed.loadingFailed();
        FBToast.errorToast(MainActivity.this,"No Account Found with this Phone Number",FBToast.LENGTH_LONG);
    }
}
