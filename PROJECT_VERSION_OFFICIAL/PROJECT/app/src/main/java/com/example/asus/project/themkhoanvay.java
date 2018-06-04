package com.example.asus.project;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class themkhoanvay extends Fragment {
    private OnFragmentInteractionListener mListener;
    String arr[] = {"Bạn bè", "Người thân", "Cho vay khác"};
    TextView tv, txtcalendar ,txtcalendarngtra;
    Spinner spinner;
    Calendar cal,calngtra;
    EditText money,laisuat,ghichu;
    Date date, datengtra;
    Button calendar,calendarngtra;
    Button add;
    Button show;
    SQLiteDatabase db;


    DataBase_Handler dataBase_handler;
    public static final String TITLE = "";
    public static final String DESCRIPTION = "description";
    public static final String BUNDLE = "bundel";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_themkhoanvay, container, false);
        dataBase_handler=new DataBase_Handler(getContext());

        tv = (TextView) view.findViewById(R.id.tv);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        txtcalendar = (TextView) view.findViewById(R.id.txtcalendar);
        txtcalendarngtra=(TextView) view.findViewById(R.id.txtcalendarngtra);
        calendar = (Button) view.findViewById(R.id.btncalendar);
        calendarngtra=(Button) view.findViewById(R.id.btncalendarngtra);
        add = (Button) view.findViewById(R.id.btnaddvay);
        //show = (Button) view.findViewById(R.id.btnshowvay);
        money = (EditText) view.findViewById(R.id.edtmoney);
        laisuat=(EditText) view.findViewById(R.id.edtlaixuat);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
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
        ghichu=(EditText) view.findViewById(R.id.edt_ghichuvay) ;

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
        String sdatengtra = simpleDateFormatngtra.format(calngtra.getTime());
        txtcalendarngtra.setText(sdatengtra);
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (money.getText().toString().trim().length() == 0||laisuat.getText().toString().trim().length() == 0)
                    show("Nhập đầy đủ số tiền và lãi suất");
                else {
                    if(cal.after(calngtra)==true )
                        show("Vui lòng kiểm tra lại ngay nhập.");
                    else{
                        dataBase_handler.add_vay(tv.getText().toString(), Long.parseLong(money.getText().toString()),Long.parseLong(laisuat.getText().toString()), txtcalendar.getText().toString(),txtcalendarngtra.getText().toString(),ghichu.getText().toString());
                        show("Thêm thành công");
                        cleartext();
                        mListener.onFragmentInteraction("1");
                    }
                }
            }
        });
        return view;
    }

    public void show(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.show();
    }
    public void cleartext() {
        money.setText("");
        laisuat.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        } else{
            throw new RuntimeException(context.toString()+ "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}

