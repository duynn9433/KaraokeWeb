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
//    /**
//     * @author nguyen ngoc duy
//     */
//    public List<Room> searchFreeRoom2(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
//        List<Room> res = new ArrayList<>();
//        List<Room> listAllRoom = getALlRoom();
//        List<Room> listBookingRoom = getRoomFromBooking(startDate, endDate);
//        for (Room r : listAllRoom) {
//            if (!listBookingRoom.contains(r)) {
//                res.add(r);
//            }
//        }
//        return res;
//    }
//
//    /**
//     * @author nguyen ngoc duy
//     *
//     * @return toan bo cac phong dang hoat dong
//     */
//    public List<Room> getALlRoom() {
//        List<Room> res = new ArrayList<>();
//        String sql = "{call getAllRoom()}";
//
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs;
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Room room = new Room();
//                room.setID(rs.getInt("id"));
//                room.setSize(rs.getString("size"));
//                room.setType(rs.getString("type"));
//                room.setPricePerHour(rs.getFloat("pricePerHour"));
//                room.setDescription(rs.getString("description"));
//                res.add(room);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return res;
//    }
//
//    /**
//     * @author nguyen ngoc duy
//     *
//     * @return cac phong dang duoc dat
//     */
//    public List<Room> getRoomFromBooking(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
//        List<Room> res = new ArrayList<>();
//        List<Booking> listBooking = new BookingDAO().getBookingByDate(startDate, endDate);
//        for (Booking b : listBooking) {
//            List<BookedRoom> listBookedRoom = b.getListBookedRoom();
//            for (BookedRoom br : listBookedRoom) {
//                res.add(br.getRoom());
//            }
//        }
//        return res;
//    }
}
