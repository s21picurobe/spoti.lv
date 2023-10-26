package lv.spoti.services;

import lv.spoti.models.User;
import lv.spoti.models.UserRole;

public interface IUserService {
	User createUser(String name, String email, String password, UserRole userRole);
	User retrieveUserByEmail(String email);
	User retrieveUserByIdu(Long idu);
	void updatePassword(String email, String password);
	boolean deleteUserByIdu(Long idu);
}
