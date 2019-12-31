package com.example.week01.presenter;

import com.example.week01.moudle.OneMoudle;
import com.example.week01.net.NetUtil;
import com.example.week01.view.OneView;
//MVP P层
public class OnePresenter {
    private OneMoudle oneMoudle;
    private OneView oneView;

    public OnePresenter(OneView oneView) {
        this.oneView = oneView;
        oneMoudle = new OneMoudle();
    }
    public void getdata(String path){
        oneMoudle.getdata(path, new NetUtil.CallBack() {
            @Override
            public void oncheng(String json) {
                oneView.onjson(json);
            }

            @Override
            public void onshibai(String msg) {
                oneView.onerror(msg);
            }
        });
    }
}
