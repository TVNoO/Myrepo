package tvn.com.repository;

import org.springframework.data.repository.CrudRepository;

import tvn.com.entity.Server;

public interface ServerRepository extends CrudRepository<Server, Integer>{
	
}
