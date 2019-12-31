package com.example.week01.moudle;

import com.example.week01.net.NetUtil;
//M层
public class OneMoudle {
    public void getdata(String path, NetUtil.CallBack callBack){
        NetUtil instance = NetUtil.getInstance();
        instance.doGet(path,callBack);
    }
}
