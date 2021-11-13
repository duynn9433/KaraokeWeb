/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

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
import model.Service;

/**
 *
 * @author xxxx9
 */
@WebServlet(name = "AddServicesServlet", urlPatterns = {"/AddServicesServlet"})
public class AddServicesServlet extends HttpServlet {

    public static String REQUEST_BOOKING = "addservices_request_booking";
    public static String RETURN_BOOKING = "addservices_return_booking";
    
    
    static String SESSION_BOOKING = "addservices_session_booking";
    static String SESSION_SERVICES = "addservices_session_services";
    static String LIST_SERVICE = "listServices";
    static String BOOKING = "booking";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String url = "/receptionist/AddServices.jsp";

        try {
            if (request.getParameter("ADD_SERVICE") != null) {
                
                
            } else if (request.getParameter("DONE") != null) {
            } else if (request.getParameter("SEARCH_SERVICE") != null) {
                ServiceDao dao = new ServiceDao();
                String name = request.getParameter("service_name");
                List<Service> services = dao.findService(name);
                             
                session.setAttribute(SESSION_SERVICES, services);
                request.setAttribute(LIST_SERVICE, services);
                
                Booking booking = (Booking) session.getAttribute(SESSION_BOOKING);
                request.setAttribute(BOOKING, booking);
            }
            else
            {
                Booking booking = (Booking) request.getAttribute(REQUEST_BOOKING);
                session.setAttribute(SESSION_BOOKING, booking);
                request.setAttribute(BOOKING, booking);
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
