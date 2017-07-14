package tvn.com.service;

import tvn.com.entity.Server;

public interface IServerService {
	
	Iterable<Server> findAll();

	Server findOne(int id);

    void save(Server contact);

    void delete(int id);
}
