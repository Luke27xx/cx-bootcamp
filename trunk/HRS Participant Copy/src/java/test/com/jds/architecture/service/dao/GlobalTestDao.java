package test.com.jds.architecture.service.dao;

import javax.sql.rowset.CachedRowSet;

import com.jds.architecture.utilities.CalendarConstants;

import junit.framework.TestCase;

public class GlobalTestDao extends TestCase {
	EmployeeDaoTest test = null;

	public GlobalTestDao() {
		try {
			test = new EmployeeDaoTest();
			test.myConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inicializateEmployee()
	{
		

	}

	public void testToStandardMonthNormal()throws Exception {
	
//TEST CREATE!-----------------------------------
		CachedRowSet help=null;
		test.myCreate();
		help=(CachedRowSet)test.findByAll();
		System.out.print(help.size()+"<<<kol-vo~!!!!!!!!!");
		assertEquals(129,help.size());	
//---------------------------------------------------------
		
		
		
	}

	public void testToStandardMonthError() {

	}
}
