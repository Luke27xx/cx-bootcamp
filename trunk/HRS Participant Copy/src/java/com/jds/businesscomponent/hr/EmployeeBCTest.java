package com.jds.businesscomponent.hr;

import junit.framework.TestCase;


public class EmployeeBCTest extends TestCase {

    protected String f;

    /** JUnit test classes require this constructor */
    public EmployeeBCTest(String name) {
        super(name);
    }

    public void testEmployeeBC() {
        f = "...";
        assertEquals("Ian Darwin", f);
    }
}