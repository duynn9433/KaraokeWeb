/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.manager;

import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Service;

/**
 *
 * @author Administrator
 */

@WebServlet(name = "EditServiceServlet", urlPatterns = {"/EditServiceServlet"})
public class EditServiceServlet extends HttpServlet {

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
        String url = "/index.jsp";
        HttpSession session = request.getSession();

        Service service = null;
        ServiceDAO sd = new ServiceDAO();
        String msg = null;
        
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("sua")) {
            String name = request.getParameter("name");
            String unity = request.getParameter("unity");
            String price = request.getParameter("price");
            String des = request.getParameter("des");
            service = new Service(name, unity, Float.parseFloat(price),des);
            
            try{
                sd.editService(service);
                msg="Sua thanh cong";
                url="/manager/EditServiceView.jsp";
            }catch(Exception e){
                e.printStackTrace();
                msg="Sua that bai";
                url="/manager/EditServiceView.jsp";
            }
        }
        request.getSession().setAttribute("editServiceMsg", msg);
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
