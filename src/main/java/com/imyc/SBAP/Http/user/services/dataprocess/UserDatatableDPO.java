package com.imyc.SBAP.Http.user.services.dataprocess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.Http.role.dao.repository.RoleRepository;
import com.imyc.SBAP.Http.role.viewobject.RoleVO;
import com.imyc.SBAP.Http.user.dao.User;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;
import com.imyc.SBAP.Http.user.dao.repository.UserSpecification;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;
import com.imyc.SBAP.config.repositroy.SearchCriteria;

@Repository
public class UserDatatableDPO {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;

	@Autowired
	public UserDatatableDPO(UserRepository userRepo, RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	// Index

	public UserDatatableVO getUserDatatableVO(DatatableServerSideConfig datatableServerSideConfig) {

		int draw = (int) datatableServerSideConfig.getDraw();
		int start = (int) datatableServerSideConfig.getStart();
		int length = (int) datatableServerSideConfig.getLength();
		String keyword = (String) datatableServerSideConfig.getKeyword();
		
		UserDatatableVO userDatatableVO = new UserDatatableVO();
		List<UserRow> userRowList = new ArrayList<UserRow>();
		
		UserSpecification spec1 = new UserSpecification(new SearchCriteria("name", ":", keyword));
		UserSpecification spec2 = new UserSpecification(new SearchCriteria("email", ":", keyword));
		
		Pageable pageRequest = PageRequest.of(start, length);
		Page<User> allUser = userRepo.findAll(Specification.where(spec1).or(spec2), pageRequest);
		
		for(User user : allUser) {
			UserRow userRow = new UserRow();
			userRow
				.setId(user.getId())
				.setName(user.getName())
				.setEmail(user.getEmail())
				.setDisabled(user.isDisabled());
			
			userRowList.add(userRow);
		}
		
		userDatatableVO
			.setDraw(draw)
			.setRecordsTotal(allUser.getTotalElements())
			.setRecordsFiltered(allUser.getTotalElements())
			.setData(userRowList);
		
		return userDatatableVO;
	}

	// Read

	public Optional<UserReadVO> getUserDetailForRead(int id) {

		Optional<User> optionalUser = userRepo.findById(id);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			List<String> roleList = new ArrayList<String>();
			for (Role role : user.getRoles()) {
				roleList.add(role.getName());
			}

			UserReadVO userReadVO = new UserReadVO();

			userReadVO
				.setId(user.getId())
				.setName(user.getName())
				.setEmail(user.getEmail())
				.setUsername(user.getUsername())
				.setCreatedAt(user.getCreatedAt())
				.setUpdatedAt(user.getUpdatedAt())
				.setDisabled(user.isDisabled())
				.setRoles(roleList.toArray(new String[0]));

			return Optional.of(userReadVO);
		}else{
			return Optional.empty();
		}
	}

	// Delete

	public boolean deleteUserWithRelationById(int id) {
		
		boolean isExist = userRepo.existsById(id);
		
		if (isExist) {
			userRepo.deleteById(id);
		}else {
			return false;
		}
		return true;
	}

	// Create
	
	public UserCreateVO getRoleListForUserCreate() {

		List<RoleVO> roleVOList = getAllRoleList();
		
		UserCreateVO userCreateVO = new UserCreateVO();
		userCreateVO.setRoleVOList(roleVOList);
			
		return userCreateVO;
	}
	
	public boolean userCreate(UserCreateDTO userCreateDTO) {
		
		List<Integer> rawRoleList = userCreateDTO.getRoles();
		Set<Role> roleSet = new HashSet<>(roleRepo.findAllById(rawRoleList));
		
		User user = new User();
		user
			.setName(userCreateDTO.getName())
			.setUsername(userCreateDTO.getUsername())
			.setEmail(userCreateDTO.getEmail())
			.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()))
			.setAccountExpired(false)
			.setAccountLocked(false)
			.setCredentialsExpired(false)
			.setDisabled(false)
			.setRoles(roleSet);
			
		userRepo.save(user);
			
		return true;
	}

	// Update

	public Optional<UserUpdateVO> getUserForUpdate(int id) {

		Optional<User> optionalUser = userRepo.findById(id);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			UserUpdateVO userUpdateVO = new UserUpdateVO();

			List<Role> roleList = roleRepo.findAll();

			List<RoleVO> roleVOList = new ArrayList<>();
			for (Role role : roleList) {
				RoleVO roleVO = new RoleVO();
				roleVO
						.setId(role.getId())
						.setName(role.getName());
				for(Role userRole : user.getRoles()) {
					if (role.getId() == userRole.getId()) {
						roleVO.setChecked(true);
					}
				}
				roleVOList.add(roleVO);
			}

			userUpdateVO
					.setName(user.getName())
					.setEmail(user.getEmail())
					.setUsername(user.getUsername())
					.setRoleVOList(roleVOList);

			return Optional.of(userUpdateVO);
		}else{
			return Optional.empty();
		}
	}

	public boolean userUpdate(UserUpdateDTO userUpdateDTO, int id) {
		List<Integer> rawRoleList = userUpdateDTO.getRoles();
		Set<Role> roleSet = new HashSet<>(roleRepo.findAllById(rawRoleList));

		User user = new User();
		user
				.setId(id)
				.setName(userUpdateDTO.getName())
				.setUsername(userUpdateDTO.getUsername())
				.setEmail(userUpdateDTO.getEmail())
				.setRoles(roleSet);
		if (!userUpdateDTO.getPassword().equals("")) {
			user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
		}

		userRepo.save(user);
		return true;
	}

	// Other
	private List<RoleVO> getAllRoleList() {
		List<Role> roleList = roleRepo.findAll();

		List<RoleVO> roleVOList = new ArrayList<>();
		for (Role role : roleList) {
			RoleVO roleVO = new RoleVO();
			roleVO
					.setId(role.getId())
					.setName(role.getName());

			roleVOList.add(roleVO);
		}

		return roleVOList;
	}

}
