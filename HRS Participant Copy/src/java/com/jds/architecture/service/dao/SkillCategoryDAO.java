/*
 * Created on Dec 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.RowSet;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;

import com.jds.apps.beans.SkillCategory;
import com.jds.architecture.Logger;
import com.jds.architecture.ServiceFactory;
import com.jds.architecture.logging.LoggerService;
import com.jds.architecture.service.dao.assembler.SkillCategoryAssembler;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenCategory;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenEmployee;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenerator;
import com.jds.architecture.service.dao.stmtgenerator.StatementGeneratorFactory;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.sun.rowset.CachedRowSetImpl;

/**
 * <p>
 * EmployeeDAO is data access object class for Employee table
 * </p>
 * 
 * 
 * @author E. Iljichov
 * @version 11/2004 initial draft c.b.balajadia
 * @since 1.1
 */
public class SkillCategoryDAO implements DataAccessObjectInterface {

 private DBAccess dbAccess = null;
 private static Logger log = (Logger) ServiceFactory.getInstance()
   .getService(LoggerService.class);

 StatementGenerator stmtGen = null;

 /**
  * Constructor initializes variables
  * 
  * @throws DAOException
  * @throws DBAccessException
  */
 protected SkillCategoryDAO() throws DAOException, DBAccessException {
  log.info("initializing SkillCategoryDAO");
  dbAccess = DBAccess.getDBAccess();
  stmtGen = StatementGeneratorFactory.getGenerator().getStmtGenerator(
    DAOConstants.GEN_SKILLCAT);

 }

 /**
  * Creates or insert new record to the table
  * 
  * @param Connection -
  *            database connection
  * @param Object -
  *            must be an instance of EmployeeInfo,contains object for insert
  */
 public void create(Connection conn, Object object) throws DAOException {

  if (!(object instanceof SkillCategory))
   throw new DAOException("invalid.object.empdao", null,
     DAOException.ERROR, true);

  String sqlstmt = DAOConstants.SKILLCAT_CREATE;
  SkillCategory skillcategory = (SkillCategory) object;

  if (skillcategory.getId()== null)
   throw new DAOException("invalid.object.empdao", null,
     DAOException.ERROR, true);

  log.debug("creating SkillCategory entry");
  try {
   PreparedStatement stmt = conn.prepareStatement(sqlstmt);
   SkillCategoryAssembler.getPreparedStatement(skillcategory, stmt);
   stmt.executeUpdate();

   log.debug("created SkillCategory entry");
  } catch (SQLException e) {
   throw new DAOException("sql.create.exception.empdao", e,
     DAOException.ERROR, true);
  } catch (Exception e) {
   throw new DAOException("create.exception.empdao", e.getCause(),
     DAOException.ERROR, true);

  } 
 }

 /**
  * Removes a record from the table
  * 
  * @param Connection -
  *            database connection
  * @param Object -
  *            must be an instance of String , primary key of the object
  */
 public boolean remove(Connection conn, Object object) throws DAOException {
  // SkillCategory eInf;
  String query = DAOConstants.SKILLCAT_DELETE;
  //
  PreparedStatement stmnt = null;
  if ((object == null) || (!(object instanceof String))) {

  }

  try {
   // eInf = (SkillCategory)object;
   stmnt = conn.prepareStatement(query);
   stmnt.setString(1, (String) object);
   stmnt.executeUpdate();
  } catch (Exception e) {
   e.printStackTrace();
  } finally {
   try {
    stmnt.close();
   } catch (Exception e) {
    e.printStackTrace();
   }
  }

  return true;
 }

 /**
  * Finds a record from the table
  * 
  * @return Object - String
  * @param Object -
  *            String instance, primary key of the record
  */
 public Object findByPK(Object object) throws DAOException {
  String sqlStmt = DAOConstants.SKILLCAT_FIND_BYPK;
  SkillCategory SkillCategoryReturn = null;

  if (!(object instanceof String))
   throw new DAOException("invalid.object.empdao", null,
     DAOException.ERROR, true);
  sqlStmt = "SELECT * FROM employee WHERE empno = " + object;
  // String pk = (String) object;
  Connection conn = null;

  try {
   log.debug("finding pk SkillCategory entry");
   conn = dbAccess.getConnection();

   PreparedStatement stmt = conn.prepareStatement(sqlStmt);
   // stmt.setString(1, pk);
   ResultSet rs = stmt.executeQuery();

   if (rs.next()) {
    // System.out.print("RS<>" + rs.getString(2));
    SkillCategoryReturn = SkillCategoryAssembler.getInfo(rs);
   }

   rs.close();
   log.debug("found by pk SkillCategory entry");
  } catch (DBAccessException e) {
   throw new DAOException(e.getMessageKey(), e, DAOException.ERROR,
     true);
  } catch (SQLException e) {
   throw new DAOException("sql.findpk.exception.empdao", e,
     DAOException.ERROR, true);
  } finally {
   try {
    dbAccess.closeConnection(conn);
   } catch (DBAccessException e1) {

   }
  }

  return SkillCategoryReturn;

 }

