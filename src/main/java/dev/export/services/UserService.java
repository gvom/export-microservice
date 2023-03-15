package dev.export.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import dev.export.models.User;
import dev.export.repositorys.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	public void add(User user) {
		userRepository.insert(user);
	}
	
	public void update(User user) {
        userRepository.save(user);
    }
 
    public List<User> findAll() {
       return userRepository.findAll();
    }
 
    public long count() {
        return userRepository.count();
    }
 
    public User findById(String id) {
        return userRepository.findById(id).get();
    }
 
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
    
    public boolean exists(String id) {
        if (findById(id) != null)
			return true;
		else
			return false;
    }
    
    public User findByDocumentNumber(String documentNumber) {
    	return userRepository.findByDocumentNumber(documentNumber);
    }
}
