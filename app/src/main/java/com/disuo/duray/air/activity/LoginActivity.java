package com.disuo.duray.air.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.disuo.duray.air.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.disuo.duray.air.global.GlobalContants.LOAD_URL;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_userloginname;
    private EditText edt_userloginpassword;
    private TextView tv_userloginregister;
    private Button btn_userlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview();
        initdata();
        initEvent();
    }

    private void initview(){
        edt_userloginname=findViewById(R.id.edt_userloginname);
        edt_userloginpassword=findViewById(R.id.edt_userloginpassword);
        tv_userloginregister=findViewById(R.id.tv_userloginregister);
        btn_userlogin=findViewById(R.id.btn_userlogin);
    }
    private void initdata(){}
    private void initEvent(){
//        log_forgetpswd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
//            }
//        });

        tv_userloginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestParams params = new RequestParams(LOAD_URL + "/servlet/UserLoginServlet");
                final String user = edt_userloginname.getText().toString();
                final String pswd = edt_userloginpassword.getText().toString();

                params.addQueryStringParameter("user", user);
                params.addQueryStringParameter("pswd", pswd);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //解析result
                        Log.e("TAG", "onSuccess" + result);
                        if (Integer.parseInt(result) == 1) {
                            SharedPreferences preferences = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("user", user);
                            editor.commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();


                        } else if (Integer.parseInt(result) == 2) {
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "账户不存在", Toast.LENGTH_SHORT).show();
                        }

                    }

                    //请求异常后的回调方法
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("TAG", "onError" + ex.getMessage());
                    }

                    //主动调用取消请求的回调方法
                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.e("TAG", "onCancelled" + cex.getMessage());
                    }

                    @Override
                    public void onFinished() {
                        Log.e("TAG", "onFinished");
                    }
                });

            }
        });
    }
}
