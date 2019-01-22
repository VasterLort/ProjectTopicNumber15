package by.http.it_academy.dao.sql;

import static by.http.it_academy.dao.util.DaoConstantStorage.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.http.it_academy.dao.CarDao;
import by.http.it_academy.web.entity.Car;

public class CarDaoSQLImpl implements CarDao {
	@Override
	public List<Car> getCars() {
		List<Car> cars = new ArrayList<>();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_PRESENTATION_INFO_CARS);
			while (res.next()) {
				if (res.getString(COMMAND_ALLOW_CAR).equals(COMMAND_CHECK_TRUE)) {
					cars.add(new Car(res.getInt(COMMAND_ID_CAR), res.getString(COMMAND_MARK_CAR),
							res.getString(COMMAND_MODEL_CAR), res.getString(COMMAND_TYPE_CAR),
							res.getString(COMMAND_BODY_CAR), res.getString(COMMAND_COLOR_CAR),
							res.getString(COMMAND_BOX_CAR), res.getString(COMMAND_PRIVOD_CAR),
							res.getInt(COMMAND_COST_CAR), res.getString(COMMAND_CURRNCY_CAR),
							res.getString(COMMAND_DATE_ISSUE_CAR)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cars;
	}

	@Override
	public Car singleCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Car car = new Car();
		String check = checkRentCar(session);
		session.setAttribute(COMMAND_SESSION_CHECK_USER, check);
		if (check.equals(COMMAND_CHECK_FALSE)) {
			try {
				Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
				Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(REQUEST_PRESENTATION_INFO_CARS);
				while (res.next()) {
					if (req.getParameter(COMMAND_ID_CAR).equals(res.getString(COMMAND_ID_CAR))) {
						car = new Car(res.getInt(COMMAND_ID_CAR), res.getString(COMMAND_MARK_CAR),
								res.getString(COMMAND_MODEL_CAR), res.getString(COMMAND_TYPE_CAR),
								res.getString(COMMAND_BODY_CAR), res.getString(COMMAND_COLOR_CAR),
								res.getString(COMMAND_BOX_CAR), res.getString(COMMAND_PRIVOD_CAR),
								res.getInt(COMMAND_COST_CAR), res.getString(COMMAND_CURRNCY_CAR),
								res.getString(COMMAND_DATE_ISSUE_CAR));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return car;
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
	public void rentCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			PreparedStatement preparedStmt = con.prepareStatement(REQUEST_INSERT_DATA_ORDER);
			preparedStmt.setString(1, (String) session.getAttribute(COMMAND_LOGIN_USER));
			preparedStmt.setString(2, req.getParameter(COMMAND_PASSPORT_USER));
			preparedStmt.setInt(3, (Integer) session.getAttribute(COMMAND_ID_CAR));
			preparedStmt.setString(4, (String) req.getParameter(COMMAND_END_TIME_USER));
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		allowCar(session);
	}

	private void allowCar(HttpSession session) throws ServletException, IOException {
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			PreparedStatement preparedStmt = con.prepareStatement(REQUEST_UPDATE_ALLOW_TO_CAR);
			preparedStmt.setInt(1, 2);
			preparedStmt.setInt(2, (Integer) session.getAttribute(COMMAND_ID_CAR));
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
