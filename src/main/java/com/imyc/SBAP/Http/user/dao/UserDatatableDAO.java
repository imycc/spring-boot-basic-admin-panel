package com.imyc.SBAP.Http.user.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.repository.UserRepository;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;

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
		
		UserDatatableVO userDatatableVO = new UserDatatableVO();
		List<UserRow> userRowList = new ArrayList<UserRow>();
		
		Pageable pageRequest = PageRequest.of(start, length);
		Page<Users> allUser = userRepo.findAll(pageRequest);
		
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
			.setRecordsTotal(allUser.getTotalPages())
			.setRecordsFiltered(allUser.getTotalPages())
			.setData(userRowList);
		
		return userDatatableVO;
	}
}
