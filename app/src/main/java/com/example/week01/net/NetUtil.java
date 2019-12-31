package com.example.week01.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//创建网络工具类
public class NetUtil {
    private static NetUtil instance;
//单一实例
    private NetUtil() {
    }
    public static NetUtil getInstance(){
        if(instance == null){
            instance = new NetUtil();
        }
        return instance;
    }
    //请求网络数据
    @SuppressLint("StaticFieldLeak")
    public void doGet(String path, final CallBack callBack){
        //判断网络状态
        boolean zhuangtai = zhuangtai();
        if(!zhuangtai){
            Toast.makeText(App.context, "网络错误", Toast.LENGTH_SHORT).show();
        }
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String string = strings[0];
                try {
                    URL url = new URL(string);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    int responseCode = httpURLConnection.getResponseCode();
                    if(responseCode == 200){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        int len =-1;
                        byte[] bytes = new byte[1024];
                        StringBuffer sb = new StringBuffer();
                        while ((len = inputStream.read(bytes))!=-1){
                            String s = new String(bytes,0,len);
                            sb.append(s);
                        }
                        String json = sb.toString();
                        inputStream.close();
                        return json;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(TextUtils.isEmpty(s)){
                    callBack.onshibai("");
                }else {
                    callBack.oncheng(s);
                }
            }
        }.execute(path);
    }
    //网络判断
    public boolean zhuangtai(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){
            boolean available = activeNetworkInfo.isAvailable();
            return available;
        }
        return false;
    }
    public interface CallBack{
        void oncheng(String json);
        void onshibai(String msg);
    }
}
