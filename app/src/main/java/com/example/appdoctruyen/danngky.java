package com.example.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.TaiKhoan;

public class danngky extends AppCompatActivity {

    Button button_DK,button_DN;
    EditText editTextName,editTextEmail,editTextMK;

    com.example.appdoctruyen.database.databasedoctruyen databasedoctruyen;
    private Button button_Sign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);


        databasedoctruyen = new databasedoctruyen(this);

        Anhxa();
           button_DK.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String taikhoan = editTextName.getText().toString();
        String matkhau = editTextMK.getText().toString();
        String Email = editTextEmail.getText().toString();
        TaiKhoan taiKhoan1= CreateTK();
        if(taikhoan.equals("") || Email.equals("")|| matkhau.equals("")){
            Log.e("Thông báo:","Chưa nhập đầy đủ  thông tin");
        }
        else {
            databasedoctruyen.AddTk(taiKhoan1);
            Toast.makeText(danngky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        }
    }
      });
        button_DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private TaiKhoan CreateTK(){
        String taikhoan = editTextName.getText().toString();
        String matkhau = editTextMK.getText().toString();
        String Email = editTextEmail.getText().toString();

        int phanquyen=1;
        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,Email,phanquyen);
        return tk;
    }
    private void Anhxa() {
           editTextName =  findViewById(R.id.editTextName);
        editTextMK =  findViewById(R.id.editTextMK);
        editTextEmail =  findViewById(R.id.editTextEmail);
        button_DK = findViewById(R.id.button_DK);
        button_DN = findViewById(R.id.button_DN);

    }
}
