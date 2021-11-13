/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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
import model.User;

/**
 *
 * @author xxxx9
 */
@WebServlet(name = "SelectStaffServlet", urlPatterns = {"/SelectStaffServlet"})
public class SelectStaffServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public static String REQUEST_BOOKING = "selectstaff_request_booking";
    public static String RETURN_BOOKING = "selectstaff_return_booking";
    
    
    static String SESSION_BOOKING = "selectstaff_session_booking";
    static String LIST_STAFF = "listStaffs";
    static String SESSION_STAFF = "sessionStaffs";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String url = "/receptionist/SelectStaff.jsp";

        if (request.getParameter("select_staff") != null) {
            String[] selectStaff = request.getParameterValues("selectedStaff");
            String[] selectBookedRoom = request.getParameterValues("selectedBooked");
            
            Booking booking = (Booking) session.getAttribute(SESSION_BOOKING);
            int selectedBookedRoomIndex = Integer.parseInt(selectBookedRoom[0]);
            BookedRoom br = booking.getListBookedRoom().get(selectedBookedRoomIndex);
            
            List<User> staffs = (List<User>) session.getAttribute(SESSION_STAFF);
            
            List<BookedStaff> brStaff = new ArrayList<>();
            
            List<Integer> removedStaff = new ArrayList<>();
            
            for (String i : selectStaff) {
                User selected = staffs.get(Integer.parseInt(i));
                
                BookedStaff bs = new BookedStaff();
                bs.setUser(selected);
                
                brStaff.add(bs);
                
                //staffs.remove(Integer.parseInt(i));
                removedStaff.add(selected.getID());
            }
            
            for(int i: removedStaff)
            {
                staffs.removeIf(u -> u.getID() == i);
            }
                       
            br.setListHiredStaff(brStaff);

            request.setAttribute(LIST_STAFF, staffs);
            session.setAttribute(SESSION_BOOKING, booking);
            session.setAttribute(SESSION_STAFF, staffs);
        } 
        else if(request.getParameter("select_done") != null)
        {
            request.setAttribute(RETURN_BOOKING, session.getAttribute(SESSION_BOOKING));
            session.setAttribute("action", "RETURN_STAFF");
            url="/CheckinServlet";
        }
        else {
            UserDAO dao = new UserDAO();
            
            Booking booking =(Booking) request.getAttribute(REQUEST_BOOKING);
            
            BookedRoom br = booking.getListBookedRoom().get(0);
            
            List<User> freeStaff = dao.getAllStaff(br.getCheckin(), br.getCheckout());
            
            request.setAttribute(LIST_STAFF, freeStaff);
            session.setAttribute(SESSION_BOOKING, booking);
            session.setAttribute(SESSION_STAFF, freeStaff);
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
