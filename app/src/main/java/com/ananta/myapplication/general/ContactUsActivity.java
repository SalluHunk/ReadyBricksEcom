package com.ananta.myapplication.general;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ananta.myapplication.R;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tfb.fbtoast.FBToast;

import impl.AddContactPresenterImpl;
import presenter.ContactUsPresenter;
import util.AlaBricks;
import view.ContactUsView;

public class ContactUsActivity extends AppCompatActivity implements ContactUsView {

    private ImageView imgBack;
    private MaterialEditText edTitle;
    private MaterialEditText edDescription;
    private LoadingButton btnAddContact;
    ContactUsPresenter contactUsPresenter;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_contactus);

        sharedPreferences = getSharedPreferences(AlaBricks.sharedName,MODE_PRIVATE);
        progressDialog =new ProgressDialog(ContactUsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);

        btnAddContact = (LoadingButton)findViewById(R.id.btnAddContact);
        edDescription  = (MaterialEditText)findViewById(R.id.edDescription);
        edTitle = (MaterialEditText)findViewById(R.id.edTitle);
        imgBack = (ImageView)findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        contactUsPresenter = new AddContactPresenterImpl(this);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AlaBricks.validateBlankValidation(edTitle))
                {
                    FBToast.errorToast(ContactUsActivity.this,"Please Add Title",FBToast.LENGTH_LONG);
                }
                else
                {
                    if(!AlaBricks.validateBlankValidation(edDescription))
                    {
                        FBToast.errorToast(ContactUsActivity.this,"Please Add Description",FBToast.LENGTH_LONG);
                    }
                    else
                    {
                        progressDialog.show();
                        contactUsPresenter.addContact(sharedPreferences.getString(AlaBricks.sharedUserId,""),AlaBricks.getStringFromEditText(edTitle),AlaBricks.getStringFromEditText(edDescription));
                    }
                }
            }
        });

    }

    @Override
    public void onSuccessContactUs() {
        progressDialog.dismiss();
        FBToast.successToast(ContactUsActivity.this,"Your Query has been sent to the Admin",FBToast.LENGTH_LONG);
        finish();
    }

    @Override
    public void onFailedContactUs() {

    }
}
