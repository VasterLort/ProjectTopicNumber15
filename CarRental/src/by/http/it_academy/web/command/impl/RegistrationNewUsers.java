package by.http.it_academy.web.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.service.CatalogService;
import by.http.it_academy.service.impl.CatalogServiceImpl;
import by.http.it_academy.web.command.BasicAction;

import static by.http.it_academy.web.util.WebConstantStorage.*;

public class RegistrationNewUsers implements BasicAction {
	private CatalogService catalogService;

	@Override
	public void perfomeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		catalogService = new CatalogServiceImpl();
		if(catalogService.addUser(req, resp).equals(COMMAND_CHECK_TRUE)) req.getRequestDispatcher(LINK_TO_AUTHORIZATION_JSP).forward(req, resp);
		else req.getRequestDispatcher(LINK_TO_REGISTRATION_AGAIN_JSP).forward(req, resp);
	}
}
