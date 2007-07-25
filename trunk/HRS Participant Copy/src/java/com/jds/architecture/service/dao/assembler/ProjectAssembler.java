/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.service.dao.DAOConstants;


/**
 * @author c.b.balajadia
 * @since HRS 2.0
 * @version 02/2005 -  initial draft
 * ProjectAssembler is a class used in populating prepared statement
 * and in retrieving project vo from result set.
 */
public class ProjectAssembler {

	/**
	 * Populates prepared statement using details of value object
	 * @param project Project value object
	 * @param stmt prepared statement from data access object
	 * @throws SQLException 
	 */
	public static void  getPreparedStatement(ProjectInfo project, 
		PreparedStatement stmt) throws SQLException {
		stmt.setString(1, project.getProjectId());
		stmt.setString(2, project.getProjectName());
		stmt.setString(3, project.getProjectDescrition());
		stmt.setDate(4, new java.sql.Date(project.getStartDate().getTime()));			
		stmt.setDate(5, new java.sql.Date(project.getEndDate().getTime()));
		stmt.setString(6, project.getClient());
	}
	
	/**
	 * Creates populated ProjectInfo vo from the Row set 
	 * @param rs Row Set 
	 * @return ProjectInfo
	 * @throws SQLException
	 */
	public static ProjectInfo getInfo(ResultSet rs) throws SQLException   {
		ProjectInfo projectReturn  = new ProjectInfo();
		projectReturn.setProjectId(rs.getString("id"));
		projectReturn.setProjectName(rs.getString("name"));
		projectReturn.setProjectDescription(rs.getString("description"));
		projectReturn.setStartDate(new java.util.Date(((java.sql.Date)rs.getObject("startDate")).getTime()));
		projectReturn.setEndDate(new java.util.Date(((java.sql.Date)rs.getObject("endDate")).getTime()));
		projectReturn.setClient(rs.getString("clientName"));		
		return projectReturn;
	}
	
	/**
	 * Creates populated ProjectInfo vo from the Result set 
	 * @param rs Result Set 
	 * @return ProjectInfo
	 * @throws SQLException
	 */
	public static ProjectInfo getInfo(RowSet rs) throws SQLException{
		ProjectInfo projectReturn  = new ProjectInfo();
		projectReturn.setProjectId(rs.getString("id"));
		projectReturn.setProjectName(rs.getString("name"));
		projectReturn.setProjectDescription(rs.getString("description"));
		projectReturn.setStartDate(new java.util.Date(((java.sql.Timestamp)rs.getObject("startDate")).getTime()));
		projectReturn.setEndDate(new java.util.Date(((java.sql.Timestamp)rs.getObject("endDate")).getTime()));
		projectReturn.setClient(rs.getString("clientName "));		
		return projectReturn;
	}
	
	
	public static void toEmptyStringAllNull(ProjectInfo obj) {
		if(obj.getProjectDescrition() == null) obj.setProjectDescription(DAOConstants.STR_SPACE);
		/*if(obj.getCity() == null) obj.setCity(DAOConstants.STR_SPACE);
		if(obj.getCitizenship() == null) obj.setCitizenship(DAOConstants.STR_SPACE);
		if(obj.getRecognitions() == null) obj.setRecognitions(DAOConstants.STR_SPACE);
		if(obj.getAge() <= 0) obj.setAge(0);
		if(obj.getTinNo() == null) obj.setTinNo(DAOConstants.STR_SPACE);
		if(obj.getStreet2() == null) obj.setStreet2(DAOConstants.STR_SPACE);		    
		if(obj.getHomePhoneNo() == null) obj.setHomePhoneNo(DAOConstants.STR_SPACE);
		if(obj.getMobilePhoneNo() == null) obj.setMobilePhoneNo(DAOConstants.STR_SPACE);
		if(obj.getState() == null) obj.setState(DAOConstants.STR_SPACE);
		if(obj.getCountry() == null) obj.setCountry(DAOConstants.STR_SPACE);*/
	}
	
	
	
}