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
import com.ananta.myapplication.adapter.TransporterOrderListAdapter;

import java.util.ArrayList;

import impl.TransporterOrderListPresenterImpl;
import network.OrderData;
import presenter.TransporterOrderListPresenter;
import util.AlaBricks;
import view.TransporterOrderListView;

public class TransPastOrderFragment extends Fragment  implements TransporterOrderListView {

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
        transporterOrderListPresenter.listTransporterOrder(sharedPreferences.getString(AlaBricks.sharedUserId,""),"4");
        super.onResume();
    }

    @Override
    public void onSuccessTransporterOrderList(ArrayList<OrderData> orderDataArrayList) {
        lstOrder.setAdapter(new TransporterOrderListAdapter(getContext(),orderDataArrayList));
    }

    @Override
    public void onFailedTransporterOrderList() {

    }
}

