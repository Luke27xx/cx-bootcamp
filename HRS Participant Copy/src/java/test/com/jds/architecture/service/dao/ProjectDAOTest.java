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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.nio.cs.ext.ISCII91;

import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DAOFactory;
import com.jds.architecture.service.dao.EmployeeDAO;
import com.jds.architecture.service.dao.ProjectDAO;
import com.jds.architecture.service.dbaccess.DBAccess;

public class ProjectDAOTest {

	private String url = "jdbc:oracle:oci:@localhost:1521:XE";

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
			conn.commit();
			stmt.close();
		} catch (SQLException e) {

		} finally {
			
		}
	}

	private void deleteTestRow(Connection conn) {
		String createQuery = "DELETE FROM project "
				+ "WHERE id='10' AND name='TestProject'";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(createQuery);
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
		} catch (Exception e) {

		} finally {
			
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
	private void closeConnection(Connection conn){
		try{
			if (!conn.isClosed()){
				conn.close();
			}
		}catch(SQLException e){
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
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByPK() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		Connection conn = null;
		Date startDate = Date.valueOf("2007-06-23");
		Date endDate = Date.valueOf("2007-08-15");
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

		try {
			conn = setUpConnection();
			
			insertTestRow(conn);
			
			ProjectDAO prj = (ProjectDAO) DAOFactory.getFactory()
					.getDAOInstance(DAOConstants.DAO_PROJ);
			set = prj.find(inf);
			set.first();
			tmp = set.getString("name");
			if (!tmp.contentEquals("TestProject")){
				fail("Main test clause failed");
			}
			
			deleteTestRow(conn);
			
		} catch (DAOException e) {
			fail("DAO exception failure");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("SQL exception failure");
			e.printStackTrace();
		} catch (Exception e) {
			fail("Exception failure. Probably from DAOFactory");
		} finally {
			closeConnection(conn);
		}
	
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByAll() {
		fail("Not yet implemented");
	}

}
