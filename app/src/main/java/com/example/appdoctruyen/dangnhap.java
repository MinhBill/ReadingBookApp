package com.example.appdoctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen.database.databasedoctruyen;

public class dangnhap extends AppCompatActivity {
    EditText editTaiKhoan, editMatKhau;
    Button button_login,button_Sign;
    com.example.appdoctruyen.database.databasedoctruyen databasedoctruyen;

   private Button button_Login;
   private Button button_SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        button_Login = (Button) findViewById(R.id.button_login);

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tentaikhoan = editTaiKhoan.getText().toString();
                String matkhau  = editMatKhau.getText().toString();

                Cursor cursor = databasedoctruyen.getData();
                while (cursor.moveToNext()){

                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);
                    if(datatentaikhoan.equals(tentaikhoan)&& datamatkhau.equals(matkhau)){
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);
                        Intent intent = new Intent(dangnhap.this, MainActivity.class);
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);
                        startActivity(intent);


                    }
                }
                 cursor.moveToFirst();
                cursor.close();

            }
        });
        button_SignIn = (Button) findViewById(R.id.button_Sign);

        button_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangnhap.this, danngky.class);
                startActivity(intent);
            }
        });
        AnhXa();
        databasedoctruyen = new databasedoctruyen(this);
}

    private void AnhXa() {
        editMatKhau = findViewById(R.id.editMatKhau);
        editTaiKhoan = findViewById(R.id.editTaiKhoan);
        button_Sign = findViewById(R.id.button_Sign);
        button_login = findViewById(R.id.button_login);
    }
}
