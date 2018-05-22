package com.disuo.duray.air.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.disuo.duray.air.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView tv_origin;
    private TextView tv_destination;
    private TextView tv_origindate;
    private TextView tv_requirement;
    private Button btn_search;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        tv_origin = findViewById(R.id.tv_origin);
        tv_destination = findViewById(R.id.tv_destination);
        tv_origindate = findViewById(R.id.tv_origindate);
        tv_requirement = findViewById(R.id.tv_requirement);
        btn_search = findViewById(R.id.btn_search);
    }

    private void initData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tv_origindate.setText(str);

        city = null;

        SharedPreferences read = getSharedPreferences("city", MODE_WORLD_READABLE);
        String scity = read.getString("scity", "");
        String ecity = read.getString("ecity", "");
        city = getIntent().getStringExtra("city");

        if (TextUtils.isEmpty(scity) && TextUtils.isEmpty(ecity)) {
            tv_origin.setText("北京");
            tv_destination.setText("上海");
            return;
        } else{
            tv_origin.setText(scity);
            tv_destination.setText(ecity);

        }


    }

    private void initEvent() {
        tv_origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                finish();
            }
        });
        tv_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                finish();
            }
        });
        tv_origindate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar ca = Calendar.getInstance();
                final int iYear = ca.get(Calendar.YEAR);
                final int iMonth = ca.get(Calendar.MONTH);
                final int iDay = ca.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog mDialog = new DatePickerDialog(MainActivity.this, null,
                        iYear, iMonth, iDay);
                mDialog.setCancelable(true);
                mDialog.setCanceledOnTouchOutside(true);
                mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatePicker datePicker = mDialog.getDatePicker();
                                int year = datePicker.getYear();
                                int month = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();
                                tv_origindate.setText(new StringBuffer().append(year).append("年").append(month + 1).append("月").append(day).append("日"));
//                                tv_origindate.setText(new StringBuffer().append(year).append("-").append(month + 1).append("-").append(day).append(" "));
//                                String date = (String) user_birth_in.getText();
//                                upload(date, "Birthday", phone);
                            }
                        });
                mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Picker", "Cancel!");
                            }
                        });
                mDialog.show();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scity= (String) tv_origin.getText();
                String ecity= (String) tv_destination.getText();
                Intent intent=new Intent(MainActivity.this,ViewFilghtActivity.class);
                intent.putExtra("scity", scity);
                intent.putExtra("ecity", ecity);
                startActivity(intent);
            }
        });

    }


}
