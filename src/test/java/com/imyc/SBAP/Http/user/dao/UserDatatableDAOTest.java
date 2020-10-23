package com.imyc.SBAP.Http.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.user.repository.UserRepository;

public class UserDatatableDAOTest {

	@Mock
	private UserRepository userRepo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		
	}

}
