package com.jds.architecture.service.dao.stmtgenerator;

import org.apache.log4j.Logger;

import com.jds.apps.beans.EmployeeInfo;
import com.jds.apps.beans.SkillCategory;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.assembler.EmployeeAssembler;
import com.jds.architecture.service.dao.assembler.SkillCategoryAssembler;
import com.jds.architecture.utilities.CalendarToString;
import com.jds.architecture.utilities.Transformer;

public class StatementGenCategory extends StatementGenerator{

	Logger log = Logger.getLogger(StatementGenCategory.class);
		
	/**
	 * transformStmt creates an extension for SQL statements
	 * @param Object object 
	 * @param int stmtType  
	 * @throws Exception throws exception when unexpected error occurs
	 */
	public String transformStmt(Object object, int stmtType) throws Exception {
		StringBuffer strBuffer = new StringBuffer();
		String strTemp = null;
		String strConstant =  DAOConstants.STR_AND;
		boolean isSet = false;
		SkillCategory obj = (SkillCategory)object;
        
		if (stmtType == DAOConstants.STMT_TYPE_SET) {
			strConstant = DAOConstants.CHAR_COMMA;
			isSet = true;
			SkillCategoryAssembler.toEmptyStringAllNull(obj);
		}
         
        
		if(obj.getCategoryId() != null) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_ID, 
					obj.getCategoryId(), strConstant));				
			
		} 
		
		
        
		if(obj.getName() != null) {  
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_NAME, 
					obj.getName(), strConstant));	
			log.debug("firstname:" + strBuffer.toString());			
			
		} 
        
		if(obj.getDescription() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_DESCRIPTION, 
					obj.getDescription(), strConstant));				
			
		} 
		
		
		if(obj.getStatus() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_STATUS, 
					obj.getStatus(), strConstant));			
			
		} 
		log.debug("transform strTemp:" + strTemp);
		
		return strTemp;
	}

}
