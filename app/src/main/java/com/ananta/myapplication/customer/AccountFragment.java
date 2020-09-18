package com.ananta.myapplication.customer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.ananta.myapplication.R;
import com.ananta.myapplication.general.ChangePassword;
import com.ananta.myapplication.general.ContactUsActivity;
import com.ananta.myapplication.general.MainActivity;
import com.ananta.myapplication.general.ProfileActivity;
import com.ananta.myapplication.general.SignupActivity;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.tfb.fbtoast.FBToast;

import impl.NewsletterPresenterImpl;
import presenter.NewsletterPresenter;
import util.AlaBricks;
import view.NewsletterView;

public class AccountFragment extends Fragment implements NewsletterView {

    private TextView txtLogout,txtEditProfile,txtNofication,txtNewsLetter,txtContact,txtResetPassword;
    private LinearLayout linLogout;
    private Typeface typefaceBold,typefaceRegular;
    private GoogleSignInClient mGoogleSignInClient;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private LinearLayout linMain,linEmpty;
    TextView txtEmpty;
    Button btnLogin;
    private LinearLayout linResetPassword,linContact,linEditProfile;
    private SwitchCompat switchNewsLetter;
    int newsLetter=0;
    NewsletterPresenter newsletterPresenter;
    private LinearLayout linNotification;
    private TextView txtSignup;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);

        newsletterPresenter = new NewsletterPresenterImpl(this);
        sharedPreferences = getContext().getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);
        newsLetter = sharedPreferences.getInt(AlaBricks.sharedNews,0);

        editor = sharedPreferences.edit();
        txtEditProfile = (TextView)view.findViewById(R.id.txtEditProfile);
        txtNofication = (TextView)view.findViewById(R.id.txtNofication);
        txtNewsLetter = (TextView)view.findViewById(R.id.txtNewsLetter);
        txtContact = (TextView)view.findViewById(R.id.txtContact);
        txtResetPassword = (TextView)view.findViewById(R.id.txtResetPassword);
        txtLogout = (TextView)view.findViewById(R.id.txtLogout);
        btnLogin = (Button)view.findViewById(R.id.btnLogin);
        txtEmpty = (TextView)view.findViewById(R.id.txtEmpty);
        linEmpty = (LinearLayout)view.findViewById(R.id.linEmpty);
        linMain  = (LinearLayout)view.findViewById(R.id.linMain);
        linLogout =(LinearLayout)view.findViewById(R.id.linLogout);
        linResetPassword = (LinearLayout)view.findViewById(R.id.linResetPassword);
        linEditProfile = (LinearLayout)view.findViewById(R.id.linEditProfile);
        linNotification = (LinearLayout)view.findViewById(R.id.linNotification);
        txtSignup = (TextView)view.findViewById(R.id.txtSignup);

        typefaceBold = Typeface.createFromAsset(getContext().getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getContext().getAssets(),"regularfont.otf");
        linContact = (LinearLayout)view.findViewById(R.id.linContact);
        switchNewsLetter =(SwitchCompat)view.findViewById(R.id.switchNewsLetter);

        txtEditProfile.setTypeface(typefaceRegular);
        txtNofication.setTypeface(typefaceRegular);
        txtNewsLetter.setTypeface(typefaceRegular);
        txtContact.setTypeface(typefaceRegular);
        txtResetPassword.setTypeface(typefaceRegular);
        txtLogout.setTypeface(typefaceRegular);
        txtEmpty.setTypeface(typefaceRegular);
        btnLogin.setTypeface(typefaceBold);
        txtSignup.setTypeface(typefaceRegular);

        if(newsLetter==0)
        {
            switchNewsLetter.setChecked(false);
        }
        if(newsLetter==1)
        {
            switchNewsLetter.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("path","normal");
                startActivity(intent);
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SignupActivity.class);
                intent.putExtra("path","normal");
                startActivity(intent);
            }
        });

        switchNewsLetter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    newsletterPresenter.updateNewsletter(sharedPreferences.getString(AlaBricks.sharedUserId,""),"1");
                    Toast.makeText(getContext(), "TRUE", Toast.LENGTH_SHORT).show();
                    editor.putInt(AlaBricks.sharedNews,1);
                    editor.commit();
                }
                else
                {

                    newsletterPresenter.updateNewsletter(sharedPreferences.getString(AlaBricks.sharedUserId,""),"0");
                    Toast.makeText(getContext(), "FALSE", Toast.LENGTH_SHORT).show();
                    editor.putInt(AlaBricks.sharedNews,0);
                    editor.commit();
                }
            }
        });

        linNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationListActivity.class);
                startActivity(intent);

            }
        });

        linEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        linContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
                startActivity(intent);
            }
        });

        linResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePassword.class);
                startActivity(intent);

            }
        });

        if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
        {
            linMain.setVisibility(View.GONE);
            linEmpty.setVisibility(View.VISIBLE);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

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

                Intent intent =new Intent(getContext(), MainActivity.class);
                intent.putExtra("path","normal");
                startActivity(intent);
                getActivity().finishAffinity();

            }
        });

        return view;
    }

    @Override
    public void onSuccessNewsletter() {
    }

    @Override
    public void onFailedNewsletter() {

    }
}
