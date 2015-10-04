package entities;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;

/**
 * Entity implementation class for Entity: SubscribeEmail
 *
 */
@Entity
public class SubscribeEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Email(message="Email is not valid")
	private String email;

	public SubscribeEmail() {}   
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}
