package com.ananta.myapplication.general;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.facebook.share.Share;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import impl.ChangePasswordPresenterImpl;
import impl.ForgotPasswordPresenterImpl;
import presenter.ChangePasswordPresenter;
import presenter.ForgotPasswordPresenter;
import util.AlaBricks;
import view.ChangePasswordView;

public class ChangePassword extends AppCompatActivity implements ChangePasswordView {
    private ImageView imgBack;
    private MaterialEditText edConfirmPassword,edPassword;
    private LoadingButton btnProceed;
    Intent intent;
    ProgressDialog progressDialog;
    private MaterialEditText edOldPassword;
    ChangePasswordPresenter changePasswordPresenter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        sharedPreferences  =getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        changePasswordPresenter =new ChangePasswordPresenterImpl(this);
        progressDialog = new ProgressDialog(ChangePassword.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        intent = getIntent();
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        edConfirmPassword = (MaterialEditText)findViewById(R.id.edConfirmPassword);
        edPassword = (MaterialEditText)findViewById(R.id.edPassword);
        edOldPassword = (MaterialEditText)findViewById(R.id.edOldPassword);
        imgBack = (ImageView)findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        btnProceed.setText("SUBMIT");
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validatePassword(edOldPassword))
                {
                    FBToast.errorToast(ChangePassword.this, "Invalid Old Password. Minimum length is 8 characters.", FBToast.LENGTH_LONG);
                }
                else {
                    if (!AlaBricks.validatePassword(edPassword)) {
                        FBToast.errorToast(ChangePassword.this, "Invalid New Password. Minimum length is 8 characters.", FBToast.LENGTH_LONG);
                    } else {
                        if (!AlaBricks.validatePassword(edConfirmPassword)) {
                            FBToast.errorToast(ChangePassword.this, "Invalid Confirm Password. Minimum length is 8 characters.", FBToast.LENGTH_LONG);
                        } else {
                            if (!AlaBricks.getStringFromEditText(edPassword).equals(AlaBricks.getStringFromEditText(edPassword))) {
                                FBToast.errorToast(ChangePassword.this, "Password doesn't match", FBToast.LENGTH_LONG);
                            } else {
                                progressDialog.show();
                                changePasswordPresenter.changePassword(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.getStringFromEditText(edOldPassword),AlaBricks.getStringFromEditText(edConfirmPassword));
                            }
                        }
                    }
                }


            }
        });
    }

    @Override
    public void onSuccessChangePassword() {
        progressDialog.dismiss();
        FBToast.successToast(ChangePassword.this,"Password has been updated",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedChangePassword() {
        progressDialog.dismiss();
        FBToast.errorToast(ChangePassword.this,"Technical Error",FBToast.LENGTH_LONG);
    }

    @Override
    public void onNoMatchPassword() {
        progressDialog.dismiss();
        FBToast.errorToast(ChangePassword.this,"Old Password is not correct",FBToast.LENGTH_LONG);

    }
}
