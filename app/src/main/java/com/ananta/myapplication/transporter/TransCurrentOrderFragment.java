package com.ananta.myapplication.transporter;

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
import com.ananta.myapplication.adapter.TransporterOrderListAdapter;

import java.util.ArrayList;

import impl.ManuOrderListPresenterImpl;
import impl.TransporterOrderListPresenterImpl;
import network.OrderData;
import presenter.ManuOrderListPresenter;
import presenter.TransporterOrderListPresenter;
import util.AlaBricks;
import view.ManuOrderListView;
import view.TransporterOrderListView;

public class TransCurrentOrderFragment extends Fragment implements TransporterOrderListView {

    TransporterOrderListPresenter transporterOrderListPresenter;
    SharedPreferences sharedPreferences;
    private ListView lstOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_manu_current_order,container,false);
        lstOrder = (ListView)view.findViewById(R.id.lstOrder);

        sharedPreferences = getContext().getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);
        transporterOrderListPresenter = new TransporterOrderListPresenterImpl(this);

        return view;
    }

    @Override
    public void onResume() {
        transporterOrderListPresenter.listTransporterOrder(sharedPreferences.getString(AlaBricks.sharedUserId,""),"0");
        super.onResume();
    }

    @Override
    public void onSuccessTransporterOrderList(ArrayList<OrderData> orderDataArrayList) {
        if(orderDataArrayList.size()>0)
        {
            lstOrder.setAdapter(new TransporterOrderListAdapter(getContext(),orderDataArrayList));
        }
    }

    @Override
    public void onFailedTransporterOrderList() {

    }
}
