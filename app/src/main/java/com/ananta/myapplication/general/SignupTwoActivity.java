package com.ananta.myapplication.general;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.CustomerDashboard;
import com.ananta.myapplication.customer.SingleProductActivity;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import java.util.concurrent.TimeUnit;

import impl.SignupPresenterImpl;
import items.Customer;
import presenter.SignupPresenter;
import util.AlaBricks;
import view.SignupView;

public class SignupTwoActivity extends AppCompatActivity  implements SignupView {
    private LoadingButton btnProceed;
    MaterialEditText edEmail,edPhone,edOTP,edGST,edVat;
    TextView txtContactDetails,txtSendOTP,txtFinancial;
    private ImageView imgBack;
    private Typeface typefaceBold,typefaceRegular;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId="";
    ProgressDialog progressDialog;
    SignupPresenter signupPresenter;
    String currentPhoneNumber="";
    String path="";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String signupId="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);

        signupPresenter = new SignupPresenterImpl(this);
        Intent intent = getIntent();
        path = intent.getExtras().getString("path");
        init();
        clickListeners();

    }

    public void init()
    {

        imgBack = (ImageView)findViewById(R.id.imgBack);
        txtContactDetails = (TextView)findViewById(R.id.txtContactDetails);
        edEmail = (MaterialEditText)findViewById(R.id.edEmail);
        edPhone = (MaterialEditText)findViewById(R.id.edPhone);
        txtSendOTP = (TextView)findViewById(R.id.txtSendOTP);
        edOTP = (MaterialEditText)findViewById(R.id.edOTP);
        txtFinancial = (TextView)findViewById(R.id.txtFinancial);
        edGST =(MaterialEditText)findViewById(R.id.edGST);
        edVat = (MaterialEditText)findViewById(R.id.edVat);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);

        progressDialog = new ProgressDialog(SignupTwoActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        txtContactDetails.setTypeface(typefaceBold);
        txtFinancial.setTypeface(typefaceBold);
        txtSendOTP.setTypeface(typefaceBold);

        sharedPreferences =  getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        GoogleCallbacks();



    }
    public void clickListeners()
    {
            btnProceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!AlaBricks.validatePhoneNumber(edPhone))
                    {
                        FBToast.errorToast(SignupTwoActivity.this,getString(R.string.txt_phone_error),FBToast.LENGTH_SHORT);
                    }
                    else
                    {
                        if(!AlaBricks.validateBlankValidation(edEmail))
                        {
                            FBToast.errorToast(SignupTwoActivity.this,getString(R.string.txt_email_error),FBToast.LENGTH_SHORT);
                        }
                        else
                        {
                            String phone = AlaBricks.getStringFromEditText(edPhone);
                            if(!phone.equals(currentPhoneNumber))
                            {
                                FBToast.errorToast(SignupTwoActivity.this,getString(R.string.txt_regenerate_error),FBToast.LENGTH_SHORT);
                            }
                            else {

                                if(AlaBricks.userType.equals("2"))
                                {
                                    if (AlaBricks.globalCustomer.getCompanyDetails() != "") {
                                        if (!AlaBricks.validateBlankValidation(edGST) || !AlaBricks.validateBlankValidation(edVat)) {
                                            FBToast.errorToast(SignupTwoActivity.this, getString(R.string.txt_financial_error), FBToast.LENGTH_SHORT);
                                        } else {
                                            progressDialog.show();
                                            Customer customer = new Customer(AlaBricks.globalCustomer.getFirstName(),AlaBricks.globalCustomer.getLastName(),AlaBricks.globalCustomer.getCompanyDetails(),AlaBricks.globalCustomer.getPassword(),AlaBricks.getStringFromEditText(edPhone),AlaBricks.getStringFromEditText(edEmail),AlaBricks.getStringFromEditText(edGST),AlaBricks.getStringFromEditText(edVat));
                                            AlaBricks.globalCustomer = customer;
                                            signupPresenter.signup(AlaBricks.userType,AlaBricks.globalCustomer.getCompanyDetails(),AlaBricks.globalCustomer.getFirstName(),AlaBricks.globalCustomer.getLastName(),AlaBricks.globalCustomer.getPassword(),AlaBricks.globalCustomer.getEmailAddress(),AlaBricks.globalCustomer.getMobileNumber(),AlaBricks.globalCustomer.getVat(),AlaBricks.globalCustomer.getGstin(),AlaBricks.signupType,"");
                                        }
                                    } else {
                                        progressDialog.show();
                                        Customer customer = new Customer(AlaBricks.globalCustomer.getFirstName(),AlaBricks.globalCustomer.getLastName(),AlaBricks.globalCustomer.getCompanyDetails(),AlaBricks.globalCustomer.getPassword(),AlaBricks.getStringFromEditText(edPhone),AlaBricks.getStringFromEditText(edEmail),AlaBricks.getStringFromEditText(edGST),AlaBricks.getStringFromEditText(edVat));
                                        AlaBricks.globalCustomer = customer;
                                        signupPresenter.signup(AlaBricks.userType,AlaBricks.globalCustomer.getCompanyDetails(),AlaBricks.globalCustomer.getFirstName(),AlaBricks.globalCustomer.getLastName(),AlaBricks.globalCustomer.getPassword(),AlaBricks.globalCustomer.getEmailAddress(),AlaBricks.globalCustomer.getMobileNumber(),AlaBricks.globalCustomer.getVat(),AlaBricks.globalCustomer.getGstin(),AlaBricks.signupType,"");
                                    }
                                }
                                else
                                {
                                        if (!AlaBricks.validateBlankValidation(edGST) || !AlaBricks.validateBlankValidation(edVat)) {
                                            FBToast.errorToast(SignupTwoActivity.this, getString(R.string.txt_financial_error), FBToast.LENGTH_SHORT);
                                        } else {
                                            progressDialog.show();
                                            signupPresenter.signup(AlaBricks.userType,AlaBricks.manufacturer.getCompanyName(),"","",AlaBricks.manufacturer.getPassword(),AlaBricks.getStringFromEditText(edEmail),AlaBricks.getStringFromEditText(edPhone),AlaBricks.getStringFromEditText(edVat),AlaBricks.getStringFromEditText(edGST),AlaBricks.signupType,AlaBricks.manufacturer.getLogo());
                                        }
                                }
                            }
                        }
                    }

                }
            });

            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            txtSendOTP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(AlaBricks.validateBlankValidation(edPhone))
                    {
                        progressDialog.show();
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91-"+AlaBricks.getStringFromEditText(edPhone),        // Phone number to verify
                                60,                 // Timeout duration
                                TimeUnit.SECONDS,   // Unit of timeout
                                SignupTwoActivity.this,               // Activity (for callback binding)
                                mCallbacks);        // OnVerificationStateChangedCallbacks
                    }
                }
            });
    }

    public void GoogleCallbacks()
    {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                progressDialog.dismiss();
                Log.d("GoogleAuthentication", "onVerificationCompleted:" + credential);

          //      signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("GoogleAuthentication", "onVerificationFailed", e);
                progressDialog.dismiss();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                progressDialog.dismiss();
                Log.d("GoogleAuthentication", "onCodeSent:" + verificationId);
                currentPhoneNumber = AlaBricks.getStringFromEditText(edPhone);
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                FBToast.successToast(SignupTwoActivity.this,"OTP has been sent",FBToast.LENGTH_SHORT);

                // ...
            }
        };

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("GoogleAuthentication", "signInWithCredential:success");
                            FBToast.successToast(SignupTwoActivity.this,"OTP has been Verified",FBToast.LENGTH_SHORT);
                            progressDialog.dismiss();
                            if(AlaBricks.userType.equals("2"))
                            {
                                AlaBricks.globalCustomer = null;

                                editor.putString(AlaBricks.sharedUserId,signupId);
                                editor.putString(AlaBricks.sharedUserType,"2");
                                editor.commit();

                                FBToast.successToast(SignupTwoActivity.this,"You are successfully Signup As a Customer",FBToast.LENGTH_LONG);
                               if(path.equals("special"))
                               {
                                   Intent intent = new Intent(SignupTwoActivity.this, SingleProductActivity.class);
                                   intent.putExtra("path","special");
                                   startActivity(intent);
                                   finishAffinity();
                               }
                               else
                               {
                                   Intent intent = new Intent(SignupTwoActivity.this, CustomerDashboard.class);
                                   startActivity(intent);
                                   finishAffinity();
                               }
                            }
                            else
                            {

                                Intent intent = new Intent(SignupTwoActivity.this,SignupThreeActivity.class);
                                intent.putExtra("signupId",signupId);
                                startActivity(intent);
                            }
      // FirebaseUser user = task.getResult().getUser();
                            // ...
                        }
                        else {

                            progressDialog.dismiss();
                            FBToast.errorToast(SignupTwoActivity.this,"Wrong OTP",FBToast.LENGTH_SHORT);
                            // Sign in failed, display a message and update the UI
                            Log.w("GoogleAuthentication", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        if(AlaBricks.globalCustomer!=null)
        {
            edEmail.setText(AlaBricks.globalCustomer.getEmailAddress());
            edPhone.setText(AlaBricks.globalCustomer.getMobileNumber());
            edGST.setText(AlaBricks.globalCustomer.getGstin());
            edVat.setText(AlaBricks.globalCustomer.getVat());
            if(AlaBricks.userType.equals("2"))
            {
                if(AlaBricks.globalCustomer.getCompanyDetails().equals(""))
                {
                    edVat.setVisibility(View.GONE);
                    edGST.setVisibility(View.GONE);
                    txtFinancial.setVisibility(View.GONE);
                }
            }
        }
        if(!AlaBricks.signupType.equals("Normal"))
        {
            edEmail.setText(AlaBricks.publicEmail);
        }
        super.onResume();
    }

    @Override
    public void onSuccessSignup(String signupId) {
        this.signupId = signupId;
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, AlaBricks.getStringFromEditText(edOTP));
        signInWithPhoneAuthCredential(credential);


    }

    @Override
    public void onFailedSignup() {
        progressDialog.dismiss();
        FBToast.errorToast(SignupTwoActivity.this,getString(R.string.txt_exist_phone),FBToast.LENGTH_SHORT);
    }

    @Override
    public void onFailedSignupEmail() {
        progressDialog.dismiss();
        FBToast.errorToast(SignupTwoActivity.this,getString(R.string.txt_exist_email),FBToast.LENGTH_SHORT);
    }
}
