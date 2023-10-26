package lv.spoti.models;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "user_table")
@Entity
public class User{
	
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_role")
	private UserRole role;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idu;
	
	@OneToMany(mappedBy="user")
	private Collection<Spot> spots;
	
	
	//GETTERS AND SETTERS
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public int getIdu() {
		return idu;
	}
	
	//CONSTRUCTORS
	public User(String name, String email, String password, UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public User() {
		super();
		this.name = "name";
		this.email = "email@email.com";
		this.password = "password";
		this.role = UserRole.USER;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + ", idu=" + idu
				+ "]";
	}
	
	
	
	
}
