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

public class FullList implements BasicAction {
	private CatalogService catalogService;

	@Override
	public void perfomeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		catalogService = new CatalogServiceImpl();
		if (!catalogService.getFullList(req, resp).isEmpty()) {
			HttpSession session = req.getSession();
			session.setAttribute(COMMAND_SESSION_FULL_LIST, catalogService.getFullList(req, resp));
			req.getRequestDispatcher(LINK_TO_FULL_LIST_RENTALS_JSP).forward(req, resp);
		} else
			req.getRequestDispatcher(LINK_TO_HAVE_NOT_ORDER_JSP).forward(req, resp);
	}
}
