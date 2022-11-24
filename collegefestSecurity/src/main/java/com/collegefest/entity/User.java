package com.collegefest.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "userId")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;
	private String username;
	private String password;
	private String emailAddress;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private Set<Role> roles;
	
	public void addRole(Role role) {
		if(this.roles==null) {
			this.roles = new HashSet<>();
		}
		this.roles.add(role);
		
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(long userId, String username, String password, String emailAddress, Set<Role> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		this.roles = roles;
	}

	public User(String username, String password, String emailAddress, Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		this.roles = roles;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", emailAddress="
				+ emailAddress + ", roles=" + roles + "]";
	}
	
	

}
