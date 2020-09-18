package com.ananta.myapplication.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.CartListAdapter;
import com.ananta.myapplication.general.MainActivity;
import com.ananta.myapplication.general.SignupActivity;
import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;

import impl.CartPresenterImpl;
import impl.DeleteCartPresenterImpl;
import impl.UpdateCartPresenterImpl;
import items.SendDataToCartFragment;
import network.CartData;
import okhttp3.internal.Internal;
import presenter.CartPresenter;
import presenter.DeleteCartPresenter;
import presenter.UpdateCartPresenter;
import util.AlaBricks;
import util.SendDataFromCartListToCartFragment;
import view.CartView;
import view.DeleteCartItemView;
import view.UpdateCartView;

public class CartFragment extends Fragment implements CartView, SendDataToCartFragment, UpdateCartView, SendDataFromCartListToCartFragment, DeleteCartItemView {

    CartPresenter cartPresenter;
    SharedPreferences sharedPreferences;
    private TextView txtPrice,txtPriceText;
    float cartTotal = 0;
    private Button btnAddProductToCart;
    private ListView lstCart;
    ProgressDialog progressDialog;
    UpdateCartPresenter updateCartPresenter;
    ArrayList<String> arrayListQty = new ArrayList<>();
    ArrayList<String> arraylistPrice = new ArrayList<>();
    private LinearLayout linBottom;
    private LinearLayout linMain,linEmpty;
    TextView txtEmpty;
    private Typeface typefaceRegular,typefaceBold;
    DeleteCartPresenter deleteCartPresenter;
    private ImageView imgEmpty;
    private Button btnLogin;
    private TextView txtSignup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);

        deleteCartPresenter = new DeleteCartPresenterImpl(this);
        updateCartPresenter = new UpdateCartPresenterImpl(this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);

        sharedPreferences = getContext().getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);
        txtPriceText = (TextView)view.findViewById(R.id.txtPriceText);
        txtPrice = (TextView)view.findViewById(R.id.txtPrice);
        lstCart = (ListView)view.findViewById(R.id.lstCart);
        btnAddProductToCart = (Button)view.findViewById(R.id.btnAddProductToCart);
        linBottom = (LinearLayout)view.findViewById(R.id.linBottom);
        txtEmpty  =(TextView)view.findViewById(R.id.txtEmpty);
        linEmpty = (LinearLayout)view.findViewById(R.id.linEmpty);
        linMain  = (LinearLayout)view.findViewById(R.id.linMain);
        imgEmpty = (ImageView)view.findViewById(R.id.imgEmpty);
        btnLogin = (Button)view.findViewById(R.id.btnLogin);
        txtSignup = (TextView)view.findViewById(R.id.txtSignup);

        typefaceBold = Typeface.createFromAsset(getContext().getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getContext().getAssets(),"regularfont.otf");

        txtEmpty.setTypeface(typefaceRegular);
        txtPriceText.setTypeface(typefaceRegular);
        txtPrice.setTypeface(typefaceBold);
        btnAddProductToCart.setTypeface(typefaceBold);
        btnLogin.setTypeface(typefaceBold);
        txtSignup.setTypeface(typefaceRegular);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("path","normal");
                startActivity(intent);
            }
        });


        btnAddProductToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arraylistPrice.size()>0)
                {
                    Intent intent = new Intent(getContext(),ProcessOrderFirstActivithy.class);
                    intent.putExtra("cartTotal",txtPrice.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    FBToast.errorToast(getContext(),"No Products in the list",FBToast.LENGTH_LONG);
                }

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

        cartPresenter = new CartPresenterImpl(this);
        if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
        {
            linMain.setVisibility(View.GONE);
            linBottom.setVisibility(View.GONE);
            linEmpty.setVisibility(View.VISIBLE);
            txtEmpty.setText("You must sign-in to access this section.");
            imgEmpty.setImageResource(R.drawable.entrepreneur);
        }
        else
        {
            progressDialog.show();
            cartPresenter.displayCart(sharedPreferences.getString(AlaBricks.sharedUserId,""));
        }

        return view;
    }

    @Override
    public void onSuccessCartDisplay(ArrayList<CartData> cartDataArrayList) {
        progressDialog.dismiss();
        arraylistPrice.clear();
        arrayListQty.clear();
        cartTotal=0;
        lstCart.setAdapter(null);

        linEmpty.setVisibility(View.GONE);
        float present=0;
        for (int i=0;i<cartDataArrayList.size();i++)
        {
            arraylistPrice.add(cartDataArrayList.get(i).getPrice());
            arrayListQty.add(cartDataArrayList.get(i).getQty());
            present = Float.parseFloat(cartDataArrayList.get(i).getQty())  * Float.parseFloat(cartDataArrayList.get(i).getPrice());
            cartTotal = cartTotal + present;
            present = 0;
        }
        if(cartDataArrayList.size()>0)
        {
            CartListAdapter cartListAdapter = new CartListAdapter(getContext(),cartDataArrayList,this,this);
            lstCart.setAdapter(cartListAdapter);
        }

        txtPrice.setText("₹ "+cartTotal);
    }

    @Override
    public void onFailedCartDisplay() {
        progressDialog.dismiss();
        linMain.setVisibility(View.GONE);
        linBottom.setVisibility(View.GONE);
        linEmpty.setVisibility(View.VISIBLE);
        txtEmpty.setText("Your Cart is Empty.");
        imgEmpty.setImageResource(R.drawable.market);
        btnLogin.setVisibility(View.GONE);
    }

    @Override
    public void onClick(String cartId, String qty,int pos) {
        progressDialog.show();
        updateCartPresenter.onUpdateCart(cartId,qty);
        cartTotal=0;
        float present=0;
        arrayListQty.set(pos,qty);
        for (int i=0;i<arrayListQty.size();i++)
        {

            present = Float.parseFloat(arrayListQty.get(i))  * Float.parseFloat(arraylistPrice.get(i));
            cartTotal = cartTotal + present;
            present = 0;
        }
        txtPrice.setText("₹ "+cartTotal);
    }

    @Override
    public void onSuccessUpdateCart() {
        progressDialog.dismiss();
        FBToast.successToast(getContext(),"Cart has been updated",FBToast.LENGTH_LONG);
    }

    @Override
    public void onFailedUpdateCart() {
        progressDialog.dismiss();
        FBToast.errorToast(getContext(),"Cart upgradation failed",FBToast.LENGTH_LONG);
    }

    @Override
    public void onClickDelete(String cartId) {
        progressDialog.show();
        deleteCartPresenter.onDelete(cartId);
    }

    @Override
    public void onSuccesDeleteCart() {
        progressDialog.dismiss();
        FBToast.successToast(getContext(),"Product Successfully Removed From Cart",FBToast.LENGTH_LONG);
        progressDialog.show();
        cartPresenter.displayCart(sharedPreferences.getString(AlaBricks.sharedUserId,""));
    }

    @Override
    public void onFailedDeleteCart() {

    }
}
