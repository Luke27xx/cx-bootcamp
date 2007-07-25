/*
 * Created on Jul 23, 2007
 *
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

// import com.jds.apps.beans.EmployeeInfo;
import com.jds.apps.beans.SkillsInformation;
import com.jds.apps.beans.valueutil.SkillInformationValueUtil;
import com.jds.architecture.Logger;
import com.jds.architecture.ServiceFactory;
import com.jds.architecture.logging.LoggerService;
import com.jds.architecture.service.dao.assembler.SkillAssembler;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenSkill;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenerator;
import com.jds.architecture.service.dao.stmtgenerator.StatementGeneratorFactory;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.sun.rowset.CachedRowSetImpl;

/**
 * <p>
 * SkillDAO is data access object class for Skill table
 * </p>
 * 
 * 
 * @author j.zenkovs
 */

public class SkillDAO implements DataAccessObjectInterface {

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
	protected SkillDAO() throws DAOException, DBAccessException {
		log.info("initializing SkillDAO");
		dbAccess = DBAccess.getDBAccess();
		stmtGen = StatementGeneratorFactory.getGenerator().getStmtGenerator(
				DAOConstants.GEN_SKILL);

	}

	/**
	 * Creates or insert new record to the table
	 * 
	 * @param Connection -
	 *            database connection
	 * @param Object -
	 *            must be an instance of SkillsInformation,contains object for
	 *            insert
	 */
	public void create(Connection conn, Object object) throws DAOException {

		if (!(object instanceof SkillsInformation))
			throw new DAOException("invalid.object.skilldao", null,
					DAOException.ERROR, true);

		String sqlstmt = DAOConstants.SKILL_CREATE;
		SkillsInformation skill = (SkillsInformation) object;

		if (skill.getCategoryId() == null)
			throw new DAOException("invalid.object.skilldao", null,
					DAOException.ERROR, true);

		log.debug("creating SkillsInformation entry");
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlstmt);
			SkillAssembler.getPreparedStatement(skill, stmt);
			stmt.executeUpdate();

			log.debug("created SkillsInformation entry");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("sql.create.exception.skilldao", e,
					DAOException.ERROR, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("create.exception.skilldao", e.getCause(),
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
		String query = DAOConstants.SKILL_DELETE;
		PreparedStatement stmnt = null;
		if ((object == null) || (!(object instanceof String))) {

		}

		try {
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
		String sqlStmt = DAOConstants.SKILL_FIND_BYPK;
		SkillsInformation skillReturn = null;

		if (!(object instanceof String))
			throw new DAOException("invalid.object.empdao", null,
					DAOException.ERROR, true);
		//SkillsInformation brr = (SkillsInformation)object;
		sqlStmt = "SELECT * FROM skill WHERE id = " + object;
		//SkillsInformation pk = (SkillsInformation) object;
		//System.out.println(pk.toString());
		Connection conn = null;

		try {
			log.debug("finding pk SkillsInformation entry");
			conn = dbAccess.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			//SkillAssembler.getPreparedStatement(pk, stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				skillReturn = SkillAssembler.getInfo(rs);
			}
			rs.close();
			log.debug("found by pk SkillsInformation entry");
		} catch (DBAccessException e) {
			throw new DAOException(e.getMessageKey(), e, DAOException.ERROR,
					true);
		} catch (SQLException e) {
			throw new DAOException("sql.findpk.exception.skilldao", e,
					DAOException.ERROR, true);
		} finally {
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {

			}
		}

		return skillReturn;

	}

	/**
	 * Finds all records that matches the criteria
	 * 
	 * @param Object -
	 *            instance of SkillInformation used as search criteria
	 * @return RowSet - rowset of found records
	 */
	public RowSet find(Object object) throws DAOException {
		SkillsInformation eInf = (SkillsInformation) object;
		String query = DAOConstants.SKILL_FIND_MAIN;
		String criteria = "name like \"%" + eInf.getSkillName();
		query = query.replaceAll("@", criteria);
		ResultSet rs;
		CachedRowSet result = null;
		Connection conn = null;

		try {
			log.debug("finding all SkillsInformation entries");
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
			throw new DAOException("sql.findpk.exception.skilldao", e,
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
	 *            instance of SkillInformation, set the new values of a
	 *            particular record
	 * @param objWher-
	 *            instance of SkillInformation, used as update criteria
	 * @return boolean - true if record is updated
	 */
	public boolean update(Connection conn, Object objSet, Object objWhere)
			throws DAOException {

		if ((!(objSet instanceof SkillsInformation))
				|| (!(objWhere instanceof SkillsInformation)))
			throw new DAOException("invalid.object.skilldao", null,
					DAOException.ERROR, true);

		SkillsInformation skillSet = (SkillsInformation) objSet;
		SkillsInformation skillWhere = (SkillsInformation) objWhere;

		String sqlstmt = DAOConstants.SKILL_UPDATE_MAIN;

		try {
			StatementGenerator gen = new StatementGenSkill();

			String set = gen
					.transformStmt(skillSet, DAOConstants.STMT_TYPE_SET);
			String where = gen.transformStmt(skillWhere,
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
		String sqlStmt = DAOConstants.EMPSKILLSQL_FIND_ALL;
		SkillsInformation skillReturn = null;
		RowSet rowS = null;
		CachedRowSet crset = null;
		Connection conn = null;

		try {
			log.debug("finding all SkillInformation entries");
			conn = dbAccess.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			ResultSet rs = stmt.executeQuery();
			crset = new CachedRowSetImpl();
			crset.populate(rs);
			rs.close();
			stmt.close();
			log.debug("found All Skill entries");
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessageKey(), e, DAOException.ERROR,
					true);

		} catch (SQLException e) {
			throw new DAOException("sql.findpk.exception.skilldao", e,
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
