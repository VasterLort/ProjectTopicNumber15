package by.http.it_academy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.entity.ReturnCar;
import by.http.it_academy.web.entity.Car;
import by.http.it_academy.web.entity.Order;
import by.http.it_academy.web.entity.Problem;
import by.http.it_academy.web.entity.Profile;

public interface CatalogService {
	List<Car> catalogCars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	List<ReturnCar> listCarForAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	List<Order> getFullList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	List<Problem> getListProblem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	ReturnCar checkStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	Car singleCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	Profile getProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	Order getSingleOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	String returnCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	String addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	String checkUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void rentCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void updaterOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void payDamages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void acceptOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
