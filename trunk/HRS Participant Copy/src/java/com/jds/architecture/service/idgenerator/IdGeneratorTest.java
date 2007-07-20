/**
 * 
 */
package com.jds.architecture.service.idgenerator;

import junit.framework.TestCase;

/**
 * @author training
 * 
 */
public class IdGeneratorTest extends TestCase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.idgenerator.EmployeeIdGenerator#getInstance()}.
	 */
	public void testGetInstance() {
		assertSame(EmployeeIdGenerator.getInstance(), EmployeeIdGenerator
				.getInstance());
		assertSame(CategoryIdGenerator.getInstance(), CategoryIdGenerator
				.getInstance());
		assertSame(SkillIdGenerator.getInstance(), SkillIdGenerator
				.getInstance());
		assertSame(ProjectIdGenerator.getInstance(), ProjectIdGenerator
				.getInstance());
	}

	/**
	 * Test method for
	 * {@link com.jds.architecture.service.idgenerator.EmployeeIdGenerator#getNextId()}.
	 * 
	 * @throws IdGeneratorException
	 */
	public void testGetNextId() throws IdGeneratorException {
		long a, b, c, d;
		a = EmployeeIdGenerator.getInstance().getNextId();
		b = CategoryIdGenerator.getInstance().getNextId();
		c = SkillIdGenerator.getInstance().getNextId();
		d = ProjectIdGenerator.getInstance().getNextId();
		assertNotNull(a);
		assertNotNull(b);
		assertNotNull(c);
		assertNotNull(d);
		System.out.println("EmployeeID=" + a);
		System.out.println("SkillCategoryID=" + b);
		System.out.println("SkillID=" + c);
		System.out.println("ProjectID=" + d);
	}

}
