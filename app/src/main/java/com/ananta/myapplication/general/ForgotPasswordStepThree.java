package com.ananta.myapplication.general;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import impl.ForgotPasswordPresenterImpl;
import presenter.ForgotPasswordPresenter;
import util.AlaBricks;
import view.ForgotPasswordView;

public class ForgotPasswordStepThree extends AppCompatActivity implements ForgotPasswordView
{

    private ImageView imgBack;
    private MaterialEditText edConfirmPassword,edPassword;
    TextView txtVerificationText,txtVerification;
    private LoadingButton btnProceed;
    Intent intent;
    String id="";
    ProgressDialog progressDialog;
    ForgotPasswordPresenter forgotPasswordPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_three);

        forgotPasswordPresenter = new ForgotPasswordPresenterImpl(this);
        progressDialog = new ProgressDialog(ForgotPasswordStepThree.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        intent = getIntent();
        id =  intent.getExtras().getString("id");
        btnProceed = (LoadingButton)findViewById(R.id.btnProceed);
        edConfirmPassword = (MaterialEditText)findViewById(R.id.edConfirmPassword);
        edPassword = (MaterialEditText)findViewById(R.id.edPassword);
        txtVerificationText = (TextView)findViewById(R.id.txtVerificationText);
        txtVerification = (TextView)findViewById(R.id.txtVerification);
        imgBack = (ImageView)findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordStepThree.this);
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ForgotPasswordStepThree.this,MainActivity.class);
                                intent.putExtra("path","normal");
                                startActivity(intent);
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validatePassword(edPassword))
                {
                    FBToast.errorToast(ForgotPasswordStepThree.this,"Invalid New Password. Mininum length is 8 characters.",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(!AlaBricks.validatePassword(edConfirmPassword))
                    {
                        FBToast.errorToast(ForgotPasswordStepThree.this,"Invalid Confirm Password. Minimum length is 8 characters.",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        if(!AlaBricks.getStringFromEditText(edPassword).equals(edConfirmPassword))
                        {
                            FBToast.errorToast(ForgotPasswordStepThree.this,"Password doesn't match",FBToast.LENGTH_LONG);
                        }
                        else
                        {
                            progressDialog.show();
                            forgotPasswordPresenter.forgotPasswordUpdate(id,AlaBricks.getStringFromEditText(edPassword));
                        }
                    }
                }

            }
        });
    }

    @Override
    public void onSuccessForgotPassword() {
        progressDialog.dismiss();
        FBToast.successToast(ForgotPasswordStepThree.this,"Password has been updated",FBToast.LENGTH_LONG);
        Intent intent = new Intent(ForgotPasswordStepThree.this,MainActivity.class);
        intent.putExtra("path","normal");
        startActivity(intent);
        finishAffinity();

    }

    @Override
    public void onFailedForgotPassword() {
        progressDialog.dismiss();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordStepThree.this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ForgotPasswordStepThree.this,MainActivity.class);
                        intent.putExtra("path","normal");
                        startActivity(intent);
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
