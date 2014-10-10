package edu.sjsu.cmpe282.resources;

import edu.sjsu.cmpe282.dao.UserDao;
import edu.sjsu.cmpe282.dto.User;

public class UserResource {

	private UserDao userDao;

	public UserResource() {
		userDao = new UserDao();
	}

	public User signIn(String userid, String password) {
		long time = userDao.login(userid, password);
		if (time != -1L) {
			User user = userDao.getUser(userid, time);
			return user;
		}
		return null;
	}

	public User signUp(User user) {
		if (userDao.addUser(user)) {
			User user1 = userDao.getUser(user.getEmail(), System.currentTimeMillis());
			return user1;
		}
		return null;
	}

}
