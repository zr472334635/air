package com.disuo.duray.air.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.disuo.duray.air.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.disuo.duray.air.global.GlobalContants.LOAD_URL;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_username;
    private EditText edt_userpassword;
    private EditText edt_usercofpassword;
    private Button btn_regconf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initview();
        initdata();
        initEvent();
    }
    private void initview(){
        edt_username=findViewById(R.id.edt_username);
        edt_userpassword=findViewById(R.id.edt_userpassword);
        edt_usercofpassword=findViewById(R.id.edt_usercofpassword);
        btn_regconf=findViewById(R.id.btn_regconf);
    }
    private void initdata(){}
    private void initEvent(){
        btn_regconf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regphone=edt_username.getText().toString();
                String regpswd=edt_userpassword.getText().toString();
                String cofpswd=edt_usercofpassword.getText().toString();
                if(regphone.equals(" ") || regpswd.equals(" ") || cofpswd.equals(" ")){
                    Toast.makeText(RegisterActivity.this,"请全部填写",Toast.LENGTH_SHORT).show();
                }else{
                    upload(regphone,regpswd,cofpswd);

                }
            }
        });
    }

    public void upload(String regphone,String regpswd,String cofpswd){
        RequestParams params = new RequestParams(LOAD_URL+"/servlet/UserRegisterServlet");
        params.addQueryStringParameter("regphone",regphone);
        params.addQueryStringParameter("regpswd",regpswd);
        params.addQueryStringParameter("cofpswd",cofpswd);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                if(Integer.parseInt(result)==1){
                    Toast.makeText(RegisterActivity.this,"账户已注册",Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(result)==2){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }else if(Integer.parseInt(result)==3){
                    Toast.makeText(RegisterActivity.this,"未注册成功",Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(result)==4){
                    Toast.makeText(RegisterActivity.this,"两次密码不一样",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
                }
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","onError"+ex.getMessage());
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG","onCancelled"+cex.getMessage());
            }
            @Override
            public void onFinished() {
                Log.e("TAG","onFinished");
            }
        });
    }
}
