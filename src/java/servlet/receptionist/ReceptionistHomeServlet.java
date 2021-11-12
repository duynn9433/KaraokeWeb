/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.receptionist;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author xxxx9
 */
@WebServlet(name = "ReceptionistHomeServlet", urlPatterns = {"/ReceptionistHomeServlet"})
public class ReceptionistHomeServlet extends HttpServlet {

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
        response.setContentType("text/html;harset=UTF-8");
        
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String url = "/receptionist/Checkin.jsp";

        String actionAttr = (String) session.getAttribute("action");
        String actionPara = (String) request.getParameter("action");
        System.out.println("action attr" + actionAttr);
        System.out.println("action param" + actionPara);
        
        
        User user = new User();
        user.setID(3);
        user.setUsername("receptionist");
        user.setPassword("receptionist");
        user.setName("receptionist");
        user.setPhoneNumber("100003");
        user.setIsActive(true);
        
        session.setAttribute("savedUser", user);

        try {

            //From other jsp
            
                if (actionAttr.equals("ACTION_CHECKIN")) {
                    url = "/receptionist/Checkin.jsp";
                }
                else 
                {
                    
                }
            
        }
        catch(Exception e)
        {
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
