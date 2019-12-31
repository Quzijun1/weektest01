package com.example.week01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.week01.adapter.MyAdapter;
import com.example.week01.bean.OneBean;
import com.example.week01.bean.TwoBean;
import com.example.week01.presenter.OnePresenter;
import com.example.week01.view.OneView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OneView {
    private OnePresenter onePresenter;
    private ListView listview;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例 控件
        listview = findViewById(R.id.listview);
        //实例P层
        onePresenter = new OnePresenter(MainActivity.this);
        onePresenter.getdata("http://blog.zhaoliang5156.cn/api/news/smsbak.json");
    }

    @Override
    public void onjson(String json) {
        //获取数据
        Log.d("XXX",json);
        //解析
        Gson gson = new Gson();
        OneBean oneBean = gson.fromJson(json, OneBean.class);
        List<TwoBean> smses = oneBean.getSmses();
        //设置适配器
        adapter = new MyAdapter(MainActivity.this,smses);
        //添加适配器
        listview.setAdapter(adapter);
    }

    @Override
    public void onerror(String msg) {

    }
}
