/*
 * Created on Jul 23, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jds.architecture.service.dao.stmtgenerator;

import org.apache.log4j.Logger;

import com.jds.apps.beans.AccentureDetails;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.assembler.AccDetailsAssembler;
import com.jds.architecture.utilities.CalendarToString;
import com.jds.architecture.utilities.Transformer;

/**
 * StatementGenAccDetails is a class that generates SQL statements for
 * AccDetailsInfo Statement Generator DAO functions
 * 
 * @author M.Skovitovs
 * @author last modified by: $Author: M.Skovitovs $
 * @version 07/2007 initial draft 23/07/2007 - created JavaDocs
 * 
 * @since HRS 2.0
 * 
 */
public class StatementGenAccDetails extends StatementGenerator {

	Logger log = Logger.getLogger(StatementGenEmployee.class);

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
		AccentureDetails obj = (AccentureDetails) object;

		if (stmtType == DAOConstants.STMT_TYPE_SET) {
			strConstant = DAOConstants.CHAR_COMMA;
			isSet = true;
			AccDetailsAssembler.toEmptyStringAllNull(obj);
		}

		if (obj.getEmployeeNo() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_EMPNO, obj
					.getEmployeeNo(), strConstant));

		}

		if (obj.getEnterpriseId() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_ENTID, obj
					.getEnterpriseId(), strConstant));
			log.debug("enterpriseId:" + strBuffer.toString());

		}

		if (obj.getEnterpriseAddress() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_EMAIL, obj
					.getEnterpriseAddress(), strConstant));

		}

		if (obj.getLevel() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_EMPLEVEL,
					obj.getLevel(), strConstant));

		}

		if (obj.getLMU() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_LMU, obj
					.getLMU(), strConstant));

		}

		if (obj.getStatus() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_STATUS, obj
					.getStatus(), strConstant));

		}

		if (obj.getDateHired() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_DATEHIRED,
					(String) Transformer.transform(new CalendarToString(), obj
							.getDateHired()), strConstant));
		}

		if (obj.getGMU() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_GMU, obj
					.getGMU(), strConstant));

		}

		if (obj.getWorkGroup() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_WORKGROUP,
					obj.getWorkGroup(), strConstant));

		}

		if (obj.getSpecialty() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_SPECIALTY,
					String.valueOf(obj.getSpecialty()), strConstant));
		}

		if (obj.getServiceLine() != null) {
			strBuffer.append(getColumnValue(isSet,
					DAOConstants.COL_SERVICELINE, String.valueOf(obj
							.getServiceLine()), strConstant));
		}

		if (strBuffer.toString().length() > 0) {
			if (stmtType == DAOConstants.STMT_TYPE_SET) {
				strTemp = strBuffer.toString().substring(0,
						strBuffer.toString().length() - 1);
			} else {
				strTemp = strBuffer.toString().substring(0,
						strBuffer.toString().lastIndexOf(DAOConstants.STR_AND));
			}
		}

		log.debug("transform strTemp:" + strTemp);
		return strTemp;
	}

}
