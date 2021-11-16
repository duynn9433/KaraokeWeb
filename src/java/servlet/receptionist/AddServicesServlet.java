/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.Booking;
import model.Service;
import model.UsedService;

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
    static String SESSION_ISEDIT = "addservices_session_ISEDIT";
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
                String[] selectService = request.getParameterValues("selectedService");
                String[] selectBookedRoom = request.getParameterValues("selectedBookedRoom");

                Booking booking = (Booking) session.getAttribute(SESSION_BOOKING);
                BookedRoom br = booking.getListBookedRoom().get(Integer.parseInt(selectBookedRoom[0]));

                List<Service> services = (List<Service>) session.getAttribute(SESSION_SERVICES);

                List<UsedService> usedServices = new ArrayList<>();

                for (String s : selectService) {
                    int i = Integer.parseInt(s);
                    Service service = services.get(i);
                    
                    

                    UsedService usedService = new UsedService();
                    usedService.setID(-1);
                    usedService.setService(service);
                    usedService.setPricePerUnit(service.getPricePerUnit());

                    usedServices.add(usedService);
                }

                if (br.getListUsedService() == null) {
                    br.setListUsedService(usedServices);
                } else {
                    br.getListUsedService().addAll(usedServices);
                }

                request.setAttribute(BOOKING, booking);
                request.setAttribute(LIST_SERVICE, services);
                session.setAttribute(SESSION_ISEDIT, true);
            } else if (request.getParameter("DONE") != null) {
                Booking booking = (Booking) session.getAttribute(SESSION_BOOKING);

                boolean isEdit = (boolean) session.getAttribute(SESSION_ISEDIT);

                if (isEdit) {
                    String[] amounts = request.getParameterValues("amountServices");
                    String[] notes = request.getParameterValues("noteServices");

                    int i = 0;

                    for (BookedRoom br : booking.getListBookedRoom()) {
                        for (UsedService us : br.getListUsedService()) {
                            int amount = Integer.parseInt(amounts[i]);

                            us.setAmount(amount);
                            us.setTotalPrice(us.getPricePerUnit() * amount);
                            us.setNote(notes[i]);

                            i++;
                        }
                    }
                }

                session.setAttribute("action", "RETURN_SERVICES");
                request.setAttribute(RETURN_BOOKING, booking);

                url = "/CheckoutServlet";
            } else if (request.getParameter("SEARCH_SERVICE") != null) {
                ServiceDAO dao = new ServiceDAO();
                String name = request.getParameter("service_name");
                List<Service> services = dao.findService(name);

                session.setAttribute(SESSION_SERVICES, services);
                request.setAttribute(LIST_SERVICE, services);

                Booking booking = (Booking) session.getAttribute(SESSION_BOOKING);
                request.setAttribute(BOOKING, booking);
            } else {
                Booking booking = (Booking) request.getAttribute(REQUEST_BOOKING);
                session.setAttribute(SESSION_BOOKING, booking);
                session.setAttribute(SESSION_ISEDIT, false);
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
