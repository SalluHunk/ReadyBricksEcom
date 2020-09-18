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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ananta.myapplication.R;
import com.ananta.myapplication.general.MainActivity;
import com.ananta.myapplication.general.SignupActivity;
import com.google.android.material.tabs.TabLayout;

import util.AlaBricks;

public class OrderFragment extends Fragment {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout linEmpty;
    SharedPreferences sharedPreferences;
    private Button btnLogin;
    private LinearLayout linMain;
    Typeface typefaceRegular;
    private Typeface typefaceBold;
    private TextView txtEmpty;
    private TextView txtSignup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order,container,false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        linEmpty = (LinearLayout)view.findViewById(R.id.linEmpty);
        btnLogin = (Button)view.findViewById(R.id.btnLogin);
        linMain = (LinearLayout)view.findViewById(R.id.linMain);
        txtEmpty = (TextView)view.findViewById(R.id.txtEmpty);
        txtSignup = (TextView)view.findViewById(R.id.txtSignup);

        typefaceBold = Typeface.createFromAsset(getContext().getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(getContext().getAssets(),"regularfont.otf");

        btnLogin.setTypeface(typefaceBold);
        txtEmpty.setTypeface(typefaceRegular);
        txtSignup.setTypeface(typefaceRegular);

        sharedPreferences = getActivity().getSharedPreferences(AlaBricks.sharedName, Context.MODE_PRIVATE);

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

        if(sharedPreferences.getString(AlaBricks.sharedUserId,"").equals(""))
        {
            linEmpty.setVisibility(View.VISIBLE);
            linMain.setVisibility(View.GONE);
        }
        else
        {
            linEmpty.setVisibility(View.GONE);
            linMain.setVisibility(View.VISIBLE);
            adapter = new TabAdapter(getFragmentManager());
            adapter.addFragment(new CurrentOrderFragment(), "CURRENT");
            adapter.addFragment(new PastOrderFragment(), "PAST");
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }


        return view;
    }
}
