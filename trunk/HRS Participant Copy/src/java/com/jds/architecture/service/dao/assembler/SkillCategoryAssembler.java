package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.jds.apps.beans.EmployeeInfo;
import com.jds.apps.beans.SkillCategory;
import com.jds.architecture.service.dao.DAOConstants;

public class SkillCategoryAssembler {

	public static void getPreparedStatement(SkillCategory skillCategory,
			PreparedStatement stmt) throws SQLException {
		stmt.setString(1, skillCategory.getCategoryId());
		stmt.setString(2, skillCategory.getCategoryName());
		stmt.setString(3, skillCategory.getCategoryDescription());
		stmt.setString(4, skillCategory.getStatus());
	}

	/**
	 * Creates populated EmployeeInfo vo from the Row set
	 * 
	 * @param rs
	 *            Row Set
	 * @return EmployeeInfo
	 * @throws SQLException
	 */
	public static SkillCategory getInfo(ResultSet rs) throws SQLException {
		SkillCategory skillCategoryReturn = new SkillCategory();
		skillCategoryReturn.setCategoryId(rs.getString("id"));
		skillCategoryReturn.setName(rs.getString("name"));
		skillCategoryReturn.setDescription(rs.getString("description"));
		skillCategoryReturn.setStatus(rs.getString("status"));
		return skillCategoryReturn;
	}

	/**
	 * Creates populated EmployeeInfo vo from the Result set
	 * 
	 * @param rs
	 *            Result Set
	 * @return EmployeeInfo
	 * @throws SQLException
	 */
	public static SkillCategory getInfo(RowSet rs) throws SQLException {
		SkillCategory skillCategoryReturn = new SkillCategory();
		skillCategoryReturn.setCategoryId(rs.getString("id"));
		skillCategoryReturn.setName(rs.getString("name"));
		skillCategoryReturn.setDescription(rs.getString("description"));
		skillCategoryReturn.setStatus(rs.getString("status"));
		return skillCategoryReturn;
	}

	public static void toEmptyStringAllNull(SkillCategory obj) {
		if (obj.getCategoryName() == null)
			obj.setCategoryName(DAOConstants.STR_SPACE);
		if (obj.getCategoryDescription() == null)
			obj.setCategoryDescription(DAOConstants.STR_SPACE);
		if (obj.getStatus() == null)
			obj.setStatus(DAOConstants.STR_SPACE);
	}

}
