package by.http.it_academy.dao.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.dao.ProblemDao;
import by.http.it_academy.web.entity.Problem;

import static by.http.it_academy.dao.util.DaoConstantStorage.*;

public class ProblemDaoSQLImpl implements ProblemDao{
	@Override
	public List<Problem> getListProblem(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Problem> listProblem = new ArrayList<>();
		try {
			Class.forName(FIELD_DYNAMIC_DRIVER_LOADING).newInstance();
			Connection con = DriverManager.getConnection(FIELD_URL, FIELD_USER_NAME, FIELD_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(REQUEST_INFO_ABOUT_PROBLEMS);
			while (res.next()) {
				listProblem.add(new Problem(res.getInt(COMMAND_ID_PROBLEM), res.getString(COMMAND_NAME_PROBLEM)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listProblem;
	}
}
