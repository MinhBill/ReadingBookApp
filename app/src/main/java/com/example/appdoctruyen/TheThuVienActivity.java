package com.example.appdoctruyen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TheThuVienActivity extends AppCompatActivity {

    EditText editTextHoten1, editTextSdt, editTextNgaysinh, editTextDiachi;
    Button buttonDongy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thethuvien);

        editTextHoten1 = findViewById(R.id.editTextHoten1);
        editTextSdt = findViewById(R.id.editTextSdt);
        editTextNgaysinh = findViewById(R.id.editTextNgaysinh);
        editTextDiachi = findViewById(R.id.editTextDiachi);
        buttonDongy = findViewById(R.id.buttonDongy);

        buttonDongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi bấm nút "Đồng ý"
                // Quay lại trang chủ
                finish();
            }
        });
    }
}
