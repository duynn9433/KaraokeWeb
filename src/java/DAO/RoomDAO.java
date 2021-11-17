/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author duynn
 */
public class RoomDAO extends DAO {

    public RoomDAO() {
        super();
    }

    /**
     * @author nguyen ngoc duy
     *
     * @return cac phong trong co the dat
     */
    public List<Room> searchFreeRoom(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<Room> res = new ArrayList<>();
        String sql = "{call getFreeRoom(?,?)}";

        PreparedStatement ps = con.prepareStatement(sql);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ps.setString(1, dtf.format(startDate));
        ps.setString(2, dtf.format(endDate));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getBoolean("isActive")) {
                Room r = new Room();
                r.setID(rs.getInt("ID"));
                r.setName(rs.getString("name"));
                r.setSize(rs.getString("size"));
                r.setType(rs.getString("type"));
                r.setPricePerHour(rs.getFloat("pricePerHour"));
                r.setDescription(rs.getString("description"));
                r.setIsActive(true);
                res.add(r);
            }
        }

        return res;
    }
    
    //by truong
    public List<Room> searchRoom(String name) throws SQLException{
        List<Room> dsRoom = new ArrayList<>();
        String sql= "select* from tblroom where id='%?%' and isActive='1';";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Room room = new Room();
            room.setID(rs.getInt("id"));
            room.setSize(rs.getString("size"));
            room.setType(rs.getString("type"));
            room.setPricePerHour(rs.getFloat("pricePerHour"));
            room.setDescription(rs.getString("description"));
            dsRoom.add(room);
        }
        return dsRoom;
    }
    public void addRoom(Room room) throws SQLException{
        String sql = "INSERT INTO tblroom (size, type, pricePerHour, tblkaraokebarID, isActive, name) "
                + "VALUES (?, ?, ?, '1001', '1', ?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,room.getSize());
        ps.setString(2,room.getType());
        ps.setString(3,String.valueOf(room.getPricePerHour()));
        ps.setString(4,room.getName());
        ps.executeQuery();
    }
    public void delRoom(Room room) throws SQLException{
        String sql = "update tblroom set isActive='0' where id=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, String.valueOf(room.getID()));
        ps.executeQuery();
    }
    public void editRoom(Room room) throws SQLException{
        String sql ="UPDATE tblroom SET name = ?,size=?,type=?,pricePerHour=?,"
                + "description=? WHERE (`ID` = ?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, room.getName());
        ps.setString(2, room.getSize());
        ps.setString(3, room.getType());
        ps.setString(4, String.valueOf(room.getPricePerHour()));
        ps.setString(5, room.getDescription());
        ps.executeQuery();
    }
}
