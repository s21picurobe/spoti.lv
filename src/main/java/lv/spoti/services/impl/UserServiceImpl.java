package lv.spoti.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.spoti.models.User;
import lv.spoti.models.UserRole;
import lv.spoti.repos.IUserRepo;
import lv.spoti.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public User createUser(String name, String email, String password, UserRole userRole) {
		ArrayList<User> temp = (ArrayList<User>) userRepo.findAll();
		for (User user: temp) {
			if (user.getEmail().equals(email)) {
				return null;
			}
		}
		User newUser = new User(name, email, password, userRole);
		userRepo.save(newUser);
		return newUser;
	}

	@Override
	public boolean deleteUserByIdu(Long idu) {
		ArrayList<User> temp = (ArrayList<User>) userRepo.findAll();
		for (User user : temp) {
			if (user.getIdu() == idu) {
				userRepo.delete(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public User retrieveUserByEmail(String email) {
		ArrayList<User> temp = (ArrayList<User>) userRepo.findAll();
		for (User user : temp) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User retrieveUserByIdu(Long idu) {
		ArrayList<User> temp = (ArrayList<User>) userRepo.findAll();
		for (User user : temp) {
			if (user.getIdu() == idu) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void updatePassword(String email, String password) {
		User user = retrieveUserByEmail(email);
		user.setPassword(password);
		userRepo.save(user);
	}
	
}
