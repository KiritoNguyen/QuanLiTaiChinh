package com.example.asus.project;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class DS_khoanno extends Fragment implements OnFragmentInteractionListener {
    ListView list;

    String arr[] = {"Mua sắm", "Giải trí", "Điện nước"};

    Spinner spinner;
    Calendar cal,cangtra;
    EditText money,laysuat;
    TextView txtcalendar,txtcalendarngtra;
    Button calendar,refresh;



    ArrayList<String> listData;
    ArrayAdapter<String> adapter;

    DataBase_Handler dataBase_handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ds_khoanno,container,false);
        dataBase_handler=new DataBase_Handler(getContext());

        money = (EditText) view.findViewById(R.id.edtmoneycustom_no);
        laysuat=(EditText) view.findViewById(R.id.edtlaysuatcustom_no);
        list = (ListView) view.findViewById(R.id.listkhoanno);

        txtcalendar = (TextView) view.findViewById(R.id.txtcalendarcustom_no);
        txtcalendarngtra=(TextView) view.findViewById(R.id.txtcalendarcustomngtra_no);
        refresh=(Button) view.findViewById(R.id.btnrefreshno);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Refreshno();
            }
        });
        Refreshno();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String str = listData.get(i).substring(12,15);
                String str1 = listData.get(i);
                Toast.makeText(getContext(), str1, Toast.LENGTH_LONG).show();
                //Toast.makeText(DS_khoanno.this, str, Toast.LENGTH_LONG).show();
                //  dialog();

                CustomDialog_No ct=new CustomDialog_No(getActivity(),str);
                ct.show();
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
    public void dialog() {

        Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.activity_custom_dialog__no);

        dialog.setTitle("Thông báo");


        dialog.show();


    }
    public void Refreshno()
    {
        listData=dataBase_handler.showallno();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listData);
        list.setAdapter(adapter);
    }


    public void dialogdelete()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("NHẬP ID:");

        final EditText input = new EditText(getContext());

        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String a = input.getText().toString();
                dataBase_handler.delete(a);
                show("Xóa thành công");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void onFragmentInteraction(String name) {
        if(name.equals("1")){
            Refreshno();
        }
    }
}

