package shop;

import java.sql.*;

public class DBHelper_user {
    private static Connection con=SQLConnect();
    private static PreparedStatement pst=null;
    private static ResultSet rs=null;
    private static Connection SQLConnect(){
        Connection c=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            String url="jdbc:mysql://localhost:3306/user_admin_info?autoReconnect=true&useSSL=false";
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
    public static PreparedStatement doUpdate(String sql,String username,String password){
        try{
            pst=con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            int rst=pst.executeUpdate();
            //System.out.println(rst>0?"执行成功":"执行失败");
        } catch (Exception e){
            e.printStackTrace();
        }
        return pst;
    }
    public static ResultSet doSelect(String sql,String username){
        try{
            pst=con.prepareStatement(sql);
            pst.setString(1,username);
            rs=pst.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
