package com.ananta.myapplication.general;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import impl.CheckMobilePresenterImpl;
import network.SocialMedia;
import presenter.CheckMobilePresenter;
import util.AlaBricks;
import view.CheckMobileView;

public class ForgotPasswordStepOne extends AppCompatActivity implements CheckMobileView {

    private LoadingButton btnProceed;
    private TextView txtVerification,txtVerificationText;
    private MaterialEditText edPhone;
    private Typeface typefaceBold,typefaceRegular;
    CheckMobilePresenter checkMobilePresenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_one);

        progressDialog = new ProgressDialog(ForgotPasswordStepOne.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        checkMobilePresenter =new CheckMobilePresenterImpl(this);

        txtVerification = (TextView)findViewById(R.id.txtVerification);
        txtVerificationText =  (TextView)findViewById(R.id.txtVerificationText);
        edPhone = (MaterialEditText)findViewById(R.id.edPhone);
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);

        typefaceBold = Typeface.createFromAsset(getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getAssets(),"regularfont.otf");


        txtVerification.setTypeface(typefaceBold);
        txtVerificationText.setTypeface(typefaceRegular);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validatePhoneNumber(edPhone))
                {
                    FBToast.errorToast(ForgotPasswordStepOne.this,"Invalid Phone Number",FBToast.LENGTH_LONG);
                }
                else
                {
                    progressDialog.show();
                    checkMobilePresenter.checkPhone(AlaBricks.getStringFromEditText(edPhone));
                }
            }
        });
    }

    @Override
    public void onSuccessCheckMobileView(SocialMedia socialMedia) {
        progressDialog.dismiss();
        Intent intent =new Intent(ForgotPasswordStepOne.this,ForgotPasswordStepTwo.class);
        intent.putExtra("id",socialMedia.getUserId());
        intent.putExtra("phone",AlaBricks.getStringFromEditText(edPhone));
        startActivity(intent);

    }

    @Override
    public void onFailedCheckMobileView() {
        progressDialog.dismiss();
        FBToast.errorToast(ForgotPasswordStepOne.this,"Phone Number is not Registered",FBToast.LENGTH_LONG);
    }


}
