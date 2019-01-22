package by.http.it_academy.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.http.it_academy.web.entity.Profile;

public interface ProfileDao {
	Profile getProfile(HttpServletRequest req, HttpServletResponse resp);
}
