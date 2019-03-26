/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp_Projet;

import java.sql.SQLException;
import java.time.*;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


/**
 *
 * @author Yasmina
 */
public class DAOTest {
    private DAO myDao;
    private DataSource myDataSource;
  //  private String d1;
  //  private String d2;
    
    @Before
    public void setUp() throws SQLException {
	myDataSource = DataSourceFactory.getDataSource();
	myDao = new DAO(myDataSource);
    }
    
    //Créer une ligne de commande
    int order_num = 168;
    int id_client = 3;
    int id_produit = 980002;
    int quantite = 10;
    float frais = 45;
    String date_achat = "2019-05-05";
    String date_envoi = "2019-05-09";
    String compagnie = "Poney Express";
    
   OrderEntity order = new OrderEntity(order_num, id_client,id_produit,quantite,frais,date_achat,date_envoi,compagnie);
    
    //@Test
    public void testCreerCommande() throws DAOException {
        assertEquals(1,myDao.ajoutPurchaseOrder(order));
    }
    // la supprimer
    @Test
    public void testSupprimerCommande() throws DAOException {
        assertEquals(1,myDao.deletePurchaseOrder(166));
    }
    //la modifier
    @Test
    public void testModifierCommande() throws DAOException {
       assertEquals(1,myDao.modifierPurchaseOrder(order));
    }
    
    @Test
    public void testCAParCategorie() throws DAOException {
        //HashMap<String,Double> tabCAPer = new HashMap<>();
        String d1 = "2011-02-25";
        String d2 = "2011-06-25";
        assertEquals(9750.0,myDao.CAParCategorie(d1, d2).get("BK"),0.1);
    }
    
     @Test
    public void testCAParState() throws DAOException {
        String d1 = "2011-02-25";
        String d2 = "2011-06-25";
        assertEquals(865.0,myDao.CAParState(d1, d2).get("GA"),0.1); 
    }
    
    @Test
    public void testCAParCustomer() throws DAOException {
        String d1 = "2011-02-25";
        String d2 = "2011-06-25";
        assertEquals(9750.0,myDao.CAParCustomer(d1, d2).get("Big Car Parts"),0.1); 
    }
    
}
