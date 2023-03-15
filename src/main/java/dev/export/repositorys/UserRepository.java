package dev.export.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.export.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	public User findByDocumentNumber(String documentNumber);
	
}
