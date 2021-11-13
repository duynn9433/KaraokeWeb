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
public class BillDAO extends DAO {

    public BillDAO() {
        super();
    }

    /**
     * @author nguyen ngoc duy
     */
    public List<Bill> getBill(IncomeStat incomeStat) throws SQLException {
        List<Bill> res = new ArrayList<>();
        String sqlBill="{call getBillByMonth(?,?)}";
        String sqlBooking = "select bo.* from tblbill b, tblbooking bo where bo.id = b.tblbookingID and b.id= ?";
        String sqlUser = "{call getUserByBooking(?)}";
        String sqlClient = "{call getClientByBooking(?)}";
        String sqlBookedRoom = "{call getBookedRoomByBooking(?)}";
        String sqlRoom = "{call getRoomByBookedRoom(?)}";
        String sqlUsedService = "{call getUsedServiceByBookedRoom(?)}";
        String sqlService = "{call getServiceByUsedService(?)}";
        String sqlBookedStaff = "{call getBookedStaffByBookedRoom(?)}";
        String sqlStaff = "{call getUserByBookedStaff(?)}";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] data = incomeStat.getThang().split("/");
        PreparedStatement ps = con.prepareStatement(sqlBill);
        ps.setInt(1, Integer.parseInt(data[0]));
        ps.setInt(2, Integer.parseInt(data[1]));
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Bill bill = new Bill();
            bill.setID(rs.getInt("ID"));
            bill.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime().toLocalDate());
            //phai tinh bill.setPaymentAmount(rs.getFloat("paymentAmount"));
            bill.setPaymentType(rs.getString("paymentType"));
            bill.setNote(rs.getString("note"));
            
            //set booking
            PreparedStatement ps0 = con.prepareStatement(sqlBooking);
            ps0.setInt(1, bill.getID());

            ResultSet rs0 = ps0.executeQuery();
            rs0.next();
            Booking b = new Booking();
            b.setID(rs0.getInt("ID"));
            b.setBookDate(rs0.getTimestamp("bookDate").toLocalDateTime());
            b.setSaleOff(rs0.getFloat("saleOff"));
            b.setNote(rs0.getString("note"));

            User u = new User();
            PreparedStatement ps1 = con.prepareStatement(sqlUser);
            ps1.setInt(1, rs0.getInt("tbluserID"));
            ResultSet rs1 = ps1.executeQuery();
            rs1.next();
            u.setID(rs1.getInt("ID"));
            u.setUsername(rs1.getString("username"));
            u.setPassword(rs1.getString("password"));
            u.setPosition(rs1.getString("position"));
            u.setName(rs1.getString("name"));
            u.setPhoneNumber(rs1.getString("phoneNumber"));
            b.setUser(u);

            Client c = new Client();
            PreparedStatement ps2 = con.prepareStatement(sqlClient);
            ps2.setInt(1, rs0.getInt("tblclientID"));
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            c.setID(rs2.getInt("ID"));
            c.setName(rs2.getString("name"));
            c.setPhoneNumber(rs2.getString("phoneNumber"));
            c.setAddress(rs2.getString("address"));
            c.setMail(rs2.getString("mail"));
            c.setNote(rs2.getString("note"));
            b.setClient(c);

            //listBookedRoom
            List<BookedRoom> listBookedRoom = new ArrayList<>();
            PreparedStatement ps3 = con.prepareStatement(sqlBookedRoom);
            ps3.setInt(1, b.getID());
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                BookedRoom br = new BookedRoom();
                br.setID(rs3.getInt("ID"));
                br.setCheckin(rs3.getTimestamp("checkin").toLocalDateTime());
                br.setCheckout(rs3.getTimestamp("checkout").toLocalDateTime());
                br.setPricePerHour(rs3.getFloat("pricePerHour"));
                //br.setAmount(rs3.getFloat("amount"));
                br.setTotalPrice(br.getPricePerHour() * br.getAmount());
                br.setNote(rs3.getString("note"));

                //room
                Room room = new Room();
                PreparedStatement ps4 = con.prepareStatement(sqlRoom);
                ps4.setInt(1, br.getID());
                ResultSet rs4 = ps4.executeQuery();
                rs4.next();
                room.setID(rs4.getInt("id"));
                room.setName(rs4.getString("name"));
                room.setSize(rs4.getString("size"));
                room.setType(rs4.getString("type"));
                room.setPricePerHour(rs4.getFloat("pricePerHour"));
                room.setDescription(rs4.getString("description"));
                br.setRoom(room);

                //listUsedService
                ArrayList<UsedService> listUsedService = new ArrayList<>();
                PreparedStatement ps5 = con.prepareStatement(sqlUsedService);
                ps5.setInt(1, br.getID());
                ResultSet rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    UsedService usedService = new UsedService();
                    usedService.setID(rs5.getInt("id"));
                    usedService.setAmount(rs5.getInt("amount"));
                    usedService.setPricePerUnit(rs5.getFloat("pricePerUnit"));
                    usedService.setNote(rs5.getString("note"));
                    usedService.setTotalPrice(usedService.getAmount() * usedService.getPricePerUnit());

                    br.setTotalPrice(br.getTotalPrice() + usedService.getTotalPrice());

                    //add service
                    PreparedStatement ps6 = con.prepareStatement(sqlService);
                    ps6.setInt(1, usedService.getID());
                    ResultSet rs6 = ps6.executeQuery();
                    rs6.next();
                    Service service = new Service();
                    service.setID(rs6.getInt("id"));
                    service.setName(rs6.getString("name"));
                    service.setUnity(rs6.getString("unity"));
                    service.setPricePerUnit(rs6.getFloat("pricePerUnit"));
                    service.setDescription(rs6.getString("description"));

                    usedService.setService(service);
                    listUsedService.add(usedService);
                }
                br.setListUsedService(listUsedService);

                //listBookedStaff
                List<BookedStaff> listBookedStaff = new ArrayList<>();
                PreparedStatement ps7 = con.prepareStatement(sqlBookedStaff);
                ps7.setInt(1, br.getID());
                ResultSet rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    BookedStaff bookedStaff = new BookedStaff();
                    bookedStaff.setID(rs7.getInt("id"));
                    bookedStaff.setRating(rs7.getInt("rating"));

                    //add user
                    User userStaff = new User();
                    PreparedStatement ps8 = con.prepareStatement(sqlStaff);
                    ps8.setInt(1, bookedStaff.getID());
                    ResultSet rs8 = ps8.executeQuery();
                    rs8.next();
                    userStaff.setID(rs8.getInt("id"));
                    userStaff.setName(rs8.getString("name"));
                    userStaff.setUsername(rs8.getString("username"));
                    userStaff.setPassword(rs8.getString("password"));
                    userStaff.setPosition(rs8.getString("position"));
                    userStaff.setPhoneNumber(rs8.getString("phoneNumber"));
                    bookedStaff.setUser(userStaff);

                    listBookedStaff.add(bookedStaff);
                }
                br.setListHiredStaff(listBookedStaff);

//                    br.setTotalPrice(br.getTotalPrice() + 
//                            (br.getCheckout().minus(br.getCheckin()))/60
//                                    *br.getPricePerHour());                    
                listBookedRoom.add(br);
            }
            b.setListBookedRoom(listBookedRoom);
            bill.setBooking(b);
            res.add(bill);
        }

        return res;
    }
    public static void main(String[] args) {
        IncomeStat is = new IncomeStat(1200, "12/2020");
        BillDAO bd = new BillDAO();
        List<Bill> l;
        try {
            l = bd.getBill(is);
            for(Bill b: l) System.out.println(b.toString());
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
