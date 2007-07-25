package test.com.jds.architecture.service.dao;

import static org.junit.Assert.*;

import java.beans.Statement;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import sun.nio.cs.ext.ISCII91;

import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DAOFactory;
import com.jds.architecture.service.dao.EmployeeDAO;
import com.jds.architecture.service.dao.ProjectDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.sun.net.httpserver.Authenticator.Success;
import com.sun.rowset.CachedRowSetImpl;

public class ProjectDAOTest {

	private String url = "jdbc:oracle:thin:@10.122.131.216:1521:XE";

	private void insertTestRow(Connection conn) {
		String createQuery = "INSERT INTO project "
				+ "(id, name, description, startdate, enddate, clientname, status) VALUES"
				+ "(10, 'TestProject', 'Test description', '15-FEB-2007', '23-JUL-2007', 'TestClient', 'TestStatus')";
		PreparedStatement stmt = null;
		try {
			if ((conn == null) || (conn.isClosed())) {
				throw new IllegalArgumentException();
			}
			stmt = conn.prepareStatement(createQuery);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}

	private void deleteTestRow(Connection conn) {
		String createQuery = "DELETE FROM project "
				+ "WHERE id='10'";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(createQuery);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	private boolean findTestRow(Connection conn) throws SQLException {
		String findQuery = "SELECT * FROM project WHERE id='10'";
		String tmp = null;
		PreparedStatement stmt = null;
		ResultSet rs;
		CachedRowSet set = null;
		// try {
		stmt = conn.prepareStatement(findQuery);
		rs = stmt.executeQuery();

		set = new CachedRowSetImpl();
		set.populate(rs);
		set.first();
		System.err.println("");
		tmp = set.getString("name");

		set.close();
		rs.close();
		stmt.close();

		/*
		 * } catch (Exception e) { e.printStackTrace(); } finally { }
		 */

		if (tmp.contentEquals("TestProject")) {
			return true;
		} else {
			return false;
		}
	}

	private Connection setUpConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "sampleuser",
					"samplepassword");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return conn;
	}

	private void closeConnection(Connection conn) {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");

	}

	@After
	public void tearDown() throws Exception {

	}

	
	@Test
	public void testCreate() throws DAOException, Exception {
		Connection conn = null;

		Date startDate = Date.valueOf("2007-02-23");
		Date endDate = Date.valueOf("2007-06-15");

		boolean wasFound;
		ProjectInfo inf = new ProjectInfo();

		inf.setProjectId("10");
		inf.setProjectName("TestProject");
		inf.setDescription("Test description");
		inf.setStartDate(startDate);
		inf.setEndDate(endDate);
		inf.setClient("TestClient");
		inf.setStatus("TestStatus");

		// try {
		conn = setUpConnection();

		ProjectDAO prj = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
				DAOConstants.DAO_PROJ);

		prj.create(conn, inf); // Main tested method

		wasFound = findTestRow(conn);

		if (!wasFound) {
			fail("Main test clause failed");
		}

		/*
		 * } catch (DAOException e) { e.printStackTrace(); fail("DAO exception
		 * failure"); } catch (SQLException e) { e.printStackTrace(); fail("SQL
		 * exception failure"); } catch (Exception e) { e.printStackTrace();
		 * fail("Exception failure. Probably from DAOFactory"); } finally {
		 */
		deleteTestRow(conn);
		closeConnection(conn);
	}

	
	@Test
	public void testRemove() throws Exception {
		Connection conn = null;

		ProjectDAO prj = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
				DAOConstants.DAO_PROJ);

		conn = setUpConnection();
		insertTestRow(conn);

		assertTrue(prj.remove(conn, "10"));

		closeConnection(conn);

	}
	
	@Test
	public void testFindByPK() throws DAOException, Exception {
		String tmp;
		ProjectDAO prj = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
				DAOConstants.DAO_PROJ);
		String pk = "10";

		Connection conn = setUpConnection();
		insertTestRow(conn);

		ProjectInfo rs = (ProjectInfo) prj.findByPK(pk);
		tmp = rs.getName();
		
		assertTrue(tmp.contentEquals("TestProject"));
		deleteTestRow(conn);
		

	}

	
	@Test
	public void testFind() throws DAOException, Exception {
		Connection conn = null;
		// Date startDate = Date.valueOf("2007-02-23");
		// Date endDate = Date.valueOf("2007-06-15");
		RowSet set;
		String tmp = null;
		ProjectInfo inf = new ProjectInfo();

		inf.setProjectId("10");
		inf.setProjectName("TestProject");
		// inf.setDescription("Test description");
		// inf.setStartDate(startDate);
		// inf.setEndDate(endDate);
		// inf.setClient("TestClient");
		// inf.setStatus("TestStatus");

		conn = setUpConnection();

		insertTestRow(conn);

		ProjectDAO prj = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
				DAOConstants.DAO_PROJ);

		set = prj.find(inf); // Main tested method

		set.first();
		tmp = set.getString("name");

		if (!tmp.contentEquals("TestProject")) {
			fail("Main test clause failed");
		}

		deleteTestRow(conn);

	}

	
	@Test
	public void testUpdate() throws DAOException, Exception {
		ProjectDAO prj = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
				DAOConstants.DAO_PROJ);
		Connection conn = null;

		ProjectInfo set = new ProjectInfo();
		ProjectInfo where = new ProjectInfo();

		set.setProjectId("10");
		set.setProjectName("ChangedTestProject");
		set.setDescription("!!!!!!");

		where.setProjectId("10");
		where.setProjectName("TestProject");
		where.setDescription("Test description");
		conn = setUpConnection();

		insertTestRow(conn);
			
		assertTrue(prj.update(conn, set, where));
		
		deleteTestRow(conn);
		
	}

	@Test
	public void testFindByAll() throws DAOException, Exception {
		Connection conn = setUpConnection();
		
		ProjectDAO prj = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
				DAOConstants.DAO_PROJ);
		CachedRowSet rs = new CachedRowSetImpl();
		
		Date startDate = Date.valueOf("2007-02-23");
		Date endDate = Date.valueOf("2007-06-15");
		
		Date startDate2 = Date.valueOf("2007-03-23");
		Date endDate2 = Date.valueOf("2007-07-15");
		
		ProjectInfo inf = new ProjectInfo();
		
		inf.setProjectId("1");
		inf.setProjectName("TestProject1");
		inf.setDescription("Test description1");
		inf.setStartDate(startDate);
		inf.setEndDate(endDate);
		inf.setClient("TestClient1");
		inf.setStatus("TestStatus1");
		
		prj.create(conn, inf);
		
		ProjectInfo inf2 = new ProjectInfo();
		
		inf2.setProjectId("2");
		inf2.setProjectName("TestProject2");
		inf2.setDescription("Test description2");
		inf2.setStartDate(startDate2);
		inf2.setEndDate(endDate2);
		inf2.setClient("TestClient2");
		inf2.setStatus("TestStatus2");
		
		prj.create(conn, inf2);
		
		
		rs = (CachedRowSet)prj.findByAll();
		rs.beforeFirst ();
		int i = 0;
		while (rs.next()){
			i++;
		}
		if (i <= 0){
			fail("Returned row count <= 0");
		}
		rs.close();
		prj.remove(conn, "1");
		prj.remove(conn, "2");
		
		
		closeConnection(conn);
		
	}

}
