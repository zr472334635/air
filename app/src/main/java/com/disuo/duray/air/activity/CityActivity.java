package com.disuo.duray.air.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.disuo.duray.air.R;

public class CityActivity extends AppCompatActivity {

    private TextView tv_beijing;
    private TextView tv_shanghai;
    private TextView tv_guangzhou;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initView();
        initdata();
        initEvent();
    }

    private void initView(){
        tv_beijing=findViewById(R.id.tv_beijing);
        tv_shanghai=findViewById(R.id.tv_shanghai);
        tv_guangzhou=findViewById(R.id.tv_guangzhou);
    }
    private void initdata(){
        type= getIntent().getStringExtra("type");
//        Toast.makeText(CityActivity.this,type,Toast.LENGTH_SHORT).show();

    }
    private void initEvent(){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = null;
                SharedPreferences.Editor editor = getSharedPreferences("city", MODE_WORLD_WRITEABLE).edit();
                switch (view.getId()){
                    case R.id.tv_beijing:
                        city= (String) tv_beijing.getText();

                        break;
                    case R.id.tv_shanghai:
                        city= (String) tv_shanghai.getText();
                        break;
                    case R.id.tv_guangzhou:
                        city= (String) tv_guangzhou.getText();
                        break;
                }
                if(type.equals("1")){
                    editor.putString("scity", city);
                }else if(type.equals("2")){
                    editor.putString("ecity", city);
                }else {
                    Toast.makeText(CityActivity.this,"出错",Toast.LENGTH_SHORT).show();
                }
                editor.commit();
                Intent intent=new Intent(CityActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        tv_beijing.setOnClickListener(listener);
        tv_shanghai.setOnClickListener(listener);
        tv_guangzhou.setOnClickListener(listener);
    }
}
