package com.mysql1.person;

import android.content.Intent;
import android.util.Log;

import com.mysql1.mysqlconnectiontest.DBOpenHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private static Connection conn;
    private static PreparedStatement preStmt;
    private static ResultSet rs;

    public List<Item> loginList(String pangduang) throws SQLException {
         String sql2 = "select name,username,phone,vip,address from users where name = ?";
        List<Item> list = new ArrayList<>();
        conn = DBOpenHelper.getConn();
        Log.d("数据库连接返回值", "login: "+conn);
        preStmt = conn.prepareStatement(sql2);
        preStmt.setString(1,pangduang);
        rs = preStmt.executeQuery();
        while(rs.next()){
            Item item = new Item();
            item.setName1(rs.getString(1));
            item.setUsername(rs.getString(2));
            item.setDianhua(rs.getString(3));
            if(rs.getString(4).equals("0"))
            {
            item.setVip("普通用户");
            }
            else  item.setVip("尊敬的vip用户");
            item.setAddress(rs.getString(5));
            list.add(item);
        }
        DBOpenHelper.close(conn);
        return list;
    }
    public  void update(String name, String  newAge) {
        // 更新数据的 sql 语句
        String sql = "update users set vip = ? where name = ?";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值
            ps.setString(1, newAge);
            ps.setString(2, name);
            // 执行语句
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public  void update2(String username1,String phone,String address,String name) {
        // 更新数据的 sql 语句
        String sql = "update users set username = ?,phone= ?,address= ? where name = ?";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值
            ps.setString(1, username1);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4,name);


            // 执行语句
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
