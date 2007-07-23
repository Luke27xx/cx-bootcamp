package com.jds.architecture.service.dao.stmtgenerator;

import org.apache.log4j.Logger;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.assembler.SkillAssembler;
import com.jds.architecture.utilities.CalendarToString;
import com.jds.architecture.utilities.Transformer;

public class StatementGenSkill extends StatementGenerator {

	Logger log = Logger.getLogger(StatementGenSkill.class);

	/**
	 * transformStmt creates an extension for SQL statements
	 * 
	 * @param Object
	 *            object
	 * @param int
	 *            stmtType
	 * @throws Exception
	 *             throws exception when unexpected error occurs
	 */
	public String transformStmt(Object object, int stmtType) throws Exception {
		StringBuffer strBuffer = new StringBuffer();
		String strTemp = null;
		String strConstant = DAOConstants.STR_AND;
		boolean isSet = false;
		SkillsInformation obj = (SkillsInformation) object;

		if (stmtType == DAOConstants.STMT_TYPE_SET) {
			strConstant = DAOConstants.CHAR_COMMA;
			isSet = true;
			SkillAssembler.toEmptyStringAllNull(obj);
		}

		if (obj.getSkillId() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_SKILLID, obj
					.getSkillId(), strConstant));

		}
		
		if (obj.getCategoryId() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_CATID, obj
					.getCategoryId(), strConstant));

		}

		if (obj.getSkillName() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_NAME, obj
					.getSkillName(), strConstant));
			log.debug("name:" + strBuffer.toString());

		}

		if (obj.getSkillDescription() != null) {
			strBuffer.append(getColumnValue(isSet,
					DAOConstants.COL_DESCRIPTION, obj.getSkillDescription(),
					strConstant));

		}

		if (obj.getStatus() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_STATUS, obj
					.getStatus(), strConstant));

		}
		log.debug("transform strTemp:" + strTemp);

		return strTemp;
	}

}
