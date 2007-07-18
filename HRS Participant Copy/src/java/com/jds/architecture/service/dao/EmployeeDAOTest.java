/**
 * 
 */
package com.jds.architecture.service.dao;
//import com.jds.architecture.service.dao.EmployeeDAO;
import com.jds.architecture.service.dbaccess.DBAccessException;

import junit.framework.TestCase;

/**
 * @author training
 *
 */
public class EmployeeDAOTest extends TestCase {

	EmployeeDAO edb = null;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		//super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		//super.tearDown();
	}

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#EmployeeDAO()}.
	 */
	public void testEmployeeDAO() {
		try{
			edb = new EmployeeDAO();
		}
		catch(DAOException e){
			fail("DAOException");
		}
		catch(DBAccessException e) {
			fail("DBAccessException");
		}
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.jds.architecture.service.dao.EmployeeDAO#create(java.sql.Connection, java.lang.Object)}.
	 */
	public void testCreate() {
		fail("Not yet implemented");
	}

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
		fail("Not yet implemented");
	}

}
