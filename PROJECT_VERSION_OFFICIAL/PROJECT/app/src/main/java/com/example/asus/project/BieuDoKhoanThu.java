package com.example.asus.project;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class BieuDoKhoanThu extends AppCompatActivity {

    private static String TAG = "BieuDoKhoanThu";
    //
    private float[] yData ;
    //            yData[0]=2.0f;
//        yData[1]=6.1f;
//        yData[2]=8.3f;
    private String[] xData = {"Lương thưởng", "Kinh doanh", "Nhận lãi"};
    PieChart pieChart;
    DataBase_Handler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieu_do_khoan_thu);
        db=new DataBase_Handler(BieuDoKhoanThu.this);
        yData=new float[3];
        float lt=db.gettienthuluongthuong();
        float kd=db.gettienthukinhdoanh();
        float nl=db.gettienthunhanlai();
        yData[0]=lt;
        yData[1]=kd;
        yData[2]=nl;
        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setDescription("Thống kê khoản thu ");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Khoản Thu");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum): ");
                String sales = e.toString().substring(pos1 + 7);

                for(int i = 0; i < yData.length; i++){
                    if(yData[i] == Float.parseFloat(sales)){
                        pos1 = i;
                        break;
                    }
                }
                String employee = xData[pos1 + 0];
                Toast.makeText(BieuDoKhoanThu.this, "Thể Loại " + employee + "\n" + "Số tiền:" + sales , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Khoản thu: Lương thưởng, Kinh doanh, Nhận lãi");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
