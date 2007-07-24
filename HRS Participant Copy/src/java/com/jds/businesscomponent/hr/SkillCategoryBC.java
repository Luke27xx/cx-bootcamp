package com.jds.businesscomponent.hr;

import java.sql.Connection;
import java.util.Collection;

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
	//private DataAccessObjectInterface catAccDao = null;
	private DBAccess dbAccess = null;

	private static Logger log = (Logger) ServiceFactory.getInstance()
			.getService(LoggerService.class);
//==================================================================
	public SkillCategoryBC () throws HRSSystemException {
		
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
	
//========================================================================	
	
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
		throw new HRSLogicalException(e.getMessageKey() + ".skillcategory");
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
	
//=========================================================
	
	public SkillCategory searchCategory(String skillcat) throws HRSSystemException,
	HRSLogicalException {

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
	
//============================================================	
	public Collection<SkillCategory> searchReferencesDate(AbstractReferenceData dataFind, String approvalType)
	throws HRSSystemException, HRSLogicalException, DAOException {

log.info("entered searchSkillCategory method");
RowSet data3;
SkillCategory data4;
if (info == null) {
	data3 = catDao.findByAll();
	// throw new HRSLogicalException ("id.required.exception");
	data4 = (SkillCategory) data3;
	return (Collection<SkillCategory>) data4;

}
SkillCategory data = null;
//AccentureDetails accData = null;
Object temp = null;

try {
	data3 = catDao.find(info);

	if (data == null)
		throw new HRSLogicalException("employee.no.record.exception");



} catch (DAOException e) {
	throw new HRSSystemException(e.getMessageKey(), e.getCause());
} catch (Exception e) {
	throw new HRSSystemException("business.component.exception", e
			.getCause());
}

log.info("exited searchEmployee method");

Collection<SkillCategory> data2 = (Collection<SkillCategory>) data;
return data2;
}

//==============================================

//==============================================
	
public void updateSkillCategory(SkillCategory info) {

}
}
