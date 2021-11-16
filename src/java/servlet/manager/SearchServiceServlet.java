/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.manager;

import DAO.KaraokeBarDAO;
import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.KaraokeBar;
import model.Service;

/**
 *
 * @author Administrator
 */

@WebServlet(name = "SearchServiceServlet", urlPatterns = {"/SearchServiceServlet"})
public class SearchServiceServlet extends HttpServlet {

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
        String url = "/index.jsp";
        HttpSession session = request.getSession();
     
        ServiceDAO sd = new ServiceDAO();
        String msg = null;
        List<Service> listService =null;
        String action = request.getParameter("action");
        
        if(action.equals("search")){
            try {
                listService = sd.findService(request.getParameter("key"));
                session.setAttribute("listService", listService);
                url="/manager/SearchServiceView.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(EditInforKaraServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equals("edit")){
            listService = (List<Service>) session.getAttribute("listService");
            session.removeAttribute("listService");
            String[] indexs = request.getParameterValues("selectedItems");
            request.setAttribute("service", listService.get(Integer.parseInt(indexs[0])));
            System.out.println(listService.get(Integer.parseInt(indexs[0])).toString());
            url="/manager/EditServiceView.jsp";
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
