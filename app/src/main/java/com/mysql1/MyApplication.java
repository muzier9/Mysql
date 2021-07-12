package com.mysql1;

import android.app.Application;

import com.mysql1.mysql.User;

public class MyApplication extends Application {

    private Boolean isLogin;//是否登录
    private User user;//登录的用户类

    //单例application类
    private static MyApplication application;
    public static MyApplication getInstance(){
        if(null == application){
            application=new MyApplication();
        }
        return application;
    }


    public MyApplication(){
        isLogin=false;
    }

    public Boolean isLogin(){
        return isLogin;
    }

    public void login(User user){
        this.user=user;
        isLogin=true;
    }

    public void logout(){
        isLogin=false;
    }

    public User getUser(){
        return user;
    }


}
