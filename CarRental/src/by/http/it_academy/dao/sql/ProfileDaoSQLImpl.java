package by.http.it_academy.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.http.it_academy.dao.ProfileDao;
import by.http.it_academy.web.entity.Car;
import by.http.it_academy.web.entity.Profile;
import by.http.it_academy.web.entity.User;

import static by.http.it_academy.dao.util.DaoConstantStorage.*;

public class ProfileDaoSQLImpl implements ProfileDao{
	@Override
	public Profile getProfile(HttpServletRequest req, HttpServletResponse resp) {
		Profile profile = new Profile();
		profile.setUser(getUser(req, resp));
		HttpSession session = req.getSession();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_PROFILE);
			while (res.next()) {
				if (res.getString(COMMAND_LOGIN_USER).equals((String) session.getAttribute(COMMAND_LOGIN_USER))) {
					profile.setCar(new Car(res.getString(COMMAND_MARK_CAR), res.getString(COMMAND_MODEL_CAR),
							res.getString(COMMAND_TYPE_CAR), res.getString(COMMAND_BODY_CAR),
							res.getString(COMMAND_COLOR_CAR), res.getString(COMMAND_BOX_CAR),
							res.getString(COMMAND_PRIVOD_CAR), res.getInt(COMMAND_COST_CAR),
							res.getString(COMMAND_CURRNCY_CAR), res.getString(COMMAND_DATE_ISSUE_CAR)));
					profile.setRental_end(res.getString(COMMAND_END_ORDER));
					session.setAttribute(COMMAND_ID_ORDER, res.getString(COMMAND_ID_ORDER));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profile;
	}
	
	private User getUser(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		HttpSession session = req.getSession();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_USERS);
			while (res.next()) {
				if (res.getString(COMMAND_LOGIN_USER).equals((String) session.getAttribute(COMMAND_LOGIN_USER))) {
					user.setName(res.getString(COMMAND_NAME_USER));
					user.setSurname(res.getString(COMMAND_SURNAME_USER));
					user.setLogin(res.getString(COMMAND_LOGIN_USER));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
