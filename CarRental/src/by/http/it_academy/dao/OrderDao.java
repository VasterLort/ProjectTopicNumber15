package by.http.it_academy.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.entity.Order;

public interface OrderDao {
	List<Order> getFullList(HttpServletRequest req, HttpServletResponse resp);
	Order getSingleOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void updaterOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void acceptOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
