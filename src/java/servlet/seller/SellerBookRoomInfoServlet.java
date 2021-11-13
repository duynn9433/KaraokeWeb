/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.seller;

import DAO.ClientDAO;
import DAO.RoomDAO;
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
import model.Booking;
import model.Client;
import model.Room;
import model.User;

/**
 *
 * @author duynn
 */
@WebServlet(name = "SellerBookRoomInfo", urlPatterns = {"/SellerBookRoomInfo"})
public class SellerBookRoomInfoServlet extends HttpServlet {

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
        System.out.println("SellerBookRoomInfo"+user.toString());
        Booking booking = (Booking) session.getAttribute("booking");
        
        ClientDAO clientDAO = new ClientDAO();
        List<Client> listClient = null;
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        
        
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("Tim")) {
            name = request.getParameter("name");
            phoneNumber = request.getParameter("phoneNumber");
            try {
                listClient = clientDAO.searchClient(name, phoneNumber);
                for(Client c : listClient) System.out.println(c.toString());
            } catch (SQLException ex) {
                Logger.getLogger(SellerBookRoomInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getSession().removeAttribute("listClient");
            request.getSession().setAttribute("listClient", listClient);
            request.setAttribute("name", name);
            request.setAttribute("phoneNumber", phoneNumber);
            url="/seller/SellerBookRoomInfoView.jsp";
        }else if(action.equals("Them")){
            name = request.getParameter("name");
            phoneNumber = request.getParameter("phoneNumber");
            request.setAttribute("name", name);
            request.setAttribute("phoneNumber", phoneNumber);
            url="/seller/SellerAddClientView.jsp";
            
        }else if(action.equals("Luu")){
            listClient = (List<Client>) session.getAttribute("listClient");
            request.getSession().removeAttribute("listClient");
            
            String[] index = request.getParameterValues("selectedItems");
            booking.setClient(listClient.get(Integer.parseInt(index[0])));
            
            session.removeAttribute("booking");
            session.setAttribute("booking", booking);
            
            url="/seller/SellerConfirmView.jsp";
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
