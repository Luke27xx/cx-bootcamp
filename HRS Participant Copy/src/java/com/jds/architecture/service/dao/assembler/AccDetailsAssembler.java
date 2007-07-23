/**
 * 
 */
package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.jds.apps.beans.AccentureDetails;
import com.jds.architecture.service.dao.DAOConstants;

/**
 * @author M.Skovitovs
 * 
 */
public class AccDetailsAssembler {
	/**
	 * Populates prepared statement using details of value object
	 * 
	 * @param accentureDet
	 *            AccentureDetails value object
	 * @param stmt
	 *            prepared statement from data access object
	 * @throws SQLException
	 */
	public static void getPreparedStatement(AccentureDetails accentureDet,
			PreparedStatement stmt) throws SQLException {
		stmt.setString(1, accentureDet.getEmployeeNo());
		stmt.setString(2, accentureDet.getEnterpriseId());
		stmt.setString(3, accentureDet.getEnterpriseAddress());
		stmt.setString(4, accentureDet.getLevel());
		stmt.setString(5, accentureDet.getLMU());
		stmt.setString(6, accentureDet.getStatus());
		stmt.setDate(7,
				new java.sql.Date(accentureDet.getDateHired().getTime()));
		stmt.setString(8, accentureDet.getGMU());
		stmt.setString(9, accentureDet.getWorkGroup());
		stmt.setString(10, accentureDet.getSpecialty());
		stmt.setString(11, accentureDet.getServiceLine());
	}

	/**
	 * Creates populated AccentureDetails vo from the Row set
	 * 
	 * @param rs
	 *            Row Set
	 * @return AccentureDetails
	 * @throws SQLException
	 */
	public static AccentureDetails getInfo(ResultSet rs) throws SQLException {
		AccentureDetails AccDetreturn = new AccentureDetails();
		AccDetreturn.setEmployeeNo(rs.getString("empNo"));
		AccDetreturn.setEnterpriseId(rs.getString("enterpriseId"));
		AccDetreturn.setEnterpriseAddress(rs.getString("emailAdd"));
		AccDetreturn.setLevel(rs.getString("empLevel"));

		AccDetreturn.setLMU(rs.getString("LMU"));

		AccDetreturn.setStatus(rs.getString("status"));
		AccDetreturn.setDateHired(new java.util.Date(((java.sql.Timestamp) rs
				.getObject("datehired")).getTime()));
		AccDetreturn.setGMU(rs.getString("GMU"));
		AccDetreturn.setWorkGroup(rs.getString("WORKGROUP"));
		AccDetreturn.setSpecialty(rs.getString("SPECIALTY"));
		AccDetreturn.setServiceLine(rs.getString("SERVICELINE"));

		return AccDetreturn;
	}

	/**
	 * Creates populated AccentureDetails vo from the Result set
	 * 
	 * @param rs
	 *            Result Set
	 * @return AccentureDetails
	 * @throws SQLException
	 */
	public static AccentureDetails getInfo(RowSet rs) throws SQLException {
		AccentureDetails AccDetreturn = new AccentureDetails();
		AccDetreturn.setEmployeeNo(rs.getString("empNo"));
		AccDetreturn.setEnterpriseId(rs.getString("enterpriseId"));
		AccDetreturn.setEnterpriseAddress(rs.getString("emailAdd"));
		AccDetreturn.setLevel(rs.getString("empLevel"));

		AccDetreturn.setLMU(rs.getString("LMU"));

		AccDetreturn.setStatus(rs.getString("status"));
		AccDetreturn.setDateHired(new java.util.Date(((java.sql.Timestamp) rs
				.getObject("datehired")).getTime()));
		AccDetreturn.setGMU(rs.getString("GMU"));
		AccDetreturn.setWorkGroup(rs.getString("WORKGROUP"));
		AccDetreturn.setSpecialty(rs.getString("SPECIALTY"));
		AccDetreturn.setServiceLine(rs.getString("SERVICELINE"));

		return AccDetreturn;
	}

	public static void toEmptyStringAllNull(AccentureDetails obj) {
		if (obj.getEnterpriseAddress() == null)
			obj.setEnterpriseAddress(DAOConstants.STR_SPACE);
		if (obj.getLevel() == null)
			obj.setLevel(DAOConstants.STR_SPACE);
		if (obj.getStatus() == null)
			obj.setStatus(DAOConstants.STR_SPACE);
		if (obj.getWorkGroup() == null)
			obj.setWorkGroup(DAOConstants.STR_SPACE);
		if (obj.getSpecialty() == null)
			obj.setSpecialty(DAOConstants.STR_SPACE);
		if (obj.getServiceLine() == null)
			obj.setServiceLine(DAOConstants.STR_SPACE);

	}

}
