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

/**
 *
 * @author duynn
 */
@WebServlet(name = "EditInforKaraServlet", urlPatterns = {"/EditInforKaraServlet"})
public class EditInforKaraServlet extends HttpServlet {

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

        KaraokeBar karaoke = null;
        KaraokeBarDAO karaokeBarDAO = new KaraokeBarDAO();
        String msg = null;
        List<KaraokeBar> listKara =null;
        String action = request.getParameter("action");
        //System.out.println("action " + action);
        if(action == null){
            try {
                listKara = karaokeBarDAO.getInforKara();
                session.setAttribute("listKara", listKara);
                url="/manager/EditInforKara.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(EditInforKaraServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equals("edit")) {
            listKara = (List<KaraokeBar>) session.getAttribute("listKara");
            session.removeAttribute("listKara");
            String[] indexs = request.getParameterValues("selectedItems");
            request.setAttribute("karaokeBar", listKara.get(Integer.parseInt(indexs[0])));
            System.out.println(listKara.get(Integer.parseInt(indexs[0])).toString());
            url="/manager/EditDetailKara.jsp";
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
