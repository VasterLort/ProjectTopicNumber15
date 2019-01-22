package by.http.it_academy.dao.sql;

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

import by.http.it_academy.dao.OrderDao;
import by.http.it_academy.web.entity.Order;

import static by.http.it_academy.dao.util.DaoConstantStorage.*;

public class OrderDaoSQLImpl implements OrderDao {
	@Override
	public List<Order> getFullList(HttpServletRequest req, HttpServletResponse resp) {
		List<Order> orders = new ArrayList<>();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_ORDERS);
			while (res.next()) {
				orders.add(new Order(res.getInt(COMMAND_ID_GENERAL), res.getString(COMMAND_LOGIN_USER), res.getString(COMMAND_PASSPORT_USER),
						res.getInt(COMMAND_ID_CAR), res.getString(COMMAND_RENTAL_TIME_ORDER)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public Order getSingleOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Order order = new Order();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_ORDERS);
			while (res.next()) {
				if (Integer.parseInt(req.getParameter(COMMAND_ID_ORDER)) == res.getInt(COMMAND_ID_GENERAL)) {
					order.setIdOrder(res.getInt(COMMAND_ID_GENERAL));
					order.setLogin(res.getString(COMMAND_LOGIN_USER));
					order.setPassport(res.getString(COMMAND_PASSPORT_USER));
					order.setIdCar(res.getInt(COMMAND_ID_CAR));
					order.setRentalTime(res.getString(COMMAND_RENTAL_TIME_ORDER));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return order;
	}

	@Override
	public void updaterOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Order order = (Order) session.getAttribute(COMMAND_SESSION_THE_RIGHT_MACHINE);
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			PreparedStatement preparedStmt = con.prepareStatement(REQUEST_UPDATE_ORDER);
			preparedStmt.setInt(1, 2);
			preparedStmt.setInt(2, Integer.parseInt(req.getParameter(COMMAND_INT_PROBLEM)));
			preparedStmt.setInt(3, order.getIdOrder());
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void acceptOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Order order = (Order) session.getAttribute(COMMAND_SESSION_THE_RIGHT_MACHINE);
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			PreparedStatement preparedStmt1 = con.prepareStatement(REQUEST_DELETE_RETURN_ORDER);
			PreparedStatement preparedStmt2 = con.prepareStatement(REQUEST_DELETE_ORDER);
			preparedStmt1.setInt(1, order.getIdOrder());
			preparedStmt2.setInt(1, order.getIdOrder());
			preparedStmt1.executeUpdate();
			preparedStmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		giveAllowCar(order.getIdCar());
	}

	private void giveAllowCar(int idCar) throws ServletException, IOException {
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			PreparedStatement preparedStmt = con.prepareStatement(REQUEST_UPDATE_ALLOW_TO_CAR);
			preparedStmt.setInt(1, 1);
			preparedStmt.setInt(2, idCar);
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
