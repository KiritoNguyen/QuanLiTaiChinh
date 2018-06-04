
package com.example.asus.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button tkc,tkt,tkn,tkv,ttcn,saveimage,thongke;
    ImageView imguser;
    ImageButton btnrefresh;
    public static final String TITLE = "";
    DataBase_Handler dataBase_handler;
    private static final int CAMERA_REQUEST = 1888;

    public static final String BUNDLE = "bundel";
    TextView txtname,txtmoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imguser=(ImageView)findViewById(R.id.img_user);
        imguser.setImageResource(R.drawable.user);
          imguser.setOnClickListener(btnChoosePhotoPressed);


        readdata();

        dataBase_handler=new DataBase_Handler(MainActivity.this);

        ttcn=(Button)findViewById(R.id.btn_info);
        ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String s=dataBase_handler.getnameuser().toString();
                Intent i=new Intent(MainActivity.this,USER.class);
                startActivity(i);
            }
        });

        txtname=(TextView)findViewById(R.id.txt_name);

        String s=dataBase_handler.getnameuser();
        if(s!=null) {
            txtname.setText(s);
        }
        else
        {
            txtname.setText("USER");
        }
        txtmoney=(TextView)findViewById(R.id.txt_money);
        String s1=dataBase_handler.getmoneyuser().toString();
        if(s1!="0L") {
            txtmoney.setText(s1 + " VND");
        }
        else
        {
            txtmoney.setText("MONEY");
        }
        tkc=(Button)findViewById(R.id.btntkc);
        tkc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Khoanchi.class);
                startActivity(i);
            }
        });

        tkt=(Button)findViewById(R.id.btntkt);
        tkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Khoanthu.class);
                startActivity(i);
            }
        });

        tkn=(Button)findViewById(R.id.btntkn);
        tkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Khoanno.class);
                startActivity(i);
            }
        });

        tkv=(Button)findViewById(R.id.btntkv);
        tkv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Khoanvay.class);
                startActivity(i);
            }
        });
        thongke=(Button)findViewById(R.id.btnthongke);
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,CustomThongKe.class);
                startActivity(i);
            }
        });

        saveimage=(Button)findViewById(R.id.btnsaveimage);
        saveimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writedata();
            }
        });
    }
    public void byBundle()
    {
        Intent intent=new Intent(MainActivity.this,USER.class);
        Bundle bundle=new Bundle();
        boolean check=dataBase_handler.checkinfo();
        bundle.putBoolean(TITLE,check);
        intent.putExtra(BUNDLE,bundle);
        startActivity(intent);

    }
    public View.OnClickListener btnChoosePhotoPressed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,123);
        }
    };
    protected void onActivityResult(int requestcode,int resultcode,Intent data) {
        super.onActivityResult(requestcode, resultcode, data);

        if (requestcode == 123 && resultcode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imguser.setImageResource(android.R.color.transparent);
            imguser.setImageBitmap(bitmap);
        }
    }

    public String Saveimage(Bitmap bitmap) throws IOException {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File path = new File(directory,"image.jpg");

        FileOutputStream fon = null;
        try
        {
            fon = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG , 100,fon);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (fon != null)
                fon.close();
        }
        return directory.getAbsolutePath();
    }

    public void LoadImage(String Path)
    {
        try {
            File file = new File(Path, "image.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

            imguser.setImageBitmap(bitmap);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void writedata()
    {
        try
        {
            FileOutputStream fo=openFileOutput("newfile.txt",MODE_PRIVATE);
            OutputStreamWriter ow=new OutputStreamWriter(fo);
            BitmapDrawable bitmapdrawable = (BitmapDrawable)imguser.getDrawable();
            Bitmap bitmap = bitmapdrawable.getBitmap();
            ow.write(Saveimage(bitmap));
            ow.flush();
            ow.close();
            Toast.makeText(getBaseContext(),"Lưu thành công",Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
        }
    }
    public void readdata()
    {
        try {
            int n=0;
            FileInputStream fi = openFileInput("newfile.txt");
            BufferedReader bf=new BufferedReader(new InputStreamReader(fi));
            StringBuilder build=new StringBuilder();
            String data;
      ArrayList<String> ar=new ArrayList<String>();
            while((data=bf.readLine())!=null)
            {
                build.append(data);
                ar.add(n,build.toString());
                build.append("\n");
                n++;
                build.delete(0,build.length());
            }
            LoadImage(ar.get(0));

            bf.close();
        }catch (IOException e)
        {
        }

    }
    @Override
    public void onBackPressed() {
    AlertDialog.Builder buider=new AlertDialog.Builder(MainActivity.this);
    buider.setMessage("Bạn có muốn thoát ?");
    buider.setCancelable(true);
    buider.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
            System.exit(0);
        }
    });
    buider.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });
        AlertDialog dialog = buider.create();
        // tạo dialog
        dialog.show();
    }
}









