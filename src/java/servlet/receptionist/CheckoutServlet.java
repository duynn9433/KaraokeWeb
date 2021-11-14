/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

import DAO.BookingDAO;
import DAO.ClientDAO;
import DAO.ServiceDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Booking;
import model.Client;

/**
 *
 * @author xxxx9
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    private static String SESSION_BOOKINGS = "checkoutServlet_sessionBookings";
    private static String SESSION_CLIENT = "sessionClient";
    private static String REQUEST_BOOKINGS = "listBookings";

    private static String SELECTING_BOOKING = "SELECTING_BOOKING";

    private static String JSP_URL = "/receptionist/Checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false);
        String url = JSP_URL;
        String actionAttr = (String) session.getAttribute("action");

        try {
            if (actionAttr != null) {
                if (actionAttr.equals("RETURN_SERVICES")) {

                    List<Booking> bookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);
                    int seletingBooking = (int) session.getAttribute(SELECTING_BOOKING);

                    Booking booking = (Booking) request.getAttribute(AddServicesServlet.RETURN_BOOKING);

                    bookings.set(seletingBooking, booking);

                    request.setAttribute(REQUEST_BOOKINGS, bookings);
                }

                session.removeAttribute("action");
            } else {
                if (request.getParameter("ADD_SERVICES") != null) {
                    int selectedBookingIndex = Integer.parseInt(request.getParameterValues("selectedBooking")[0]);
                    List<Booking> bookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);
                    request.setAttribute(AddServicesServlet.REQUEST_BOOKING, bookings.get(selectedBookingIndex));

                    session.setAttribute(SELECTING_BOOKING, selectedBookingIndex);

                    url = "/AddServicesServlet";
                } else if (request.getParameter("SEARCH_CUSTOMER") != null) {

                    BookingDAO dao = new BookingDAO();

                    String cusName = request.getParameter("customer_name");
                    String cusPhone = request.getParameter("customer_phone");

                    ClientDAO clientDAO = new ClientDAO();

                    Client client = clientDAO.searchClient(cusName, cusPhone).get(0);

                    List<Booking> bookings = dao.getUncheckoutBooking(cusName, cusPhone);

                    request.setAttribute(REQUEST_BOOKINGS, bookings);
                    session.setAttribute(SESSION_BOOKINGS, bookings);
                    session.setAttribute(SESSION_CLIENT, client);

                    url = JSP_URL;
                } else if (request.getParameter("PAYMENT") != null) {
                    ServiceDao dao = new ServiceDao();
                    
                    int selectedBookingIndex = Integer.parseInt(request.getParameterValues("selectedBooking")[0]);
                    List<Booking> bookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);
                    Booking booking = bookings.get(0);
                    
                    dao.addUsedService(booking);
                    
                    request.setAttribute(PaymentServlet.REQUEST_BOOKING, booking);
                    
                    url="/PaymentServlet";
                }
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
