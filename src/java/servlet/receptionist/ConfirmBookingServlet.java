/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.receptionist;

import servlet.seller.*;
import DAO.BookingDAO;
import DAO.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import javax.swing.JOptionPane;
import model.Booking;
import model.Client;
import model.User;

/**
 *
 * @author duynn
 */
@WebServlet(name = "ConfirmBookingServlet", urlPatterns = {"/ConfirmBookingServlet"})
public class ConfirmBookingServlet extends HttpServlet {

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
        System.out.println("ConfirmBooking"+user.toString());
        Booking booking = (Booking) session.getAttribute("booking");
        
        BookingDAO bookingDAO = new BookingDAO();
        String msg = null;
        
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("Xacnhan")) {
            String note = request.getParameter("note");
            booking.setBookDate(LocalDateTime.now());
            booking.setUser(user);
            booking.setNote(note);
            booking.setSaleOff(Float.parseFloat(request.getParameter("saleOff")));
            try {
                bookingDAO.addBooking(booking);
                msg="Luu thanh cong";
                request.getSession().removeAttribute("booking");
            } catch (SQLException ex) {
                msg="Luu that bai";
                Logger.getLogger(ConfirmBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println(msg);
            url="/receptionist/ConfirmBooking.jsp";
        }
        request.getSession().removeAttribute("confirmBookingMsg");
        request.getSession().setAttribute("confirmBookingMsg", msg);
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
