package com.mysql1.person;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mysql1.R;
import java.util.List;

public class personAdapter extends ArrayAdapter {

    private  int resourceId;/*ListView子项布局ID*/

    public personAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        resourceId = resource;/*ListView子项布局ID*/
    }

    @Override
    public View getView(int positiion, View convertView, ViewGroup parent){/*positiion  相当于索引*/
        Item item = (Item) getItem(positiion);/*获取当前项的Item实例*/
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);/*为ListView 子项加载传入的布局*/
        //LayoutInflater的inflate()方法接收3个参数：需要实例化布局资源的id、ViewGroup类型视图组对象、false
        //false表示只让父布局中声明的layout属性生效，但不会为这个view添加父布局
        TextView name1 = (TextView)view.findViewById(R.id.name1);
        TextView username = (TextView)view.findViewById(R.id.username1);
        TextView dianhua = (TextView)view.findViewById(R.id.dianhua1);
        TextView vip = (TextView)view.findViewById(R.id.vip);
        TextView address = (TextView)view.findViewById(R.id.address);
        name1.setText("账号："+item.getName1());
        username.setText("用户名："+item.getUsername());
        dianhua.setText("电话："+item.getDianhua());
        vip.setText("用户状态："+item.getVip());
        address.setText("地址："+item.getAddress());
        return view;/*返回子项布局*/
    }
}
