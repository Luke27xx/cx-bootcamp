package test.com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.RowSet;

import com.jds.apps.beans.AccentureDetails;
import com.jds.apps.beans.EmployeeInfo;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.EmployeeDAO;
import com.jds.architecture.service.dbaccess.DBAccessException;

import junit.framework.TestCase;

public class EmployeeDaoTest extends EmployeeDAO {
	Connection conn = null;
	final static String url = "jdbc:oracle:thin:@10.122.131.216:1521:XE";
	//Statement stmt = null;
	//ResultSet rset = null;

	public EmployeeDaoTest() throws DAOException, DBAccessException {
		super();
   
	}

	@Override
	public void create(Connection conn, Object object) throws DAOException {
		// TODO Auto-generated method stub
		super.create(conn, object);
	}

	@Override
	public RowSet find(Object object) throws DAOException {
		// TODO Auto-generated method stub
		return super.find(object);
	}

	@Override
	public Object findByPK(Object object) throws DAOException {
		// TODO Auto-generated method stub
		return super.findByPK(object);
	}

	@Override
	public boolean remove(Connection conn, Object object) throws DAOException {
		// TODO Auto-generated method stub
		return super.remove(conn, object);
	}

	@Override
	public boolean update(Connection conn, Object objSet, Object objWhere)
			throws DAOException {
		// TODO Auto-generated method stub
		return super.update(conn, objSet, objWhere);
	}

	@Override
	public RowSet findByAll() throws DAOException {
		// TODO Auto-generated method stub
		return super.findByAll();
	}

	public EmployeeInfo myCreate() throws ClassNotFoundException, SQLException {
		EmployeeInfo employee = new EmployeeInfo();
		myConnect();

		try {

			Date dates = new Date();

			// employee.setEmpNo(""+Math.round(Math.random()*1000));
			employee.setEmpNo("1314701132");
			employee.setFirstName("" + Math.round(Math.random() * 1000));
			employee.setLastName("" + Math.round(Math.random() * 1000));
			employee.setMiddleName("" + Math.round(Math.random() * 1000));
			employee.setDob(dates);
			employee.setAge(Integer.parseInt(""
					+ Math.round(Math.random() * 1000)));
			employee.setGender('m');
			employee.setCivilStatus("" + Math.round(Math.random() * 1000));
			employee.setCitizenship("" + Math.round(Math.random() * 1000));
			employee.setSssNo("" + Math.round(Math.random() * 1000));
			employee.setTinNo("" + Math.round(Math.random() * 1000));
			employee.setMobilePhoneNo("" + Math.round(Math.random() * 1000));
			employee.setHomePhoneNo("" + Math.round(Math.random() * 1000));
			employee.setStreet1("" + Math.round(Math.random() * 1000));
			employee.setStreet2("" + Math.round(Math.random() * 1000));
			employee.setCity("" + Math.round(Math.random() * 1000));
			employee.setState("" + Math.round(Math.random() * 1000));
			employee.setCountry("" + Math.round(Math.random() * 1000));
			employee.setEducationalAttainment(""
					+ Math.round(Math.random() * 1000));
			employee.setRecognitions("" + Math.round(Math.random() * 1000));

			create(conn, employee);

			// conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return employee;
	}

	public void myConnect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		conn = DriverManager.getConnection(url, "sampleuser", "samplepassword");

	}

	public void myRemove() {
		try {

			remove(conn, "1314701132");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public Object myFindByPK(Object object) throws DAOException {

		return findByPK(object);

	}

	public void myUpdate() throws DAOException, DBAccessException {
		EmployeeInfo employee = new EmployeeInfo();

		Date dates = new Date();

		// employee.setEmpNo(""+Math.round(Math.random()*1000));
		// employee.setEmpNo("1314701132");
		employee.setFirstName("ZZZ");
		employee.setLastName("XXX");
		employee.setMiddleName("" + Math.round(Math.random() * 1000));
		employee.setDob(dates);
		employee
				.setAge(Integer.parseInt("" + Math.round(Math.random() * 1000)));
		employee.setGender('m');
		employee.setCivilStatus("" + Math.round(Math.random() * 1000));
		employee.setCitizenship("" + Math.round(Math.random() * 1000));
		employee.setSssNo("" + Math.round(Math.random() * 1000));
		employee.setTinNo("" + Math.round(Math.random() * 1000));
		employee.setMobilePhoneNo("" + Math.round(Math.random() * 1000));
		employee.setHomePhoneNo("" + Math.round(Math.random() * 1000));
		employee.setStreet1("" + Math.round(Math.random() * 1000));
		employee.setStreet2("" + Math.round(Math.random() * 1000));
		employee.setCity("" + Math.round(Math.random() * 1000));
		employee.setState("" + Math.round(Math.random() * 1000));
		employee.setCountry("" + Math.round(Math.random() * 1000));
		employee
				.setEducationalAttainment("" + Math.round(Math.random() * 1000));
		employee.setRecognitions("" + Math.round(Math.random() * 1000));

		System.out.println("DO>" + findByPK("1314701132"));
		update(conn, employee, findByPK("1314701132"));
		System.out.println("posle>" + findByPK("1314701132"));

	}

	// public myFindByPK(){}
	public void closeConn() {
		try {
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void errorTestCreate() throws Exception {
		try {
			create(conn, null);
			} catch (Exception e) {

		}
			
			try {
				EmployeeInfo empl = new EmployeeInfo();
				create(conn, empl);
				} catch (Exception e) {

			}

	}
}
