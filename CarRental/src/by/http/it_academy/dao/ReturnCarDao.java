package by.http.it_academy.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.entity.ReturnCar;

public interface ReturnCarDao {
	List<ReturnCar> listCarForAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	ReturnCar checkStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	String returnCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void payDamages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
