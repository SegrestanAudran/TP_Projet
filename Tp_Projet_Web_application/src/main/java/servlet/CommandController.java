/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Tp_Projet.DAO;
import Tp_Projet.DAOException;
import Tp_Projet.DataSourceFactory;
import Tp_Projet.OrderEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.HashMap;

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
            throws ServletException, IOException, DAOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PageCommande.jsp");
        //HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        request.setAttribute("Prod", dao.mesproduits());
        System.out.println("Coucou1");
        request.setAttribute("Company", dao.listeCompany());
        System.out.println("Coucou2");
        //System.out.print(dao.purchaseOrderPourUnClient(1).size());
        if (null != action) {
            switch (action) {
                case "Ajouter une commande":
                    request.getRequestDispatcher("formulaireAjout.jsp").forward(request, response);
                    break;
                case "Enregistrer ma commande":
                    AjouterCommandeForm(request, response);
                    request.getRequestDispatcher("PageCommande.jsp").forward(request, response);
                    break;
                case "Modifier":
                    ModifierCommande(request);
                    break;
                case "Supprimer":
                    SupprimerCommande(request);
                    request.getRequestDispatcher("PageCommande.jsp").forward(request, response);

                    break;
            }
        }
        dispatcher.forward(request, response);
    }

    private void AjouterCommandeForm(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException, SQLException {
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        HttpSession session = request.getSession(false);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String name_product = request.getParameter("name_product");
        String compagnie = request.getParameter("name_company");
        String name = (String) session.getAttribute("userName");
        request.setAttribute("quantity", quantity);
        request.setAttribute("name_product", name_product);
        request.setAttribute("compagnie", compagnie);
        request.setAttribute("userName", name);
        OrderEntity o = dao.completePurchaseOrder(name_product, quantity, name, compagnie);
        request.setAttribute("userName", name);
        OrderEntity i = dao.ajoutPurchaseOrder(o);
        request.setAttribute("Commande", dao.purchaseOrderPourUnClient((int) session.getAttribute("id")));

    }

    private void ModifierCommande(HttpServletRequest request) throws DAOException {

    }

    private void SupprimerCommande(HttpServletRequest request) throws DAOException, SQLException {
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        int o = Integer.valueOf(request.getParameter("Order_num"));
       dao.deletePurchaseOrder(o);
        HttpSession session = request.getSession(false);
        request.setAttribute("Commande", dao.purchaseOrderPourUnClient((int) session.getAttribute("id")));

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
        } catch (SQLException ex) {
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
        } catch (SQLException ex) {
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

    private String findUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session == null) ? null : (String) session.getAttribute("userName");
    }
}
