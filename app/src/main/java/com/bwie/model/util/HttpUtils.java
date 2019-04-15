package com.bwie.model.util;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/4/15 9:03
 * Description:网络工具类
 */
public class HttpUtils {

    private HttpUtils instance = new HttpUtils();

    private HttpUtils() {

    }

    public HttpUtils getInstance() {
        return instance;
    }

    public static String getDataFromServer(String url) {

        HttpURLConnection connection = null;
        try {
            URL url1 = new URL(url);
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            //我也不知道这是干什么。。
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void getDataFromServer(final String url, final HttpUtilsCallBack callBack) {
        new AsyncTask<String, Void, String>(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(!TextUtils.isEmpty(s)){
                    callBack.success(s);
                }else {
                    callBack.failed();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                return getDataFromServer(strings[0]);
            }
        }.execute(url);
    }

    public interface HttpUtilsCallBack{
        void success(String result);
        void failed();
    }

}
