/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.BookedStaff;
import model.Room;
import model.User;

/**
 *
 * @author xxxx9
 */
public class StaffDao extends DAO {

    public StaffDao() {
        super();
    }

    public List<User> getAllStaff() {
         List<User> res = new ArrayList<>();
          String sql = "{call getAllStaff()}";
        try {
           

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setID(rs.getInt("ID"));
                u.setIsActive(rs.getBoolean("isActive"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setPosition(rs.getString("position"));
                u.setUsername(rs.getString("username"));
                res.add(u);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
