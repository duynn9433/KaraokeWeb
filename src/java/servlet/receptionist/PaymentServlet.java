/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

import DAO.BillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bill;
import model.BookedRoom;
import model.Booking;
import model.UsedService;
import model.User;

/**
 *
 * @author xxxx9
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    public static String REQUEST_BOOKING = "payment_request_booking";
    public static String RETURN_BOOKING = "payment_return_booking";

    public static String SESSION_BOOKING = "payment_session_booking";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false);
        String url = "/receptionist/Payment.jsp";

        try {
            if (request.getParameter("DONE") != null) {
                Booking booking = (Booking) session.getAttribute(SESSION_BOOKING);

                Bill bill = new Bill();

                bill.setBooking(booking);
                bill.setUser((User) session.getAttribute("user"));
                String payMethod = (String) request.getParameter("paymentMethod");
                bill.setPaymentType(payMethod);
                bill.setPaymentDate(LocalDate.now());
                String note = (String) request.getParameter("note");
                bill.setNote(note);

                BillDAO dao = new BillDAO();

                dao.addBill(bill);
                url="/receptionist/ReceptionistHome.jsp";

            } else {
                Booking booking = (Booking) request.getAttribute(REQUEST_BOOKING);

                if (booking == null) {
                    return;
                }

                session.setAttribute(SESSION_BOOKING, booking);

                

                float totalMoney = 0f;

                for (BookedRoom bookedRoom : booking.getListBookedRoom()) {
                    long diff =  Duration.between(bookedRoom.getCheckin(), bookedRoom.getCheckout()).toHours();
                    totalMoney += bookedRoom.getPricePerHour() * diff;

                    for (UsedService us : bookedRoom.getListUsedService()) {
                        us.setTotalPrice(us.getPricePerUnit() * us.getAmount());
                        totalMoney += us.getTotalPrice();
                    }
                }
                
                request.setAttribute("booking", booking);
                request.setAttribute("totalMoney", totalMoney);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
