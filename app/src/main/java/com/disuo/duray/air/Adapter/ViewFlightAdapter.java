package com.disuo.duray.air.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.disuo.duray.air.R;
import com.disuo.duray.air.entity.FlightEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ViewFlightAdapter extends ArrayAdapter {
    private final int resourceId;

    private TextView tv_viewscity;
    private TextView tv_viewecity;
    private TextView tv_viewstime;
    private TextView tv_viewetime;
    private TextView tv_viewprice;


    public ViewFlightAdapter(Context context, int textViewResourceId, List<FlightEntity> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        FlightEntity flightEntity = (FlightEntity) getItem(position); // 获取当前项的Fruit实例

        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象

        tv_viewscity = view.findViewById(R.id.tv_viewscity);
        tv_viewecity = view.findViewById(R.id.tv_viewecity);
        tv_viewstime = view.findViewById(R.id.tv_viewstime);
        tv_viewetime = view.findViewById(R.id.tv_viewetime);
        tv_viewprice = view.findViewById(R.id.tv_viewprice);

        String str = flightEntity.getStartTime();

        tv_viewscity.setText(flightEntity.getStartPlace());
        tv_viewecity.setText(flightEntity.getEndPlace());
        tv_viewstime.setText(flightEntity.getStartTime());
        tv_viewetime.setText(flightEntity.getEndTime());
        tv_viewprice.setText(flightEntity.getPrice());

        return view;
    }
}
