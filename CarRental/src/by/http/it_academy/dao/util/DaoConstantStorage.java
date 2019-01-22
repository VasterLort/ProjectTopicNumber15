package by.http.it_academy.dao.util;

public class DaoConstantStorage {
	private DaoConstantStorage() {
	}

	public static final String FIELD_URL = "jdbc:mysql://localhost/rent_cars?serverTimezone=Europe/Moscow&useSSL=false&allowPublicKeyRetrieval=true";
	public static final String FIELD_USER_NAME = "root";
	public static final String FIELD_PASSWORD = "1234";
	public static final String FIELD_DYNAMIC_DRIVER_LOADING = "com.mysql.cj.jdbc.Driver";
	
	public static final String REQUEST_INSERT_INFO_ABOUT_USERS = "INSERT INTO info_about_users (name, surname, login, password) values (?, ?, ?, ?)";
	public static final String REQUEST_INSERT_DATA_ORDER = "INSERT INTO data_order (login, passport, id_car, rental_time) values (?, ?, ?, ?)";
	public static final String REQUEST_INSERT_INFO_ABOUT_RETURN_ORDER = "INSERT INTO return_order (id_order, date_return_car, id_status_car, id_problem_with_car) values (?, ?, ?, ?)";
	public static final String REQUEST_PRESENTATION_INFO_CARS = "SELECT * FROM presentation_car";
	public static final String REQUEST_PRESENTATION_INFO_RETURN_CARS = "SELECT * FROM presentation_return_car";
	public static final String REQUEST_INFO_ABOUT_ORDERS = "SELECT * FROM data_order";
	public static final String REQUEST_INFO_ABOUT_PROBLEMS = "SELECT * FROM problem_with_car";
	public static final String REQUEST_INFO_ABOUT_USERS = "SELECT * FROM info_about_users";
	public static final String REQUEST_INFO_ABOUT_PROFILE = "SELECT * FROM info_about_users_and_order";
	public static final String REQUEST_INFO_ABOUT_RETURN_ORDER = "SELECT * FROM return_order";
	public static final String REQUEST_UPDATE_ALLOW_TO_CAR = "UPDATE car SET allow = ? WHERE id_car = ? ";
	public static final String REQUEST_UPDATE_ORDER = "UPDATE return_order SET id_status_car = ?, id_problem_with_car = ? WHERE id_order = ? ";
	public static final String REQUEST_DELETE_ORDER = "DELETE FROM data_order WHERE id = ? ";
	public static final String REQUEST_DELETE_RETURN_ORDER = "DELETE FROM return_order WHERE id_order = ? ";
	
	public static final String COMMAND_ID_CAR = "id_car";
	public static final String COMMAND_MARK_CAR = "mark";
	public static final String COMMAND_MODEL_CAR = "model";
	public static final String COMMAND_TYPE_CAR = "type";
	public static final String COMMAND_BODY_CAR = "body";
	public static final String COMMAND_COLOR_CAR = "color";
	public static final String COMMAND_BOX_CAR = "box";
	public static final String COMMAND_PRIVOD_CAR = "privod";
	public static final String COMMAND_COST_CAR = "cost";
	public static final String COMMAND_CURRNCY_CAR = "currency";
	public static final String COMMAND_DATE_ISSUE_CAR = "date_issue";
	public static final String COMMAND_ALLOW_CAR = "allow";
	
	public static final String COMMAND_ID_USER = "id_user";
	public static final String COMMAND_NAME_USER = "name";
	public static final String COMMAND_SURNAME_USER = "surname";
	public static final String COMMAND_LOGIN_USER = "login";
	public static final String COMMAND_PASSWORD_USER = "password";
	public static final String COMMAND_PASSPORT_USER = "passport";
	public static final String COMMAND_END_TIME_USER = "date_end";
	
	public static final String COMMAND_ID_PROBLEM = "id";
	public static final String COMMAND_NAME_PROBLEM = "name";
	public static final String COMMAND_INT_PROBLEM = "problem";
	
	public static final String COMMAND_DATE_RETURN_CAR = "date_return_car";
	public static final String COMMAND_END_TIME = "end_time";
	
	public static final String COMMAND_ID_GENERAL = "id";
	public static final String COMMAND_ID_ORDER = "id_order";
	public static final String COMMAND_RENTAL_TIME_ORDER = "rental_time";
	public static final String COMMAND_END_ORDER = "rental_end";
	public static final String COMMAND_COST_ORDER = "cost";
	public static final String COMMAND_CURRENCY_ORDER = "currency";
	public static final String COMMAND_STATUS_ORDER = "status";
	
	public static final String COMMAND_CHECK_TRUE = "true";
	public static final String COMMAND_CHECK_FALSE = "false";
	public static final String COMMAND_CHECK_HAVE_CAR = "don't have a car";
	
	public static final String COMMAND_SESSION_CHECK_USER = "check_user";
	public static final String COMMAND_SESSION_CHECK_STATUS = "check_status";
	public static final String COMMAND_SESSION_THE_RIGHT_MACHINE = "the_right_machine";
}
