/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp_Projet;

import java.sql.SQLException;

/**
 *
 * @author manga
 */
public class main {

    public static void main(String[] args) throws SQLException, DAOException {
 DAO dao = new DAO(DataSourceFactory.getDataSource());
   OrderEntity o = dao.completePurchaseOrder("Identity Server", 15, "Jumbo Eagle Corp", "Coastal Freight");
   System.out.print(o.getCompagnie());
    OrderEntity i=dao.ajoutPurchaseOrder(o);
    System.out.println(i.getId_produit());
}
}
