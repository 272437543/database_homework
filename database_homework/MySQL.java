package database_homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class MySQL {

	boolean try_login(String username, String password) {
		String sql = "SELECT admins.password FROM admins WHERE admins.username=\'" + username + "\'";
		// System.out.println(sql);
		System.out.println("login");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver link success!");
			String url = "jdbc:mysql://localhost:3306/employee_manager?serverTimezone=UTC";
			String s = getDataSet(sql, DriverManager.getConnection(url, "root", "zxc123789")).get(0);
			String regEx = "[^0-9|a-z|A-Z]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(s);
			String res = m.replaceAll("").trim();
			return res.equals(password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public List<String> getSearchResultByParam(int permission, String database, String reason) {
		if (permission == 1) {
			// 普通用户
			if (database.matches("admins")) {
				return null;
			}
		}
		
		// TODO 从param里面得到查询参数
		String sql = "SELECT * FROM " + database;
		if (!reason.equals("")) {
			sql += " where " + reason;
		}
		List<String> ret = new ArrayList<>();

		// String ret = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver link success!");
			String url = "jdbc:mysql://localhost:3306/employee_manager?serverTimezone=UTC";
			ret = getDataSet(sql, DriverManager.getConnection(url, "root", "zxc123789"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public void update(String database, String reason, String type) {
		String sql = "";
		if (type.equals("delete")) {
			sql = "delete FROM " + database;
			if (!reason.equals("")) {
				sql += " where " + reason;
			}
			
		}

		if (type.equals("update")) {
			try {
				String[] reasons = reason.split(" "); // 按空格分开
				sql = "update " + database;
				if (!reason.equals("")) {
					sql += " set " + reasons[0] + " where " + reasons[1];
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (type.equals("adding")) {
			try {
				String[] reasons = reason.split(" "); // 按空格分开
				sql = "insert into " + database + " " + reasons[0] + " values " + reasons[1];
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(sql);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver link success!");
			String url = "jdbc:mysql://localhost:3306/employee_manager?serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url, "root", "zxc123789");
			conn.createStatement().executeUpdate(sql);

			JOptionPane.showMessageDialog(null, "更新完成");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean try_signin(String username, String password) {
		System.out.println("Sign in");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver link success!");
			String url = "jdbc:mysql://localhost:3306/employee_manager?serverTimezone=UTC";
			// String sql = "INSERT INTO admins (name, password, permission) values (\'" +
			// username + "\', \'" + password + "\', 1)";
			String sql = "INSERT INTO admins (username, password, permission) values (?, ?, 1)";
			// System.out.println(sql);
			PreparedStatement pst = DriverManager.getConnection(url, "root", "zxc123789").prepareStatement(sql);
			// pst是用来设置占位符
			pst.setString(1, username);
			pst.setString(2, password);
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void test() {
		// TODO Auto-generated constructor stub
		/**
		 * TODO: 回去封装Java连接mysql的类
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver link success!");
			String url = "jdbc:mysql://localhost:3306/employee_manager?serverTimezone=UTC";
			Connection conn;
			System.out.println("connect success!");

			String sql = "SELECT\r\n" + "employees.department,\r\n" + "departments.people,\r\n"
					+ "employees.`name`,\r\n" + "salary.basic_salary,\r\n" + "salary.bouns,\r\n" + "salary.welfare,\r\n"
					+ "salary.total\r\n" + "FROM\r\n" + "employees\r\n"
					+ "INNER JOIN departments ON departments.`name` = employees.department\r\n"
					+ "INNER JOIN salary ON employees.id = salary.id";
			String username = "root";
			String password = "zxc123789";
			System.out.println(getDataSet(sql, DriverManager.getConnection(url, username, password)));

			String sql2 = "INSERT INTO departments VALUES (2, '机器学习', 5)";
			updateData(sql2, DriverManager.getConnection(url, username, password));

			String sql3 = "SELECT * FROM departments";
			System.out.println(getDataSet(sql3, DriverManager.getConnection(url, username, password)));
			// System.out.println("length: " + rowcount + " " +
			// rs.getMetaData().getColumnCount());

			// String sql = "update student set Sage=Sage+? where Sno=?";
			// PreparedStatement pst = conn.prepareStatement(sql);
			// pst是用来设置占位符
			// pst.setInt(1, 1);
			// pst.setString(2, "201215122");
			// pst.executeUpdate();
			/*
			 * String sql2 = "CREATE TABLE student_2 " + "(id INTEGER not NULL, " +
			 * " first VARCHAR(255), " + " last VARCHAR(255), " + " age INTEGER, " +
			 * " PRIMARY KEY ( id ))";
			 */

			// String sql2 = "INSERT INTO student_2 " + "VALUES (1002, 'Drake', 'Watcher',
			// 23)";

			// executeUpdate更新操作
			// state.executeUpdate(sql2);

			// showDataSet(rs);

			// String sql3 = "select * from student_2";
			// executeQuery查询操作

			// ResultSet rs2 = state.executeQuery(sql3);

			// showDataSet(rs2);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	void updateData(String sql, Connection conn) {
		try {
			Statement state = conn.createStatement();
			state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> getDataSet(String sql, Connection conn) {

		List<String> ret = new ArrayList<>();
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			int columns = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				String tmp = "";
				for (int c = 1; c <= columns; c++) {
					tmp += (rs.getString(c) + " ");
				}
				ret.add(tmp);
			}
			state.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

	}

}
