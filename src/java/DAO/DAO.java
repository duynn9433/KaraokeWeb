package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DAO {
    protected static Connection con;
     
    public DAO(){
        if(con == null){
            String dbUrl = 
                "jdbc:mysql://localhost:3306/pttkht?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection (dbUrl, "root", "quanghuy572000");
                System.out.println(con);
            }catch(ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        DAO d = new DAO();
        System.out.println(d);
    }
}