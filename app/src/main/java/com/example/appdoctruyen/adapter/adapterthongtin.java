package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.TaiKhoan;

import java.util.List;

public class adapterthongtin extends BaseAdapter {

    private Context context;
    private  int layout;

    private List<TaiKhoan> taiKhoanList;

    public adapterthongtin(Context context, int layout, List<TaiKhoan> taiKhoanList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public int getCount() {
        return taiKhoanList.size();
    }

    @Override
    public Object getItem(int position) {
        return taiKhoanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        converView = inflater.inflate(layout,null);

        TextView txtTenTaikhoan = (TextView) converView.findViewById(R.id.TEXT_NAME);
        TextView txtEmail = (TextView) converView.findViewById(R.id.Text_Gmail);

        TaiKhoan taiKhoan = taiKhoanList.get(position);

        txtTenTaikhoan.setText(taiKhoan.getmTenTaiKhoan());
        txtEmail.setText(taiKhoan.getmEmail());


        return converView;
    }
}
