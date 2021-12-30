/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

import DAO.BookingDAO;
import DAO.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookedRoom;
import model.Booking;
import model.Room;
import model.User;
import servlet.seller.SellerBookRoomServlet;

/**
 *
 * @author xxxx9
 */
@WebServlet(name = "CreateBookingServlet", urlPatterns = {"/CreateBookingServlet"})
public class CreateBookingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        ServletContext context = getServletContext();
        String url = "/receptionist/CreateBooking.jsp";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        LocalDateTime checkin = null;
        LocalDateTime checkout = null;
        List<Room> listRoom = null;
        RoomDAO roomDAO = new RoomDAO();
        
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if(action==null){
            request.setAttribute("checkinStr", "2021-12-30T20:00:00");
            request.setAttribute("checkoutStr", "2021-12-30T20:00:00");
        } else if (action.equals("searchFreeRoom")) {
            String checkinString = (String) request.getParameter("checkin");
            String checkoutString = (String) request.getParameter("checkout");
            
            request.setAttribute("checkinStr", checkinString);
            request.setAttribute("checkoutStr", checkoutString);
            
            checkinString = checkinString.replace('T', ' ');
            checkoutString = checkoutString.replace('T', ' ');
            
            request.setAttribute("checkin", checkinString);
            request.setAttribute("checkout", checkoutString);

            System.out.println(checkinString + " " + checkoutString);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            checkin = LocalDateTime.parse(checkinString, dtf);
            checkout = LocalDateTime.parse(checkoutString, dtf);

            try {
                listRoom = roomDAO.searchFreeRoom(checkin, checkout);
                request.setAttribute("listRoom", listRoom);
            } catch (SQLException ex) {
                Logger.getLogger(SellerBookRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("bookRoom")) {
            //lay thu tu phong da chon
            String[] indexString = request.getParameterValues("selectedItems");
            try {
                String checkinString = (String) request.getParameter("checkin");
                String checkoutString = (String) request.getParameter("checkout");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                checkin = LocalDateTime.parse(checkinString, dtf);
                checkout = LocalDateTime.parse(checkoutString, dtf);
                System.out.println("bookroomdate:" + checkin + " " + checkout);
                listRoom = roomDAO.searchFreeRoom(checkin, checkout);
            } catch (SQLException ex) {
                Logger.getLogger(SellerBookRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //tao booking
            Booking b = new Booking();
            List<BookedRoom> listBookedRoom = new ArrayList<>();
            for (int i = 0; i < indexString.length; i++) {
                int index = Integer.parseInt(indexString[i]);
                BookedRoom br = new BookedRoom();
                br.setCheckin(checkin);
                br.setCheckout(checkout);
                br.setRoom(listRoom.get(index));
                br.setPricePerHour(br.getRoom().getPricePerHour());
                br.setAmount(checkout.getHour() - checkin.getHour()); //****************************************
                br.setTotalPrice(br.getAmount() * br.getPricePerHour());
               

                //br.setID(i);
                listBookedRoom.add(br);
            }
            b.setListBookedRoom(listBookedRoom);
            b.setBookDate(LocalDateTime.now());
            b.setID(-1);
            b.setUser(user);
            
            request.changeSessionId();
            session.setAttribute("booking", b);
            session.setAttribute("action", "ADD_BOOKING");
 
            
            url = "/CheckinServlet";
         
        }

        //request.setAttribute("user", user);
        context.getRequestDispatcher(url).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
