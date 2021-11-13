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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    static String SESSION_BOOKINGS = "sessionBookings";
    static String SESSION_CLIENT = "sessionClient";
    static String REQUEST_BOOKINGS = "requestBookings";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false);
        String url = "/receptionist/Checkin.jsp";
        String actionAttr = (String) session.getAttribute("action");

        try {
            if (actionAttr != null) {
              if (actionAttr.equals("ADD_BOOKING")) {
                    Booking b = (Booking) session.getAttribute("booking");
                    System.out.println("booked:" + b.getListBookedRoom().size());

                    List<Booking> bookings = (List<Booking>) session.getAttribute(SESSION_BOOKINGS);
                    
                    if(bookings == null) bookings = new ArrayList<Booking>();
                    
                    bookings.add(b);

                    request.setAttribute(REQUEST_BOOKINGS, bookings);
                    session.setAttribute(SESSION_BOOKINGS, bookings);
                    url = "/receptionist/Checkin.jsp";
                }
              
                session.removeAttribute("action");
            } else {
                if (request.getParameter("CREATE_BOOKING") != null) {
                    url = "/receptionist/SearchFreeRoom.jsp";
                } else if (request.getParameter("SELECT_STAFF") != null) {

                } else if (request.getParameter("CHECKIN") != null) {
                    String[] indexString = request.getParameterValues("selectedBooking");
                    System.out.println("test" + indexString[0]);
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

        System.out.println("Checkin foward: " + url);
        context.getRequestDispatcher(url).forward(request, response);

//        String url = "/receptionist/Checkin.jsp";
//
//        String actionAttr = (String) session.getAttribute("action");
//        String actionPara = (String) request.getParameter("action");
//        System.out.println("action attr" + actionAttr);
//        System.out.println("action param" + actionPara);
//
//        try {
//
//            //From other jsp
//            if (actionAttr != null) {
//                if (actionAttr.equals("ADD_BOOKING")) {
//                    Booking b = (Booking) session.getAttribute("booking");
//                    System.out.println("booked:" + b.getListBookedRoom().size());
//
//                    List<Booking> bookings = new ArrayList<Booking>();
//                    bookings.add(b);
//
//                    request.setAttribute("bookings", bookings);
//                    url = "/receptionist/Checkin.jsp";
//
//                    session.setAttribute("savedBookings", bookings);
//                    //session.removeAttribute(url);
//                } else if (actionAttr.equals("RETURN_STAFF")) {
//
//                    System.out.println("staff" + ((List) session.getAttribute("return_staff")).size());
//                    
//                    
//                    int bookedRoomIndex = Integer.parseInt((String) session.getAttribute("savedBookedRoomIndex"));
//                    
//                    List<BookedRoom> allBookedRooms = new ArrayList<>();
//                    
//                    List<Booking> bookings = (List<Booking>) session.getAttribute("savedBookings");
//                    
//                    for(Booking b : bookings)
//                        allBookedRooms.addAll(b.getListBookedRoom());
//                    
//                    List<BookedStaff> bookedStaffs = new ArrayList<>();
//                    
//                    List<User> staffs = (List) session.getAttribute("return_staff");
//                    for(User u: staffs)
//                    {
//                        BookedStaff bs = new BookedStaff();
//                        
//                        bs.setRating(0);
//                        bs.setUser(u);
//                        
//                        bookedStaffs.add(bs);
//                    }
//                        
//                    
//                    allBookedRooms.get(bookedRoomIndex).setListHiredStaff(bookedStaffs);
//                    
//                    request.setAttribute("bookings", bookings);
//
//                    session.removeAttribute("return_staff");
//                }
//
//                session.removeAttribute("action");
//
//            } //From params
//            else {
//                if (actionPara.equals("SEARCH_CUSTOMER")) {
//
//                    BookingDAO dao = new BookingDAO();
//                    
//                    String cusName = request.getParameter("customer_name");
//                    String cusPhone = request.getParameter("customer_phone");
//                    
//                    ClientDAO clientDAO = new ClientDAO();
//                    
//                    Client client = clientDAO.searchClient(cusName, cusPhone).get(0);
//                
//
//                    List<Booking> bookings = dao.searchBookingByClient(cusName, cusPhone);
//
//                    request.setAttribute("bookings", bookings);
//                    session.setAttribute("savedBookings", bookings);
//                    session.setAttribute("savedClient", client);
//                    
//
//                } else if (actionPara.equals("ADD_BOOKING")) {
//                    url = "/receptionist/SearchFreeRoom.jsp";
//                } else if (actionPara.equals("SELECT_STAFF")) {
//                    url = "/SelectStaffServlet";
//
//                    String[] indexString = request.getParameterValues("selectedBooked");
//                    System.out.println("test" + indexString[0]);
//         
//                    session.setAttribute("savedBookedRoomIndex", indexString[0]);
//                }
//                else if(actionPara.equals("SAVE_BOOKING"))
//                {
//                    Client client = (Client) session.getAttribute("savedClient");
//                    
//                     List<Booking> bookings = (List<Booking>) session.getAttribute("savedBookings");
//                     BookingDAO dao = new BookingDAO();
//                     
//                     User user = (User) session.getAttribute("user");
//                 
//                     bookings.get(0).setClient(client);
//                     bookings.get(0).setUser(user);
//                     
//                     
//                     for(BookedRoom bkr : bookings.get(0).getListBookedRoom())
//                         bkr.setIsCheckin(true);
//                     
//                     dao.addBooking(bookings.get(0));
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Checkin foward: " + url);
//        context.getRequestDispatcher(url).forward(request, response);
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
