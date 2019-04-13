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
            throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PageAdministrateur.jsp");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("adminAction");
        if (null != action){
            switch(action){
                case "Voir mon graphique" :
                    //AfficherGraph(request);
                    request.getRequestDispatcher("PageAdministrateur.jsp").forward(request,response);
                    break;
            }
        }
        dispatcher.forward(request, response);
    }
    private void AfficherGraph(HttpServletRequest request,HttpServletResponse response) throws SQLException, DAOException, IOException{
        /*DAO dao = new DAO(DataSourceFactory.getDataSource());
        HttpSession session = request.getSession(false);
        String cat = request.getParameter("ChoixSelect");
        String datd = request.getParameter("Date_debut");
        String datf = request.getParameter("Date_fin");
        if(cat == "Categorie"){
            request.setAttribute("CA",dao.CAParCategorie(datd, datf));
        }
        if(cat == "Client"){
            request.setAttribute("CA",dao.CAParCustomer(datd, datf));
        }
        if(cat == "Zone Géographique"){
            request.setAttribute("CA",dao.CAParState(datd, datf));
        }*/
        DAO dao = new DAO(DataSourceFactory.getDataSource());
		// Properties est une Map<clé, valeur> pratique pour générer du JSON
		String datd = request.getParameter("Date_debut");
                String datf = request.getParameter("Date_fin");
                Properties resultat = new Properties();
                resultat.put("records", dao.CAParCustomer(datd, datf));

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
}
        