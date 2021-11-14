/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Date;
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
public class BookingDAO extends DAO {

    public BookingDAO() {
        super();
    }

    /**
     * @author nguyen ngoc duy
     *
     * <p>
     * tra ve cac Booking theo ngay checkin checkout</p>
     * <p>
     * startDate &le; checkin &le; checkout &le; endDate</p>
     *
     * @return danh sach Booking theo ngay
     */
    public List<Booking> getBookingByDate(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<Booking> res = new ArrayList<>();
        String sqlBooking = "{call getBookingByDate(?,?)}";
        String sqlUser = "{call getUserByBooking(?)}";
        String sqlClient = "{call getClientByBooking(?)}";
        String sqlBookedRoom = "{call getBookedRoomByBooking(?)}";
        String sqlRoom = "{call getRoomByBookedRoom(?)}";
        String sqlUsedService = "{call getUsedServiceByBookedRoom(?)}";
        String sqlService = "{call getServiceByUsedService(?)}";
        String sqlBookedStaff = "{call getBookedStaffByBookedRoom(?)}";
        String sqlStaff = "{call getUserByBookedStaff(?)}";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps0 = con.prepareStatement(sqlBooking);
        ps0.setString(1, dtf.format(startDate));
        ps0.setString(2, dtf.format(endDate));

        ResultSet rs0 = ps0.executeQuery();
        while (rs0.next()) {
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
            u.setPosition(rs1.getString("possition"));
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
                br.setAmount(rs3.getFloat("amount"));
                br.setTotalPrice(br.getPricePerHour() * br.getAmount());
                br.setNote(rs3.getString("note"));

                //room
                Room room = new Room();
                PreparedStatement ps4 = con.prepareStatement(sqlRoom);
                ps4.setInt(1, br.getID());
                ResultSet rs4 = ps4.executeQuery();
                rs4.next();
                room.setID(rs4.getInt("id"));
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

        }

        return res;
    }

    /**
     * @author nguyen ngoc duy
     */
    public void addBooking(Booking booking) throws SQLException {
        //set id

        con.setAutoCommit(false);

        int bookingMaxID;
        int bookedMaxID;
        PreparedStatement psinit = null;
        ResultSet rsinit = null;

        psinit = con.prepareStatement("select max(b.id) as max from tblbooking b");
        rsinit = psinit.executeQuery();
        rsinit.next();
        bookingMaxID = rsinit.getInt("max");
        psinit = con.prepareStatement("select max(b.id) as max from tblbookedroom b");
        rsinit = psinit.executeQuery();
        rsinit.next();
        bookedMaxID = rsinit.getInt("max");

        booking.setID(++bookingMaxID);
        for (BookedRoom br : booking.getListBookedRoom()) {
            br.setID(++bookedMaxID);
        }

        psinit.close();
        rsinit.close();

        //add booking
        String sqlBooking = "{call insertBooking(?,?,?,?,?,?,?,?)}";
        String sqlBookedRoom = "{call insertBookedRoom(?,?,?,?,?,?,?)}";
        String sqlBookedStaff = "{call insertBookedStaff(?,?,?)}";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps = con.prepareStatement(sqlBooking);
        ps.setInt(1, booking.getID());
        ps.setString(2, dtf.format(booking.getBookDate()));
        ps.setFloat(3, booking.getSaleOff());
        ps.setString(4, booking.getNote());
        ps.setBoolean(5, booking.isIsCheckin());
        ps.setBoolean(6, booking.isIsCheckout());
        ps.setInt(7, booking.getClient().getID());
        ps.setInt(8, booking.getUser().getID());

        ps.executeUpdate();

        for (BookedRoom i : booking.getListBookedRoom()) {
            PreparedStatement ps1 = con.prepareStatement(sqlBookedRoom);
            ps1.setInt(1, i.getID());
            ps1.setString(2, dtf.format(i.getCheckin()));
            ps1.setString(3, dtf.format(i.getCheckout()));
            ps1.setFloat(4, i.getPricePerHour());
            ps1.setString(5, i.getNote());
            ps1.setInt(6, booking.getID());
            ps1.setInt(7, i.getRoom().getID());
            ps1.executeUpdate();

            if (i.getListHiredStaff() != null) {
                for (BookedStaff staff : i.getListHiredStaff()) {
                    PreparedStatement ps2 = con.prepareStatement(sqlBookedStaff);
                    ps2.setInt(1, staff.getRating());
                    ps2.setInt(2, staff.getUser().getID());
                    ps2.setInt(3, i.getID());
                    ps2.executeUpdate();
                }
            }

        }

        con.commit();
        con.setAutoCommit(true);

    }

