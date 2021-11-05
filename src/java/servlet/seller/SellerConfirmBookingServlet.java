/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.seller;

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
@WebServlet(name = "SellerConfirmBookingServlet", urlPatterns = {"/SellerConfirmBookingServlet"})
public class SellerConfirmBookingServlet extends HttpServlet {

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
        String url = "/seller/SellerBookRoom.jsp";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("------------");
        System.out.println("SellerConfirmBooking"+user.toString());
        Booking booking = (Booking) session.getAttribute("booking");
        
        BookingDAO bookingDAO = new BookingDAO();
        String msg = null;
        
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("Xac nhan")) {
            String note = request.getParameter("note");
            booking.setBookDate(LocalDateTime.now());
            booking.setUser(user);
            booking.setNote(note);
            try {
                bookingDAO.addBooking(booking);
                msg="Luu thanh cong";
            } catch (SQLException ex) {
                msg="Luu that bai";
                Logger.getLogger(SellerConfirmBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println(msg);
            url="/seller/SellerConfirmView.jsp";
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
