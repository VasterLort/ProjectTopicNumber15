package by.http.it_academy.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.dao.CarDao;
import by.http.it_academy.dao.OrderDao;
import by.http.it_academy.dao.ProblemDao;
import by.http.it_academy.dao.ProfileDao;
import by.http.it_academy.dao.ReturnCarDao;
import by.http.it_academy.dao.UserDao;
import by.http.it_academy.dao.sql.CarDaoSQLImpl;
import by.http.it_academy.dao.sql.OrderDaoSQLImpl;
import by.http.it_academy.dao.sql.ProblemDaoSQLImpl;
import by.http.it_academy.dao.sql.ProfileDaoSQLImpl;
import by.http.it_academy.dao.sql.ReturnCarDaoSQLImpl;
import by.http.it_academy.dao.sql.UserDaoSQLImpl;
import by.http.it_academy.service.CatalogService;
import by.http.it_academy.web.entity.ReturnCar;
import by.http.it_academy.web.entity.Car;
import by.http.it_academy.web.entity.Order;
import by.http.it_academy.web.entity.Problem;
import by.http.it_academy.web.entity.Profile;

public class CatalogServiceImpl implements CatalogService {
	private CarDao carDao;
	private UserDao userDao;
	private OrderDao orderDao;
	private ProblemDao problemDao;
	private ProfileDao profileDao;
	private ReturnCarDao returnCarDao;

	public CatalogServiceImpl() {
		carDao = new CarDaoSQLImpl();
		userDao = new UserDaoSQLImpl();
		orderDao = new OrderDaoSQLImpl();
		problemDao = new ProblemDaoSQLImpl();
		profileDao = new ProfileDaoSQLImpl();
		returnCarDao = new ReturnCarDaoSQLImpl();
	}

	@Override
	public List<Car> catalogCars(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return carDao.getCars();
	}

	@Override
	public String addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return userDao.addUser(req, resp);
	}

	@Override
	public String checkUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return userDao.checkUser(req, resp);
	}

	@Override
	public Car singleCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return carDao.singleCar(req, resp);
	}

	@Override
	public void rentCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		carDao.rentCar(req, resp);
	}

	@Override
	public Profile getProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return profileDao.getProfile(req, resp);
	}

	@Override
	public String returnCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return returnCarDao.returnCar(req, resp);
	}

	@Override
	public List<ReturnCar> listCarForAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return returnCarDao.listCarForAdmin(req, resp);
	}

	@Override
	public List<Order> getFullList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return orderDao.getFullList(req, resp);
	}

	@Override
	public Order getSingleOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return orderDao.getSingleOrder(req, resp);
	}

	@Override
	public List<Problem> getListProblem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return problemDao.getListProblem(req, resp);
	}

	@Override
	public void updaterOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		orderDao.updaterOrder(req, resp);
	}

	@Override
	public ReturnCar checkStatus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return returnCarDao.checkStatus(req, resp);
	}

	@Override
	public void payDamages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		returnCarDao.payDamages(req, resp);
	}

	@Override
	public void acceptOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		orderDao.acceptOrder(req, resp);
	}
}