    public void checkinBooking(Booking booking) throws SQLException {
        con.setAutoCommit(false);

        String sqlCheckinBooking = "UPDATE tblbooking set isCheckin=? WHERE ID=?";
        String sqlInsertStaff = "INSERT INTO tblbookedstaff (rating, tbluserID, tblbookedroomid) VALUES (?,?,?)";

        PreparedStatement ps = con.prepareStatement(sqlCheckinBooking);
        ps.setBoolean(1, true);
        ps.setInt(2, booking.getID());
        ps.executeUpdate();

        PreparedStatement ps1 = con.prepareStatement(sqlInsertStaff);

        for (BookedRoom br : booking.getListBookedRoom()) {
            for (BookedStaff staff : br.getListHiredStaff()) {
                ps1.setInt(1, staff.getRating());
                ps1.setInt(2, staff.getUser().getID());
                ps1.setInt(3, br.getID());

                ps1.executeUpdate();
            }
        }

        con.commit();
        con.setAutoCommit(true);
    }

    /**
     * @author nguyen ngoc duy
     */
    public List<Booking> searchBookingByClient(String clientName, String clientPhoneNumber) throws SQLException {
        List<Booking> res = new ArrayList<>();
        String sqlBooking = "{call getBookingByClient(?,?)}";
        String sqlUser = "{call getUserByBooking(?)}";
        String sqlClient = "{call getClientByBooking(?)}";
        String sqlBookedRoom = "{call getBookedRoomByBooking(?)}";
        String sqlRoom = "{call getRoomByBookedRoom(?)}";
        String sqlBookedStaff = "{call getStaffByBookedRoom(?)}";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps0 = con.prepareStatement(sqlBooking);
        ps0.setString(1, clientName);
        ps0.setString(2, clientPhoneNumber);

        ResultSet rs0 = ps0.executeQuery();
        while (rs0.next()) {
            Booking b = new Booking();
            b.setID(rs0.getInt("ID"));
            b.setBookDate(rs0.getTimestamp("bookDate").toLocalDateTime());
            b.setSaleOff(rs0.getFloat("saleOff"));
            b.setNote(rs0.getString("note"));
            b.setIsCheckin(rs0.getBoolean("isCheckin"));
            b.setIsCheckout(rs0.getBoolean("isCheckout"));

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

                //Booked staff
                List<BookedStaff> staffs = new ArrayList<>();
                PreparedStatement ps5 = con.prepareStatement(sqlBookedStaff);
                ps5.setInt(1, br.getID());
                ResultSet rs5 = ps5.executeQuery();

                while (rs5.next()) {
                    BookedStaff bookedStaff = new BookedStaff();
                    bookedStaff.setID(rs5.getInt("ID"));
                    bookedStaff.setRating(rs5.getInt("rating"));

                    int staffId = rs5.getInt("tbluserID");

                    User staff = new User();
                    PreparedStatement ps6 = con.prepareStatement("SELECT * FROM tbluser WHERE tbluser.ID = ?");
                    ps6.setInt(1, staffId);
                    ResultSet rs6 = ps6.executeQuery();
                    rs6.next();
                    staff.setID(rs6.getInt("ID"));
                    staff.setUsername(rs6.getString("username"));
                    staff.setPassword(rs6.getString("password"));
                    staff.setPosition(rs6.getString("position"));
                    staff.setName(rs6.getString("name"));
                    staff.setPhoneNumber(rs6.getString("phoneNumber"));

                    bookedStaff.setUser(staff);

                    staffs.add(bookedStaff);
                }
                br.setListHiredStaff(staffs);

                listBookedRoom.add(br);
            }
            b.setListBookedRoom(listBookedRoom);

            //xoa booking => chua checkin va chua thanh toan thi moi hien thi
            if ((!b.isIsCheckin()) && (!b.isIsCheckout())) {
                res.add(b);
            }

        }

