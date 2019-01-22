package by.http.it_academy.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.entity.Car;

public interface CarDao {
	List<Car> getCars();
	Car singleCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	void rentCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
