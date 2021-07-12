package com.mysql1.person;

public class Item {
    private String name;
    private String username;
    private String dianhua;
    private String vip;
    private String address;
    public void setName1(String name){
        this.name =name;
    }
    public void setUsername(String username){
        this.username =username;
    }
    public void setDianhua(String dianhua){
        this.dianhua =dianhua;
    }
    public void setVip(String vip){
        this.vip =vip;
    }
    public void setAddress(String address){
        this.address =address;
    }

    public String getName1(){
        return name;
    }
    public String getUsername(){
        return username;
    }
    public String getDianhua(){
         return dianhua;
    }
    public String getVip(){
       return vip;
    }
    public String getAddress(){
        return address;
    }


}

