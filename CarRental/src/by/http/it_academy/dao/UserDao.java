package by.http.it_academy.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserDao {
	String addUser(HttpServletRequest req, HttpServletResponse resp);
	String checkUser(HttpServletRequest req, HttpServletResponse resp);
}
