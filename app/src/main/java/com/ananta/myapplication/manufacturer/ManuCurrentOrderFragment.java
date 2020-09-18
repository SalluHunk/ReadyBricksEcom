package com.ananta.myapplication.manufacturer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ananta.myapplication.R;
import com.ananta.myapplication.adapter.ManufacturerOrderListAdapter;
import com.ananta.myapplication.adapter.OrderListAdapter;

import java.util.ArrayList;

import impl.ManuOrderListPresenterImpl;
import impl.OrderListPresenterImpl;
import network.OrderData;
import presenter.ManuOrderListPresenter;
import presenter.OrderListPresenter;
import util.AlaBricks;
import view.ManuOrderListView;
import view.OrderListView;

public class ManuCurrentOrderFragment extends Fragment implements ManuOrderListView {

    ManuOrderListPresenter orderListPresenter;
    SharedPreferences sharedPreferences;
    private ListView lstOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_manu_current_order,container,false);
        lstOrder = (ListView)view.findViewById(R.id.lstOrder);

        sharedPreferences = getContext().getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);
        orderListPresenter = new ManuOrderListPresenterImpl(this);
        orderListPresenter.orderListManufacturer(sharedPreferences.getString(AlaBricks.sharedUserId,""),"0");

        return view;
    }

    @Override
    public void onSuccessOrderList(ArrayList<OrderData> orderDataArrayList) {
        lstOrder.setAdapter(new ManufacturerOrderListAdapter(getContext(),orderDataArrayList));
    }

    @Override
    public void onFailedSuccessOrderList() {

    }
}
