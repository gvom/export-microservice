package dev.export.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.export.models.User;

public class ThreadService implements Runnable {
	
	@Autowired
	private UserService userService;
	
	private Integer importId;
	private List<User> users;
	private Integer percentage;
	
	public ThreadService(List<User> users, Integer importId) {
		super();
		this.users = users;
		this.percentage = 0;
		this.importId = importId;
	}

	@Override
	public void run() {
		int count = 0; 
		for(User user : users) {
			count++;
			
			userService.add(user);
			
			percentage = count / users.size();
			percentage *= 100;
		}
		
		this.percentage = 100;
	}

	public Integer getImportId() {
		return importId;
	}

	public void setImportId(Integer importId) {
		this.importId = importId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
}
