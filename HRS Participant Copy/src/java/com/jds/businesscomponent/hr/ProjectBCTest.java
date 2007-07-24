package com.jds.businesscomponent.hr;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;

public class ProjectBCTest {
	private ProjectBC pBC;

	@Before
	public void setUp() throws Exception, HRSSystemException {
		pBC = new ProjectBC();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateProjectValid() throws HRSLogicalException,
			HRSSystemException {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("2007-02-23");
		Date endDate = Date.valueOf("2007-05-01");

		info.setProjectId("" + Math.round(Math.random() * 999));
		info.setDescription("01010001");
		info.setClient("lalalka");
		info.setStartDate(startDate);
		info.setEndDate(endDate);

		info.setProjectName("" + Math.round(Math.random() * 999));

		pBC.createProject(info);
	}

	@Test
	public void testCreateProjectNull() {
		ProjectInfo info = null;

		try {
			pBC.createProject(info);
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("invalid.input.exception"))
				return;
		} catch (HRSSystemException e) {
			e.printStackTrace();
			fail("Wrong exception catched. Smth wrong with IDGen or something");
		}
		fail("Did not catch an expected exception");
	}

	@Test
	public void testCreateProjectWrongStartDate() {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("1000-02-23");
		Date endDate = Date.valueOf("2007-05-01");

		info.setProjectId("" + Math.round(Math.random() * 999));
		info.setDescription("01010001");
		info.setClient("lalalka");
		// info.setStartDate(startDate);
		info.setEndDate(endDate);
		info.setProjectName("" + Math.round(Math.random() * 999));
		try {
			pBC.createProject(info);
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("start.date.required.exception"))
				return;
		} catch (HRSSystemException e) {
			e.printStackTrace();
			fail("Wrong exception catched. Smth wrong with IDGen or something");
		}
		fail("Did not catch an expected exception");
	}

	@Test
	public void testSearchProject() throws HRSLogicalException, HRSSystemException {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("2007-02-23");
		Date endDate = Date.valueOf("2007-05-01");

		info.setProjectId("123321");
		info.setDescription("asfsawww222444");
		info.setClient("asdsafsafsaf");
		info.setStartDate(startDate);
		info.setEndDate(endDate);
		info.setProjectName("" + Math.round(Math.random() * 999));

		pBC.createProject(info);
		info = pBC.searchProject("123321");
	}

	@Test
	public void testSearchProjectWrongID() {
		String id = null;
		try {
			pBC.searchProject(id);
		} catch (HRSSystemException e) {
			fail("smth  wrong, coz wrong Exception thrown");
			e.printStackTrace();
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("id.required.exception"))
				return;
		}
		fail("smth  wrong, coz no exception thrown");
	}

	@Test
	public void testSearchProjectNotFound() {
		try {
			pBC.searchProject("123456789876");
		} catch (HRSSystemException e) {
			System.err.println(e.getMessageKey());
			fail("smth wrong with DAO or IDGens, coz wrong Exception thrown");
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("record.not.found.exception"))
				return;
		}
		fail("smth  wrong, coz no exception thrown");
	}
	
	@Test
	public void testUpdateProject() throws HRSLogicalException,
			HRSSystemException {
		ProjectInfo info = new ProjectInfo();
		Date startDate = Date.valueOf("1000-02-23");
		Date endDate = Date.valueOf("2007-05-01");

		info.setProjectId("" + Math.round(Math.random() * 999));
		info.setDescription("01010001");
		info.setClient("lalalka");
		info.setStartDate(startDate);
		info.setStartDate(endDate);
		info.setProjectName("" + Math.round(Math.random() * 999));
		pBC.createProject(info);
		info.setDescription("010gsdgsdg10001");
		info.setClient("lasdgsdglalka");
		pBC.updateProject(info);

	}

	@Test
	public void testUpdateProjectNull() {
		ProjectInfo info = null;
		try {
			pBC.updateProject(info);
		} catch (HRSLogicalException e) {
			if (e.getMessageKey().equals("invalid.input.exception"))
				return;
		} catch (HRSSystemException e) {
			e.printStackTrace();
			fail("Wrong exception catched. Smth wrong with IDGen or something");
		}
		fail("Did not catch an expected exception");
	}

	@Test
	public void searchReferenceData() {
		// TODO: Someone
		// Any ideas how to do it ?
		fail("Not yet implemented");
	}
}
