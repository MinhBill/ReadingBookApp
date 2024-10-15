package com.example.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KeToanActivity extends AppCompatActivity {

    EditText editTextNhapThang, editTextNhapSach, editTextNhapLuotMuon;
    Button buttonXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ketoan); //check ke toan

        editTextNhapThang = findViewById(R.id.editTextNhapThang);
        editTextNhapSach = findViewById(R.id.editTextNhapSach);
        editTextNhapLuotMuon = findViewById(R.id.editTextNhapLuotMuon);
        buttonXacNhan = findViewById(R.id.buttonXacNhan);

        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thang = editTextNhapThang.getText().toString();
                String tenSach = editTextNhapSach.getText().toString();
                String luotMuon = editTextNhapLuotMuon.getText().toString();

                if (thang.isEmpty() || tenSach.isEmpty() || luotMuon.isEmpty()) {
                    Toast.makeText(KeToanActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Thực hiện lưu thông tin hoặc xử lý cần thiết tại đây

                    // Quay lại trang chủ
                    Intent intent = new Intent(KeToanActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
