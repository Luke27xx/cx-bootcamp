/*
 * Created on Feb 14, 2005
 * 
 * JAVA Development School
 * Copyright 2005 Accenture
 *
 */

package com.jds.businesscomponent.hr;

import java.sql.Connection;
import java.util.Collection;

import javax.sql.RowSet;

import com.jds.apps.Constants;
//import com.jds.apps.beans.AccentureDetails;
import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.Logger;
import com.jds.architecture.ServiceFactory;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.logging.LoggerService;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DAOFactory;
import com.jds.architecture.service.dao.DataAccessObjectInterface;
import com.jds.architecture.service.dao.ProjectDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.ProjectIdGenerator;
import com.jds.architecture.service.idgenerator.IdGeneratorException;

/**
 * ProjectBC is a class that serves as an HR module business functions
 * 
 * @author aldie.m.lim
 * @author last modified by: $Author: c.b.balajadia $
 * @version 2.0
 */
public class ProjectBC {

	private Constants cons;
	private DataAccessObjectInterface projDao = null;
	//private DataAccessObjectInterface empAccDao = null;
	private DBAccess dbAccess = null;

	private static Logger log = (Logger) ServiceFactory.getInstance()
			.getService(LoggerService.class);

	/**
	 * Constructor initializes data access objects
	 */
	public ProjectBC() throws HRSSystemException {

		log.info("entered ProjectBC constructor");

		try {
			projDao = (ProjectDAO) DAOFactory.getFactory().getDAOInstance(
					DAOConstants.DAO_PROJ);

			
			dbAccess = DBAccess.getDBAccess();
			cons = new Constants();

		} catch (DBAccessException e) {
			throw new HRSSystemException("intialize.dbaccess.exception", e
					.getCause());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new HRSSystemException("intialize.dao.exception", e
					.getCause());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HRSSystemException("intialize.project.bc.exception", e
					.getCause());
		}

		log.info("exited ProjectBC constructor");

	}

	/**
	 * Create employee information by HRManager
	 * Validate the ProjectInfo object passed:
	 * @param info
	 *            ProjectInfo
	 * @throws HRSLogicalException with message key “invalid.input.exception”
	 *             object passed is null
	 * @throws HRSLogicalException with message key “start.date.required.exception”
	 *             If its start date is null
	 * @throws HRSSystemException 
	 */
	public void createProject(ProjectInfo info) throws HRSLogicalException, HRSSystemException {

		log.info("entered createProject method");

		long id = 0;

		if (info == null)
			throw new HRSLogicalException("invalid.input.exception");
		
		if (info.getStartDate()==null)
			throw new HRSLogicalException("start.date.required.exception");

		Connection conn = null;

		try {
			conn = dbAccess.getConnection();
			id = ProjectIdGenerator.getInstance().getNextId();
			info.setProjectId(String.valueOf(id));
			projDao.create(conn, info);
			RowSet set = projDao.find(info);

			dbAccess.commitConnection(conn);
		} catch (IdGeneratorException e) {
			try {
				dbAccess.rollbackConnection(conn);
			} catch (DBAccessException e1) {
			}
			throw new HRSSystemException(e.getMessageKey(), e.getCause());
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

		System.out.println("exited createProject method");

	}

	/**
	 * Searches employee by primary key which is the employee no. by HRManager
	 * 
	 * @param id
	 *            String
	 * @return ProjectInfo object of searched Project with Accenture Details
	 *         as a default and with all the employee related objects as an
	 *         option
	 * @throws HRSLogicalException
	 *             when employee no. is null
	 * @throws HRSSystemException
	 *             when system exception occurred (e.g. Failed database
	 *             connection)
	 */
	public ProjectInfo searchProject(String id) throws HRSSystemException,
			HRSLogicalException {

		System.out.println("entered searchProject method");

		if (id == null)
			throw new HRSLogicalException("id.required.exception");

		ProjectInfo data = null;

		try {
			data = (ProjectInfo) projDao.findByPK(id);

			if (data == null)
				throw new HRSLogicalException("record.not.found.exception");
			
		} catch (DAOException e) {
			throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} catch (Exception e) {
			throw new HRSSystemException("business.component.exception", e
					.getCause());
		}

		System.out.println("exited searchProject method");

		return data;
	}

	public Collection<ProjectInfo> searchProjects(ProjectInfo info)
			throws HRSSystemException, HRSLogicalException, DAOException {

		log.info("entered searchProject method");
		RowSet data3;
		ProjectInfo data4;
		if (info == null) {
			data3 = projDao.findByAll();
			// throw new HRSLogicalException ("id.required.exception");
			data4 = (ProjectInfo) data3;
			return (Collection<ProjectInfo>) data4;

		}
		ProjectInfo data = null;
		//AccentureDetails accData = null;
		Object temp = null;

		try {
			data3 = projDao.find(info);

			if (data == null)
				throw new HRSLogicalException("employee.no.record.exception");

			//temp = empAccDao.findByPK(data3.getInt(1));

			//if (temp == null)
			//	throw new HRSLogicalException(
			//			"accenture.details.no.record.exception");

			//accData = (AccentureDetails) temp;
			//data.setAccentureDetails(accData);

		} catch (DAOException e) {
			throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} catch (Exception e) {
			throw new HRSSystemException("business.component.exception", e
					.getCause());
		}

		log.info("exited searchProject method");

		Collection<ProjectInfo> data2 = (Collection<ProjectInfo>) data;
		return data2;
	}

	public void updateProject(ProjectInfo info) {

	}
}
