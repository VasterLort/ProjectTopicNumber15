package by.http.it_academy.dao.sql;

import static by.http.it_academy.dao.util.DaoConstantStorage.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.dao.UserDao;

public class UserDaoSQLImpl implements UserDao {
	@Override
	public String addUser(HttpServletRequest req, HttpServletResponse resp) {
		String check = checkLogin(req, resp);
		if (check.equals(COMMAND_CHECK_TRUE)) {
			try {
				Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
				Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
				PreparedStatement preparedStmt = con.prepareStatement(REQUEST_INSERT_INFO_ABOUT_USERS);
				preparedStmt.setString(1, req.getParameter(COMMAND_NAME_USER));
				preparedStmt.setString(2, req.getParameter(COMMAND_SURNAME_USER));
				preparedStmt.setString(3, req.getParameter(COMMAND_LOGIN_USER));
				preparedStmt.setString(4, req.getParameter(COMMAND_PASSWORD_USER));
				preparedStmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	@Override
	public String checkUser(HttpServletRequest req, HttpServletResponse resp) {
		String check = COMMAND_CHECK_FALSE;
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_USERS);
			while (res.next()) {
				if (req.getParameter(COMMAND_LOGIN_USER).equals(res.getString(COMMAND_LOGIN_USER))
						&& req.getParameter(COMMAND_PASSWORD_USER).equals(res.getString(COMMAND_PASSWORD_USER))) {
					check = COMMAND_CHECK_TRUE;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public String checkLogin(HttpServletRequest req, HttpServletResponse resp) {
		String check = COMMAND_CHECK_TRUE;
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_USERS);
			while (res.next()) {
				if (req.getParameter(COMMAND_LOGIN_USER).equals(res.getString(COMMAND_LOGIN_USER))) {
					check = COMMAND_CHECK_FALSE;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
}
