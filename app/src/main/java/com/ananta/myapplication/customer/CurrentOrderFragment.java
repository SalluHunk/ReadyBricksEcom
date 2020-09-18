package com.ananta.myapplication.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.OrderListAdapter;

import java.util.ArrayList;

import impl.OrderListPresenterImpl;
import network.OrderData;
import presenter.OrderListPresenter;
import util.AlaBricks;
import view.OrderListView;

public class CurrentOrderFragment extends Fragment implements OrderListView {

    OrderListPresenter orderListPresenter;
    SharedPreferences sharedPreferences;
    private ListView lstOrder;
    ProgressDialog progressDialog;
    private LinearLayout linNoOrder;
    TextView txtNoOrders;
    private Typeface typefaceBold,typefaceRegular;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_current_order,container,false);
        lstOrder = (ListView)view.findViewById(R.id.lstOrder);


        txtNoOrders = (TextView)view.findViewById(R.id.txtNoOrders);
        linNoOrder  =(LinearLayout)view.findViewById(R.id.linNoOrder);

        typefaceBold = Typeface.createFromAsset(getContext().getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getContext().getAssets(),"regularfont.otf");

        txtNoOrders.setTypeface(typefaceBold);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        sharedPreferences = getContext().getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);
        orderListPresenter = new OrderListPresenterImpl(this);

        return view;
    }

    @Override
    public void onResume() {
        progressDialog.show();
        orderListPresenter.listOrder(sharedPreferences.getString(AlaBricks.sharedUserId,""),"0");
        super.onResume();
    }

    @Override
    public void onSuccessOrderList(ArrayList<OrderData> orderDataArrayList) {

        progressDialog.dismiss();
        lstOrder.setVisibility(View.VISIBLE);
        linNoOrder.setVisibility(View.GONE);
        lstOrder.setAdapter(new OrderListAdapter(getContext(),orderDataArrayList));

    }

    @Override
    public void onFailedOrderList() {
        progressDialog.dismiss();
        lstOrder.setVisibility(View.GONE);
        linNoOrder.setVisibility(View.VISIBLE);

    }
}
