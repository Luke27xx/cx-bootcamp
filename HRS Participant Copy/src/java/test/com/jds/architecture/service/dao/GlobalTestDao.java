package test.com.jds.architecture.service.dao;

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
		//System.out.print(test.findByAll().getMaxRows()+"<<<kol-vo~!!!!!!!!!");
		test.myCreate();
		System.out.print(test.findByAll().getMaxRows()+"<<<kol-vo~!!!!!!!!!");
		assertEquals(test.findByAll().getMaxRows(),test.findByAll().getMaxRows());	

	}

	public void testToStandardMonthError() {

	}
}
