/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Tp_Projet.DAO;
import Tp_Projet.DAOException;
import Tp_Projet.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import static java.lang.System.console;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Properties;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rbastide
 */
@WebServlet(name = "salesByCustomer", urlPatterns = {"/salesByCustomer"})
public class SalesByCustomerInJSON extends HttpServlet {

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
            throws ServletException, IOException, DAOException, SQLException {
        // Créér le DAO avec sa source de données
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        HttpSession session = request.getSession(false);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate l = LocalDate.now();
        String today = dt.format(l);
        
        // Properties est une Map<clé, valeur> pratique pour générer du JSON
        Properties resultat = new Properties();
        if(session.getAttribute("CA") != null)
            resultat.put("records", session.getAttribute("CA"));
        else
            resultat.put("records", dao.CAParCategorie("2011-01-01", today));
        try (PrintWriter out = response.getWriter()) {
            // On spécifie que la servlet va générer du JSON
            response.setContentType("application/json;charset=UTF-8");
            // Générer du JSON
            // Gson gson = new Gson();
            // setPrettyPrinting pour que le JSON généré soit plus lisible
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String gsonData = gson.toJson(resultat);
            out.println(gsonData);
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
            Logger.getLogger(SalesByCustomerInJSON.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SalesByCustomerInJSON.class.getName()).log(Level.SEVERE, null, ex);
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
