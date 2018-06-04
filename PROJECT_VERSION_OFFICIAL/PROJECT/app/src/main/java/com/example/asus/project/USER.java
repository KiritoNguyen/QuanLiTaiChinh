package com.example.asus.project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class USER extends AppCompatActivity {
    Calendar cal;
    TextView txtcalendar;
    Button calendar,save;
    Date date;
    EditText edtname,edtmoney;
    DataBase_Handler dataBase_handler;
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        receiveintent();

        dataBase_handler=new DataBase_Handler(this);

        txtcalendar=(TextView)findViewById(R.id.txt_calendar);
        calendar=(Button)findViewById(R.id.btn_calendar);
        cal = Calendar.getInstance();

        edtname=(EditText)findViewById(R.id.edt_name);
        edtmoney=(EditText)findViewById(R.id.edt_money);
        check=dataBase_handler.checkinfo();
        if(check==false)
        {
            edtmoney.setVisibility(View.INVISIBLE);
        }
        else
        {
            edtmoney.setVisibility(View.VISIBLE);
        }
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
                        USER.this,
                        callback, nam, thang, ngay);
                pic.setTitle("Chọn ngày hoàn thành");
                pic.show();
            }
        });

        save=(Button)findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edtname.getText().toString().trim().length() == 0 || edtmoney.getText().toString().trim().length() == 0)&&check==true)
                    show("Nhập đầy đủ thông tin cá nhân");
                else if(edtname.getText().toString().trim().length() == 0 && check==false)
                {
                    show("Nhập tên cần cập nhật");
                }
                else {
                    if(check==true)
                    {
                        dataBase_handler.add_user(edtname.getText().toString(), txtcalendar.getText().toString(), Long.parseLong(edtmoney.getText().toString()));
                        show("Thêm thành công");
                    }
                    else
                    {
                        dataBase_handler.updateinfo(edtname.getText().toString(), txtcalendar.getText().toString());
                        show("Cập nhật thành công");

                    }

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
    public void receiveintent()
    {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(MainActivity.BUNDLE);
            if (bundle != null) {
                check = bundle.getBoolean(MainActivity.TITLE);

            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(USER.this,MainActivity.class);
        startActivity(i);
    }

}
