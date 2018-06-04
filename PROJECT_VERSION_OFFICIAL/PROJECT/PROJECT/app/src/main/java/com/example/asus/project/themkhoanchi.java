package com.example.asus.project;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class themkhoanchi extends AppCompatActivity {
    String arr[] = {"Mua sắm", "Giải trí", "Điện nước"};
    TextView tv, txtcalendar;
    Spinner spinner;
    Calendar cal;
    EditText money;
    Date date;
    Button calendar;
    Button add;
    Button show;
    SQLiteDatabase db;


    DataBase_Handler dataBase_handler;
    public static final String TITLE = "";
    public static final String DESCRIPTION = "description";
    public static final String BUNDLE = "bundel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themkhoanchi);
        dataBase_handler=new DataBase_Handler(this);

        tv = (TextView) findViewById(R.id.tv);
        spinner = (Spinner) findViewById(R.id.spinner);
        txtcalendar = (TextView) findViewById(R.id.txtcalendar);
        calendar = (Button) findViewById(R.id.btncalendar);
        add = (Button) findViewById(R.id.btnaddchi);
        show = (Button) findViewById(R.id.btnshowchi);
        money = (EditText) findViewById(R.id.edtmoney);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv.setText(arr[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv.setText("");
            }
        });

        cal = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String sdate = simpleDateFormat.format(cal.getTime());
        txtcalendar.setText(sdate);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtcalendar.setText(day + "/" + (month + 1) + "/" + year);
                        cal.set(year, month, day);
                        date = cal.getTime();
                    }
                };
                String s = txtcalendar.getText() + "";
                String strArrtmp[] = s.split("/");
                int ngay = Integer.parseInt(strArrtmp[0]);
                int thang = Integer.parseInt(strArrtmp[1]) - 1;
                int nam = Integer.parseInt(strArrtmp[2]);
                //Hiển thị ra Dialog
                DatePickerDialog pic = new DatePickerDialog(
                        themkhoanchi.this,
                        callback, nam, thang, ngay);
                pic.setTitle("Chọn ngày hoàn thành");
                pic.show();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                txtshow.setMovementMethod(new ScrollingMovementMethod());

                Intent i=new Intent(themkhoanchi.this,DS_khoanchi.class);
                startActivity(i);
                byBundle();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (money.getText().toString().trim().length() == 0)
                    show("Nhập đầy đủ số tiền");
                else {
                    dataBase_handler.add(tv.getText().toString(), Long.parseLong(money.getText().toString()), txtcalendar.getText().toString());
                    show("Thêm thành công");
                    cleartext();
                }
            }
        });
    }
    public void show(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.show();
    }
    public void cleartext() {
        money.setText("");
    }
    public void byBundle()
    {
        Intent intent=new Intent(themkhoanchi.this,DS_khoanchi.class);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList(TITLE,dataBase_handler.showall());
        intent.putExtra(BUNDLE,bundle);
        startActivity(intent);

    }


}





