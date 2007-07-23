/**
 * 
 */
package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jds.apps.beans.AccentureDetails;

/**
 * @author M.Skovitovs
 *
 */
public class AccDetailsAssembler {
	/**
	 * Populates prepared statement using details of value object
	 * @param accentureDet AccentureDetails value object
	 * @param stmt prepared statement from data access object
	 * @throws SQLException 
	 */
	public static void  getPreparedStatement(AccentureDetails accentureDet, 
			PreparedStatement stmt) throws SQLException {
		stmt.setString(1, accentureDet.getEmployeeNo());
		stmt.setString(2, accentureDet.getEnterpriseId());
		stmt.setString(3, accentureDet.getEnterpriseAddress());
		stmt.setString(4, accentureDet.getLevel());
		stmt.setString(5, accentureDet.getLMU());
		stmt.setString(6, accentureDet.getStatus());
		stmt.setDate(7, new java.sql.Date(accentureDet.getDateHired().getTime()));
		stmt.setString(8, accentureDet.getGMU());
		stmt.setString(9, accentureDet.getWorkGroup());
		stmt.setString(10, accentureDet.getSpecialty());
		stmt.setString(11, accentureDet.getServiceLine());
	}
}
