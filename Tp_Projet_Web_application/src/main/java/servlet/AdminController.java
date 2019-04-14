/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Tp_Projet.DAO;
import Tp_Projet.DAOException;
import Tp_Projet.DataSourceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manga
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DAOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PageAdministrateur.jsp");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("adminAction");
        AfficherGraph(request,response);
        if (null != action){
            switch(action){
                case "Voir mon graphique" :
                    AfficherGraph(request,response);
                    request.getRequestDispatcher("PageAdministrateur.jsp").forward(request,response);
                    break;
            }
        }
        dispatcher.forward(request, response);
    }
    private void AfficherGraph(HttpServletRequest request,HttpServletResponse response) throws DAOException, IOException, SQLException, ServletException{
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        HttpSession session = request.getSession(false);
        String cat = request.getParameter("ChoixSelect");
        String datd = request.getParameter("Date_debut");
        String datf = request.getParameter("Date_fin");
        if(cat == "Categorie"){
            //request.getRequestDispatcher("SalesByCustomersInJSON").forward(request, response);
            session.setAttribute("CA",dao.CAParCategorie(datd, datf));
        }
        if(cat == "Client"){
            session.setAttribute("CA",dao.CAParCustomer(datd, datf));
        }
        if(cat == "Zone Géographique"){
            session.setAttribute("CA",dao.CAParState(datd, datf));
        }
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
            try {
                processRequest(request, response);
            } catch (DAOException ex) {
                Logger.getLogger(SalesByCustomerInJSON.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            try {
                processRequest(request, response);
            } catch (DAOException ex) {
                Logger.getLogger(SalesByCustomerInJSON.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        