 /**
  * Finds all records that matches the criteria
  * 
  * @param Object -
  *            instance of SkillCategory used as search criteria
  * @return RowSet - rowset of found records
  */
 public RowSet find(Object object) throws DAOException {
  SkillCategory eInf = (SkillCategory) object;
  String query = DAOConstants.SKILLCAT_FIND_MAIN;
  String criteria = "name like \"%" + eInf.getCategoryName() + "%\"";
  query = query.replaceAll("@", criteria);
  ResultSet rs;
  CachedRowSet result = null;
  Connection conn = null;

  try {
   log.debug("finding all SkillCategory entries");
   conn = dbAccess.getConnection();
   result = new CachedRowSetImpl();

   PreparedStatement stmnt = conn.prepareStatement(query);
   rs = stmnt.executeQuery();

   result.populate(rs);
   rs.close();
   stmnt.close();
  }

  catch (DBAccessException e) {
   throw new DAOException(e.getMessageKey(), e, DAOException.ERROR,
     true);
  } catch (SQLException e) {
   throw new DAOException("sql.findpk.exception.empdao", e,
     DAOException.ERROR, true);
  }

  finally {
   try {
    dbAccess.closeConnection(conn);
   } catch (DBAccessException e1) {

   }
  }

  return result;
 }

 /**
  * @param Connectin -
  *            database connection
  * @param ObjectSet -
  *            instance of SkillCategory, set the new values of a particular
  *            record
  * @param objWher-
  *            instance of SkillCategory, used as update criteria
  * @return boolean - true if record is updated
  */
 public boolean update(Connection conn, Object objSet, Object objWhere)
   throws DAOException {

  if ((!(objSet instanceof SkillCategory))
    || (!(objWhere instanceof SkillCategory)))
   throw new DAOException("invalid.object.empdao", null,
     DAOException.ERROR, true);

  SkillCategory skillCategorySet = (SkillCategory) objSet;
  SkillCategory skillCategoryWhere = (SkillCategory) objWhere;

  String sqlstmt = DAOConstants.SKILLCAT_UPDATE_MAIN;

  try {
   StatementGenerator gen = new StatementGenCategory();

   String set = gen.transformStmt(skillCategorySet,
     DAOConstants.STMT_TYPE_SET);
   String where = gen.transformStmt(skillCategoryWhere,
     DAOConstants.STMT_TYPE_WHERE);

   sqlstmt = sqlstmt.replaceFirst("@", set).replaceFirst("@", where);

   PreparedStatement st = conn.prepareStatement(sqlstmt);
   st.executeQuery();

  } catch (SQLException e) {
   throw new DAOException("sql.create.exception.empdao", e,
     DAOException.ERROR, true);

  } catch (Exception e) {
   throw new DAOException("create.exception.empdao", e.getCause(),
     DAOException.ERROR, true);
  }

  return true;

 }

 public RowSet findByAll() throws DAOException {
  String sqlStmt = DAOConstants.SKILLCAT_FIND_ALL;
  SkillCategory skillCategoryReturn = null;
  RowSet rowS = null;
  CachedRowSet crset = null;
  Connection conn = null;

  try {
   log.debug("finding all SkillCategory entries");
   conn = dbAccess.getConnection();
   PreparedStatement stmt = conn.prepareStatement(sqlStmt);
   ResultSet rs = stmt.executeQuery();
   crset = new CachedRowSetImpl();
   crset.populate(rs);
   rs.close();
   stmt.close();
   log.debug("found All Employee entries");
  } catch (DBAccessException e) {
   e.printStackTrace();
   throw new DAOException(e.getMessageKey(), e, DAOException.ERROR,
     true);

  } catch (SQLException e) {
   throw new DAOException("sql.findpk.exception.empdao", e,
     DAOException.ERROR, true);
  } finally {
   try {

    dbAccess.closeConnection(conn);

   } catch (DBAccessException e1) {

   }
  }

  return crset;

 }

}