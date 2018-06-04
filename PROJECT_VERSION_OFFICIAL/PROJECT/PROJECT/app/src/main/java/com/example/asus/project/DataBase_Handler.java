package com.example.asus.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 17-Nov-17.
 */

public class DataBase_Handler extends SQLiteOpenHelper {
    private static final String Database_name = "Quanlytaichinh";
    private static final int Database_version = 1;
    private static final String Table_name = "danhsachkhoanchi";
    private static final String Table_name_thu = "danhsachkhoanthu";
    static int n=0;
    String s;
    public DataBase_Handler(Context context) {
        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = String.format("CREATE TABLE IF NOT EXISTS " + Table_name + "(Makhoanchi VARCHAR PRIMARY KEY,TenKhoanChi VARCHAR,SoTien NUMERIC,NgayChi VARCHAR)");
        db.execSQL(s);

        String s1 = String.format("CREATE TABLE IF NOT EXISTS " + Table_name_thu + "(Makhoanthu VARCHAR PRIMARY KEY,TenKhoanThu VARCHAR,SoTien NUMERIC,NgayThu VARCHAR)");
        db.execSQL(s1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String s1 = String.format("DROP TABLE IF EXISTS " + Table_name);
        db.execSQL(s1);
        onCreate(db);

        String s2 = String.format("DROP TABLE IF EXISTS " + Table_name_thu);
        db.execSQL(s2);
        onCreate(db);
    }

    public void add(String s1, Long s2, String s3) {

        String temp="KC"+n;
        s=temp;
        ArrayList ss=checkid();
        for(int i=0;i<ss.size();i++)
        {
          //  String ss1=ss.get(i).toString().substring(0,3);
            if(ss.get(i).toString().substring(0,3).contains(s))
           // if(s.compareTo(ss1)==0)
            {
                n++;
                s="KC"+n;
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Table_name+" VALUES('" + s + "','" + s1 + "','" + s2 +
                "','" + s3 + "');");

        db.close();
        n++;
    }

    public void add_thu(String s1, Long s2, String s3) {

        String temp="KT"+n;
        s=temp;
        ArrayList ss=checkid_thu();
        for(int i=0;i<ss.size();i++)
        {
            //  String ss1=ss.get(i).toString().substring(0,3);
            if(ss.get(i).toString().substring(0,3).contains(s))
            // if(s.compareTo(ss1)==0)
            {
                n++;
                s="KT"+n;
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Table_name_thu+" VALUES('" + s + "','" + s1 + "','" + s2 +
                "','" + s3 + "');");

        db.close();
        n++;
    }

//    public StringBuffer showall() {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor c = db.rawQuery("SELECT * FROM "+Table_name, null);
//        StringBuffer buffer = new StringBuffer();
//        if (c.getCount() == 0) {
//                buffer.append("Không có student nào");
//
//        } else {
//            while (c.moveToNext()) {
//                buffer.append("Tên khoản chi:" + c.getString(0) + "\n");
//                buffer.append("Số tiền" + c.getString(1) + "\n");
//                buffer.append("Ngày chi" + c.getString(2) + "\n");
//            }
//            return buffer;
//        }
//        return buffer;
//    }

    public ArrayList showall() {
        SQLiteDatabase db = this.getWritableDatabase();

        int id = 0;

        Cursor c = db.rawQuery("SELECT * FROM " + Table_name, null);
        ArrayList<String> list_chi = new ArrayList<String>();
        if (c.getCount() == 0) {
            list_chi.add("Không có student nào");

        } else {
            while (c.moveToNext()) {
                String s0 = ("Mã khoản chi:" + c.getString(0) + "\n");
                String s1 = ("Tên khoản chi:" + c.getString(1) + "\n");
                String s2 = ("Số tiền:" + c.getString(2) + "\n");
                String s3 = ("Ngày chi:" + c.getString(3) + "\n");
                list_chi.add(s0 + s1 + s2 + s3);

            }
            return list_chi;
        }
        return list_chi;
    }
    public ArrayList showallthu() {

        SQLiteDatabase db = this.getWritableDatabase();

        int id=0;

        Cursor c = db.rawQuery("SELECT * FROM "+Table_name_thu, null);
        ArrayList<String> list_thu = new ArrayList<String>();
        if (c.getCount() == 0) {
            list_thu.add("Không có lần thu nào");

        } else {
            while (c.moveToNext()) {
                String s0=("Mã khoản Thu:"+c.getString(0)+"\n");
                String s1=("Tên khoản Thu:" + c.getString(1) + "\n");
                String s2=("Số tiền:" + c.getString(2) + "\n");
                String s3=("Ngày Thu:" + c.getString(3) + "\n");
                list_thu.add(s0+s1+s2+s3);

            }
            return list_thu;
        }

    return list_thu;
    }

   public ArrayList checkid()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Makhoanchi FROM danhsachkhoanchi", null);
        ArrayList<String> list_chi = new ArrayList<String>();
        while (c.moveToNext()) {
        list_chi.add(c.getString(0)+"\n");

        }
        return  list_chi;
    }

    public ArrayList checkid_thu()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Makhoanthu FROM danhsachkhoanthu", null);
        ArrayList<String> list_thu = new ArrayList<String>();
        while (c.moveToNext()) {
            list_thu.add(c.getString(0)+"\n");

        }
        return  list_thu;
    }

    public void delete(String a)
    {
        int e=n;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name+" WHERE Makhoanchi= "+'"'+a.toString()+'"');
        db.close();
        n=e;

    }

    public void delete_thu(String a)
    {
        int e=n;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name_thu+" WHERE Makhoanthu= "+'"'+a.toString()+'"');
        db.close();
        n=e;

    }


    public void update(String a,String s1,Long s2,String s3)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name+" SET TenKhoanChi="+'"'+s1+'"'+",SoTien="+s2+",NgayChi="+'"'+s3+'"'+" WHERE Makhoanchi= "+'"'+a.toString()+'"';
        db.execSQL(s);
        db.close();
    }

    public void updatethu(String a,String s1,Long s2,String s3)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name_thu+" SET TenKhoanThu="+'"'+s1+'"'+",SoTien="+s2+",NgayThu="+'"'+s3+'"'+" WHERE Makhoanthu= "+'"'+a.toString()+'"';
        db.execSQL(s);
        db.close();
    }

    public void deleteall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name);
        db.close();
    }

    public void deleteallthu()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name_thu);
        db.close();
    }


}