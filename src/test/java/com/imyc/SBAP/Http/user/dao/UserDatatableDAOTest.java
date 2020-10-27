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
import com.imyc.SBAP.factories.dummy.user.DummyUserDatatableVOFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserFactory;

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
		Users user = new DummyUserFactory("ADMIN").make();
		userList.add(user);
	}

	@Test
	public void testGetUserDatatableVO() {
		
		int start = (int) serverSideConfig.get("start");
		int length = (int) serverSideConfig.get("length");
		
		userDatatableVO = new DummyUserDatatableVOFactory().make();
		
		PageRequest pagable = PageRequest.of(start, length);
		Page<Users> pageUserList = new PageImpl<>(userList, pagable, userList.size());
		
		Mockito.when(userRepo.findAll(ArgumentMatchers.<Specification<Users>>any(), ArgumentMatchers.<Pageable>any()))
				.thenReturn(pageUserList);
		UserDatatableVO actual = new UserDatatableDAO(userRepo).getUserDatatableVO(serverSideConfig);
		
		assertNotNull(actual);
		assertNotNull(actual.getData());
		assertEquals(userDatatableVO.getDraw(), actual.getDraw());
		assertEquals(userDatatableVO.getRecordsFiltered(), actual.getRecordsFiltered());
		assertEquals(userDatatableVO.getRecordsTotal(), actual.getRecordsTotal());
	}

}
