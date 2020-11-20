package com.imyc.SBAP.Http.privilege;

import com.imyc.SBAP.Http.role.dao.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain=true)
@Entity
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String group;
	private String name;
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date created_at;
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date updated_at;

	@ManyToMany(mappedBy = "privileges")
	private Set<Role> roles;

}
