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
import model.*;
/**
 *
 * @author duynn
 */
public class BillDAO extends DAO{

    public BillDAO() {
        super();
    }
    
    public ArrayList<Bill> getBill(LocalDateTime startDay, LocalDateTime endDay){
//        System.out.println(startDay);
//        System.out.println(endDay);
        ArrayList<Bill> res = new ArrayList<>();
        //String sqlBill = "select b.* from tblbill b, tblbookedroom br where br.billID = b.id and br.checkin > ? and br.checkout < ? group by b.id";
        String sqlBill = "select b.* , min(br.checkin) as minin, "
                + "max(br.checkout) as maxout from tblbill b, tblbookedroom br \n" +
                            "where br.billID = b.id \n" +
                            "group by b.id\n" +
                            "having minin > ? and maxout < ? ";
        String sqlClient = "select c.* from tblclient c, tblbill b "
                + "where b.clientID = c.id and b.id = ?";
        String sqlUser = "select u.* from tbluser u, tblbill b "
                + "where b.userCreateBillID = u.id and b.id = ?";
        String sqlBookedRoom = "select br.*, "
                + "(timestampdiff(hour,br.checkin, br.checkout)) as amount "
                + "from tblbookedroom br , tblbill b "
                + "where b.id = br.billID and b.id = ?";
        String sqlRoom = "select r.* from tblroom r, tblbookedroom br "
                + "where r.id = br.roomID and br.id = ?";
        String sqlUsedService = "select us.* from tblusedservice us, tblbookedroom br "
                + "where us.bookedRoomID = br.id and br.id = ?";
        String sqlService = "select s.* from tblusedservice us, tblservice s "
                + "where us.serviceID = s.id and us.id = ?";
        String sqlHiredStaff = "select ht.* from tblhiredstaff ht, tblbookedroom br "
                + "where ht.bookedRoomID = br.id and br.id = ?";
        String sqlUserStaff = "select u.* from tblhiredstaff ht, tbluser u "
                + "where ht.userStaffID = u.id and ht.id = ?";
       
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement ps = con.prepareStatement(sqlBill);
            ps.setString(1, dtf.format(startDay));
            ps.setString(2, dtf.format(endDay));
            
            ResultSet rs = ps.executeQuery();
            
            //boolean checkBill;
            while(rs.next()){
                //checkBill = false;
                
                Bill bill = new Bill();
                bill.setID(rs.getInt("id"));
                bill.setPaymentDate(rs.getDate("paymentDate").toLocalDate());
                //bill.setPaymentAmount(rs.getFloat("paymentAmount"));
                bill.setPaymentType(rs.getString("paymentType"));
                bill.setNote(rs.getString("note"));
                //lay Client
                Client client = new Client();
                PreparedStatement ps1 = con.prepareStatement(sqlClient);
                ps1.setInt(1, bill.getID());
                ResultSet rs1 = ps1.executeQuery();
                rs1.next();
                
                client.setID(rs1.getInt("id"));
                client.setName(rs1.getString("name"));
                client.setAddress(rs1.getString("address"));
                client.setMail(rs1.getString("mail"));
                client.setPhoneNumber(rs1.getString("phoneNumber"));
                client.setNote(rs1.getString("note"));
                
                bill.setClient(client);
                //lay user
                User user = new User();
                PreparedStatement ps2 = con.prepareStatement(sqlUser);
                ps2.setInt(1, bill.getID());
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                
                user.setID(rs2.getInt("id"));
                user.setName(rs2.getString("name"));
                user.setUsername(rs2.getString("username"));
                user.setPassword(rs2.getString("password"));
                user.setPosition(rs2.getString("position"));
                user.setPhoneNumber(rs2.getString("phoneNumber"));
                bill.setUser(user);
                //lay listBookedRoom
                ArrayList<BookedRoom> listBookedRoom = new ArrayList<>();
                PreparedStatement ps3 = con.prepareStatement(sqlBookedRoom);
                ps3.setInt(1, bill.getID());
                ResultSet rs3 = ps3.executeQuery();
                
                while(rs3.next()){
                    BookedRoom bookedRoom = new BookedRoom();
                    bookedRoom.setID(rs3.getInt("id"));
                    bookedRoom.setCheckin(rs3.getTimestamp("checkin").toLocalDateTime());
                    bookedRoom.setCheckout(rs3.getTimestamp("checkout").toLocalDateTime());
                    bookedRoom.setPricePerHour(rs3.getFloat("pricePerHour"));
                    bookedRoom.setAmount(rs3.getFloat("amount"));
                    bookedRoom.setTotalPrice(bookedRoom.getPricePerHour() * bookedRoom.getAmount());
                    bookedRoom.setNote(rs3.getString("note"));
                    
                    //lay room
                    Room room = new Room();
                    PreparedStatement ps4 = con.prepareStatement(sqlRoom);
                    ps4.setInt(1, bookedRoom.getID());
                    ResultSet rs4 = ps4.executeQuery();
                    rs4.next();
                    
                    room.setID(rs4.getInt("id"));
                    room.setSize(rs4.getString("size"));
                    room.setType(rs4.getString("type"));
                    room.setPricePerHour(rs4.getFloat("pricePerHour"));
                    room.setDescription(rs4.getString("description"));
                    
                    bookedRoom.setRoom(room);
                    
                    //lay listUsedService
                    ArrayList<UsedService> listUsedService = new ArrayList<>();
                    PreparedStatement ps5 = con.prepareStatement(sqlUsedService);
                    ps5.setInt(1,bookedRoom.getID());
                    ResultSet rs5 = ps5.executeQuery();
                    
                    while(rs5.next()){
                        UsedService usedService = new UsedService();
                        usedService.setID(rs5.getInt("id"));
                        usedService.setAmount(rs5.getInt("amount"));
                        usedService.setPricePerUnit(rs5.getFloat("pricePerUnit"));
                        usedService.setNote(rs5.getString("note"));
                        usedService.setTotalPrice(usedService.getAmount() * usedService.getPricePerUnit());
                        
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
                    
                    bookedRoom.setListUsedService(listUsedService);
                    for(UsedService us : listUsedService){
                        //bill.setPaymentAmount(bill.getPaymentAmount() + us.getTotalPrice());
                        bookedRoom.setTotalPrice(bookedRoom.getTotalPrice() + us.getTotalPrice());
                    }
                    //lay listHiredStaff
                    
                    ArrayList<BookedStaff> listHiredStaff = new ArrayList<>();
                    PreparedStatement ps7 = con.prepareStatement(sqlHiredStaff);
                    ps7.setInt(1, bookedRoom.getID());
                    ResultSet rs7 = ps7.executeQuery();
                    
                    while(rs7.next()){
                        BookedStaff hireStaff = new BookedStaff();
                        hireStaff.setID(rs7.getInt("id"));
                        hireStaff.setRating(rs7.getInt("rating"));
                        
                        //add user
                        User userStaff = new User();
                        PreparedStatement ps8 = con.prepareStatement(sqlUserStaff);
                        ps8.setInt(1, hireStaff.getID());
                        ResultSet rs8 = ps8.executeQuery();
                        rs8.next();

                        userStaff.setID(rs8.getInt("id"));
                        userStaff.setName(rs8.getString("name"));
                        userStaff.setUsername(rs8.getString("username"));
                        userStaff.setPassword(rs8.getString("password"));
                        userStaff.setPosition(rs8.getString("position"));
                        userStaff.setPhoneNumber(rs8.getString("phoneNumber"));
                        hireStaff.setUser(userStaff);
                        
                        listHiredStaff.add(hireStaff);
                    }
                    
                    bookedRoom.setListHiredStaff(listHiredStaff);
                    
                    listBookedRoom.add(bookedRoom);
                    bill.setPaymentAmount(bill.getPaymentAmount() + bookedRoom.getTotalPrice());
                    
                }
                
                bill.setListBookedRoom(listBookedRoom);
                res.add(bill); 
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return res;
    }

    
}
