/**
 * 
 */
package com.jds.architecture.service.dao;

import java.sql.Connection;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jds.apps.beans.AccentureDetails;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.idgenerator.EmployeeIdGenerator;
import com.jds.architecture.service.idgenerator.IdGeneratorException;

/**
 * @author M.Skovitovs
 * 
 */

public class EmpAccDetailsDAOTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	private DataAccessObjectInterface test1 = null;
	private AccentureDetails emp;
	private DBAccess dbAccess;
	private long id;
	Connection conn = null;

	@Before
	public void setUp() throws Exception {
		try {
			conn = dbAccess.getConnection();
			id = EmployeeIdGenerator.getInstance().getNextId();
			test1 = new EmpAccentureDetailsDAO();
			emp = new AccentureDetails();
			emp.setEmployeeNo(String.valueOf(id));
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

		} catch (IdGeneratorException e) {
			// TODO: handle exception
		}

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dbAccess.closeConnection(conn);
	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.dao.EmpAccentureDetailsDAO#EmpAccentureDetailsDAO()}.
	 */
	/*
	 * @Test public void testEmpAccentureDetailsDAO() { //fail("Not yet
	 * implemented"); }
	 */

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.dao.EmpAccentureDetailsDAO#create(java.sql.Connection, java.lang.Object)}.
	 */
	@Test
	public void testCreate() {
		try {
			test1.create(conn, emp);
			assertEquals(test1, test1.findByPK(id));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.dao.EmpAccentureDetailsDAO#remove(java.sql.Connection, java.lang.Object)}.
	 */
	@Test
	public void testRemove() {
		try {
			test1.remove(conn, emp);
			System.out.println(test1);
			System.out.println(test1.findByPK(id));
			assertEquals(test1, test1.findByPK(id));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.dao.EmpAccentureDetailsDAO#findByPK(java.lang.Object)}.
	 */
	@Test
	public void testFindByPK() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.dao.EmpAccentureDetailsDAO#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.dao.EmpAccentureDetailsDAO#update(java.sql.Connection, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.dao.EmpAccentureDetailsDAO#findByAll()}.
	 */
	@Test
	public void testFindByAll() {
		fail("Not yet implemented");
	}

}
