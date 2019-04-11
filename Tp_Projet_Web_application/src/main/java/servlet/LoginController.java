/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Tp_Projet.CustomerEntity;
import Tp_Projet.DAO;
import Tp_Projet.DAOException;
import Tp_Projet.DataSourceFactory;
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
import javax.servlet.http.Cookie;

/**
 *
 * @author manga
 */
@WebServlet(name = "Log_Controller", urlPatterns = {"/Log_Controller"})
public class LoginController extends HttpServlet {

    DAO dao;

    public LoginController() throws SQLException {
        this.dao = new DAO(DataSourceFactory.getDataSource());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException {
        // Quelle action a appelé cette servlet ?
        String action = request.getParameter("action");
        if (null != action) {
            switch (action) {
                case "login":
                    checkLogin(request);
                    break;
                case "logout":
                    doLogout(request);
                    break;
            }
        }

        // Est-ce que l'utilisateur est connecté ?
        // On cherche l'attribut userName dans la session
        String userName = findUserInSession(request);
        System.out.print(userName);
        String jspView;
        Cookie message = new Cookie("userName", userName);
        response.addCookie(message);
        if (null == userName) { // L'utilisateur n'est pas connecté
            // On choisit la page de login
            jspView = "PageConnection.jsp";

        } else if (userName == getInitParameter("userName")) { // L'administrateur est connecté
            // On choisit la page d'affichage de l'administrateur
            jspView = "PageCommande.jsp";
            
        } else { // L'utilisateur est connecté
            // On choisit la page d'affichage
            jspView = "PageCommande.jsp";
            
        }
        // On va vers la page choisie
        request.getRequestDispatcher(jspView).forward(request, response);

    }

    private void checkLogin(HttpServletRequest request) throws DAOException {
        // Les paramètres transmis dans la requête
        String loginParam = request.getParameter("loginParam");
        String passwordParam = request.getParameter("passwordParam");
        // Le login/password défini dans web.xml est celui de la connexion en administrateur
        String loginAdmin = getInitParameter("adminL");
        String passwordAdmin = getInitParameter("adminP");
        String adminName = "Coucou";
        // Le login/password défini dans la base de données est celui des utilisateurs
        String loginUser;
        String passwordUser;
        String userName;
        //System.out.print("Coucou ici");
        if ((loginAdmin.equals(loginParam) && (passwordAdmin.equals(passwordParam)))) {
            // On a trouvé la combinaison login / password
            // On stocke l'information dans la session
            //System.out.print("Coucou là encore");
            HttpSession session = request.getSession(true); // démarre la session
            session.setAttribute("userName", adminName);
        }

        if (dao.findCustomers(loginParam).size() != 0) {
            loginUser = loginParam;
            CustomerEntity user = dao.findCustomers(loginUser).get(0);
            passwordUser = String.valueOf(user.getCustomerId());
            userName = dao.findCustomers(loginUser).get(0).getName();
            if ((loginUser.equals(loginParam) && (passwordUser.equals(passwordParam)))) {
                // On a trouvé la combinaison login / password
                // On stocke l'information dans la session
                HttpSession session = request.getSession(true); // démarre la session
                session.setAttribute("userName", userName);
            }
        }
        //On positionne un message d'erreur pour l'afficher dans la JSP
        request.setAttribute("errorMessage","erreur" );

    }

    private void doLogout(HttpServletRequest request) {
        // On termine la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    private String findUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session == null) ? null : (String) session.getAttribute("userName");
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
