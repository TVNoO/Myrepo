package tvn.com.service;

import tvn.com.entity.User;

public interface IUserService {
	
	Iterable<User> findAll();

    User findOne(int id);

    void save(User contact);

    void delete(int id);
    
    User findByEmailAndPassword(String email, String password);
}
