package Tp_Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import java.lang.*;
import static java.lang.System.console;
import java.time.*;
import static java.util.Collections.list;
import javafx.util.Pair;

/**
 *
 * @author manga
 */
public class DAO {

    private final DataSource myDataSource;

    /**
     *
     * @param dataSource la source de données à utiliser
     */
    public DAO(DataSource dataSource) {
        this.myDataSource = dataSource;
    }

    /**
     *
     * @ajoute une commande
     * @param l'objet order
     * @result le nombre de ligne ajoutée dans la table PURCHASE_ORDER
     * @throws DAOException
     */
    public int ajoutPurchaseOrder(OrderEntity o) throws DAOException {
        // Une requête SQL paramétrée
        String sql = "INSERT INTO Purchase_Order(Order_num,customer_id,product_id,quantity,shipping_cost, sales_date,shipping_date,freight_company) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // Définir la valeur du paramètre
            stmt.setInt(1, o.getOrder_num());
            stmt.setInt(2, o.getId_client());
            stmt.setInt(3, o.getId_produit());
            stmt.setInt(4, o.getQuantite());
            stmt.setFloat(5, o.getFrais());
            stmt.setString(6, o.getDate_achat());
            stmt.setString(7, o.getDate_envoi());
            stmt.setString(8, o.getCompagnie());

            if (stmt.executeUpdate() == 0) {
                throw new DAOException("Échec de la création de la commande, aucune ligne ajoutée dans la table.");
            }
            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     *
     * @modifier une commande
     * @param l'objet order à modifier
     * @return le nombre de ligne modifiée dans la table PURCHASE_ORDER
     * @throws DAOException
     */
    public int modifierPurchaseOrder(OrderEntity o) throws DAOException {
        // Une requête SQL paramétrée
        String sql = "UPDATE Purchase_Order SET customer_id = ?,product_id = ?,quantity = ?,shipping_cost = ?, sales_date = ?,shipping_date = ?,freight_company = ? where Order_num = ? ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // Définir la valeur du paramètre

            stmt.setInt(1, o.getId_client());
            stmt.setInt(2, o.getId_produit());
            stmt.setInt(3, o.getQuantite());
            stmt.setFloat(4, o.getFrais());
            stmt.setString(5, o.getDate_achat());
            stmt.setString(6, o.getDate_envoi());
            stmt.setString(7, o.getCompagnie());
            stmt.setInt(8, o.getOrder_num());

            if (stmt.executeUpdate() == 0) {
                throw new DAOException("Échec de la modification de la commande, aucune ligne ajoutée dans la table.");
            }
            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Detruire un enregistrement dans la table PURCHASE_ORDER
     *
     * @param ORDER_NUM la clé de la commande à détruire
     * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
     * @throws DAOException
     */
    public int deletePurchaseOrder(int order_num) throws DAOException {

        // Une requête SQL paramétrée
        String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Définir la valeur du paramètre
            stmt.setInt(1, order_num);

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     *
     * @return le chiffre d'affaire par catégorie d'article
     * @param dateDebut la date de début
     * @param dateFin la date de fin
     * @return un hashMap avec le chiffre d'affaire par catégorie
     * @throws DAOException
     */
    public HashMap<String, Double> CAParCategorie(String dateDebut, String dateFin) throws DAOException {
        // List<Pair<String, Double>> list = new ArrayList<Pair<String, Double>>();
        //List<Double> result = new LinkedList<>(); // Liste vIde
        HashMap<String, Double> result = new HashMap<>();

        String sql = "SELECT  product_code,((CAST(SUM(quantity)as FLOAT ))*(cast(sum(purchase_cost)as decimal))+(cast(sum(shipping_cost) as decimal))) as CA FROM purchase_order INNER JOIN product USING(product_id) INNER JOIN product_code ON product_code.prod_code=PRODUCT.PRODUCT_CODE group by product_code ,sales_date having sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";

        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                PreparedStatement stmt = connection.prepareStatement(sql) // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {

            // Définir la valeur du paramètre
            stmt.setString(1, dateDebut);
            stmt.setString(2, dateFin);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // Tant qu'il y a des enregistrements
                    // On récupère les champs nécessaires de l'enregistrement courant
                    String product_code = rs.getString("product_code");
                    Double CA = rs.getDouble("CA");
                    //ajoute la ligne au resultat
                    result.put(product_code, CA);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }
        return result;
    }

    /**
     *
     * @return le chiffre d'affaire par zone géographique
     * @param dateDebut la date de début
     * @param dateFin la date de fin
     * @return un hashMap avec le chiffre d'affaire zone geographique
     * @throws DAOException
     */
    public HashMap<String, Double> CAParState(String dateDebut, String dateFin) throws DAOException {

        HashMap<String, Double> result = new HashMap<>();

        String sql = "SELECT  state,((CAST(SUM(quantity)as FLOAT ))*(cast(sum(purchase_cost)as decimal))+(cast(sum(shipping_cost) as decimal))) as CA FROM CUSTOMER INNER JOIN  purchase_order USING(customer_id) INNER JOIN product USING(product_id) INNER JOIN product_code ON product_code.prod_code=PRODUCT.PRODUCT_CODE group by state,sales_date having sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                PreparedStatement stmt = connection.prepareStatement(sql) // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {

            // Définir la valeur du paramètre
            stmt.setString(1, dateDebut);
            stmt.setString(2, dateFin);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // Tant qu'il y a des enregistrements
                    // On récupère les champs nécessaires de l'enregistrement courant
                    String state = rs.getString("state");
                    Double CA = rs.getDouble("CA");
                    //ajoute la ligne au resultat
                    result.put(state, CA);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return result;
    }

    /**
     *
     * @return le chiffre d'affaire par client
     * @param dateDebut la date de début
     * @param dateFin la date de fin
     * @return un hashMap avec le chiffre d'affaire client
     * @throws DAOException
     */
    public HashMap<String, Double> CAParCustomer(String dateDebut, String dateFin) throws DAOException {

        HashMap<String, Double> result = new HashMap<>();

        String sql = "SELECT  customer.name as nameCustomer,((CAST(SUM(quantity)as FLOAT ))*(cast(sum(purchase_cost)as decimal))+(cast(sum(shipping_cost) as decimal))) as CA FROM CUSTOMER INNER JOIN  purchase_order USING(customer_id) INNER JOIN product USING(product_id) INNER JOIN product_code ON product_code.prod_code=PRODUCT.PRODUCT_CODE group by customer.name,sales_date having sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";

        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                PreparedStatement stmt = connection.prepareStatement(sql) // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {

            // Définir la valeur du paramètre
            stmt.setString(1, dateDebut);
            stmt.setString(2, dateFin);
            // System.out.println("ça marche ici");
            try (ResultSet rs = stmt.executeQuery()) {

                // System.out.println("ça marche ici");
                while (rs.next()) { // Tant qu'il y a des enregistrements
                    // On récupère les champs nécessaires de l'enregistrement courant

                    String nomClient = rs.getString("nameCustomer");
                    Double CA = rs.getDouble("CA");
                    // list.add(product_code,CA);
                    result.put(nomClient, CA);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return result;
    }

    /**
     * Renvoie l'email et l'id du client et le nom
     *
     * @param
     * @return la un tableau contenu de l'email et l'id
     * @throws DAOException
     */
    public List<CustomerEntity> customers() throws DAOException {
        String sql = "SELECT customer_ID, name, email FROM CUSTOMER";
        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                PreparedStatement stmt = connection.prepareStatement(sql) // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {
            List<CustomerEntity> result = new LinkedList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // Tant qu'il y a des enregistrements
                    // On récupère les champs nécessaires de l'enregistrement courant

                    int id = rs.getInt("customer_ID");
                    String name = rs.getString("name");
                    String email = rs.getString("email");

                    // On crée l'objet entité
                    CustomerEntity c = new CustomerEntity(id, name, email);
                    // On l'ajoute à la liste des résultats
                    result.add(c);
                }
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

    }
    
    
     /**
     * Renvoie tous les commandes d'un client donné
     *
     * @param l'id du client
     * @return un tableau contenant les commandes du client
     * @throws DAOException
     */
    public List<OrderEntity> purchaseOrderPourUnClient(int id) throws DAOException {
        String sql = "SELECT order_num, customer_id, product_id, quantity,shipping_cost, sales_date, shipping_date,freight_company FROM PURCHASE_ORDER WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                PreparedStatement stmt = connection.prepareStatement(sql) // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {
            
            stmt.setInt(1, id);
            List<OrderEntity> result = new LinkedList<>();
            
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // Tant qu'il y a des enregistrements
                    // On récupère les champs nécessaires de l'enregistrement courant
                     int order = rs.getInt("order_num");
                   // int id = rs.getInt("customer_ID");
                    int product_id = rs.getInt("product_id");
                    int quantity = rs.getInt("quantity");
                    float shipping_cost = rs.getFloat("shipping_cost");
                    String sales_date = rs.getString("sales_date");
                    String shipping_date = rs.getString("shipping_date");
                    String freight_company = rs.getString("freight_company");
                    
                    // On crée l'objet entité
                    OrderEntity c = new OrderEntity(id, order, product_id,quantity,shipping_cost,sales_date,shipping_date,freight_company);
                    // On l'ajoute à la liste des résultats
                    result.add(c);
                }
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }
    }

}
