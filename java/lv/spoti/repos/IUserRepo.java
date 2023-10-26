package lv.spoti.repos;

import org.springframework.data.repository.CrudRepository;

import lv.spoti.models.User;

public interface IUserRepo extends CrudRepository<User, Long> {
	User findUserByIdu(long idu);
	User findUserByEmail(String email);
}
