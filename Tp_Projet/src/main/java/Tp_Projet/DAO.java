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
	 * @ajoute le une commande
         * @param 
         * @param
	 * @throws DAOException
	 */
        
        /**
	 *
	 * @return le chiffre d'affaire par catégorie d'article
         * @param dateDebut la date de début
         * @param dateFin la date de fin
	 * @throws DAOException
	 */
        public HashMap<String,Double> CAPeriode(String dateDebut,String dateFin) throws DAOException{
            
            HashMap<String,Double> result = new HashMap<String,Double>();
        
            String sql = "SELECT  product_code,((CAST(SUM(quantity)as FLOAT ))*(cast(sum(purchase_cost)as decimal))+(cast(sum(shipping_cost) as decimal))) as CA FROM purchase_order INNER JOIN product USING(product_id) INNER JOIN product_code ON product_code.prod_code=PRODUCT.PRODUCT_CODE group by product_code ,sales_date having sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
        // String sql = "SELECT product_id, quantity, shipping_cost,description,purchase_cost  FROM purchase_order INNER JOIN product USING(product_id) WHERE sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)  ORDER BY product_id";
        
            try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
			PreparedStatement stmt = connection.prepareStatement(sql)    // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {
                  
                        // Définir la valeur du paramètre
			stmt.setString(1, dateDebut);
                        stmt.setString(2, dateFin);

			try(ResultSet rs = stmt.executeQuery()){
                            while (rs.next()) { // On test tous les enregistrements
                                result.put(rs.getString("PRODUCT_CODE"),rs.getDouble("CA"));
                                //Completer la liste de client
			}
			
                        }
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        
                System.out.println(result);
                return result;
        }
        
    
}
