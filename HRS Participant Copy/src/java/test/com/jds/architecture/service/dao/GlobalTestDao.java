package test.com.jds.architecture.service.dao;

/**
 * 
 */

import javax.sql.rowset.CachedRowSet;
//import org.junit.Test;

//import com.jds.architecture.service.dao.DAOException;


import junit.framework.TestCase;

public class GlobalTestDao extends TestCase {
/*	EmployeeDaoTest test = null;

	public GlobalTestDao() {
		try {
			test = new EmployeeDaoTest();
			test.myConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreate() throws Exception {
		test.myCreate();
	}

	public void testFindAll() throws Exception {
		CachedRowSet help = null;
		help = (CachedRowSet) test.findByAll();
		System.out.println(help.size() + "<<<kol-vo11111");

	}

	public void testFindPK() throws Exception {
		System.out.println("<<<findByPK!>>" + test.findByPK("1314701132"));

	}

	public void testDelete() throws Exception {
		test.myRemove();
	}
*/
	public void testToStandardNormal() throws Exception {

		// TEST CREATE!-----------------------------------
		/*CachedRowSet help = null;

		help = (CachedRowSet) test.findByAll();
		int before = help.size();
		System.out.println(help.size() + "<<<kol-vo");
		test.myCreate();
		help = (CachedRowSet) test.findByAll();

		System.out.println(help.size() + "<<<kol-vo1!>>"
				+ test.findByPK("1314701132"));
		// test.myUpdate(objSet, objWhere)

		before++;
		assertEquals(before, help.size());
		// ---------------------------------------------------------
		test.myUpdate();
		test.myRemove();
		before--;
		help = (CachedRowSet) test.findByAll();
		System.out.print(help.size() + "<<<kol-vo!");
		
		assertEquals(before, help.size());
		*/
		SkillCategoryTest testCategoryTest= new SkillCategoryTest();
		testCategoryTest.myCreate();
		
		
		testCategoryTest.closeConn();
		
	//	test.closeConn();
	}

/*	@Test(expected = DAOException.class)
	public void testToStandardError() {
		try {
		test.errorTestCreate();

		} catch (Exception e) {

		}
	}*/
}
