/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

import DAO.BookingDAO;
import DAO.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookedRoom;
import model.BookedStaff;
import model.Booking;
import model.Client;
import model.User;

/**
 *
 * @author xxxx9
 */
@WebServlet(name = "CheckinServlet", urlPatterns = {"/CheckinServlet"})
public class CheckinServlet extends HttpServlet {
    
    private static String SESSION_BOOKINGS = "checkinServlet_sessionBookings";
    private static String SESSION_CLIENT = "sessionClient";
    private static String REQUEST_BOOKINGS = "requestBookings";
    
    private static String SELECTING_STAFF_BOOKING = "SELECTING_STAFF_BOOKING";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        System.out.println("Checkin");
        
        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false);
        String url = "/receptionist/Checkin.jsp";
        String actionAttr = (String) session.getAttribute("action");

        try {
            if (actionAttr != null) {
                if (actionAttr.equals("ADD_BOOKING")) {
                    Booking b = (Booking) session.getAttribute("booking");

                    List<Booking> bookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);

                    if (bookings == null) {
                        bookings = new ArrayList<Booking>();
                    }

                    bookings.add(b);
                    
                    Client client  = (Client) session.getAttribute(SESSION_CLIENT);
                    
                    b.setClient(client);
                    

                    request.setAttribute(REQUEST_BOOKINGS, bookings);
                    session.setAttribute(SESSION_BOOKINGS, bookings);
                    url = "/receptionist/Checkin.jsp";
                }
                else if (actionAttr.equals("RETURN_STAFF"))
                {
                    Booking booking = (Booking) request.getAttribute(SelectStaffServlet.RETURN_BOOKING);
                    int i = (int) session.getAttribute(SELECTING_STAFF_BOOKING);
                          
                    List<Booking> sessionBookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);
                    sessionBookings.set(i, booking);
                    
                    request.setAttribute(REQUEST_BOOKINGS, sessionBookings);
                }

                session.removeAttribute("action");
            } else {
                if (request.getParameter("CREATE_BOOKING") != null) {
                    url = "/receptionist/CreateBooking.jsp";
                } else if (request.getParameter("SELECT_STAFF") != null) {
                    int selectedBookingIndex = Integer.parseInt(request.getParameterValues("selectedBooking")[0]);
                    List<Booking> bookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);
                    request.setAttribute(SelectStaffServlet.REQUEST_BOOKING, bookings.get(selectedBookingIndex));
                    
                    session.setAttribute(SELECTING_STAFF_BOOKING, selectedBookingIndex);
                    
                    url = "/SelectStaffServlet";
                } else if (request.getParameter("CHECKIN") != null) {
                    int selectedBookingIndex = Integer.parseInt(request.getParameterValues("selectedBooking")[0]);
                    List<Booking> bookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);
                    Booking booking = bookings.get(selectedBookingIndex);
                    
                    booking.setIsCheckin(true);
                    BookingDAO dao = new BookingDAO();
                    
                    if(booking.getID() == -1)
                    {
                        //Save new booking
                        dao.addBooking(booking);
                    }
                    else 
                    {
                        //booking crteate, need update
                        dao.checkinBooking(booking);
                    }
                    
                    
                } else if (request.getParameter("SEARCH_CUSTOMER") != null) {
                    BookingDAO dao = new BookingDAO();

                    String cusName = request.getParameter("customer_name");
                    String cusPhone = request.getParameter("customer_phone");

                    ClientDAO clientDAO = new ClientDAO();

                    Client client = clientDAO.searchClient(cusName, cusPhone).get(0);

                    List<Booking> bookings = dao.searchBookingByClient(cusName, cusPhone);

                    request.setAttribute(REQUEST_BOOKINGS, bookings);
                    session.setAttribute(SESSION_BOOKINGS, bookings);
                    session.setAttribute(SESSION_CLIENT, client);
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
