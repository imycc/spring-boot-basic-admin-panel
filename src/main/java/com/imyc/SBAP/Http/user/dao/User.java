package com.imyc.SBAP.Http.user.dao;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.imyc.SBAP.Http.role.dao.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private String name;
	private String email;
	private String password;
	private boolean disabled;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date createdAt;
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date updatedAt;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "role_user",
		joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, 
		inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
	private Set<Role> roles;

}
