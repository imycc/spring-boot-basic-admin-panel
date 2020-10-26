package com.imyc.SBAP.Http.user.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.repository.UserRepository;
import com.imyc.SBAP.Http.user.repository.UserSpecification;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;
import com.imyc.SBAP.config.repositroy.SearchCriteria;

@Repository
public class UserDatatableDAO {

	private UserRepository userRepo;

	@Autowired
	public UserDatatableDAO(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public UserDatatableVO getUserDatatableVO(HashMap<String, Object> serverSideConfig) {
		
		int draw = (int) serverSideConfig.get("draw");
		int start = (int) serverSideConfig.get("start");
		int length = (int) serverSideConfig.get("length");
		String keyword = (String)  serverSideConfig.get("keyword");
		
		UserDatatableVO userDatatableVO = new UserDatatableVO();
		List<UserRow> userRowList = new ArrayList<UserRow>();
		
		UserSpecification spec1 = new UserSpecification(new SearchCriteria("name", ":", keyword));
		UserSpecification spec2 = new UserSpecification(new SearchCriteria("email", ":", keyword));
		
		Pageable pageRequest = PageRequest.of(start, length);
		Page<Users> allUser = userRepo.findAll(Specification.where(spec1).or(spec2), pageRequest);
		
		for(Users user : allUser) {
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
}
