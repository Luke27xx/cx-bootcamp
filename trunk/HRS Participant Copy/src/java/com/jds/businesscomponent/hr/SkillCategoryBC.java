package com.jds.businesscomponent.hr;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import javax.sql.RowSet;

import com.jds.apps.Constants;
import com.jds.apps.beans.AbstractReferenceData;
import com.jds.apps.beans.AccentureDetails;
import com.jds.apps.beans.EmployeeInfo;
import com.jds.apps.beans.SkillCategory;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.Logger;
import com.jds.architecture.ServiceFactory;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.logging.LoggerService;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DAOFactory;
import com.jds.architecture.service.dao.DataAccessObjectInterface;
import com.jds.architecture.service.dao.SkillCategoryDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.CategoryIdGenerator;
import com.jds.architecture.service.idgenerator.IdGeneratorException;

public class SkillCategoryBC {

	private Constants cons;
	private DataAccessObjectInterface catDao = null;
	// private DataAccessObjectInterface catAccDao = null;
	private DBAccess dbAccess = null;

	private static Logger log = (Logger) ServiceFactory.getInstance()
			.getService(LoggerService.class);

	// ==================================================================
	public SkillCategoryBC() throws HRSSystemException {

		log.info("entered SkilCategoryBC constructor");

		try {
			catDao = (SkillCategoryDAO) DAOFactory.getFactory().getDAOInstance(
					DAOConstants.DAO_SKILLCAT);

			// TODO: For implementation.
			//					
			// empAccDao = (EmpAccentureDetailsDAO)DAOFactory.getFactory()
			// .getDAOInstance(DAOConstants.DAO_EMPACC);

			dbAccess = DBAccess.getDBAccess();
			cons = new Constants();

		} catch (DBAccessException e) {
			throw new HRSSystemException("initialize.dbaccess.exception", e
					.getCause());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new HRSSystemException("initialize.dao.exception", e
					.getCause());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HRSSystemException("initialize.bc.exception", e
					.getCause());
		}

		log.info("exited SkilCategoryBC constructor");
	}

	// ========================================================================

	public void createCategory(SkillCategory info) throws HRSSystemException,
			HRSLogicalException {

		log.info("entered createCategory method");

		long id = 0;

		if (info == null)
			throw new HRSLogicalException("invalid.input.exception");

		Connection conn = null;

		try {
			conn = dbAccess.getConnection();

			id = CategoryIdGenerator.getInstance().getNextId();

			info.setCategoryId(String.valueOf(id));

			catDao.create(conn, info);
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
				throw new HRSLogicalException(e.getMessageKey()
						+ ".skillcategory");
			else
				throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} finally {
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {
			}
		}

		log.info("exited createEmployee method");

	}

	// =========================================================

	public SkillCategory searchCategory(String skillcat)
			throws HRSSystemException, HRSLogicalException {

		log.info("entered searchEmployee method");

		if (skillcat == null)
			throw new HRSLogicalException("id.required.exception");

		SkillCategory data = null;

		Object temp = null;

		try {
			data = (SkillCategory) catDao.findByPK(skillcat);

			if (data == null)
				throw new HRSLogicalException("record.not.found.exception");

		} catch (DAOException e) {
			throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} catch (Exception e) {
			throw new HRSSystemException("business.component.exception", e
					.getCause());
		}

		log.info("exited searchEmployee method");

		return data;
	}

	// ============================================================
	public Collection<SkillCategory> searchReferencesDate(
			AbstractReferenceData dataFind, String approvalType)
			throws HRSSystemException, HRSLogicalException, DAOException {

		log.info("entered searchSkillCategory method");

		RowSet data;
		SkillCategory data2;
		if (dataFind == null) {

			data = catDao.findByAll();
			data2 = (SkillCategory) data;
			return (Collection<SkillCategory>) data2;

		}
		// SkillCategory data3 = null;
		Object temp = null;
		List list = null;
		List list2 = null;

		try {
			list = (List) catDao.find(dataFind);

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).equals(approvalType)) {

					list2.add(dataFind.getStatus());
				}
			}

		} catch (DAOException e) {
			throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} catch (Exception e) {
			throw new HRSSystemException("business.component.exception", e
					.getCause());
		}

		log.info("exited searchEmployee method");

		Collection<SkillCategory> data4 = (Collection<SkillCategory>) list2;
		return data4;
	}

	// ==============================================
	public Collection<SkillCategory> searchApprovedCategories(
			SkillCategory dataFind) throws HRSSystemException, HRSLogicalException, DAOException {
		
		Collection<SkillCategory> result =  searchReferencesDate(dataFind, "APPROVED");
		
		return result;

	}

	// ==============================================

	public void updateSkillCategory(SkillCategory info)
			throws HRSLogicalException, HRSSystemException {

		log.info("entered createCategory method");

		Object id = 0;

		if (info == null)
			throw new HRSLogicalException("invalid.input.exception");

		Connection conn = null;

		try {
			conn = dbAccess.getConnection();

			id = CategoryIdGenerator.getInstance().getNextId();

			if (id == null)
				throw new HRSLogicalException("id.required.exception");
			// ---------------------------------------------------------

			Object whereUpdate = (Object) catDao.findByPK(info.getId());

			Object setUpdate = (Object) info;

			if (!(catDao.update(conn, setUpdate, whereUpdate)))
				throw new HRSLogicalException("record.not.updated.exception");

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
				throw new HRSLogicalException(e.getMessageKey()
						+ ".skillcategory");
			else
				throw new HRSSystemException(e.getMessageKey(), e.getCause());
		} finally {
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {
			}
		}

		log.info("exited createEmployee method");

	}
}
