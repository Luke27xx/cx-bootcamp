/*
 * Created on Jul 23, 2007
 */
package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOConstants;

/**
 * @author j.zenkovs SkillAssembler is a class used in populating prepared
 *         statement and in retrieving skill from result set.
 */

public class SkillAssembler {

	/**
	 * Populates prepared statement using details of value object
	 * 
	 * @param skill
	 *            Skill value object
	 * @param stmt
	 *            prepared statement from data access object
	 * @throws SQLException
	 */
	public static void getPreparedStatement(SkillsInformation skill,
			PreparedStatement stmt) throws SQLException {
		stmt.setString(1, skill.getSkillId());
		stmt.setString(2, skill.getCategoryId());
		stmt.setString(3, skill.getSkillName());
		stmt.setString(4, skill.getSkillDescription());
		//stmt.setString(5, skill.getStatus());

	}

	/**
	 * Creates populated SkillsInformation vo from the Row set
	 * 
	 * @param rs
	 *            Row Set
	 * @return SkillsInformation
	 * @throws SQLException
	 */
	public static SkillsInformation getInfo(ResultSet rs) throws SQLException {
		SkillsInformation skillReturn = new SkillsInformation();
		skillReturn.setSkillId(rs.getString("id"));
		skillReturn.setCategoryId(rs.getString("catId"));
		skillReturn.setSkillName(rs.getString("name"));
		skillReturn.setSkillDescription(rs.getString("description"));
		skillReturn.setStatus(rs.getString("status"));
		return skillReturn;
	}

	/**
	 * Creates populated SkillsInformation from the Result set
	 * 
	 * @param rs
	 *            Result Set
	 * @return SkillsInformation
	 * @throws SQLException
	 */
	public static SkillsInformation getInfo(RowSet rs) throws SQLException {
		SkillsInformation skillReturn = new SkillsInformation();
		skillReturn.setSkillId(rs.getString("id"));
		skillReturn.setCategoryId(rs.getString("catId"));
		skillReturn.setSkillName(rs.getString("name"));
		skillReturn.setSkillDescription(rs.getString("description"));
		skillReturn.setStatus(rs.getString("status"));
		return skillReturn;
	}

	public static void toEmptyStringAllNull(SkillsInformation obj) {
		if (obj.getSkillName() == null)
			obj.setSkillName(DAOConstants.STR_SPACE);
		if (obj.getSkillDescription() == null)
			obj.setSkillDescription(DAOConstants.STR_SPACE);
		if (obj.getStatus() == null)
			obj.setStatus(DAOConstants.STR_SPACE);
	}
}
