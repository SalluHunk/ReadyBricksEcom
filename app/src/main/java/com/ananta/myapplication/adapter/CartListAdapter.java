package com.ananta.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.SingleProductActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;

import items.SendDataToCartFragment;
import network.CartData;
import network.ProductData;
import util.AlaBricks;
import util.SendDataFromCartListToCartFragment;

public class CartListAdapter extends BaseAdapter {

    ArrayList<CartData> cartDataArrayList  = new ArrayList<CartData>();
    private static LayoutInflater inflater=null;
    Context context;
    private Typeface typefaceRegular,typefaceBold;
    SendDataToCartFragment sendDataToCartFragment;
    ArrayList<String> myItems = new ArrayList<String>();
    SendDataFromCartListToCartFragment sendDataFromCartListToCartFragment;
    public CartListAdapter(Context context,ArrayList<CartData> cartDataArrayList,SendDataToCartFragment sendDataToCartFragment,SendDataFromCartListToCartFragment sendDataFromCartListToCartFragment)
    {
        this.context = context;
        this.cartDataArrayList = cartDataArrayList;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sendDataToCartFragment = sendDataToCartFragment;

        for (int i = 0; i < cartDataArrayList.size(); i++) {
            myItems.add(cartDataArrayList.get(i).getQty());
        }
        this.sendDataFromCartListToCartFragment = sendDataFromCartListToCartFragment;
    }

    @Override
    public int getCount() {
        return cartDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView txtProductName,txtPrice;
        ImageView imgProduct,imgDelete;
        EditText  edQty;
        Button btnUpdate;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_cart, null);

        holder.imgProduct = (ImageView)rowView.findViewById(R.id.imgProduct);
        holder.edQty = (EditText)rowView.findViewById(R.id.edQty);
        holder.btnUpdate = (Button)rowView.findViewById(R.id.btnUpdate);
        holder.txtProductName = (TextView)rowView.findViewById(R.id.txtProductName);
        holder.txtPrice = (TextView)rowView.findViewById(R.id.txtPrice);
        holder.imgDelete = (ImageView)rowView.findViewById(R.id.imgDelete);
        holder.edQty.setId(position);
        holder.edQty.addTextChangedListener(new GenericTextWatcher(holder.edQty));


        typefaceBold = Typeface.createFromAsset(context.getAssets(),"boldfont.otf");
        typefaceRegular = Typeface.createFromAsset(context.getAssets(),"regularfont.otf");

        holder.txtProductName.setText(cartDataArrayList.get(position).getProductName());
        holder.txtPrice.setText("₹ "+cartDataArrayList.get(position).getPrice());

        holder.txtProductName.setTypeface(typefaceRegular);
        holder.txtPrice.setTypeface(typefaceBold);
      //  holder.edQty.setText(cartDataArrayList.get(position).getQty());
        holder.edQty.setText(myItems.get(position));

        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide.with(context)
                .load(AlaBricks.imagePath +cartDataArrayList.get(position).getProdcutImage()).apply(options).into(holder.imgProduct);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataFromCartListToCartFragment.onClickDelete(cartDataArrayList.get(position).getCartId());
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.edQty.getText().toString().trim().length()==0)
                {
                    FBToast.errorToast(context,"Cart Value can not be blank",FBToast.LENGTH_LONG);
                }
                else
                {
                    sendDataToCartFragment.onClick(cartDataArrayList.get(position).getCartId(),holder.edQty.getText().toString(),position);
                    holder.edQty.setText(holder.edQty.getText().toString());
                }
            }
        });

        return rowView;
    }

    private class GenericTextWatcher implements TextWatcher {

        private View view;
        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        public void afterTextChanged(Editable editable) {
            final int position = view.getId();
            final EditText editText = (EditText) view;
            myItems.set(position, editText.getText().toString());
        }
    }

}
