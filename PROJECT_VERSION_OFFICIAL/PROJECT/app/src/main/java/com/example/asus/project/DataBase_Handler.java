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
    private static final String Table_name_no = "danhsachkhoanno";
    private static final String Table_name_vay = "danhsachkhoanvay";
    private static final String Table_name_user = "thongtincanhan";
    static int n1=0,n2=0,n3=0,n4=0,n=0;
    String s;
    public DataBase_Handler(Context context) {
        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = String.format("CREATE TABLE IF NOT EXISTS " + Table_name + "(Makhoanchi VARCHAR PRIMARY KEY,TenKhoanChi VARCHAR,SoTien NUMERIC,NgayChi VARCHAR,GhiChu VARCHAR)");
        db.execSQL(s);

        String s1 = String.format("CREATE TABLE IF NOT EXISTS " + Table_name_thu + "(Makhoanthu VARCHAR PRIMARY KEY,TenKhoanThu VARCHAR,SoTien NUMERIC,NgayThu VARCHAR,GhiChu VARCHAR)");
        db.execSQL(s1);

        String s2 = String.format("CREATE TABLE IF NOT EXISTS " + Table_name_no + "(Makhoanno VARCHAR PRIMARY KEY,TenKhoanNo VARCHAR,SoTien NUMERIC,NgayNo VARCHAR, LaiSuat NUMERIC,NgayTra VARCHAR,GhiChu VARCHAR)");
        db.execSQL(s2);

        String s3 = String.format("CREATE TABLE IF NOT EXISTS " + Table_name_vay + "(Makhoanvay  VARCHAR PRIMARY KEY,TenKhoanVay VARCHAR,SoTien NUMERIC,NgayVay VARCHAR, LaiSuat NUMERIC,NgayTra VARCHAR,GhiChu VARCHAR)");
        db.execSQL(s3);

        String s4 = String.format("CREATE TABLE IF NOT EXISTS " + Table_name_user + "(MaKH VARCHAR PRIMARY KEY,HoTen VARCHAR,NgaySinh VARCHAR,SoTien NUMERIC)");
        db.execSQL(s4);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String s1 = String.format("DROP TABLE IF EXISTS " + Table_name);
        db.execSQL(s1);
        onCreate(db);

        String s2 = String.format("DROP TABLE IF EXISTS " + Table_name_thu);
        db.execSQL(s2);
        onCreate(db);

        String s3 = String.format("DROP TABLE IF EXISTS " + Table_name_no);
        db.execSQL(s3);
        onCreate(db);

        String s4 = String.format("DROP TABLE IF EXISTS " + Table_name_vay);
        db.execSQL(s4);
        onCreate(db);

        String s5 = String.format("DROP TABLE IF EXISTS " + Table_name_user);
        db.execSQL(s5);
        onCreate(db);
    }

    public void add(String s1, Long s2, String s3,String s4) {

        String temp="KC"+n1;
        s=temp;
        ArrayList ss=checkid();
        for(int i=0;i<ss.size();i++)
        {
          //  String ss1=ss.get(i).toString().substring(0,3);
            if(ss.get(i).toString().substring(0,3).contains(s))
           // if(s.compareTo(ss1)==0)
            {
                n1++;
                s="KC"+n1;
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Table_name+" VALUES('" + s + "','" + s1 + "','" + s2 +
                "','" + s3 + "','"+ s4+ "');");

        db.close();
        n1++;
    }

    public void add_thu(String s1, Long s2, String s3,String s4) {

        String temp="KT"+n2;
        s=temp;
        ArrayList ss=checkid_thu();
        for(int i=0;i<ss.size();i++)
        {
            //  String ss1=ss.get(i).toString().substring(0,3);
            if(ss.get(i).toString().substring(0,3).contains(s))
            // if(s.compareTo(ss1)==0)
            {
                n2++;
                s="KT"+n2;
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Table_name_thu+" VALUES('" + s + "','" + s1 + "','" + s2 +
                "','" + s3 + "','"+ s4+ "');");

        db.close();
        n2++;
    }

    public void add_no(String s1, Long s2, Long s3,String s4,String s5,String s6) {

        String temp="KN"+n3;
        s=temp;
        ArrayList ss=checkid_no();
        for(int i=0;i<ss.size();i++)
        {
            //  String ss1=ss.get(i).toString().substring(0,3);
            if(ss.get(i).toString().substring(0,3).contains(s))
            // if(s.compareTo(ss1)==0)
            {
                n3++;
                s="KN"+n3;
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Table_name_no+" VALUES('" + s + "','" + s1 + "','" + s2 +
                "','" + s3 + "','"+s4+"','"+s5+"','"+s6+"');");

        db.close();
        n3++;
    }

    public void add_vay(String s1, Long s2, Long s3,String s4,String s5,String s6) {

        String temp="KV"+n4;
        s=temp;
        ArrayList ss=checkid_vay();
        for(int i=0;i<ss.size();i++)
        {
            //  String ss1=ss.get(i).toString().substring(0,3);
            if(ss.get(i).toString().substring(0,3).contains(s))
            // if(s.compareTo(ss1)==0)
            {
                n4++;
                s="KV"+n4;
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Table_name_vay+" VALUES('" + s + "','" + s1 + "','" + s2 +
                "','" + s3 + "','"+s4+"','"+s5+"','"+s6+"');");

        db.close();
        n4++;
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
    public void add_user(String s1, String s2, Long s3) {
    String s="KH";
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("INSERT INTO "+Table_name_user+" VALUES('" + s + "','" + s1 + "','" + s2 + "','" + s3 +
            "');");

    db.close();
}
    public ArrayList showall() {
        SQLiteDatabase db = this.getWritableDatabase();

        int id = 0;

        Cursor c = db.rawQuery("SELECT * FROM " + Table_name, null);
        ArrayList<String> list_chi = new ArrayList<String>();
        if (c.getCount() == 0) {
            list_chi.add("Không có lần chi nào");

        } else {
            while (c.moveToNext()) {
                String s0 = ("Mã khoản chi:" + c.getString(0) + "\n");
                String s1 = ("Tên khoản chi:" + c.getString(1) + "\n");
                String s2 = ("Số tiền:" + c.getString(2) + "\n");
                String s3 = ("Ngày chi:" + c.getString(3) + "\n");
                String s4= ("Ghi chú:" + c.getString(4) + "\n");
                list_chi.add(s0 + s1 + s2 + s3+s4);

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
                String s4= ("Ghi chú:" + c.getString(4) + "\n");
                list_thu.add(s0+s1+s2+s3+s4);

            }
            return list_thu;
        }

    return list_thu;
    }

    public ArrayList showallno() {

        SQLiteDatabase db = this.getWritableDatabase();

        int id=0;

        Cursor c = db.rawQuery("SELECT * FROM "+Table_name_no, null);
        ArrayList<String> list_no = new ArrayList<String>();
        if (c.getCount() == 0) {
            list_no.add("Không có lần nợ nào");

        } else {
            while (c.moveToNext()) {
                String s0=("Mã khoản Nợ:"+c.getString(0)+"\n");
                String s1=("Tên khoản Nợ:" + c.getString(1) + "\n");
                String s2=("Số tiền:" + c.getString(2) + "\n");
                String s3=("Lãi Suất:" + c.getString(3) + "\n");
                String s4=("Ngày Nợ:" + c.getString(4) + "\n");
                String s5=("Ngày Trả:" + c.getString(5) + "\n");
                String s6= ("Ghi chú:" + c.getString(6) + "\n");
                list_no.add(s0+s1+s2+s3+s4+s5+s6);

            }
            return list_no;
        }

        return list_no;
    }

    public ArrayList showallvay() {

        SQLiteDatabase db = this.getWritableDatabase();

        int id=0;

        Cursor c = db.rawQuery("SELECT * FROM "+Table_name_vay, null);
        ArrayList<String> list_vay = new ArrayList<String>();
        if (c.getCount() == 0) {
            list_vay.add("Không có lần vay nào");

        } else {
            while (c.moveToNext()) {
                String s0=("Mã khoản Vay:"+c.getString(0)+"\n");
                String s1=("Tên khoản Vay:" + c.getString(1) + "\n");
                String s2=("Số tiền:" + c.getString(2) + "\n");
                String s3=("Lãi Suất:" + c.getString(3) + "\n");
                String s4=("Ngày Vay:" + c.getString(4) + "\n");
                String s5=("Ngày Trả:" + c.getString(5) + "\n");
                String s6= ("Ghi chú:" + c.getString(6) + "\n");
                list_vay.add(s0+s1+s2+s3+s4+s5+s6);

            }
            return list_vay;
        }

        return list_vay;
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

    public ArrayList checkid_no()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Makhoanno FROM danhsachkhoanno", null);
        ArrayList<String> list_no = new ArrayList<String>();
        while (c.moveToNext()) {
            list_no.add(c.getString(0)+"\n");

        }
        return  list_no;
    }
    public ArrayList checkid_vay()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Makhoanvay FROM danhsachkhoanvay", null);
        ArrayList<String> list_vay = new ArrayList<String>();
        while (c.moveToNext()) {
            list_vay.add(c.getString(0)+"\n");

        }
        return  list_vay;
    }

    public boolean checkinfo()
    {
        String s=null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM thongtincanhan", null);
//      s=c.getString(0);
        if(c.getCount()==0)
        {
            return true;
        }
        return false;
    }
    public void delete(String a)
    {
  //      int e=n;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name+" WHERE Makhoanchi= "+'"'+a.toString()+'"');
        db.close();
  //      n=e;

    }

    public void delete_thu(String a)
    {
   //     int e=n;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name_thu+" WHERE Makhoanthu= "+'"'+a.toString()+'"');
        db.close();
   //     n=e;

    }

    public void delete_no(String a)
    {
    //    int e=n;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name_no+" WHERE Makhoanno= "+'"'+a.toString()+'"');
        db.close();
    //    n=e;

    }


    public void delete_vay(String a)
    {
    //    int e=n;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name_vay+" WHERE Makhoanvay= "+'"'+a.toString()+'"');
        db.close();
      //  n=e;

    }

    public void update(String a,String s1,Long s2,String s3,String s4)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name+" SET TenKhoanChi="+'"'+s1+'"'+",SoTien="+s2+",NgayChi="+'"'+s3+'"'+",GhiChu="+'"'+s4+'"'+" WHERE Makhoanchi= "+'"'+a.toString()+'"';
        db.execSQL(s);
        db.close();
    }

    public void updatethu(String a,String s1,Long s2,String s3,String s4)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name_thu+" SET TenKhoanThu="+'"'+s1+'"'+",SoTien="+s2+",NgayThu="+'"'+s3+'"'+",GhiChu="+'"'+s4+'"'+" WHERE Makhoanthu= "+'"'+a.toString()+'"';
        db.execSQL(s);
        db.close();
    }

    public void updateno(String a,String s1,Long s2,String s3,Long s4,String s5,String s6)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name_no+" SET TenKhoanNo="+'"'+s1+'"'+",SoTien="+s2+",NgayNo="+'"'+s3+'"'+",LaiSuat="+'"'+s4+'"'+",NgayTra="+'"'+s5+'"'+",GhiChu="+'"'+s6+'"'+" WHERE Makhoanno= "+'"'+a.toString()+'"';
        db.execSQL(s);
        db.close();
    }

    public void updateinfo(String s1,String s2)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name_user+" SET HoTen="+'"'+s1+'"'+",NgaySinh="+'"'+s2+'"'+" WHERE MaKH= 'KH'";
        db.execSQL(s);
        db.close();
    }

    public void updatevay(String a,String s1,Long s2,String s3,Long s4,String s5,String s6)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name_vay+" SET TenKhoanVay="+'"'+s1+'"'+",SoTien="+s2+",NgayVay="+'"'+s3+'"'+",LaiSuat="+'"'+s4+'"'+",NgayTra="+'"'+s5+'"'+",GhiChu="+'"'+s6+'"'+" WHERE Makhoanvay="+'"'+a.toString()+'"';
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

    public void deleteallno()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name_no);
        db.close();
    }

    public void deleteallvay()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ Table_name_vay);
        db.close();
    }
    public String getnameuser() {
        String s =null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT HoTen FROM thongtincanhan", null);

            while (c.moveToNext()) {
                s = c.getString(c.getColumnIndex("HoTen"));
            }

            return s;
        }


    public Long getmoneyuser() {
        Long s = 0L;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT SoTien FROM thongtincanhan", null);
            while (c.moveToNext()) {
                s = c.getLong(c.getColumnIndex("SoTien"));
            }

            return s;
        }


    public void setmoneyuser(Long s1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s=" UPDATE "+ Table_name_user+" SET SoTien="+'"'+s1+'"'+" WHERE MaKH= 'KH'";
        db.execSQL(s);
        db.close();
    }

    public Float gettienchimuasam()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c = db.rawQuery("SELECT * FROM danhsachkhoanchi", null);
        while (c.moveToNext()){
            if(c.getString(1 ).compareTo("Mua sắm")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienchidienuoc()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c = db.rawQuery("SELECT * FROM danhsachkhoanchi", null);
        while (c.moveToNext()){
            if(c.getString(1 ).compareTo("Điện nước")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienchigiaitri()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c = db.rawQuery("SELECT * FROM danhsachkhoanchi", null);
        while (c.moveToNext()){
            if(c.getString(1 ).compareTo("Giải trí")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienchitrano()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c = db.rawQuery("SELECT * FROM danhsachkhoanchi", null);
        while (c.moveToNext()){
            if(c.getString(1 ).compareTo("Trả nợ")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettiennonganhang()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c=db.rawQuery("SELECT* FROM danhsachkhoanno",null);
        while (c.moveToNext()){
            if(c.getString(1).compareTo("Nợ ngân hàng")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettiennobanbe()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c=db.rawQuery("SELECT* FROM danhsachkhoanno",null);
        while (c.moveToNext()){
            if(c.getString(1).compareTo("Nợ bạn bè")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettiennonguoithan()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c=db.rawQuery("SELECT* FROM danhsachkhoanno",null);
        while (c.moveToNext()){
            if(c.getString(1).compareTo("Nợ người thân")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienvaybanbe()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c=db.rawQuery("SELECT* FROM danhsachkhoanvay",null);
        while (c.moveToNext()){
            if(c.getString(1).compareTo("Ban bè")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienvaynguoithan(){
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c=db.rawQuery("SELECT *FROM danhsachkhoanvay",null);
        while(c.moveToNext()){
            if(c.getString(1).compareTo("Người thân")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienvaykhac()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c=db.rawQuery("SELECT* FROM danhsachkhoanvay",null);
        while (c.moveToNext()){
            if(c.getString(1).compareTo("Cho vay khác")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienthuluongthuong()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c = db.rawQuery("SELECT * FROM danhsachkhoanthu", null);
        while(c.moveToNext()){
            if(c.getString(1).compareTo("Lương thưởng")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienthukinhdoanh()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c = db.rawQuery("SELECT * FROM danhsachkhoanthu", null);
        while(c.moveToNext()){
            if(c.getString(1).compareTo("Kinh doanh")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

    public Float gettienthunhanlai()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        float tong=0;
        Cursor c = db.rawQuery("SELECT * FROM danhsachkhoanthu", null);
        while(c.moveToNext()){
            if(c.getString(1).compareTo("Nhận lãi")==0){
                tong=tong+c.getFloat(2);
            }
        }
        return tong;
    }

}