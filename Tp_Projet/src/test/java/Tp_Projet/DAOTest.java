/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp_Projet;

import java.sql.SQLException;
import java.time.*;
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
    private LocalDate d1;
    private LocalDate d2;
    @Before
    public void setUp() throws SQLException {
	myDataSource = DataSourceFactory.getDataSource();
	myDao = new DAO(myDataSource);
    }
    
    
    @Test
    public void testCAPeriode() throws DAOException {
        d1 = new LocalDate.parse("2011-02-25");
        d2= new LocalDate.parse("2011-04-25");
    }
    
}
