package shop;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper_goods {
    private static Connection con=SQLConnect();
    private static PreparedStatement pst=null;
    private static ResultSet rs=null;
    private static Connection SQLConnect(){
        Connection c=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            String url="jdbc:mysql://localhost:3306/goods_info?autoReconnect=true&useSSL=false";
            String userName="root";//数据库用户名
            String passWord="1234";//数据库密码
            c= DriverManager.getConnection(url,userName,passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }
    public static PreparedStatement doUpdate(String sql,int id,String name,String price,int num){
        try{
            pst=con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,name);
            pst.setString(3,price);
            pst.setInt(4,num);
            int rst=pst.executeUpdate();
            //System.out.println(rst>0?"执行成功":"执行失败");
        } catch (Exception e){
            e.printStackTrace();
        }
        return pst;
    }
    public static ResultSet doSelect(String sql,int id){
        try{
            pst=con.prepareStatement(sql);
            pst.setInt(1,id);
            rs=pst.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public static void deleteGoodById(String sql,int id){
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            int rst=pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void UpdateName(String sql,String name){
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,name);
            int rst=pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void UpdatePrice(String sql,String price){
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,price);
            int rst=pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void UpdateNum(String sql,int num){
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,num);
            int rst=pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<Good> GetGoodsList(String sql){
        try {
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Good> list=new ArrayList<>();
        while(true){
            try {
                if (!rs.next()) break;
                Good good=new Good();
                good.setId(rs.getInt(1));
                good.setName(rs.getString(2));
                good.setPrice(new BigDecimal(rs.getString(3)));
                good.setNum(rs.getInt(4));
                list.add(good);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
