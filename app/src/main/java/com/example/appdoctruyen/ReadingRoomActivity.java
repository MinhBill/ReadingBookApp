package com.example.appdoctruyen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ReadingRoomActivity extends AppCompatActivity {

    EditText editTextHoten, editTextTime, editTextDate;
    Button buttonXacnhan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readingroom);

        editTextHoten = findViewById(R.id.editTextHoten);
        editTextTime = findViewById(R.id.editTextTime);
        editTextDate = findViewById(R.id.editTextDate);
        buttonXacnhan1 = findViewById(R.id.buttonXacnhan1);

        buttonXacnhan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi bấm nút "Xác nhận"
                // Quay lại trang chủ
                finish();
            }
        });
    }
}
