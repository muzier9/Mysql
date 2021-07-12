package com.mysql1.mysql;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.mysql1.MyApplication;
import com.mysql1.R;
import com.mysql1.person.Dao;
import com.mysql1.person.Item;
import com.mysql1.person.personAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity {
    private static final String TAG = "Main2Activity活动";
    private List<Item> itemList = new ArrayList<>();
    private ListView listView;
    @SuppressLint("HandlerLeak")
    private Handler myHandler = new Handler(){

        @Override
        public void  handleMessage(Message msg){

            switch (msg.what){
                case 1:
                    Log.d(TAG, "run: UI更新成功");
                    personAdapter myAdapter= new personAdapter(Main2Activity.this, R.layout.item, itemList);
                    listView = (ListView)findViewById(R.id.list_item);
                    listView.setAdapter(myAdapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initLogin();
    }
    private void initLogin(){
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }
    private class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println(MyApplication.getInstance().getUser());
            Dao dao = new Dao();
            try {

                itemList = dao.loginList(MyApplication.getInstance().getUser().getName());
                Log.d(TAG, "run: 数据库数据读取成功");
            } catch (SQLException e) {
                e.printStackTrace();
                Log.d(TAG, "run: " +
                        "数据库数据读取失败");
            }
            Message msg = new Message();
            msg.what =1;
            myHandler.sendMessage(msg);
            Log.d(TAG, "run: Message 唯一识别码 msg.what 发送成功");
        }
    }
}
