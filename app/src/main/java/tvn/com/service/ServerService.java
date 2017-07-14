package tvn.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvn.com.entity.Server;
import tvn.com.repository.ServerRepository;

@Service
public class ServerService implements IServerService {
	public ServerService() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	ServerRepository serverRepository;

	@Override
	public Iterable<Server> findAll() {
		// TODO Auto-generated method stub
		return serverRepository.findAll();
	}

	@Override
	public Server findOne(int id) {
		// TODO Auto-generated method stub
		return serverRepository.findOne(id);
	}

	@Override
	public void save(Server server) {
		// TODO Auto-generated method stub
		serverRepository.save(server);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		serverRepository.delete(id);
	}
	
}
