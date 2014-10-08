package edu.sjsu.cmpe282.resources;


import edu.sjsu.cmpe282.dao.UserDao;
import edu.sjsu.cmpe282.dto.User;

public class UserResource {
	
	private UserDao userDao; 
	
	public UserResource() {
		userDao = new UserDao();
	}
	
	public User signIn( String userid,
			String password) {
		if(userDao.login(userid, password)) {
			User user = userDao.getUser(userid);
			return user;
		}
		return null;
	}
	
	
	public User signUp(User user) {
		if(userDao.addUser(user)) {
			User user1 = userDao.getUser(user.getEmail());
			return user1;
		}
		return null;
	}
	
}
