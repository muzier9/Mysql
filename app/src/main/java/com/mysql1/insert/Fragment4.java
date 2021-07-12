package com.mysql1.insert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;


import com.mysql1.MyApplication;
import com.mysql1.R;
import com.mysql1.mysql.Main2Activity;
import com.mysql1.mysql.UserDao;
import com.mysql1.mysql.huiyuan;
import com.mysql1.mysql.pdenglu;
import com.mysql1.mysql.vipdenglu;
import com.mysql1.mysql.xiugai;
import com.mysql1.person.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  项目名：  android_znybq
 *  包名：     com.liu.znybq.fragment
 *  文件名:    FragmentHome
 *  创建者:    shi860715@126.com liubj
 *  创建时间:   2020/4/16  12:50
 *  描述：    TODO
 */
public class Fragment4 extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;
    SimpleAdapter simpleAdapter;

    /**
     * 布局文件和framagement 关联
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment4, container, false);
        listView = view.findViewById(R.id.my_list);

        simpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.my_menu, new String[]{"title"}, new int[]{R.id.myMenu_name});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);
        return view;
    }

    private List<Map<String, Object>> getData() {
        String[] titles = {"个人信息", "申请成为会员", "修改信息", "设置地址", "5", "6", "17"};

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Map map = new HashMap();
            map.put("title", titles[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent(getActivity(), Main2Activity.class);
            //启动
            startActivity(intent);

        } else if (position == 1) {
            new Thread() {
                @Override
                public void run() {
                    Dao k = new Dao();
                    k.update(MyApplication.getInstance().getUser().getName(), "1");
                }
            }.start();
            Toast.makeText(getActivity(), "注册会员成功，跳转至会员页面", Toast.LENGTH_LONG).show();
            hand1.sendEmptyMessage(1);
        }
        else if(position==2){
            Intent intent = new Intent(getActivity(), xiugai.class);
            //启动
            startActivity(intent);
        }

    }

    final Handler hand1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                startActivity(new Intent(getActivity(), vipdenglu.class));
            } else {
                Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_LONG).show();
            }
        }
    };



    }








