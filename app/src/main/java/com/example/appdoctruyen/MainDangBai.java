package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

public class MainDangBai extends AppCompatActivity {
    EditText edtTentruyen,edtNoiDung,edtAnh;

    Button ButtonTai;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);

        edtAnh = findViewById(R.id.dbAnh);
        edtTentruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbNoidung);
        ButtonTai = findViewById(R.id.buttonTai);

        databasedoctruyen = new databasedoctruyen(this);

        ButtonTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentruyen = edtTentruyen.getText().toString();
                String noidung =  edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreateTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(MainDangBai.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    Log.e("ERR :","Nhập đầy đủ thông tin");
                }
                //them truyen vao csdl
                else{
                    databasedoctruyen.AddTruyen(truyen);
                    //chuyen ve trang admin va cap nhat lai du lieu
                    Intent intent = new Intent(MainDangBai.this,MainAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
            //tao truyen

            private Truyen CreateTruyen() {
                String tentruyen = edtTentruyen.getText().toString();
                String noidung  = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Intent intent = getIntent();

                int id = intent.getIntExtra("Id",0);
                Truyen truyen = new Truyen(tentruyen,noidung,img,id);
                return  truyen;
            }
        });
    }
}