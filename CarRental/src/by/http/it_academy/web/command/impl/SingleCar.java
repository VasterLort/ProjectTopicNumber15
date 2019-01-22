package by.http.it_academy.web.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.http.it_academy.service.CatalogService;
import by.http.it_academy.service.impl.CatalogServiceImpl;
import by.http.it_academy.web.command.BasicAction;
import by.http.it_academy.web.entity.Car;

import static by.http.it_academy.web.util.WebConstantStorage.*;

public class SingleCar implements BasicAction {
	private CatalogService catalogService;

	@Override
	public void perfomeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		catalogService = new CatalogServiceImpl();
		Car car = catalogService.singleCar(req, resp);
		HttpSession session = req.getSession();
		if (session.getAttribute(COMMAND_SESSION_CHECK_USER).equals(COMMAND_CHECK_FALSE)) {
			session.setAttribute(COMMAND_SESSION_ID_CAR, car.getId());
			req.getRequestDispatcher(LINK_TO_RENT_JSP).forward(req, resp);
		} else
			req.getRequestDispatcher(LINK_TO_NOTIFACATIONS_HAVE_CAR_JSP).forward(req, resp);
	}
}
