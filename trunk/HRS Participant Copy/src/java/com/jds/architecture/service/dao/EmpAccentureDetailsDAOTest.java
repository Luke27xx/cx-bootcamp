/**
 * 
 */
package com.jds.architecture.service.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jds.apps.beans.AccentureDetails;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.EmployeeIdGenerator;
import com.jds.architecture.service.idgenerator.IdGeneratorException;

/**
 * @author M.Skovitovs
 *
 */
public class EmpAccentureDetailsDAOTest
{
	private static EmpAccentureDetailsDAO dao;
	private static EmpAccDetailsDAOTestConnection daoConn;
	static Connection conn;

	@BeforeClass
	public static void onlyOnce()
	{
		try
		{
			daoConn = new EmpAccDetailsDAOTestConnection();
			dao = new EmpAccentureDetailsDAO();
		}
		catch (Exception e)
		{
			
		}
		
	}
	@Before
	public void setUp() throws Exception
	{
		daoConn.reconnect();
		conn = daoConn.getConnection();
	}
	@After
	public void tearDown() throws Exception
	{
		daoConn.reconnect();
		conn = daoConn.getConnection();
		Statement stmt = conn.createStatement();
		stmt.close();
		conn.close();
	}
//create()
	//test: normal flow - OK
	@Test
	public final void testCreate() throws IdGeneratorException
	{
		AccentureDetails emp = new AccentureDetails();
		//Object id = EmployeeIdGenerator.getInstance().getNextId();
		emp = new AccentureDetails();
		emp.setEmployeeNo("123");
		emp.setEnterpriseId("EnterpriseId");
		emp.setEnterpriseAddress("mail@mail.com");
		emp.setLevel("Level");
		emp.setLMU("LMU");
		emp.setStatus("Status");
		emp.setDateHired(new java.sql.Date(emp.getDateHired().getTime()));
		emp.setGMU("GMU");
		emp.setWorkGroup("WorkGroup");
		emp.setSpecialty("Specialty");
		emp.setServiceLine("ServiceLine");
		
		try
		{
			dao.create(conn, emp);
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	//test: error flow - OK
	@Test
	public final void testCreateNullObject()
	{
		try
		{
			dao.create(conn, null);
			fail("k");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail("k");
		}
	}
	@Test
	public final void testCreateNotInstanceOfEmpAccDetailsObject()
	{
		try
		{
			dao.create(conn, "Max");
			fail("k");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail("k");
		}
	}@Test
	public final void testCreateNullConnection()
	{
		AccentureDetails emp = new AccentureDetails();
		
		
		emp = new AccentureDetails();
		emp.setEmployeeNo("124");
		emp.setEnterpriseId("EnterpriseId");
		emp.setEnterpriseAddress("mail@mail.com");
		emp.setLevel("Level");
		emp.setLMU("LMU");
		emp.setStatus("Status");
		emp.setDateHired(new java.sql.Date(emp.getDateHired().getTime()));
		emp.setGMU("GMU");
		emp.setWorkGroup("WorkGroup");
		emp.setSpecialty("Specialty");
		emp.setServiceLine("ServiceLine");
		
		try
		{
			dao.create(null, emp);
			fail("k");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail("k");
		}
	}
	public final void testCreateNullConnectionNullObject()
	{
		try
		{
			dao.create(null, null);
			fail("k");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail("k");
		}
	}
	@Test
	public final void testCreateIfObjectAlreadyExist()
	{
		AccentureDetails emp = new AccentureDetails();
		
		emp = new AccentureDetails();
		emp.setEmployeeNo("123");
		emp.setEnterpriseId("EnterpriseId");
		emp.setEnterpriseAddress("mail@mail.com");
		emp.setLevel("Level");
		emp.setLMU("LMU");
		emp.setStatus("Status");
		emp.setDateHired(new java.sql.Date(emp.getDateHired().getTime()));
		emp.setGMU("GMU");
		emp.setWorkGroup("WorkGroup");
		emp.setSpecialty("Specialty");
		emp.setServiceLine("ServiceLine");
		
		try
		{
			dao.create(null, emp);
			fail("completed");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail("??");
		}
	}
//find()
	//test: normal flow - OK
	@Test
	public final void testFind()
	{
		AccentureDetails emp = new AccentureDetails();
		RowSet rs;

		emp.setEmployeeNo("111");
		
		try
		{
			rs = dao.find(emp);
			if (rs.next())
			{
				assertEquals("J2SDK1.5", rs.getString("description"));
			}
			else
			{
				fail("err: find");
			}
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	@Test
	public final void testFindAdvanced()
	{
		AccentureDetails emp = new AccentureDetails();
		RowSet rs;

		emp.setEmployeeNo("321");
		emp.setEnterpriseAddress("Adress");
		
		try
		{
			rs = dao.find(emp);
			if (rs.next())
			{
				assertEquals("321", rs.getString("empNo"));
				assertEquals("Adress", rs.getString("emailAdd"));
			}
			else
			{
				fail("err: find advanced");
			}
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	@Test
	public final void testFindNotExisting()
	{
		AccentureDetails emp = new AccentureDetails();
		RowSet rs;

		emp.setStatus("000");
		
		try
		{
			rs = dao.find(emp);
			if (rs.next())
			{
				fail("err: find not existing");
			}
			else
			{
				return;
			}
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	@Test
	public final void testFindAdvanced2()
	{
		AccentureDetails emp = new AccentureDetails();
		RowSet rs;

		emp.setServiceLine("1001");
		
		try
		{
			rs = dao.find(emp);
			while (rs.next())
			{
				System.out.print(rs.getString("empNo"));
				System.out.print(rs.getString("enterpriseId"));
			}
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	//test: error flow - OK
	@Test
	public final void testFindError()
	{
		try
		{
			dao.find(null);
			fail("err: find error");
		}
		catch (DAOException e)
		{
			return;
		}
	}

	@Test
	public final void testFindByAll()
	{
		try
		{
			EmpAccentureDetailsDAO dao = new EmpAccentureDetailsDAO();
			RowSet rs = dao.findByAll();
			
			while (rs.next())
			{
				System.out.println(rs.getString("enterpriseId"));
			}
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}

	@Test
	public final void testFindByPK()
	{
		AccentureDetails tmp;
		try
		{
			tmp = (AccentureDetails)dao.findByPK("123");
			assertEquals(tmp.getEnterpriseId(), "EnterpriseId");
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	//test: error flow - OK
	@Test
	public final void testFindByPKNullObject()
	{
		try
		{
			dao.findByPK(null);
			fail("izpildija");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	@Test
	public final void testFindByPKNotExistingObject()
	{
		AccentureDetails tmp;
		try
		{
			tmp = (AccentureDetails)dao.findByPK("000");
			assertEquals(null, tmp);
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
//remove()
	//test: normal flow - OK
	@Test
	public final void testRemove()
	{
		String id = "123";
		try
		{
			boolean b = dao.remove(conn, id);
			assertEquals(true, b);
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	//test: error flow - OK
	@Test
	public final void testRemoveNullObject()
	{
		try
		{
			dao.remove(conn, null);
			fail("izpildija");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	@Test
	public final void testRemoveNotInstanceOfString()
	{
		int id = 123;
		try
		{
			dao.remove(conn, id);
			fail("k");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	@Test
	public final void testRemoveNullConnection()
	{
		String id = "123";
		try
		{
			dao.remove(null, id);
			fail("k");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	@Test
	public final void testRemoveNullConnectionNullObject()
	{
		try
		{
			dao.remove(null, null);
			fail("izpildija");
		}
		catch (DAOException e)
		{
			return;
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
//update()
	//test: normal flow - OK
	@Test
	public final void testUpdate()
	{
		AccentureDetails empOld = new AccentureDetails();
		AccentureDetails empNew = new AccentureDetails();
		
		empOld.setEnterpriseId("456");
		
		empNew.setEnterpriseId("456");
		empNew.setLevel("Lev");
		
		try
		{
			assertEquals(true, dao.update(conn, empNew, empOld));
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	//test: error flow - OK
	/*@Test
	public final void testUpdateNullConnection()
	{
		AccentureDetails siOld = new AccentureDetails();
		AccentureDetails siNew = new AccentureDetails();
		
		siOld.setSkillId("789");
		siOld.setCategoryId("1001");
		siOld.setSkillName("Adobe Photoshop");
		siOld.setSkillDescription("VER 8");
		
		siNew.setSkillId("789");
		siNew.setCategoryId("1001");
		siNew.setSkillName("Adobe Photoshop");
		siNew.setSkillDescription("VER 8");
		
		try
		{
			dao.update(null, siNew, siOld);
			fail("err: testUpdateNullConnection");
		}
		catch (DAOException e)
		{
			return;
		}
	}*/
	@Test
	public final void testUpdateNullSiNew()
	{
		SkillsInformation siOld = new SkillsInformation();
		SkillsInformation siNew = new SkillsInformation();
		
		siOld.setSkillId("789");
		siOld.setCategoryId("1001");
		siOld.setSkillName("Adobe Photoshop");
		siOld.setSkillDescription("VER 8");
		
		siNew.setSkillId("789");
		siNew.setCategoryId("1001");
		siNew.setSkillName("Adobe Photoshop");
		siNew.setSkillDescription("VER 8");
		
		try
		{
			dao.update(conn, null, siOld);
			fail("err: testUpdateNullSiNew");
		}
		catch (DAOException e)
		{
			return;
		}
	}
	@Test
	public final void testUpdateNullSiOld()
	{
		SkillsInformation siOld = new SkillsInformation();
		SkillsInformation siNew = new SkillsInformation();
		
		siOld.setSkillId("789");
		siOld.setCategoryId("1001");
		siOld.setSkillName("Adobe Photoshop");
		siOld.setSkillDescription("VER 8");
		
		siNew.setSkillId("789");
		siNew.setCategoryId("1001");
		siNew.setSkillName("Adobe Photoshop");
		siNew.setSkillDescription("VER 8");
		
		try
		{
			dao.update(conn, siNew, null);
			fail("err: testUpdateNullSiOld");
		}
		catch (DAOException e)
		{
			return;
		}
	}
	@Test
	public final void testUpdateNotInstanceOfSkillsInformation1()
	{
		SkillsInformation siOld = new SkillsInformation();
		SkillsInformation siNew = new SkillsInformation();
		
		siOld.setSkillId("789");
		siOld.setCategoryId("1001");
		siOld.setSkillName("Adobe Photoshop");
		siOld.setSkillDescription("VER 8");
		
		siNew.setSkillId("789");
		siNew.setCategoryId("1001");
		siNew.setSkillName("Adobe Photoshop");
		siNew.setSkillDescription("VER 8");
		
		try
		{
			dao.update(null, "Ivars", siOld);
			fail("err: testUpdateNotInstanceOfSkillsInformation1");
		}
		catch (DAOException e)
		{
			return;
		}
	}
	public final void testUpdateNotInstanceOfSkillsInformation2()
	{
		SkillsInformation siOld = new SkillsInformation();
		SkillsInformation siNew = new SkillsInformation();
		
		siOld.setSkillId("789");
		siOld.setCategoryId("1001");
		siOld.setSkillName("Adobe Photoshop");
		siOld.setSkillDescription("VER 8");
		
		siNew.setSkillId("789");
		siNew.setCategoryId("1001");
		siNew.setSkillName("Adobe Photoshop");
		siNew.setSkillDescription("VER 8");
		
		try
		{
			dao.update(null, siNew, "Ivars");
			fail("err: testUpdateNotInstanceOfSkillsInformation1");
		}
		catch (DAOException e)
		{
			return;
		}
	}
}
class EmpAccDetailsDAOTestConnection
{
	protected Connection conn;
	
	private DBAccess dbAccess = null;
	
	public EmpAccDetailsDAOTestConnection() throws DAOException, DBAccessException
	{
		dbAccess = DBAccess.getDBAccess();
	}
	public void reconnect() throws DAOException {
		try
		{
			if (conn == null || conn.isClosed())
			{
				conn = dbAccess.getConnection();
			}
		}
		catch (Exception e)
		{
			throw new DAOException("Could not initialize UrlDAO", e);
		}
	}
	public void close() throws DAOException
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				throw new DAOException("Could not close connection", e);
			}
			conn = null;
		}
	}
	public Connection getConnection() {
		return conn;
	}
}
