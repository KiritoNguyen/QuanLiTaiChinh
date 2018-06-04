package com.example.asus.project;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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

public class DS_khoanthu extends AppCompatActivity {
    ListView list;

    String arr[] = {"Mua sắm", "Giải trí", "Điện nước"};

    Spinner spinner;
    Calendar cal;
    EditText money;
    TextView txtcalendar;
    Button calendar,refresh;



    ArrayList<String> listData;
    ArrayAdapter<String> adapter;

    DataBase_Handler dataBase_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_khoanthu);

        dataBase_handler=new DataBase_Handler(DS_khoanthu.this);

        money = (EditText) findViewById(R.id.edtmoneycustom_thu);
        list = (ListView) findViewById(R.id.listkhoanthu);

        txtcalendar = (TextView) findViewById(R.id.txtcalendarcustom_thu);
        refresh=(Button)findViewById(R.id.btnrefreshthu);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Refreshthu();
            }
        });

        receiveintent();
    }
    public void receiveintent()
    {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(themkhoanthu.BUNDLE);
            if (bundle != null) {
                listData = new ArrayList<>();
                listData = bundle.getStringArrayList(themkhoanthu.TITLE);
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        String str = listData.get(i).substring(13,16);
//                        Toast.makeText(DS_khoanthu.this, str, Toast.LENGTH_LONG).show();
                        //  dialog();

                        CustomDialog_Thu ct=new CustomDialog_Thu(DS_khoanthu.this,str);
                        ct.show();
                    }
                });
//                list.setAdapter(new CustomAdapter(this,adapter));
            }
            else
            {
                Intent i=new Intent(DS_khoanthu.this,themkhoanthu.class);
                startActivity(i);
            }
        }

    }
    public void show(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.show();
    }
    public void dialog() {

        Dialog dialog = new Dialog(DS_khoanthu.this);

        dialog.setContentView(R.layout.activity_custom_dialog__thu);

        dialog.setTitle("Thông báo");


        dialog.show();


    }
    public void Refreshthu()
    {
        listData=dataBase_handler.showallthu();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        list.setAdapter(adapter);
    }


    public void dialogdelete()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("NHẬP ID:");

        final EditText input = new EditText(this);

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

}

