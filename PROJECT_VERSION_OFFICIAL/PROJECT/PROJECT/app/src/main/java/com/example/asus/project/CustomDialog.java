package com.example.asus.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ASUS on 27-Nov-17.
 */

public class CustomDialog extends Dialog {
    String arr[] = {"Mua sắm", "Giải trí", "Điện nước"};
    String str=null;
    DataBase_Handler dataBase_handler;
    public Activity c;
    public Spinner spinner;
    public Dialog d;
    public Calendar cal;
    public Button calendar,delete,update;
    Date date;
    public TextView tv,txtcalendar;
    public Button cancel;
    EditText money;

    public CustomDialog(Activity a,String s) {
        super(a);
        this.c=a;
        str=s;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.customdialog);


        tv = (TextView) findViewById(R.id.tvcustom);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner=(Spinner)findViewById(R.id.spinnercustom);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv.setText(arr[i]);
            }

            //@Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv.setText("");
            }
        });



        txtcalendar = (TextView) findViewById(R.id.txtcalendarcustom);
        calendar = (Button) findViewById(R.id.btncalendarcustom);

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
                        getContext(),
                        callback, nam, thang, ngay);
                pic.setTitle("Chọn ngày hoàn thành");
                pic.show();
            }
        });
        dataBase_handler=new DataBase_Handler(getContext());
        delete=(Button)findViewById(R.id.btndeletecustom);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase_handler.delete(str);
                show("Xóa thành công");


            }
        });
        money=(EditText)findViewById(R.id.edtmoneycustom);
        update=(Button)findViewById(R.id.btnupdatecustom);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (money.getText().toString().trim().length() == 0 )
                {
                    show("Nhập số tiền cập nhật");
                }
                else {
                    String s1 = tv.getText().toString();
                    Long s2 = Long.parseLong(money.getText().toString());
                    String s3 = txtcalendar.getText().toString();
                    dataBase_handler.update(str, s1, s2, s3);
                    show("Cập nhật thành công");

                }
            }
        });

        cancel=(Button)findViewById(R.id.btncancelcustom);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });

    }
    public void show(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.show();
    }

}

