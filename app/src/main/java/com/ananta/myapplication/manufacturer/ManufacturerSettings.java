package com.ananta.myapplication.manufacturer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.ananta.myapplication.general.ChangePassword;
import com.ananta.myapplication.general.ContactUsActivity;
import com.ananta.myapplication.general.MainActivity;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import util.AlaBricks;

public class ManufacturerSettings extends AppCompatActivity {

    private LinearLayout linLogout,linContact,linResetPassword;
    private ImageView imgBack;
    SharedPreferences sharedPreferences;
    private GoogleSignInClient mGoogleSignInClient;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_manu);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        imgBack = (ImageView)findViewById(R.id.imgBack);
        linContact = (LinearLayout)findViewById(R.id.linContact);
        linResetPassword = (LinearLayout)findViewById(R.id.linResetPassword);
        linLogout = (LinearLayout)findViewById(R.id.linLogout);

        linContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManufacturerSettings.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

        linResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManufacturerSettings.this, ChangePassword.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(ManufacturerSettings.this, gso);

        linLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GraphRequest(com.facebook.AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                        .Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse) {
                        LoginManager.getInstance().logOut();
                    }
                }).executeAsync();

                LoginManager.getInstance().logOut();

                mGoogleSignInClient.signOut();

                editor.putString(AlaBricks.sharedUserId,"");
                editor.putString(AlaBricks.sharedUserType,"");
                editor.putString(AlaBricks.sharedName,"");

                editor.commit();

                Intent intent =new Intent(ManufacturerSettings.this, MainActivity.class);
                intent.putExtra("path","normal");
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}
