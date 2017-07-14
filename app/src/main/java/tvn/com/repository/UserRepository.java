package tvn.com.repository;


import org.springframework.data.repository.CrudRepository;

import tvn.com.entity.*;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmailAndPassword(String email, String password);
}
