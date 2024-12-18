package com.jdbc.practice;

import java.sql.*;
import java.util.Scanner;

public class JdbcClass {

	private static Connection con;
	// static int id = 209;
	// static String name = "Java";

	public static void getStatement() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("This is getStatement method");
	}

	public static void insertValues() throws SQLException {
		Scanner scan = new Scanner(System.in);
		String insertQuery = "INSERT INTO dept (id, name) VALUES (?, ?)";
		try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
			System.out.println("Enter id and name");
			int id = scan.nextInt();
			String name = scan.next();
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeUpdate();
			System.out.println("Record inserted successfully.");
			scan.close();
			//the end
		}

	}

	public static void findAll() throws SQLException {
		String selectQuery = "SELECT * FROM dept";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(selectQuery)) {
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name"));
			}
			System.out.println("All records retrieved successfully.");
		}

	}

	public static void getNames() throws SQLException {
		Statement sttt = con.createStatement();
		ResultSet rssr = null;
		try {
			rssr = sttt.executeQuery("select name from dept");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (rssr.next()) {

			System.out.println(rssr.getString("name"));

		}
	}

	public static void deleteEntry() throws SQLException {
		PreparedStatement s = con.prepareStatement("delete from dept where name ='Java' ");
		s.executeUpdate();
		System.out.println("Record deleted successfully.");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			JdbcClass.getStatement();
			JdbcClass.insertValues();
			JdbcClass.findAll();
			JdbcClass.getNames();
			JdbcClass.deleteEntry();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the database connection
			if (con != null) {
				try {
					con.close();
					System.out.println("Database connection closed.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
