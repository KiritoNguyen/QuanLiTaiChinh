package com.example.asus.project;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ASUS on 18-Nov-17.
 */

public class CustomAdapter extends BaseAdapter {
    ArrayAdapter arrayAdapters;
    Context context;
    Dialog dialog;

    public CustomAdapter(Context context, ArrayAdapter arrayAdapter) {
        this.context = context;
        this.arrayAdapters = arrayAdapter;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView tvNoiDung = (TextView) rowView.findViewById(R.id.txtitem);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgview);

        //lấy Nội dung của Item ra để thiết lập nội dung item cho đúng
        tvNoiDung.setText(arrayAdapters.toString());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(context);
                // khởi tạo dialog
                dialog.setContentView(R.layout.customdialog);
                // xét layout cho dialog
                dialog.setTitle("Đăng kí");
                // xét tiêu đề cho dialog


                dialog.show();
                // hiển thị dialog
            }
        });

        return rowView;
    }
}
