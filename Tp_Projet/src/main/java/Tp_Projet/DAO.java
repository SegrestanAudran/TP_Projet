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
	 * @return le chiffre d'affaire par catégorie d'article
         * @param dateDebut la date de début
         * @param dateFin la date de fin
	 * @throws DAOException
	 */
        public int CAPeriode(LocalDate dateDebut,LocalDate dateFin) throws DAOException{
            
        int CA = 0;
        int resultLigne = 0;
        
        String sql = "SELECT product_id, quantity, shipping_cost,description,purchase_cost  FROM purchase_order INNER JOIN product USING(product_id) WHERE sales_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)  ORDER BY product_id";
        try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
			PreparedStatement stmt = connection.prepareStatement(sql)    // On crée un statement préparé pour exécuter une requête paramétrée        
                ) {
                  
                        // Définir la valeur du paramètre
			stmt.setObject(1, dateDebut);
                        stmt.setObject(2, dateFin);

			try(ResultSet rs = stmt.executeQuery()){
                            while (rs.next()) { // On test tous les enregistrements
                                resultLigne = rs.getInt("quantity")*rs.getInt("purchase_cost")+rs.getInt("shipping_cost");
                                //Completer la liste de client
                                CA=CA+resultLigne;
			}
			
                        }
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        
                System.out.println(CA);
                return CA;
        }
        
    
}
