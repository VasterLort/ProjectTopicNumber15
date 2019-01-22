package by.http.it_academy.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.entity.Problem;

public interface ProblemDao {
	List<Problem> getListProblem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
