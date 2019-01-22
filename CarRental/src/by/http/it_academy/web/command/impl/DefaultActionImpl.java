package by.http.it_academy.web.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.command.BasicAction;

import static by.http.it_academy.web.util.WebConstantStorage.*;

public class DefaultActionImpl implements BasicAction {
	@Override
	public void perfomeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getAttribute(COMMAND_NO_ACTION);
	}
}
