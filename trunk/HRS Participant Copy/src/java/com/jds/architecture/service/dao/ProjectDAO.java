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

import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.Logger;
import com.jds.architecture.ServiceFactory;
import com.jds.architecture.logging.LoggerService;
import com.jds.architecture.service.dao.assembler.ProjectAssembler;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenProject;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenerator;
import com.jds.architecture.service.dao.stmtgenerator.StatementGeneratorFactory;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.sun.rowset.CachedRowSetImpl;

/**
 * <p>
 * ProjectDAO is data access object class for Project table
 * </p>
 * 
 * 
 * @author c.b.balajadia
 * @version 11/2004 initial draft c.b.balajadia
 * @since 1.1
 */
public class ProjectDAO implements DataAccessObjectInterface {

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
	protected ProjectDAO() throws DAOException, DBAccessException {
		log.info("initializing ProjectDAO");
		dbAccess = DBAccess.getDBAccess();
		stmtGen = StatementGeneratorFactory.getGenerator().getStmtGenerator(
				DAOConstants.GEN_PROJ);

	}

	/**
	 * Creates or insert new record to the table
	 * 
	 * @param Connection -
	 *            database connection
	 * @param Object -
	 *            must be an instance of ProjectInfo,contains object for insert
	 */
	public void create(Connection conn, Object object) throws DAOException {

		if (!(object instanceof ProjectInfo))
			throw new DAOException("invalid.object.projdao", null,
					DAOException.ERROR, true);

		String sqlstmt = DAOConstants.PROJ_CREATE;
		ProjectInfo project = (ProjectInfo) object;

		if (project.getProjectId() == null)
			throw new DAOException("invalid.object.projdao", null,
					DAOException.ERROR, true);

		log.debug("creating ProjectInfo entry");
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlstmt);
			ProjectAssembler.getPreparedStatement(project, stmt);
			
			stmt.executeUpdate();
			
			stmt.close();
			log.debug("created ProjectInfo entry");
		} catch (SQLException e) {
			throw new DAOException("sql.create.exception.projdao", e,
					DAOException.ERROR, true);
		} catch (Exception e) {
			throw new DAOException("create.exception.projdao", e.getCause(),
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
		
		String arg = (String) object;
		String query = DAOConstants.PROJ_DELETE;
		PreparedStatement stmnt = null;
		if ((object == null) || (!(object instanceof String))) {
			if (arg.matches("^0-9")){
				throw new DAOException("invalid.object.empdao", null,
						DAOException.ERROR, true);
			}
		}

		try {
			// eInf = (ProjectInfo)object;
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, arg);
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
		String sqlStmt = DAOConstants.PROJ_FIND_BYPK;
		String arg = (String)object;
		ProjectInfo projectReturn = null;

		if ((object == null) || (!(object instanceof String))) {
			if (arg.matches("^0-9")){
				throw new DAOException("invalid.object.projdao", null,
						DAOException.ERROR, true);
			}
		}
		
		sqlStmt = "SELECT * FROM project WHERE id = " + object;
		//String pk = (String)object;
		Connection conn = null;

		try {
			log.debug("finding pk ProjectInfo entry");
			conn = dbAccess.getConnection();

			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			//stmt.setString(1, pk);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// System.out.print("RS<>" + rs.getString(2));
				projectReturn = ProjectAssembler.getInfo(rs);
			}

			rs.close();
			stmt.close();
			
			log.debug("found by pk ProjectInfo entry");
		} catch (DBAccessException e) {
			throw new DAOException(e.getMessageKey(), e, DAOException.ERROR,
					true);
		} catch (SQLException e) {
			throw new DAOException("sql.findpk.exception.projdao", e,
					DAOException.ERROR, true);
		} finally {
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {

			}
		}

		return projectReturn;

	}

	/**
	 * Finds all records that matches the criteria
	 * 
	 * @param Object -
	 *            instance of ProjectInfo used as search criteria
	 * @return RowSet - rowset of found records
	 */
	public RowSet find(Object object) throws DAOException {
		
		if ((object == null)|(!(object instanceof ProjectInfo))){
			throw new DAOException("invalid.object.projdao", null,
			DAOException.ERROR, true);
		}
		ProjectInfo eInf = (ProjectInfo) object;
		String query = DAOConstants.PROJ_FIND_MAIN;
		String criteria = "";
		StatementGenProject stmtGen = new StatementGenProject();

		try {
			criteria = stmtGen
					.transformStmt(eInf, DAOConstants.STMT_TYPE_WHERE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		Connection conn = null;
		query = query.replaceFirst("@", criteria);
		CachedRowSet result = null;
		try {
			log.debug("finding all ProjectInfo entries");
			conn = dbAccess.getConnection();
			result = new CachedRowSetImpl();
			PreparedStatement stmnt = conn.prepareStatement(query);
			ResultSet rs = stmnt.executeQuery();
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
	 *            instance of ProjectInfo, set the new values of a particular
	 *            record
	 * @param objWher-
	 *            instance of ProjectInfo, used as update criteria
	 * @return boolean - true if record is updated
	 */
	public boolean update(Connection conn, Object objSet, Object objWhere)
			throws DAOException {

		if ((!(objSet instanceof ProjectInfo))
				|| (!(objWhere instanceof ProjectInfo)))
			throw new DAOException("invalid.object.empdao", null,
					DAOException.ERROR, true);

		ProjectInfo projectSet = (ProjectInfo) objSet;
		ProjectInfo projectWhere = (ProjectInfo) objWhere;

		String sqlstmt = DAOConstants.PROJ_UPDATE_MAIN;

		try {
			StatementGenerator gen = new StatementGenProject();

			String set = gen.transformStmt(projectSet,
					DAOConstants.STMT_TYPE_SET);
			String where = gen.transformStmt(projectWhere,
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
		String sqlStmt = DAOConstants.EMPSQL_FIND_ALL;
		ProjectInfo projectReturn = null;
		RowSet rowS = null;
		CachedRowSet crset = null;
		Connection conn = null;

		try {
			log.debug("finding all ProjectInfo entries");
			conn = dbAccess.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			ResultSet rs = stmt.executeQuery();
			crset = new CachedRowSetImpl();
			crset.populate(rs);
			rs.close();
			stmt.close();
			log.debug("found All Project entries");
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