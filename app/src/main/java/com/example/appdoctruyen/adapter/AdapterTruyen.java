package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTruyen extends BaseAdapter {

    private  Context context;
    private  ArrayList<Truyen> listTruyen;

    public AdapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void fliterList(ArrayList<Truyen> fliteredList) {

        listTruyen = fliteredList;
        notifyDataSetChanged();
    }

    public class ViewHoler{
        TextView txtTenTruyen;
        ImageView imgtruyen;
    }


    @Override
    public View getView(int position, View converView, ViewGroup parent) {

          ViewHoler viewHoler = null;
          viewHoler = new ViewHoler();

          LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         converView = inflater.inflate(R.layout.newtruyen,null);
         viewHoler.txtTenTruyen = converView.findViewById(R.id.TextViewTenTruyenNew);
         viewHoler.imgtruyen = converView.findViewById(R.id.imgnewtruyen);
         converView.setTag(viewHoler);

         Truyen truyen = (Truyen) getItem(position);
         viewHoler.txtTenTruyen.setText(truyen.getTenTruyen());

         Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.baseline_load).error(R.drawable.baseline_image_24).into(viewHoler.imgtruyen);


        return converView;
    }


}
