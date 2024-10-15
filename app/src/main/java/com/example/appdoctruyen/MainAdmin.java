package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdoctruyen.adapter.AdapterTruyen;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    AdapterTruyen adapterTruyen;

    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonThemtruyen);

        initList();
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id",0);
                Intent intent = new Intent(MainAdmin.this,MainDangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DialogDelete(position);
            }
        });
    }

    private void DialogDelete(int position){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete);
        dialog.setCanceledOnTouchOutside(false);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idtruyen = TruyenArrayList.get(position).getID();
                databasedoctruyen.Delete(idtruyen);

                Intent intent = new Intent(MainAdmin.this,MainAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(MainAdmin.this, "Xóa truyện thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void initList() {
        TruyenArrayList = new ArrayList<>();
        databasedoctruyen = new databasedoctruyen(this);
        Cursor cursorl = databasedoctruyen.getData2();

        while (cursorl.moveToNext()){
            int id = cursorl.getInt(0);
            String tentruyen = cursorl.getString(1);
            String  noidung = cursorl.getString(2);
            String anh = cursorl.getString(3);
            int id_tk = cursorl.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            adapterTruyen = new AdapterTruyen(getApplicationContext(),TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }
        cursorl.moveToFirst();
        cursorl.close();
    }
}