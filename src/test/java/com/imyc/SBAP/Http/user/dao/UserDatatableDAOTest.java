package com.imyc.SBAP.Http.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.repository.UserRepository;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;

public class UserDatatableDAOTest {

	@Mock
	private UserRepository userRepo;
	private HashMap<String, Object> serverSideConfig;
	private UserDatatableVO userDatatableVO;
	private List<Users> userList;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
		
		userList = new ArrayList<Users>();
		Users user = new Users();
		user
			.setId(1)
			.setName("admin")
			.setEmail("test@test.com")
			.setDisabled(false);
		userList.add(user);
	}

	@Test
	public void testGetUserDatatableVO() {
		
		int draw = (int) serverSideConfig.get("draw");
		int start = (int) serverSideConfig.get("start");
		int length = (int) serverSideConfig.get("length");
		
		List<UserRow> userRowList = new ArrayList<UserRow>();
		UserRow userRow = new UserRow();
		userRow
			.setId(1)
			.setName("admin")
			.setEmail("test@test.com")
			.setDisabled(false);
		userRowList.add(userRow);
		
		userDatatableVO = new UserDatatableVO();
		UserDatatableVO expected = userDatatableVO
										.setDraw(draw)
										.setRecordsTotal((long) 1)
										.setRecordsFiltered((long) 1)
										.setData(userRowList);
		
		PageRequest pagable = PageRequest.of(start, length);
		Page<Users> pageUserList = new PageImpl<>(userList, pagable, userList.size());
		
		Mockito.when(userRepo.findAll(ArgumentMatchers.<Specification<Users>>any(), ArgumentMatchers.<Pageable>any()))
				.thenReturn(pageUserList);
		UserDatatableVO actual = new UserDatatableDAO(userRepo).getUserDatatableVO(serverSideConfig);
		
		assertNotNull(actual);
		assertNotNull(actual.getData());
		assertEquals(expected.getDraw(), actual.getDraw());
		assertEquals(expected.getRecordsFiltered(), actual.getRecordsFiltered());
		assertEquals(expected.getRecordsTotal(), actual.getRecordsTotal());
	}

}
