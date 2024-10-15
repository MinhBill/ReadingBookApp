package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import com.example.appdoctruyen.adapter.AdapterTruyen;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class MainTimKiem extends AppCompatActivity {

    ListView listView;
    EditText edt;
    ArrayList<Truyen> TruyenArraylist;
    ArrayList<Truyen>  arrayList;
    AdapterTruyen adapterTruyen;


          databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);

        listView = findViewById(R.id.listviewTimKiem);

        edt = findViewById(R.id.timkiem);
        
        initList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainTimKiem.this,MainNoiDung.class);

                String tent = arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoidung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);


            }
        });

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                fliter(s.toString());
            }
        });
    }

    //search
    private  void fliter(String text){
        arrayList.clear();

        ArrayList<Truyen> fliteredList = new ArrayList<>();

        for( Truyen item: TruyenArraylist){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                fliteredList.add(item);
                arrayList.add(item);

            }

        }
        adapterTruyen.fliterList(fliteredList);
    }

    private void initList() {

        TruyenArraylist = new ArrayList<>();

       arrayList = new ArrayList<>();

        databasedoctruyen = new databasedoctruyen(this);

        Cursor cursor = databasedoctruyen.getData2();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);
            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            arrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new AdapterTruyen(getApplicationContext(),TruyenArraylist);

            listView.setAdapter(adapterTruyen);

        }
        cursor.moveToFirst();
        cursor.close();
    }
}