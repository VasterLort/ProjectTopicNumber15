package by.http.it_academy.web.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.command.BasicAction;

public class NextPagePay implements BasicAction {
	@Override
	public void perfomeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getRequestDispatcher("/jsp/user/pay_damages.jsp").forward(req, resp);
	}
}
