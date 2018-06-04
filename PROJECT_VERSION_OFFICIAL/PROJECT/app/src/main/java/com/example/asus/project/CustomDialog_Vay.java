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
 * Created by ASUS on 27-vayv-17.
 */

public class CustomDialog_Vay extends Dialog {
    String arr[] = {"Bạn bè", "Người thân", "Cho vay khác"};
    String str=null;
    DataBase_Handler dataBase_handler;
    public Activity c;
    public Spinner spinner;
    public Dialog d;
    public Calendar cal,calngtra;
    public Button calendar,calendarngtra,delete,update;
    Date date,datengtra;
    public TextView tv,txtcalendar,txtcalendarngtra;
    public Button cancel;
    EditText money,laysuat,note;

    public CustomDialog_Vay(Activity a,String s) {
        super(a);
        this.c=a;
        str=s;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_dialog__vay);


        tv = (TextView) findViewById(R.id.tvcustom_vay);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner=(Spinner)findViewById(R.id.spinnercustom_vay);

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



        txtcalendar = (TextView) findViewById(R.id.txtcalendarcustom_vay);
        txtcalendarngtra=(TextView) findViewById(R.id.txtcalendarcustomngtra_vay);
        calendar = (Button) findViewById(R.id.btncalendarcustom_vay);
        calendarngtra=(Button)findViewById(R.id.btncalendarcustomngtra_vay);

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

        calngtra=Calendar.getInstance();

        SimpleDateFormat simpleDateFormatngtra = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String sdatengtra = simpleDateFormatngtra.format(cal.getTime());
        txtcalendarngtra.setText(sdate);
        calendarngtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtcalendarngtra.setText(day + "/" + (month + 1) + "/" + year);
                        calngtra.set(year, month, day);
                        datengtra = calngtra.getTime();
                    }
                };
                String s = txtcalendarngtra.getText() + "";
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
        delete=(Button)findViewById(R.id.btndeletecustom_vay);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dataBase_handler.deleteallvay();
                dataBase_handler.delete_vay(str);
                show("Xóa thành công ");
            }
        });
        money=(EditText)findViewById(R.id.edtmoneycustom_vay);
        laysuat=(EditText) findViewById(R.id.edtlaysuatcustom_vay);
        note=(EditText)findViewById(R.id.edt_ghichuvaycustom);
        update=(Button)findViewById(R.id.btnupdatecustom_vay);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (money.getText().toString().trim().length() == 0 || laysuat.getText().toString().trim().length() == 0)
                {
                    show("Nhập số thông tin cập nhật");
                }
                else {
                    if(cal.after(calngtra)==true)
                        show("Vui lòng kiểm tra lại ngày nhập.");
                    else{
                        String s1 = tv.getText().toString();
                        Long s2 = Long.parseLong(money.getText().toString());
                        Long s4=Long.parseLong(laysuat.getText().toString());
                        String s3 = txtcalendar.getText().toString();
                        String s5=txtcalendarngtra.getText().toString();
                        String s6=note.getText().toString();
                        dataBase_handler.updatevay(str, s1, s2, s3,s4,s5,s6);
                        show("Cập nhật thành công");
                    }
                }

//                if (laysuat.getText().toString().trim().length() == 0 )
//                {
//                    show("Nhập lãy suất cập nhật");
//                }
//                else {
//                    String s1 = tv.getText().toString();
//                    Long s2 = Long.parseLong(money.getText().toString());
//                    Long s4=Long.parseLong(laysuat.getText().toString());
//                    String s3 = txtcalendar.getText().toString();
//                    String s5=txtcalendarngtra.getText().toString();
//                    dataBase_handler.updatevay(str, s1, s2, s3,s4,s5);
//                    show("Cập nhật thành công");
//                }

            }
        });

        cancel=(Button)findViewById(R.id.btncancelcustom_vay);
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

