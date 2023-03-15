package dev.export.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

	@Id
    private ObjectId id; 
    private String name; 
    private String email;
    private Long documentNumber;
    
	public User(ObjectId id, String name, String email, Long documentNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.documentNumber = documentNumber;
	}
	public User(String name, String email, Long documentNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.documentNumber = documentNumber;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}	
}
