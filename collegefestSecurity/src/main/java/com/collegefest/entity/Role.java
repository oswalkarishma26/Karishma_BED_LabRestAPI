package com.collegefest.entity;

import javax.persistence.Entity;

import java.util.Set;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode(of = "roleId")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long role_id;
	private String roleName;
		
	@ManyToMany(mappedBy ="roles",fetch=FetchType.LAZY)
	private Set<User> user;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(long role_id, String roleName, Set<User> user) {
		super();
		this.role_id = role_id;
		this.roleName = roleName;
		this.user = user;
	}

	public long getRole_id() {
		return role_id;
	}

	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	
}

