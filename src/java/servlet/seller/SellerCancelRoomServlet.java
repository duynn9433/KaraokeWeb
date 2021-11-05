/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.seller;

import DAO.BookingDAO;
import DAO.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
@WebServlet(name = "SellerCancelRoomServlet", urlPatterns = {"/SellerCancelRoomServlet"})
public class SellerCancelRoomServlet extends HttpServlet {

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
        System.out.println("SellerCancelRoom" + user.toString());

        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        BookingDAO bookingDAO = new BookingDAO();
        List<Booking> listBooking = null;

        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("searchRoom")) {
            name = request.getParameter("name");
            phoneNumber = request.getParameter("phoneNumber");
            request.setAttribute("name", name);
            request.setAttribute("phoneNumber", phoneNumber);

            try {
                listBooking = bookingDAO.searchBookingByClient(name, phoneNumber);
                for (Booking b : listBooking) {
                    System.out.println(b.toString());
                }
                //loai bo cac booking da checkin
                for (int i = 0; i < listBooking.size(); i++) {
                    Booking b = listBooking.get(i);
                    List<BookedRoom> listBr = b.getListBookedRoom();
                    for (int j = 0; j < listBr.size(); j++) {
                        BookedRoom br = listBr.get(j);
                        if (br.getCheckin().equals(true)) {
                            listBr.remove(j);
                        }
                    }
                    if (listBr.isEmpty()) {
                        listBooking.remove(i);
                    }
                }
                for (Booking b : listBooking) {
                    System.out.println(b.toString());
                }
            } catch (SQLException ex) {
                //bat exception
                Logger.getLogger(SellerCancelRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.removeAttribute("listBooking");
            session.setAttribute("listBooking", listBooking);

            url = "/seller/SellerCancelRoomView.jsp";

        } else if (action.equals("deleteRoom")) {
            listBooking = (List<Booking>) session.getAttribute("listBooking");
            for (Booking b : listBooking) {
                System.out.println(b.toString());
            }
            //session.removeAttribute("listBooking");

            String[] indexs = request.getParameterValues("selectedItems");
            for (String i : indexs) {
                System.out.println(i);
            }
            List<Integer> listDeleteBookedRoomID = new ArrayList<>();
            for (String i : indexs) {
                listDeleteBookedRoomID.add(Integer.parseInt(i));
            }
            List<BookedRoom> listDeleteBookedRoom = new ArrayList<>();
            for (Booking b : listBooking) {
                List<BookedRoom> listBr = b.getListBookedRoom();

                for (int indexBr = 0; indexBr < listBr.size(); indexBr++) {
                    BookedRoom br = b.getListBookedRoom().get(indexBr);
                    for (int i = 0; i < listDeleteBookedRoomID.size(); i++) {
                        if (listDeleteBookedRoomID.get(i) == br.getID()) {
                            listDeleteBookedRoom.add(br);
                        }
                    }
                }

            }

            session.setAttribute("listDeleteBookedRoom", listDeleteBookedRoom);
//            try {
//                bookingDAO.deleteBooking(listBooking, listDeleteBookedRoom);
//            } catch (SQLException ex) {
//                //
//                Logger.getLogger(SellerCancelRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }

            url = "/seller/SellerConfirmCancelView.jsp";
        }

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
