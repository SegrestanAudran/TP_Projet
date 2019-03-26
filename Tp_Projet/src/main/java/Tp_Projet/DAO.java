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
         * @param 
         * @param
	 * @throws DAOException
	 */
        public int ajoutPurchaseOrder(int order_num, int id_client, int id_produit, int quantite, float frais, String date_achat, String date_envoi, String compagnie) throws DAOException{
            // Une requête SQL paramétrée
		String sql = "INSERT INTO Purchase_Order(Order_num,customer_id,product_id,quantity,shipping_cost, sales_date,shipping_date,freight_company) VALUES(?,?,?,?,?,?,?,?)";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
                ) {
                        // Définir la valeur du paramètre
			stmt.setInt(1, order_num);
                        stmt.setInt(2, id_client);
                        stmt.setInt(3, id_produit);
                        stmt.setInt(4, quantite);
                        stmt.setFloat(5, frais);
                        stmt.setString(6, date_achat);
                        stmt.setString(7, date_envoi);
                        stmt.setString(8, compagnie);
                        
			if ( stmt.executeUpdate() == 0 ) {
                                throw new DAOException( "Échec de la création de la commande, aucune ligne ajoutée dans la table." );
                        }
			return stmt.executeUpdate();
                        

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        }
        
        
         /**
	 *
	 * @ajoute une commande
         * @param 
         * @param
	 * @throws DAOException
	 */
        public int modifierPurchaseOrder(int order_num, int id_client, int id_produit, int quantite, float frais, String date_achat, String date_envoi, String compagnie) throws DAOException{
                       // Une requête SQL paramétrée
		String sql = "UPDATE Purchase_Order SET customer_id = ?,product_id = ?,quantity = ?,shipping_cost = ?, sales_date = ?,shipping_date = ?,freight_company = ? where Order_num = ? ";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
                ) {
                        // Définir la valeur du paramètre
			//stmt.setInt(1, order_num);
                        stmt.setInt(1, id_client);
                        stmt.setInt(2, id_produit);
                        stmt.setInt(3, quantite);
                        stmt.setFloat(4, frais);
                        stmt.setString(5, date_achat);
                        stmt.setString(6, date_envoi);
                        stmt.setString(7, compagnie);
                        stmt.setInt(8, order_num);
                        
			if ( stmt.executeUpdate() == 0 ) {
                                throw new DAOException( "Échec de la modification de la commande, aucune ligne ajoutée dans la table." );
                        }
			return stmt.executeUpdate();
                        

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        }
        
        
        
        
	/**
	 * Detruire un enregistrement dans la table PURCHASE_ORDER
	 * @param ORDER_NUM la clé de la commande à détruire
	 * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
	 * @throws DAOException
	 */
	public int deletePurchaseOrder(int order_num) throws DAOException {

		// Une requête SQL paramétrée
		String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setInt(1, order_num);
			
			return stmt.executeUpdate();

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
	}
        
        /**
	 *
	 * @return le chiffre d'affaire par catégorie d'article
         * @param dateDebut la date de début
         * @param dateFin la date de fin
	 * @throws DAOException
	 */
        public HashMap<String,Double> CAPeriode(String dateDebut,String dateFin) throws DAOException{
               // List<Pair<String, Double>> list = new ArrayList<Pair<String, Double>>();
            //List<Double> result = new LinkedList<>(); // Liste vIde
            HashMap<String,Double> result = new HashMap<>();
        
            String sql = "SELECT  product_code,((CAST(SUM(quantity)as FLOAT ))*(cast(sum(purchase_cost)as decimal))+(cast(sum(shipping_cost) as decimal))) as CA FROM purchase_order INNER JOIN product USING(product_id) INNER JOIN product_code ON product_code.prod_code=PRODUCT.PRODUCT_CODE group by product_code ,sales_date having sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
        // String sql = "SELECT product_id, quantity, shipping_cost,description,purchase_cost  FROM purchase_order INNER JOIN product USING(product_id) WHERE sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)  ORDER BY product_id";
        
            try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
			PreparedStatement stmt = connection.prepareStatement(sql)    // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {
                  
                        // Définir la valeur du paramètre
			stmt.setString(1, dateDebut);
                        stmt.setString(2, dateFin);
                       // System.out.println("ça marche ici");
			try (ResultSet rs = stmt.executeQuery()) {
                            
                          // System.out.println("ça marche ici");
                           while (rs.next()) { // Tant qu'il y a des enregistrements
                                // On récupère les champs nécessaires de l'enregistrement courant
                                
                                System.out.println("coucou");
                                System.out.println(rs.getString("product_code"));
                                
                                
                                System.out.println("ça marche ici");
				String product_code = rs.getString("product_code");
				Double CA = rs.getDouble("CA");
                               // list.add(product_code,CA);
                                result.put(product_code, CA); 
                                
                               // System.out.println(list);
                                //Completer la liste de client
                            }
                           
                         // System.out.println(list);

                        }
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        
              //  System.out.println(result);
                return result;
        }
        
        
        /**
	 * Renvoie l'email et l'id du client et le nom
	 * @param
	 * @return la un tableau contenu de l'email et l'id
	 * @throws DAOException
	 */
	public List<CustomerEntity> customers() throws DAOException {
            String sql = "SELECT customer_ID, name, email FROM CUSTOMER";
              try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
			PreparedStatement stmt = connection.prepareStatement(sql)    // On crée un statement préparé pour exécuter une requête paramétrée        
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
                           }  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
                           
                          
                  
              
        }
    
}
