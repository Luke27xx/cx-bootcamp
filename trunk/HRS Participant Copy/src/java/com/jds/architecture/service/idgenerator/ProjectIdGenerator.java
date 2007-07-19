package com.jds.architecture.service.idgenerator;

	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;


	import com.jds.architecture.Logger;
	import com.jds.architecture.ServiceFactory;
	import com.jds.architecture.service.dbaccess.DBAccess;
	import com.jds.architecture.service.dbaccess.DBAccessException;
	import com.jds.architecture.service.transaction.TransactionException;

	/**
	 * <p>
	 * ProjectIdGenerator is a IdGenerator class for Project.
	 * </p> 
	 * 
	 *  							
	 * @author c.b.balajadia 
	 * @version  02/2004 initial draft c.b.balajadia
	  * @since 1.1
	 */
public class ProjectIdGenerator implements IdGeneratorInterface{

		private static ProjectIdGenerator  idGen = null;
		private String genID = "select seq_project.nextval from dual";
		private Statement stmt = null;
		private Connection conn = null;
		private ResultSet rs = null;
		private Logger logger = (Logger) ServiceFactory.getInstance()
		.getService(ProjectIdGenerator.class);
		
		private ProjectIdGenerator() {}
		
		/**
		 * Gets the instance of this class
		 * @return ProjectIdGenerator
		 */
		public static ProjectIdGenerator getInstance(){
			if (idGen == null) {
				idGen = new ProjectIdGenerator();
			}
			return idGen;
		}
		
		/**
		 * Gets the next id for Project table
		 * @return long -  generated id
		 */
		public long getNextId() throws IdGeneratorException {
			long id = 0;
			try {
				conn =  DBAccess.getDBAccess().getConnection();
			} catch (DBAccessException e) {
				logger.error("database connection error in id generator");
				throw new IdGeneratorException
				("connection.acquire.exception",e.getCause(),
				TransactionException.ERROR, true);
			}
			try {
				stmt = conn.createStatement();
			      rs = stmt.executeQuery(genID);
			      rs.next();
			      id = rs.getLong(1);
			      rs.close();
			      stmt.close();
			} catch (SQLException e) {
				throw new IdGeneratorException
				("id.generation.exception",e.getCause(),
						TransactionException.ERROR, true);
			} finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		    return id;

			
		}
	}