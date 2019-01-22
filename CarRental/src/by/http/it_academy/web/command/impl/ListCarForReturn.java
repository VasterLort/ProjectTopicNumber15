package by.http.it_academy.web.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.http.it_academy.service.CatalogService;
import by.http.it_academy.service.impl.CatalogServiceImpl;
import by.http.it_academy.web.command.BasicAction;

import static by.http.it_academy.web.util.WebConstantStorage.*;

public class ListCarForReturn implements BasicAction {
	private CatalogService catalogService;

	@Override
	public void perfomeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		catalogService = new CatalogServiceImpl();
		if (!catalogService.listCarForAdmin(req, resp).isEmpty()) {
			HttpSession session = req.getSession();
			session.setAttribute(COMMAND_SESSION_LIST_FOR_ADMIN, catalogService.listCarForAdmin(req, resp));
			req.getRequestDispatcher(LINK_TO_LIST_OF_CAR_FOR_RETURN_JSP).forward(req, resp);
		} else
			req.getRequestDispatcher(LINK_TO_NOTIFACATIONS_HAVE_NOT_ORDER_JSP).forward(req, resp);
	}
}
