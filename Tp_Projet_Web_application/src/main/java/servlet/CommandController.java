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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
@WebServlet(name = "CommandController", urlPatterns = {"/CommandController"})
public class CommandController extends HttpServlet {

    DAO dao;

    public CommandController() throws SQLException {
        this.dao = new DAO(DataSourceFactory.getDataSource());
    }

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
            throws ServletException, IOException, DAOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PageCommande.jsp");
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        request.setAttribute("Commande", dao.purchaseOrderPourUnClient(1));
        System.out.print(dao.purchaseOrderPourUnClient(1).size());
        if (null != action) {
            switch (action) {
                case "Ajouter une commande":
                    AjouterCommande(request);
                    break;
                case "Modifier":
                    ModifierCommande(request);
                    break;
                case "Supprimer":
                    SupprimerCommande(request);
                    break;
            }
        }
        dispatcher.forward(request, response);
    }

    private void AjouterCommande(HttpServletRequest request) throws DAOException {
        String action = request.getParameter("action");
        action = (action == null) ? "" : action; // Pour le switch qui n'aime pas les null
        String nCom = request.getParameter("numero de commande");
        String produit = request.getParameter("produit");
        String quantite = request.getParameter("quantite");
        String prix = request.getParameter("prix");
        try {
            DAO dao = new DAO(DataSourceFactory.getDataSource());
            //dao.ajoutPurchaseOrder(o);
            /*switch (action) {
                case "ADD": // Requête d'ajout (vient du formulaire de saisie)
                    dao.addDiscountCode(code, Float.valueOf(taux));
                    request.setAttribute("message", "Code " + code + " Ajouté");
                    request.setAttribute("codes", dao.allCodes());                    
                    break;
                case "DELETE": // Requête de suppression (vient du lien hypertexte)
                    try {
                        dao.deleteDiscountCode(code);
                        request.setAttribute("message", "Code " + code + " Supprimé");
                        request.setAttribute("codes", dao.allCodes());                        
                    } catch (SQLIntegrityConstraintViolationException e) {
                        request.setAttribute("message", "Impossible de supprimer " + code + ", ce code est utilisé.");
                    }
                    break;
            }*/
        } catch (Exception ex) {
            Logger.getLogger("discountEditor").log(Level.SEVERE, "Action en erreur", ex);
            request.setAttribute("message", ex.getMessage());
        }
    }

    private void ModifierCommande(HttpServletRequest request) throws DAOException {

    }

    private void SupprimerCommande(HttpServletRequest request) throws DAOException {

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
            Logger.getLogger(CommandController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CommandController.class.getName()).log(Level.SEVERE, null, ex);
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
