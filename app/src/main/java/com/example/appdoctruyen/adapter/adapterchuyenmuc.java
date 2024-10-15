package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterchuyenmuc  extends BaseAdapter {

    private Context context;
    private  int layout;
    private List<chuyenmuc> chuyenmucList;

    public adapterchuyenmuc(Context context, int layout, List<chuyenmuc> chuyenmucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucList = chuyenmucList;
    }

    @Override
    public int getCount() {
        return chuyenmucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        converView = inflater.inflate(layout,null);

        ImageView img = (ImageView) converView.findViewById(R.id.imgchuyenmuc);

        TextView txt = (TextView) converView.findViewById(R.id.TextViewTenchuyenmuc);

        chuyenmuc cm = chuyenmucList.get(position);

        txt.setText(cm.getTenchuyenmuc());

        Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.baseline_load).error(R.drawable.baseline_image_24).into(img);

        return converView;
    }
}
