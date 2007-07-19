/**
 * 
 */
package com.jds.architecture.service.idgenerator;

import junit.framework.TestCase;

/**
 * @author training
 *
 */
public class EmployeeIdGeneratorTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.jds.architecture.service.idgenerator.EmployeeIdGenerator#getInstance()}.
	 */
	public void testGetInstance() {
		assertSame(EmployeeIdGenerator.getInstance(), EmployeeIdGenerator.getInstance());
	}

	/**
	 * Test method for {@link com.jds.architecture.service.idgenerator.EmployeeIdGenerator#getNextId()}.
	 * @throws IdGeneratorException 
	 */
	public void testGetNextId() throws IdGeneratorException {
		assertNotNull(EmployeeIdGenerator.getInstance().getNextId());
	}

}
