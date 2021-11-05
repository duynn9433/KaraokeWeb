/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.seller;

import DAO.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import model.User;

/**
 *
 * @author duynn
 */
@WebServlet(name = "SellerConfirmCancelServlet", urlPatterns = {"/SellerConfirmCancelServlet"})
public class SellerConfirmCancelServlet extends HttpServlet {

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
        System.out.println("SellerConfirmCancelRoom" + user.toString());

        List<BookedRoom> listBookedRoom = null;
        List<Booking> listBooking = null;
        BookingDAO bookingDAO = new BookingDAO();
        String msg = null;
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("xacNhan")) {
            listBookedRoom = (List<BookedRoom>) session.getAttribute("listDeleteBookedRoom");
            listBooking = (List<Booking>) session.getAttribute("listBooking");
            try {
                bookingDAO.deleteBooking(listBooking, listBookedRoom);
                msg = "Xoa thanh cong";
                session.removeAttribute("listBooking");
                session.removeAttribute("listDeleteBookedRoom");
                System.out.println("Xoa thanh cong");
            } catch (SQLException ex) {
                msg="Xoa khong thanh cong";
                Logger.getLogger(SellerConfirmCancelServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            url = "/seller/SellerConfirmCancelView.jsp";
        } else if (action.equals("huy")) {

        }
        
        session.setAttribute("sellerConfirmCancelMsg", msg);
        request.getRequestDispatcher(url).forward(request, response);
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
