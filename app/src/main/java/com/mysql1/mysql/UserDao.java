package com.mysql1.mysql;

import com.mysql1.mysqlconnectiontest.DBOpenHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {


    public boolean login(String name,String password,String vip){

        String sql = "select * from users where name = ? and password = ? and vip = ?";

        Connection con = DBOpenHelper.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,name);
            pst.setString(2,password);
            pst.setString(3,vip);
            if(pst.executeQuery().next()){

                return true;

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBOpenHelper.close(con);
        }

        return false;
    }


    public boolean register(User user){

        String sql = "insert into users(name,username,password,age,phone,vip) values (?,?,?,?,?,?)";

        Connection  con = DBOpenHelper.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,user.getName());
            pst.setString(2,user.getUsername());
            pst.setString(3,user.getPassword());
            pst.setInt(4,user.getAge());
            pst.setString(5,user.getPhone());
            pst.setString(6,"0");
            int value = pst.executeUpdate();

            if(value>0){
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBOpenHelper.close(con);
        }
        return false;
    }

    public User findUser(String name){

        String sql = "select * from users where name = ?";

        Connection  con = DBOpenHelper.getConn();
        User user = null;
        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,name);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){

                int id = rs.getInt(0);
                String namedb = rs.getString(1);
                String username = rs.getString(2);
                String passworddb  = rs.getString(3);
                int age = rs.getInt(4);
                String phone = rs.getString(5);
                user = new User(id,namedb,username,passworddb,age,phone);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBOpenHelper.close(con);
        }

        return user;
    }


}