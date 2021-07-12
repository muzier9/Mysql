package com.mysql1.mysql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mysql1.MyApplication;
import com.mysql1.R;
import com.mysql1.person.Dao;

public class xiugai extends Activity {
    EditText xusername = null;
    EditText xphone = null;
    EditText xaddress = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zuche);

        xusername = findViewById(R.id.username2);
        xphone = findViewById(R.id.phone2);
        xaddress = findViewById(R.id.adderss2);

    }


    public void xiugai(View view){



        String cname = xusername.getText().toString();
        String cphone = xphone.getText().toString();
        String caddress=xaddress.getText().toString();
        System.out.println(xphone.getText().toString());



        if(cname.length() < 2  ){
            Toast.makeText(getApplicationContext(),"输入信息不符合要求请重新输入", Toast.LENGTH_LONG).show();
            return;

        }




        new Thread(){
            @Override
            public void run() {

                int msg = 1;

                Dao userDao2 = new Dao();

                userDao2.update2(cname,cphone,caddress, MyApplication.getInstance().getUser().getName());


                hand.sendEmptyMessage(msg);

            }
        }.start();


    }
    Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"该账号已经存在，请换一个账号",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 2)
            {
                startActivity(new Intent(getApplication(),pdenglu.class));

                Intent intent = new Intent();
                //将想要传递的数据用putExtra封装在intent中
                intent.putExtra("a","註冊");
                setResult(RESULT_CANCELED,intent);
                finish();
            }

        }
    };
}


