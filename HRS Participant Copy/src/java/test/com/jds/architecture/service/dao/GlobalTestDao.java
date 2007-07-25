package test.com.jds.architecture.service.dao;

/**
 * 
 */

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.rowset.CachedRowSet;

import com.jds.apps.beans.SkillCategory;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.businesscomponent.hr.SkillCategoryBC;
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
	public void testToStandardNormal() throws HRSSystemException, HRSLogicalException, Exception {

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
	//	SkillCategoryTest testCategoryTest= new SkillCategoryTest();
//		testCategoryTest.myCreate();
	
		 
		
		Collection<SkillCategory> myList = new ArrayList<SkillCategory>();
		SkillCategoryTest t=new SkillCategoryTest();
	//	t.myCreate();
			
		
		SkillCategoryBC skillCategory = new SkillCategoryBC();
		SkillCategory skCat = new SkillCategory();
		skCat.setCategoryId("111111");
		skCat.setCategoryDescription("abc");
		skCat.setCategoryName("ttt111");
		
		
		
		myList = skillCategory.searchApprovedCategories(null);
		System.out.println("myList=>>>>"+myList.size());
		
		
		
		
	//	testCategoryTest.closeConn();
		
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
