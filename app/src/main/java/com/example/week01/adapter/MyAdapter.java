package com.example.week01.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.week01.R;
import com.example.week01.bean.TwoBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    //实例变量
    private Context context;
    private List<TwoBean> list = new ArrayList<>();
//提供有参构造
    public MyAdapter(Context context, List<TwoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
//条目类型
    @Override
    public int getItemViewType(int position) {
        int isread = list.get(position).getIsread();
        if(isread == 1){
            return 0;
        }else {
            return 1;
        }
    }
//条目类型的数量
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
       //第一种
        if(type == 0){
            Hodele hodele = null;
            if(convertView == null){
                hodele = new Hodele();
                convertView = View.inflate(context, R.layout.listone,null);
                hodele.from = convertView.findViewById(R.id.from);
                hodele.nr = convertView.findViewById(R.id.nr);
                hodele.time = convertView.findViewById(R.id.timee);
                convertView.setTag(hodele);
            }else {
                hodele = (Hodele)convertView.getTag();
            }
            hodele.time.setText(list.get(position).getTime());
            hodele.nr.setText(list.get(position).getContent());
            hodele.from.setText(list.get(position).getFrom());
        }else {
            //第二种
            Hodele hodele = null;
            if(convertView == null){
                hodele = new Hodele();
                convertView = View.inflate(context, R.layout.listtwo,null);
                hodele.from = convertView.findViewById(R.id.from);
                hodele.nr = convertView.findViewById(R.id.nr);
                hodele.time = convertView.findViewById(R.id.timee);
                convertView.setTag(hodele);
            }else {
                hodele = (Hodele)convertView.getTag();
            }
            hodele.time.setText(list.get(position).getTime());
            hodele.nr.setText(list.get(position).getContent());
            hodele.from.setText(list.get(position).getFrom());
        }
        return convertView;
    }
    public class Hodele{
        TextView from,time,nr;
    }
}
