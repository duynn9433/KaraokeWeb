/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.manager;

import DAO.ClientDAO;
import DAO.KaraokeBarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Booking;
import model.Client;
import model.KaraokeBar;
import model.User;

/**
 *
 * @author duynn
 */
@WebServlet(name = "AddInfoKaraServlet", urlPatterns = {"/AddInfoKaraServlet"})
public class AddInfoKaraServlet extends HttpServlet {

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

        KaraokeBar karaoke = null;
        KaraokeBarDAO karaokeBarDAO = new KaraokeBarDAO();
        String msg = null;
        
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("them")) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String des = request.getParameter("des");
            karaoke = new KaraokeBar(0, name, address, des);
            
            try{
                karaokeBarDAO.addInfoKara(karaoke);
                msg="Them thanh cong";
                url="/manager/AddInforKara.jsp";
            }catch(Exception e){
                e.printStackTrace();
                msg="Them that bai";
                url="/manager/AddInforKara.jsp";
            }
        }
        request.getSession().setAttribute("addKaraMsg", msg);
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
