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

public class AuthorizationUsers implements BasicAction{
	private CatalogService catalogService;

	@Override
	public void perfomeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		catalogService = new CatalogServiceImpl();
		if(catalogService.checkUser(req, resp).equals(COMMAND_CHECK_TRUE)) {
			HttpSession session = req.getSession();
			session.setAttribute(COMMAND_SESSION_LOGIN, req.getParameter(COMMAND_CHECK_LOGIN));
			if(req.getParameter(COMMAND_CHECK_LOGIN).equals(COMMAND_CHECK_ADMIN)) req.getRequestDispatcher(LINK_TO_ADMIN_PAGE_JSP).forward(req, resp);
			else req.getRequestDispatcher(LINK_TO_USER_PAGE_JSP).forward(req, resp);
		}else req.getRequestDispatcher(LINK_TO_AUTHORIZATION_AGAIN_JSP).forward(req, resp);
	}
}
