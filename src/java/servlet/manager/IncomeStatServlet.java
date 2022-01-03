/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.manager;

import DAO.IncomeStatDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import model.IncomeStat;

/**
 *
 * @author duynn
 */
@WebServlet(name = "IncomeStatServlet", urlPatterns = {"/IncomeStatServlet"})
public class IncomeStatServlet extends HttpServlet {

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
        
        String msg = null;
        List<IncomeStat> listIncomeStat = null;
        IncomeStatDAO incomeStatDAO = new IncomeStatDAO();
        
        String action = request.getParameter("action");
        System.out.println("action " + action);
        if (action.equals("thongKe")) {
            String sdString = request.getParameter("startDate");
            String edString = request.getParameter("endDate");
            System.out.println(sdString);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime sd = LocalDateTime.parse(sdString + " 00:00:00", dtf);
            LocalDateTime ed = LocalDateTime.parse(edString + " 23:59:59", dtf);
            try {
                listIncomeStat = incomeStatDAO.getIncomeStat(sd, ed);
                request.setAttribute("listIncomeStat", listIncomeStat);
                url = "/manager/IncomeStatView.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(IncomeStatServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equals("chiTiet")){
            IncomeStat incomeStat = new IncomeStat(Float.parseFloat(request.getParameter("income")), 
                    request.getParameter("thang"));
            session.setAttribute("incomeStat", incomeStat);
            //url="/manager/IncomeDetailView.jsp";
            url="/IncomeDetailServlet";
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
