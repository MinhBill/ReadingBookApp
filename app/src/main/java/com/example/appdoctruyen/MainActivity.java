package com.example.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.appdoctruyen.adapter.AdapterTruyen;
import com.example.appdoctruyen.adapter.adapterchuyenmuc;
import com.example.appdoctruyen.adapter.adapterthongtin;
import com.example.appdoctruyen.database.databasedoctruyen;

import com.example.appdoctruyen.model.TaiKhoan;
import com.example.appdoctruyen.model.Truyen;
import com.example.appdoctruyen.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


Toolbar toolbar;
ViewFlipper viewFlipper;
DrawerLayout drawerLayout;
NavigationView navigationView;
ListView listView,listviewThongTin,listViewNew;

String email;
String tentaikoan;

    ArrayList<Truyen> TruyenArraylist;

    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;

 adapterchuyenmuc adapterchuyenmuc;
 adapterthongtin adapterthongtin;
    AdapterTruyen adapterTruyen;
             databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        AnhXa();
        Actionbar();
        ActionViewFiliper();

         Intent intentpq = getIntent();
         int i = intentpq.getIntExtra("phanq",0);
         int idd =  intentpq.getIntExtra("idd",0);

         email = intentpq.getStringExtra("email");
         tentaikoan = intentpq.getStringExtra("tentaikhoan");


        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this,MainNoiDung.class);
            String tent = TruyenArraylist.get(position).getTenTruyen();
            String noidungt = TruyenArraylist.get(position).getNoidung();
            intent.putExtra("tentruyen",tent);
            intent.putExtra("noidung",noidungt);
            startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    if( i == 2){
                     Intent intent = new Intent(MainActivity.this,MainAdmin.class);
                     intent.putExtra("Id",idd);
                     startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài :","bạn không có quyền");
                    }
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this,MainThongTIn.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, KeToanActivity.class);
                    startActivity(intent);
                }
                else if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, ReadingRoomActivity.class);
                    startActivity(intent);
                }
                else if (position == 4) {
                    Intent intent = new Intent(MainActivity.this, TheThuVienActivity.class);
                    startActivity(intent);
                }
                else if (position == 5) {
                    // Hoàn thành tất cả các activity để đăng xuất
                    Intent intent = new Intent(MainActivity.this, dangnhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }



    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void ActionViewFiliper() {
     ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://novaedu.vn/uploads/news/2020_09/dacnhantam.png");
        mangquangcao.add("https://nld.mediacdn.vn/291774122806476800/2022/8/5/20220727214610-165969468447547864538.jpg");
        mangquangcao.add("https://blog.dktcdn.net/articles/ban-sach-online.jpg");
        mangquangcao.add("https://i0.wp.com/thepresentwriter.com/wp-content/uploads/2018/01/Blog-Feature-_thepresentwriter.jpeg?fit=2626%2C1751&ssl=1");

        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView =  new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(4000);
            viewFlipper.setAutoStart(true);

            Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
            Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

            viewFlipper.setInAnimation(animation_slide_in);
            viewFlipper.setInAnimation(animation_slide_out);
        }
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listViewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listviewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist =  new ArrayList<>();

        Cursor cursor1 = databasedoctruyen.getData1();
        while (cursor1.moveToNext()){
           int id = cursor1.getInt(0);
           String tentruyen = cursor1.getString(1);
           String noidung = cursor1.getString(2);
           String anh = cursor1.getString(3);
           int id_tk = cursor1.getInt(4);

           TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

           adapterTruyen = new AdapterTruyen(getApplicationContext(),TruyenArraylist);
           listViewNew.setAdapter(adapterTruyen);

        }
        cursor1.moveToFirst();
        cursor1.close();

        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikoan,email));


         adapterthongtin = new adapterthongtin(this,R.layout.navigation_thongtin,taiKhoanArrayList);
         listviewThongTin.setAdapter(adapterthongtin);

         chuyenmucArrayList = new ArrayList<>();

         chuyenmucArrayList.add(new chuyenmuc("Đăng bài",R.drawable.baseline_post_add_24));
         chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.baseline_person_4_24));
         chuyenmucArrayList.add(new chuyenmuc("Kế toán", R.drawable.baseline_person_4_24));
         chuyenmucArrayList.add(new chuyenmuc("Đặt phòng đọc sách", R.drawable.baseline_person_4_24));
        chuyenmucArrayList.add(new chuyenmuc("Tạo thẻ thư viện", R.drawable.baseline_person_4_24));

        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.baseline_login_24));


         adapterchuyenmuc = new adapterchuyenmuc(this,R.layout.chuyenmuc,chuyenmucArrayList);

         listView.setAdapter(adapterchuyenmuc);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(intent);
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}