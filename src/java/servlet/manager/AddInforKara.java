/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.manager;

import DAO.KaraokeBarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.KaraokeBar;
import model.User;

/**
 *
 * @author Truong
 */
@WebServlet(name = "AddInforKara", urlPatterns = {"/AddInforKara"})
public class AddInforKara extends HttpServlet {

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
        String url ="/manager/AddInforKaraView.jsp";
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        String msg="";
       
        String action=request.getParameter("save");
        if(action.equals("save")){
            
             /*   String name = request.getParameter("name");
                String address = request.getParameter("address");
                String des = request.getParameter("des");
                KaraokeBar karaBar = new KaraokeBar(1001,name,address,des);
            try {   
                new KaraokeBarDAO().addKaraBar(karaBar);
                msg="luu thanh cong";
            } catch (SQLException ex) {
                msg = "loi";
                Logger.getLogger(AddInforKara.class.getName()).log(Level.SEVERE, null, ex);
            }*/
           url="/manager/AddInforKaraView.jsp";
           msg ="luu thanh cong";
        }
       request.getSession().setAttribute("addKaraMsg", msg);
       request.getRequestDispatcher(url).forward(request,response);
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
