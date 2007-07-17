/*
 * Created on Feb 2, 2005
 */
package com.jds.architecture.utilities;

/**
 * Validation strategy object used to determine if the argument object can
 * translates to a dobule a value. The method uses the argument object's
 * toString() method to obtain its string representation and the
 * <code>Double.parseDouble(String)</code> method for parsing.
 * 
 * The overriden method <code>transform(Object target)</code> accepts any
 * object. RuntimeExceptions will be thrown when any other argument is passed to
 * the method.
 * 
 * Classes that implement the <code>ValidationStrategy</code> interface should
 * be passed to <code>Validator</code> objects via their constructor or to the
 * <code>Validator.validate(ValidationStrategy, Object)</code> method
 * 
 * @author Eugene P. Lozada, Arthur D. Gerona
 * @see Validator
 * @see ValidationStrategy
 * 
 */
public class StringIsDouble implements ValidationStrategy {

	/**
	 * Determines if the string representation of the argument object can be
	 * translated as a double.
	 * 
	 * @param target
	 *            the object to be validated
	 * @return boolean true if the object can be parsed as a double, false
	 *         otherwise
	 */
	public boolean validate(Object target) throws RuntimeException {
		String ccc = null;
		Double x = null;
		if(target==null){throw new NullPointerException();}
		try {
			ccc = target.toString();
			
			System.out.println("value String>" + ccc);
			x = Double.parseDouble(ccc);
			
			System.out.println("value Double>" + x);
		} catch (Exception e) {
			//new RuntimeException();
			return false;
		}

		if (x.equals(null)) {
			return false;
		} else {
			return true;
		}

	}

}