        return res;
    }

    public List<Booking> getUncheckoutBooking(String clientName, String clientPhoneNumber) throws SQLException {
        List<Booking> res = new ArrayList<>();
        String sqlBooking = "{call getBookingByClient(?,?)}";
        String sqlUser = "{call getUserByBooking(?)}";
        String sqlClient = "{call getClientByBooking(?)}";
        String sqlBookedRoom = "{call getBookedRoomByBooking(?)}";
        String sqlRoom = "{call getRoomByBookedRoom(?)}";
        String sqlBookedStaff = "{call getStaffByBookedRoom(?)}";
        String sqlUsedSerivces = "SELECT * FROM tblusedservice WHERE tblbookedroomID=?";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps0 = con.prepareStatement(sqlBooking);
        ps0.setString(1, clientName);
        ps0.setString(2, clientPhoneNumber);

        ResultSet rs0 = ps0.executeQuery();
        while (rs0.next()) {
            Booking b = new Booking();
            b.setID(rs0.getInt("ID"));
            b.setBookDate(rs0.getTimestamp("bookDate").toLocalDateTime());
            b.setSaleOff(rs0.getFloat("saleOff"));
            b.setNote(rs0.getString("note"));
            b.setIsCheckin(rs0.getBoolean("isCheckin"));
            b.setIsCheckout(rs0.getBoolean("isCheckout"));

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

                //Booked staff
                List<BookedStaff> staffs = new ArrayList<>();
                PreparedStatement ps5 = con.prepareStatement(sqlBookedStaff);
                ps5.setInt(1, br.getID());
                ResultSet rs5 = ps5.executeQuery();

                while (rs5.next()) {
                    BookedStaff bookedStaff = new BookedStaff();
                    bookedStaff.setID(rs5.getInt("ID"));
                    bookedStaff.setRating(rs5.getInt("rating"));

                    int staffId = rs5.getInt("tbluserID");

                    User staff = new User();
                    PreparedStatement ps6 = con.prepareStatement("SELECT * FROM tbluser WHERE tbluser.ID = ?");
                    ps6.setInt(1, staffId);
                    ResultSet rs6 = ps6.executeQuery();
                    rs6.next();
                    staff.setID(rs6.getInt("ID"));
                    staff.setUsername(rs6.getString("username"));
                    staff.setPassword(rs6.getString("password"));
                    staff.setPosition(rs6.getString("position"));
                    staff.setName(rs6.getString("name"));
                    staff.setPhoneNumber(rs6.getString("phoneNumber"));

                    bookedStaff.setUser(staff);

                    staffs.add(bookedStaff);
                }
                br.setListHiredStaff(staffs);

                //Used service
                List<UsedService> usedServices = new ArrayList<>();
                PreparedStatement ps6 = con.prepareStatement(sqlUsedSerivces);
                ps6.setInt(1, br.getID());
                ResultSet rs6 = ps6.executeQuery();

                while (rs6.next()) {
                    UsedService usedService = new UsedService();
                    usedService.setID(rs6.getInt("ID"));
                    usedService.setAmount(rs6.getInt("amount"));
                    usedService.setPricePerUnit(rs6.getFloat("pricePerUnit"));
                    usedService.setNote(rs6.getString("note"));

                    int serviceId = rs6.getInt("tblserviceID");

                    PreparedStatement ps7 = con.prepareStatement("SELECT * FROM tblservice WHERE ID=?");
                    ps7.setInt(1, serviceId);
                    ResultSet rs7 = ps7.executeQuery();
                    rs7.next();
                    
                    Service service = new Service();                   
                    service.setID(rs7.getInt("ID"));
                    service.setName(rs7.getString("name"));
                    service.setUnity(rs7.getString("unity"));
                    service.setPricePerUnit(rs7.getFloat("pricePerUnit"));
                    service.setDescription(rs7.getString("description"));
                    
                    usedService.setService(service);
                    
                    usedServices.add(usedService);
                }
                br.setListUsedService(usedServices);

                listBookedRoom.add(br);
            }
            b.setListBookedRoom(listBookedRoom);

            if ((b.isIsCheckin()) && (!b.isIsCheckout())) {
                res.add(b);
            }

        }

        return res;
    }

    /**
     * @author nguyen ngoc duy
     */
    public void deleteBooking(List<Booking> listBooking, List<BookedRoom> listDeleteBookedRoom) throws SQLException {
        String sqlBooking = "delete from tblbooking where id=?";
        String sqlBookedRoom = "delete from tblbookedroom where id=?";
        for (Booking b : listBooking) {
            List<BookedRoom> listBr = b.getListBookedRoom();

            for (int indexBr = 0; indexBr < listBr.size(); indexBr++) {
                BookedRoom br = b.getListBookedRoom().get(indexBr);
                for (int i = 0; i < listDeleteBookedRoom.size(); i++) {
                    if (listDeleteBookedRoom.get(i).getID() == br.getID()) {
                        PreparedStatement ps1 = con.prepareStatement(sqlBookedRoom);
                        ps1.setInt(1, br.getID());
                        ps1.executeUpdate();

                        listBr.remove(indexBr);
                        listDeleteBookedRoom.remove(i);
                        i--;
                        indexBr--;
                    }
                }
            }
            if (listBr.isEmpty()) {
                PreparedStatement ps = con.prepareStatement(sqlBooking);
                ps.setInt(1, b.getID());
                ps.executeUpdate();
            }
        }

    }
}
