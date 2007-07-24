/**
 * 
 */
package com.jds.architecture.service.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.ResultSet;
import com.jds.apps.beans.EmployeeInfo;
import com.jds.architecture.service.dao.EmployeeDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.EmployeeIdGenerator;
import com.sun.rowset.CachedRowSetImpl;

import junit.framework.TestCase;

/**
 * @author training
 *
 */
public class EmployeeDAOTest extends TestCase {

	public EmployeeDAO edb = null;
	
	
	private String dbDriver;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;		
	private Connection conn = null;
	
	EmployeeInfo empInf = new EmployeeInfo();
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		//super.setUp();
		String dbDriver = "oracle.jdbc.driver.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String dbUser = "sampleuser";
		String dbPassword = "samplepassword";		
		//--------------------------------------
		
		empInf.setAge(25);
		empInf.setCitizenship("English");
		empInf.setCity("Riga");
		empInf.setCivilStatus("Status");
		empInf.setCountry("UK");
		empInf.setEducationalAttainment("Bachelor in CS");
		empInf.setEmail("John.Doe@mail.com");
		empInf.setFirstName("Jhon");
		empInf.setLastName("Doe");
		empInf.setGender('M');
		
		//--------------------------------------
		
		try{
			//DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
		}
		catch (Exception e){
			e.printStackTrace();
			//fail("Couldn't setup a connection");
		}
		finally{
			
		}
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		//super.tearDown();
		conn.close();
	}

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#EmployeeDAO()}.
	 */
	/*public void testEmployeeDAO() {
		try{
			edb = new EmployeeDAO();
		}
		catch(DAOException e){
			fail("DAOException");
		}
		catch(DBAccessException e) {
			fail("DBAccessException");
		}
		finally{
			
		}
		//fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#create(java.sql.Connection, java.lang.Object)}.
	 */
	/*public void testCreate() {
			
		try{
			edb.create(conn, empInf);
		}
		catch(DAOException e){
			fail(e.getMessageKey());
		}
		finally{
			
		}
		
	}*/

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#remove(java.sql.Connection, java.lang.Object)}.
	 */
	public void testRemove() {
	
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#findByPK(java.lang.Object)}.
	 */
	public void testFindByPK() {
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#find(java.lang.Object)}.
	 */
	public void testFind() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#update(java.sql.Connection, java.lang.Object, java.lang.Object)}.
	 */
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#findByAll()}.
	 */
	public void testFindByAll() {
		ResultSet rs;
		try{
			rs = (ResultSet)edb.findByAll();
			rs.next();
			if (rs.getString(2) == "jhon"){
				rs.next();
				if(rs.getString(2) == "AAA"){
					
				}else{
					fail("Failure");
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			fail("Not yet implemented");
		}
		finally{
			
		}
		
		
	}

}
