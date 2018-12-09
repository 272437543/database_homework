package database_homework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MysqlConnect {
	public static void main(String[] args) {
		new MysqlConnect();
	}

	ButtonListener btlistener;
	JFrame frame;

	public MysqlConnect() {
		loginFrame();
	}

	void loginFrame() {
		frame = new JFrame("��ӭ�����¼");

		JTextField txt = new JTextField("");
		txt.setFont(new Font("΢���ź�", Font.BOLD, 35));
		txt.setPreferredSize(new Dimension(550, 60));
		// txt.locate(10, 10);

		JPasswordField pas = new JPasswordField("");
		pas.setPreferredSize(new Dimension(550, 60));
		pas.setFont(new Font("΢���ź�", Font.BOLD, 35));
		// pas.locate(50, 10);

		btlistener = new ButtonListener(txt, pas, this);

		frame.add(txt);
		frame.add(pas);

		JButton login = new JButton("login");
		login.setFont(new Font("΢���ź�", Font.BOLD, 30));
		login.setPreferredSize(new Dimension(150, 100));
		JButton signin = new JButton("sign in");
		signin.setFont(new Font("΢���ź�", Font.BOLD, 30));
		signin.setPreferredSize(new Dimension(180, 100));
		login.addActionListener(btlistener);
		signin.addActionListener(btlistener);
		frame.add(login);
		frame.add(signin);
		

		frame.setSize(600, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
	}

	void showWelcome(String username) {
		frame = new JFrame("��ӭ, " + username);

		int permission = username.equals("admin") ? 9 : 1;
		// 9Ȩ�޿�����ɾ���
		// 1Ȩ��ֻ���Բ鿴

		JTextField txt = new JTextField("��ѯ��");
		txt.setFont(new Font("΢���ź�", Font.BOLD, 20));
		txt.setPreferredSize(new Dimension(250, 60));

		JTextField reason = new JTextField("");
		reason.setFont(new Font("΢���ź�", Font.BOLD, 20));
		reason.setPreferredSize(new Dimension(250, 50));

		JButton search = new JButton("search");
		search.setFont(new Font("΢���ź�", Font.BOLD, 15));
		search.setPreferredSize(new Dimension(100, 60));

		btlistener = new ButtonListener(txt, reason, this, permission);

		search.addActionListener(btlistener);

		JButton logout = new JButton("logout");
		logout.addActionListener(btlistener);
		frame.add(logout);

		frame.add(txt);
		frame.add(reason);
		frame.add(search);
		if (permission == 9) {
			JButton adding = new JButton("adding");
			adding.setFont(new Font("΢���ź�", Font.BOLD, 15));
			adding.setPreferredSize(new Dimension(100, 60));
			
			JButton delete = new JButton("delete");
			delete.setFont(new Font("΢���ź�", Font.BOLD, 15));
			delete.setPreferredSize(new Dimension(100, 60));
			
			JButton update = new JButton("update");
			update.setFont(new Font("΢���ź�", Font.BOLD, 15));
			update.setPreferredSize(new Dimension(100, 60));
			
			adding.addActionListener(btlistener);
			delete.addActionListener(btlistener);
			update.addActionListener(btlistener);
			
			frame.add(adding);
			frame.add(delete);
			frame.add(update);
		}

		frame.setSize(600, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
	}

	public void showResult(List<String> result) {
		SimpleFrame sf = new SimpleFrame(result);
		/*
		 * frame = new JFrame("��ѯ���");
		 * 
		 * frame.setSize(1000, 800); frame.setLocationRelativeTo(null);
		 * frame.setResizable(false); frame.setDefaultCloseOperation(2);
		 * frame.setLayout(new FlowLayout());
		 * 
		 * //JTextField txt = new JTextField(result); //txt.setFont(new Font("΢���ź�",
		 * Font.BOLD, 20)); //txt.setPreferredSize(new Dimension(950, 700));
		 * //frame.add(txt); frame.setVisible(true); Graphics g =
		 * frame.getRootPane().getGraphics(); g.setColor(Color.black);
		 * g.drawString(result, 0, 0); g.fillRect(0, 0, 100, 100);
		 * frame.getRootPane().repaint();
		 */
	}

	class SimpleFrame extends JFrame {
		List<String> str;

		public SimpleFrame(List<String> str) {
			setTitle("��ѯ���");
			setDefaultCloseOperation(2);
			setSize(1500, 800);
			setVisible(true);
			System.out.println(str);
			this.str = str;
		}

		int c = 50;

		public void paint(Graphics g) {
			super.paint(g);
			g.setFont(new Font("΢���ź�", Font.BOLD, 30));
			g.setColor(Color.BLACK);
			
			if (str == null)
				g.drawString("You have no right", 100, 200);
			for (int i = 0; i < str.size(); i++) {
				g.drawString(str.get(i), 50, (i + 1) * c + 50);
			}
		}
	}

	private boolean try_login(String username, String password) {
		String sql = "SELECT password FROM admins WHERE name=" + username;
		System.out.println(sql);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver link success!");
			String url = "jdbc:mysql://localhost:3306/employee_manager?serverTimezone=UTC";
			System.out.println(getDataSet(sql, DriverManager.getConnection(url, "root", "zxc123789")));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	private void test() {
		// TODO Auto-generated constructor stub
		/**
		 * TODO: ��ȥ��װJava����mysql����
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

			String sql2 = "INSERT INTO departments VALUES (2, '����ѧϰ', 5)";
			updateData(sql2, DriverManager.getConnection(url, username, password));

			String sql3 = "SELECT * FROM departments";
			System.out.println(getDataSet(sql3, DriverManager.getConnection(url, username, password)));
			// System.out.println("length: " + rowcount + " " +
			// rs.getMetaData().getColumnCount());

			// String sql = "update student set Sage=Sage+? where Sno=?";
			// PreparedStatement pst = conn.prepareStatement(sql);
			// pst����������ռλ��
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

			// executeUpdate���²���
			// state.executeUpdate(sql2);

			// showDataSet(rs);

			// String sql3 = "select * from student_2";
			// executeQuery��ѯ����

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

	String getDataSet(String sql, Connection conn) {

		String ret = "";
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			int columns = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int c = 1; c <= columns; c++) {
					ret += (rs.getString(c) + " ");
				}
				ret += '\n';
			}
			state.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

	}
}
