package com.ananta.myapplication.general;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.CustomerDashboard;
import com.ananta.myapplication.customer.SingleProductActivity;
import com.dx.dxloadingbutton.lib.LoadingButton;
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

import impl.CheckMobilePresenterImpl;
import presenter.CheckMobilePresenter;
import util.AlaBricks;

public class ForgotPasswordStepTwo extends AppCompatActivity {

    private MaterialEditText edOTP;
    private LoadingButton btnProceed;
    private TextView txtVerification,txtVerificationText;
    private Typeface typefaceBold,typefaceRegular;
    ProgressDialog progressDialog;
    String id="",phone="";
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_two);

        Intent intent = getIntent();

        id = intent.getExtras().getString("id");
        phone = intent.getExtras().getString("phone");

        edOTP = (MaterialEditText)findViewById(R.id.edOTP);

        progressDialog = new ProgressDialog(ForgotPasswordStepTwo.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        txtVerification = (TextView)findViewById(R.id.txtVerification);
        txtVerificationText =  (TextView)findViewById(R.id.txtVerificationText);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");

        progressDialog.show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91-"+phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                ForgotPasswordStepTwo.this,               // Activity (for callback binding)
                mCallbacks);
        txtVerification.setTypeface(typefaceBold);
        txtVerificationText.setTypeface(typefaceRegular);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validateOTP(edOTP))
                {
                    FBToast.errorToast(ForgotPasswordStepTwo.this,"Please Add OTP",FBToast.LENGTH_LONG);
                }
                else
                {
                    progressDialog.show();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, AlaBricks.getStringFromEditText(edOTP));
                    signInWithPhoneAuthCredential(credential);

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
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                progressDialog.dismiss();
                FBToast.successToast(ForgotPasswordStepTwo.this,"OTP has been sent",FBToast.LENGTH_SHORT);

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
                            FBToast.successToast(ForgotPasswordStepTwo.this,"OTP has been Verified",FBToast.LENGTH_SHORT);
                            progressDialog.dismiss();

                            // FirebaseUser user = task.getResult().getUser();
                            // ...
                        }
                        else {

                            progressDialog.dismiss();
                            FBToast.errorToast(ForgotPasswordStepTwo.this,"Wrong OTP",FBToast.LENGTH_SHORT);
                            // Sign in failed, display a message and update the UI
                            Log.w("GoogleAuthentication", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
