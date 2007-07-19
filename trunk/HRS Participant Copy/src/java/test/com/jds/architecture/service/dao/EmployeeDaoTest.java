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
	

	public EmployeeDaoTest() throws DAOException, DBAccessException {
		super();
		// TODO Auto-generated constructor stub
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

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		String url = "jdbc:oracle:thin:@10.122.131.216:1521:XE";

		Connection conn = DriverManager.getConnection(url, "sampleuser",
				"samplepassword");
		try {
			EmployeeDaoTest ex = new EmployeeDaoTest();
			// System.out.print(">>>>>>>>>>>>"+ex.findByPK("123"));

			EmployeeInfo employee = new EmployeeInfo();
			Date dates = new Date();
			
			employee.setEmpNo("789");
			employee.setFirstName("xz1");
			employee.setLastName("zx1");
			employee.setMiddleName("sa1");
			employee.setDob(dates);
			employee.setAge(111);
			employee.setGender('x');
			employee.setCivilStatus("shit1");
			employee.setCitizenship("dog1");
			employee.setSssNo("1451");
			employee.setTinNo("5211");
			employee.setMobilePhoneNo("347681");
			employee.setHomePhoneNo("1111");
			employee.setStreet1("gg1g");
			employee.setStreet2("t1rt");
			employee.setCity("1");
			employee.setState("ug1");
			employee.setCountry("lv1");
			employee.setEducationalAttainment("sa1df");
			employee.setRecognitions("sad1f");
			// System.out.print(">>>>>>><>>>>>>>>>" + ex.findByAll() + "\n");
ex.remove(conn,"789" );
			//ex.create(conn, employee);

		} catch (Exception e) {
			System.out.println("ypypy");
			e.printStackTrace();
			// TODO: handle exception
		}

		// conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt
				.executeQuery("SELECT firstname,lastname,age FROM employee");

		while (rset.next()) {
			System.out.println(rset.getString("firstname"));
			System.out.println(rset.getString(2));
			System.out.println(rset.getString(3));

		}
		stmt.close();
		// System.out.println ("Ok.");
	}
}
