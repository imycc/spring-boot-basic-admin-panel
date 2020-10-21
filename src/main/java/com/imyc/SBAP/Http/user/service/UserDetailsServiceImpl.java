package com.imyc.SBAP.Http.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Http.user.dao.UserDAO;
import com.imyc.SBAP.Http.user.dao.UserRepository;
import com.imyc.SBAP.Http.user.persistent.object.UserPO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDAO userDAO;
	private UserRepository userRepo;
	
	@Autowired
	public UserDetailsServiceImpl(UserDAO userDAO, UserRepository userRepo) {
		this.userDAO = userDAO;
		this.userRepo = userRepo;
	}
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
    	userDAO = new UserDAO(userRepo);
        Optional<UserPO> optionalUser = userDAO.getByUsername(userName);
        
        if(optionalUser.isPresent()) {
        	UserPO user = optionalUser.get();
        	
            return User.builder()
            	.username(user.getUsername())
            	.password(user.getPassword())
            	.disabled(user.isDisabled())
            	.accountExpired(user.isAccountExpired())
            	.accountLocked(user.isAccountLocked())
            	.credentialsExpired(user.isCredentialsExpired())
            	.roles(user.getRoles())
            	.build();
        } else {
        	throw new UsernameNotFoundException("User Name is not Found");
        }   
    }
}