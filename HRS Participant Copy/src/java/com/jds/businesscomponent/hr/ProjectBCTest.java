package com.jds.businesscomponent.hr;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DAOFactory;
import com.jds.architecture.service.dao.DataAccessObjectInterface;
import com.jds.architecture.service.dao.ProjectDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.IdGeneratorException;
import com.jds.architecture.service.idgenerator.ProjectIdGenerator;

public class ProjectBCTest {
	private ProjectBC pBC;
	private DBAccess dbAccess = null;
	private DataAccessObjectInterface projDao = null;
	
	@Before
	public void setUp() throws Exception, HRSSystemException {
		pBC = new ProjectBC();
		dbAccess = DBAccess.getDBAccess();
		projDao = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
				DAOConstants.DAO_PROJ);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Ignore
	@Test
	public void testCreateProjectValid() throws HRSLogicalException,
			HRSSystemException {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("2007-02-23");
		Date endDate = Date.valueOf("2007-05-01");

		//info.setProjectId("" + Math.round(Math.random() * 999));
		info.setDescription("01010001");
		info.setClient("lalalka");
		info.setStartDate(startDate);
		info.setEndDate(endDate);
		info.setStatus("APPROOVED");
		info.setProjectName("" + Math.round(Math.random() * 999));

		pBC.createProject(info);
		
	}
	@Ignore
	@Test
	public void testCreateProjectNull() {
		ProjectInfo info = null;

		try {
			pBC.createProject(info);
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("invalid.input.exception"))
				return;
		} catch (HRSSystemException e) {
			e.printStackTrace();
			fail("Wrong exception catched. Smth wrong with IDGen or something");
		}
		fail("Did not catch an expected exception");
	}
	@Ignore
	@Test
	public void testCreateProjectWrongStartDate() {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("1000-02-23");
		Date endDate = Date.valueOf("2007-05-01");

		info.setProjectId("" + Math.round(Math.random() * 999));
		info.setDescription("01010001");
		info.setClient("lalalka");
		// info.setStartDate(startDate);
		info.setEndDate(endDate);
		info.setProjectName("" + Math.round(Math.random() * 999));
		try {
			pBC.createProject(info);
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("start.date.required.exception"))
				return;
		} catch (HRSSystemException e) {
			e.printStackTrace();
			fail("Wrong exception catched. Smth wrong with IDGen or something");
		}
		fail("Did not catch an expected exception");
	}
	@Ignore
	@Test
	public void testSearchProject() throws HRSLogicalException, HRSSystemException {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("2007-02-23");
		Date endDate = Date.valueOf("2007-05-01");
		
		info.setProjectId("123321");
		info.setDescription("asfsawww222444");
		info.setClient("asdsafsafsaf");
		info.setStartDate(startDate);
		info.setEndDate(endDate);
		info.setProjectName("" + Math.round(Math.random() * 999));

		long id = 0;

		if (info == null)
			throw new HRSLogicalException("invalid.input.exception");

		if (info.getStartDate() == null)
			throw new HRSLogicalException("start.date.required.exception");

		Connection conn = null;

		try {
			conn = dbAccess.getConnection();
			projDao.create(conn, info);
			dbAccess.commitConnection(conn);
			info = pBC.searchProject("123321");
			assertNotNull(info);
			projDao.remove(conn, "123321");
		} catch (DBAccessException e) {
			try {
				dbAccess.rollbackConnection(conn);
			} catch (DBAccessException e1) {
			}
			throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} catch (DAOException e) {
			try {
				dbAccess.rollbackConnection(conn);
			} catch (DBAccessException e1) {
			}
			if (e.isLogical())
				throw new HRSLogicalException(e.getMessageKey() + ".employee");
			else
				throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} finally {
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {
			}
		}

		
		
		System.err.println(info.getProjectId());
	}
	@Ignore
	@Test
	public void testSearchProjectWrongID() {
		String id = null;
		try {
			pBC.searchProject(id);
		} catch (HRSSystemException e) {
			fail("smth  wrong, coz wrong Exception thrown");
			e.printStackTrace();
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("id.required.exception"))
				return;
		}
		fail("smth  wrong, coz no exception thrown");
	}
	@Ignore
	@Test
	public void testSearchProjectNotFound() {
		try {
			pBC.searchProject("123456789876");
		} catch (HRSSystemException e) {
			System.err.println(e.getMessageKey());
			fail("smth wrong with DAO or IDGens, coz wrong Exception thrown");
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("record.not.found.exception"))
				return;
		}
		fail("smth  wrong, coz no exception thrown");
	}
	
	//@Ignore
	@Test
	public void testUpdateProject() throws HRSLogicalException,
			HRSSystemException {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("2000-02-23");
		Date endDate = Date.valueOf("2007-05-01");

		//info.setProjectId("" + Math.round(Math.random() * 999));
		long id = 199115;
		info.setDescription("01010001");
		info.setClient("lalalka");
		info.setProjectId(""+id);
		info.setStartDate(startDate);
		info.setEndDate(endDate);
		info.setStatus("APPROOVED");
		info.setProjectName("" + Math.round(Math.random() * 999));
		
		Connection conn = null;

		try {
			conn = dbAccess.getConnection();
			//infoNew = info.getClass().newInstance();
			projDao.create(conn, info);
			//dbAccess.commitConnection(conn);
			
			info.setDescription("NEWINFO");
			info.setClient("NEW_CLIENT");
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {
			}
			pBC.updateProject(info);
			
			assertEquals(info, pBC.searchProject(""+info.getProjectId()));
			//projDao.remove(conn, ""+id);
		} catch (DBAccessException e) {
			try {
				dbAccess.rollbackConnection(conn);
			} catch (DBAccessException e1) {
			}
			throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} catch (DAOException e) {
			try {
				dbAccess.rollbackConnection(conn);
			} catch (DBAccessException e1) {
			}
			if (e.isLogical())
				throw new HRSLogicalException(e.getMessageKey() + ".employee");
			else
				throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} finally {
			try {
				projDao.remove(conn, ""+id);
			} catch (DAOException e) {
			}
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {
			}

		}
		
		
	}
	
	@Ignore
	@Test
	public void testUpdateProjectNull() {
		ProjectInfo info = null;
		try {
			pBC.updateProject(info);
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("invalid.input.exception"))
				return;
		} catch (HRSSystemException e) {
			e.printStackTrace();
			fail("Wrong exception catched. Smth wrong with IDGen or something");
		}
		fail("Did not catch an expected exception");
	}
	
	@Ignore
	@Test
	public void searchReferenceData() {
		// TODO: Someone
		// Any ideas how to do it ?
		fail("Not yet implemented");
	}
}
