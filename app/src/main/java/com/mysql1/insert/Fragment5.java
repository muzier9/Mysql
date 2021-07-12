package com.mysql1.insert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.mysql1.mysql.vipdenglu;
import com.mysql1.person.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment5 extends Fragment implements AdapterView.OnItemClickListener{
    ListView listView;
    SimpleAdapter simpleAdapter;

    /**
     *   布局文件和framagement 关联
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment5,container,false);
        listView =view.findViewById(R.id.my_list2);

        simpleAdapter = new SimpleAdapter(getActivity(),getData(),R.layout.my_menu,new String[]{"title"},new int[]{R.id.myMenu_name});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);
        return view;
    }

    private List<Map<String,Object>> getData() {
        String [] titles={"个人信息","修改信息","查询订单","设置地址","5","6","7"};

        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<7;i++){
            Map  map = new HashMap();
            map.put("title",titles[i]);
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
        if(position==0) {
            Intent intent = new Intent(getActivity(), Main2Activity.class);
            //启动
            startActivity(intent);

        }
        else if(position==1){

        }

    }

}
