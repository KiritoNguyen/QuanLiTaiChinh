package com.example.asus.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.ArrayList;

public class CustomThongKe extends AppCompatActivity {
    Button btntkkhoanthu,btntkkhoanchi,btntkkhoanvay,btntkkhoanno;

    ArrayList<Float> listkhoanthu;
    ArrayList<Float> listkhoanchi;
    ArrayList<Float> listkhoanvay;
    ArrayList<Float> listkhoanno;

    DataBase_Handler dataBase_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_thong_ke);

        dataBase_handler=new DataBase_Handler(CustomThongKe.this);

        btntkkhoanchi=(Button)findViewById(R.id.btntkkhoanchi);
        btntkkhoanthu=(Button)findViewById(R.id.btntkkhoanthu);
        btntkkhoanno=(Button)findViewById(R.id.btntkkhoanno);
        btntkkhoanvay=(Button)findViewById(R.id.btntkkhoanvay);


        btntkkhoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(CustomThongKe.this,BieuDoKhoanThu.class);
                startActivity(i);
            }
        });

        btntkkhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(CustomThongKe.this,BieuDoKhoanChi.class);
                startActivity(i);
            }
        });

        btntkkhoanno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(CustomThongKe.this,BieuDoKhoanNo.class);
                startActivity(i);
            }
        });
        btntkkhoanvay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(CustomThongKe.this,BieuDoKhoanVay.class);
                startActivity(i);
            }
        });
        ;
    }


}
