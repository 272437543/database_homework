package database_homework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ButtonListener implements ActionListener {
	JTextField txt;
	JTextField reason;
	JPasswordField pas;
	MysqlConnect sqlConn;
	int permission;

	public ButtonListener(JTextField txt, JPasswordField pas, MysqlConnect sqlConn) {
		//super();
		this.sqlConn = sqlConn;
		this.txt = txt;
		this.pas = pas;
	}
	
	public ButtonListener(JTextField txt, JTextField reason, MysqlConnect sqlConn, int permission) {
		//super();
		this.permission = permission;
		this.sqlConn = sqlConn;
		this.txt = txt;
		this.reason = reason;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		// System.out.println(txt.getText() + " - " + pas.getText());
		if (txt.getText().equals(""))
			return ;
		
		if (e.getActionCommand().equals("logout"))
		{
			sqlConn.frame.dispose();
			sqlConn.loginFrame();
		}
		
		MySQL db = new MySQL();
		if (e.getActionCommand().equals("login"))
		{
			System.out.println(db.try_login(txt.getText(), pas.getText()));
			if (db.try_login(txt.getText(), pas.getText()))
			{
				sqlConn.frame.dispose();
				sqlConn.showWelcome(txt.getText());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "µÇÂ½Ê§°Ü");
			}
		}
		if (e.getActionCommand().equals("sign in"))
		{
			System.out.println(db.try_signin(txt.getText(), pas.getText()));
		}
		
		if (e.getActionCommand().equals("search"))
		{
			sqlConn.showResult(db.getSearchResultByParam(permission, txt.getText(), reason.getText()));
		}
		
		if (e.getActionCommand().equals("adding"))
		{
			// Ìí¼Ó
			db.update(txt.getText(), reason.getText(), e.getActionCommand());
		}
		
		if (e.getActionCommand().equals("delete"))
		{
			// É¾³ý
			db.update(txt.getText(), reason.getText(), e.getActionCommand());
		}
		
		if (e.getActionCommand().equals("update"))
		{
			// ÐÞ¸Ä
			db.update(txt.getText(), reason.getText(), e.getActionCommand());
		}
		
	}

}
