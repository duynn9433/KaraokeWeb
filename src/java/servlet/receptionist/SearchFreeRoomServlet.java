/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.receptionist;

import servlet.seller.*;
import DAO.RoomDAO;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
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

/**
 *
 * @author duynn
 */
@WebServlet(name = "SearchFreeRoomServlet", urlPatterns = {"/SearchFreeRoomServlet"})
public class SearchFreeRoomServlet extends HttpServlet {

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
        String url = "/receptionist/SearchFreeRoom.jsp";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("------------");
        System.out.println(user.toString());
        LocalDateTime checkin = null;
        LocalDateTime checkout = null;
        List<Room> listRoom = null;
        RoomDAO roomDAO = new RoomDAO();

        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("searchFreeRoom")) {
            String checkinString = (String) request.getParameter("checkin");
            String checkoutString = (String) request.getParameter("checkout");
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
                Logger.getLogger(SearchFreeRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("bookRoom")) {
            //lay thu tu phong da chon
            String[] indexString = request.getParameterValues("selectedItems");
            for (String i : indexString) {
                System.out.println(i);
            }
            try {
                String checkinString = (String) request.getParameter("checkin");
                String checkoutString = (String) request.getParameter("checkout");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                checkin = LocalDateTime.parse(checkinString, dtf);
                checkout = LocalDateTime.parse(checkoutString, dtf);
                System.out.println("bookroomdate:" + checkin + " " + checkout);
                listRoom = roomDAO.searchFreeRoom(checkin, checkout);
                for (Room r : listRoom) {
                    System.out.println(r.toString());
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchFreeRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //tao booking
            Booking b = new Booking();
            List<BookedRoom> listBookedRoom = new ArrayList<>();
            for (int i = 0; i < indexString.length; i++) {
                int index = Integer.parseInt(indexString[i]);
                System.out.println(index);
                System.out.println(listRoom.get(index).toString());
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
            request.getSession().setAttribute("booking", b);
            request.setAttribute("bookingID", "booking");
            for(BookedRoom i : b.getListBookedRoom()){
                System.out.println(i.getRoom().getID());
            }
            
            url = "/receptionist/SearchClient.jsp";
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
