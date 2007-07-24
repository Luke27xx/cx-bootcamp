package test.com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.jds.apps.beans.SkillCategory;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.SkillCategoryDAO;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class SkillCategoryTest extends SkillCategoryDAO {
	Connection conn = null;

	static int idUniq = 4;
	final static String url = "jdbc:oracle:thin:@10.122.131.216:1521:XE";

	// Statement stmt = null;
	// ResultSet rset = null;
	public SkillCategoryTest() throws DAOException, DBAccessException {
		super();

	}

	@Override
	public void create(Connection conn, Object object) throws DAOException {
		super.create(conn, object);
	}

	@Override
	public RowSet find(Object object) throws DAOException {
		return super.find(object);
	}

	@Override
	public RowSet findByAll() throws DAOException {
		return super.findByAll();
	}

	@Override
	public Object findByPK(Object object) throws DAOException {
		return super.findByPK(object);
	}

	@Override
	public boolean remove(Connection conn, Object object) throws DAOException {
		return super.remove(conn, object);
	}

	@Override
	public boolean update(Connection conn, Object objSet, Object objWhere)
			throws DAOException {
		
		return super.update(conn, objSet, objWhere);
	}

	public void myCreate() throws Exception {
		
		try {
			
		myConnect();
		
		SkillCategory skCat = new SkillCategory();
		skCat.setCategoryId("2");
		skCat.setCategoryDescription("asd");
		skCat.setCategoryName("1179277520");
		
		
		create(conn, skCat);
		SkillCategory obj= new SkillCategory();
		
		
		obj.setDescription("11v11");
		obj.setCategoryName("nice");
		System.err.println(skCat.getCategoryName()+"< do i posle >"+obj.getCategoryName());
		update(conn, obj, skCat);
		
		SkillCategory ob=(SkillCategory)findByPK(skCat);
		System.out.println("after update,find by pk "+ob.getCategoryName());
		
		
		
		
		
		System.out.println("CREATED!!!!" + skCat);
		CachedRowSet help = null;
		help = (CachedRowSet) findByAll();
		System.out.println(help.size() + "<<<kol-vo>>>");
		
		
		remove(conn, skCat);
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			closeConn();
		}
		
	}

	public void myDelete() {

	}

	public void myConnect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		conn = DriverManager.getConnection(url, "sampleuser", "samplepassword");

	}

	public void closeConn() {
		try {
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
