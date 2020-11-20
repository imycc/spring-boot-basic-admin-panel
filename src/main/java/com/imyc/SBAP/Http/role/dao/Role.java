package com.imyc.SBAP.Http.role.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.imyc.SBAP.Http.user.dao.Users;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@Entity
@Table(name="roles")
public class Roles {

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
	private Set<Users> users = new HashSet<>();

}
