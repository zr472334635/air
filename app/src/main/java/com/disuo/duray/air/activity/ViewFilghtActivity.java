package com.disuo.duray.air.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.disuo.duray.air.Adapter.ViewFlightAdapter;
import com.disuo.duray.air.R;
import com.disuo.duray.air.entity.FlightEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.disuo.duray.air.global.GlobalContants.LOAD_URL;

public class ViewFilghtActivity extends AppCompatActivity {

    String[] PLTNo;
    String[] FlightDate;
    String[] StartTime;
    String[] EndTime;
    String[] StartPlace;
    String[] EndPlace;
    String[] price;
    String[] Requirement;
    String[] SeatNum;

    private List<FlightEntity> flightEntities = new ArrayList<FlightEntity>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_filght);
        String scity= getIntent().getStringExtra("scity");
        String ecity= getIntent().getStringExtra("ecity");
        download(scity,ecity);
        initView();
        initEvent();
    }

    private void initView() {
        PLTNo = new String[100];
        FlightDate = new String[100];
        StartTime = new String[100];
        EndTime = new String[100];
        StartPlace = new String[100];
        EndPlace = new String[100];
        price = new String[100];
        Requirement = new String[100];
        SeatNum = new String[100];



    }
    //
//    private void initData(){}
//
    private void initEvent(){
//        listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final EditText editText = new EditText(ViewFilghtActivity.this);
//                new AlertDialog.Builder(ViewFilghtActivity.this).setTitle("买票").setIcon(android.R.drawable.ic_dialog_info).setView(
//                        editText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String name = String.valueOf(editText.getText());
//                        Toast.makeText(ViewFilghtActivity.this,"订票成功",Toast.LENGTH_SHORT).show();
//
//                    }
//                }).setNegativeButton("取消", null).show();
//            }
//        });
    }

    public void download(String scity,String ecity) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/ViewFlightServlet");
        params.addQueryStringParameter("scity", scity);
        params.addQueryStringParameter("ecity", ecity);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                JsonArray jsonArray = parser.parse(result).getAsJsonArray();
                //解析result
                Log.e("TAG", "onSuccess" + result);
                Gson gson = new Gson();
                ArrayList<FlightEntity> list = new ArrayList<>();
                for (JsonElement model : jsonArray) {
                    FlightEntity flightEntity = gson.fromJson(model, FlightEntity.class);
                    list.add(flightEntity);
                }


                for (int i = 0; i < list.size(); i++) {
                    PLTNo[i] = list.get(i).getPLTNo();
                    FlightDate[i] = list.get(i).getFlightDate();
                    StartTime[i] = list.get(i).getStartTime();
                    EndTime[i] = list.get(i).getEndTime();
                    StartPlace[i] = list.get(i).getStartPlace();
                    EndPlace[i] = list.get(i).getEndPlace();
                    price[i] = list.get(i).getPrice();
                    Requirement[i] = list.get(i).getRequirement();
                    SeatNum[i] = list.get(i).getSeatNum();

                    FlightEntity a = new FlightEntity(StartTime[i], EndTime[i], StartPlace[i], EndPlace[i], price[i]);
                    flightEntities.add(a);

                }

                ViewFlightAdapter adapter = new ViewFlightAdapter(ViewFilghtActivity.this, R.layout.adapter_flight_view, flightEntities);
                ListView listView =  findViewById(R.id.list_view);
                listView.setAdapter(adapter);


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
}
