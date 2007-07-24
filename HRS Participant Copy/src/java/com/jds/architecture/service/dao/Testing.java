package com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

public class Testing {
	private static String url = "jdbc:oracle:thin:@10.122.131.216:1521:XE";
	
	public static void insertTestRow(Connection conn) {
		String createQuery = "INSERT INTO project "
				+ "(id, name, description, startdate, enddate, clientname, status) VALUES"
				+ "(10, 'TestProject', 'Test description', '15-FEB-2007', '23-JUL-2007', 'TestClient', 'TestStatus')";
		PreparedStatement stmt = null;
		try {
			if ((conn == null) || (conn.isClosed())) {
				throw new IllegalArgumentException();
			}
			stmt = conn.prepareStatement(createQuery);
			stmt.executeUpdate(); // Visnet zdesj!
			conn.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	private static void deleteTestRow(Connection conn) {
		String createQuery = "DELETE FROM project "
				+ "WHERE id='10' AND name='TestProject'";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(createQuery);
			stmt.executeUpdate();
			//conn.commit();
			stmt.close();
		} catch (Exception e) {

		} finally {
			
		}
	}
	
	public static Connection setUpConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "sampleuser",
					"samplepassword");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return conn;
	}
	public static void main(String[] args) {
		Connection conn = null;
		String query = "SELECT * FROM project";
		ResultSet rs;
		CachedRowSet set;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = setUpConnection();
			insertTestRow(conn);
			PreparedStatement stmt = conn.prepareStatement(query);
			set = new CachedRowSetImpl();
			rs = stmt.executeQuery();
			set.populate(rs);
			//------
			set.first();
			System.out.print(set.getString("name"));
			
			//deleteTestRow(conn);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				
			}
		}
		
	}
	
}
