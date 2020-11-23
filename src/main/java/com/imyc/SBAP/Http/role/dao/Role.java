package com.imyc.SBAP.Http.role.dao;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.imyc.SBAP.Http.privilege.dao.Privilege;
import com.imyc.SBAP.Http.user.dao.User;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Boolean admin;
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date createdAt;
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date updatedAt;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "roles_privileges",
			joinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "privilege_id", referencedColumnName = "id"))
	private Set<Privilege> privileges;

}
