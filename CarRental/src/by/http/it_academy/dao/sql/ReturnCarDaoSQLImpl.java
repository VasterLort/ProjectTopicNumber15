package by.http.it_academy.dao.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.http.it_academy.dao.ReturnCarDao;
import by.http.it_academy.web.entity.ReturnCar;

import static by.http.it_academy.dao.util.DaoConstantStorage.*;

public class ReturnCarDaoSQLImpl implements ReturnCarDao {
	@Override
	public List<ReturnCar> listCarForAdmin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ReturnCar> infoAboutRental = new ArrayList<>();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_PRESENTATION_INFO_RETURN_CARS);
			while (res.next()) {
				infoAboutRental.add(new ReturnCar(res.getInt(COMMAND_ID_ORDER), res.getString(COMMAND_LOGIN_USER),
						res.getString(COMMAND_PASSPORT_USER), res.getInt(COMMAND_ID_CAR), res.getString(COMMAND_STATUS_ORDER),
						res.getString(COMMAND_INT_PROBLEM), res.getInt(COMMAND_COST_ORDER), res.getString(COMMAND_CURRENCY_ORDER),
						res.getString(COMMAND_DATE_RETURN_CAR), res.getString(COMMAND_END_TIME)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return infoAboutRental;
	}

	@Override
	public String returnCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String check = generallCheck(req, resp, session);
		Date date = new Date();
		if (check.equals(COMMAND_CHECK_FALSE)) {
			try {
				Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
				Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
				PreparedStatement preparedStmt = con.prepareStatement(REQUEST_INSERT_INFO_ABOUT_RETURN_ORDER);
				preparedStmt.setInt(1, Integer.parseInt(session.getAttribute(COMMAND_ID_ORDER).toString()));
				preparedStmt.setString(2, date.toString());
				preparedStmt.setInt(3, 3);
				preparedStmt.setInt(4, 6);
				preparedStmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return check;
	}

	private String generallCheck(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
			throws ServletException, IOException {
		String checkRequest = checkRequest(session, req, resp);
		String checkOrder = checkRentCar(session);
		if (checkRequest.equals(COMMAND_CHECK_FALSE) && checkOrder.equals(COMMAND_CHECK_TRUE))
			return COMMAND_CHECK_FALSE;
		else if (checkOrder.equals(COMMAND_CHECK_FALSE))
			return COMMAND_CHECK_HAVE_CAR;
		else
			return COMMAND_CHECK_TRUE;
	}

	private String checkRequest(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String check = COMMAND_CHECK_FALSE;
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_RETURN_ORDER);
			while (res.next()) {
				if (Integer.parseInt(session.getAttribute(COMMAND_ID_ORDER).toString()) == res.getInt(COMMAND_ID_ORDER)) {
					check = COMMAND_CHECK_TRUE;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return check;
	}

	private String checkRentCar(HttpSession session) {
		String check = COMMAND_CHECK_FALSE;
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_ORDERS);
			while (res.next()) {
				if (session.getAttribute(COMMAND_LOGIN_USER).equals(res.getString(COMMAND_LOGIN_USER))) {
					check = COMMAND_CHECK_TRUE;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return check;
	}

	@Override
	public ReturnCar checkStatus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		ReturnCar checkInfo = new ReturnCar();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_PRESENTATION_INFO_RETURN_CARS);
			while (res.next()) {
				if (session.getAttribute(COMMAND_LOGIN_USER).equals(res.getString(COMMAND_LOGIN_USER))) {
					checkInfo = new ReturnCar(res.getInt(COMMAND_ID_ORDER), res.getString(COMMAND_LOGIN_USER), res.getString(COMMAND_PASSPORT_USER),
							res.getInt(COMMAND_ID_CAR), res.getString(COMMAND_STATUS_ORDER), res.getString(COMMAND_INT_PROBLEM), res.getInt(COMMAND_COST_ORDER),
							res.getString(COMMAND_CURRENCY_ORDER), res.getString(COMMAND_DATE_RETURN_CAR), res.getString(COMMAND_END_TIME));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return checkInfo;
	}

	@Override
	public void payDamages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ReturnCar order = (ReturnCar) session.getAttribute(COMMAND_SESSION_CHECK_STATUS);
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			PreparedStatement preparedStmt = con.prepareStatement(REQUEST_UPDATE_ORDER);
			preparedStmt.setInt(1, 1);
			preparedStmt.setInt(2, 6);
			preparedStmt.setInt(3, order.getIdOrder());
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
