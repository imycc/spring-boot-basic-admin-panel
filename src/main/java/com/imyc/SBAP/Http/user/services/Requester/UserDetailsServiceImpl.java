package com.imyc.SBAP.Http.user.services.Requester;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Http.user.services.dpl.UserDPO;
import com.imyc.SBAP.Http.user.viewobject.UserVO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDPO userDAO;
	
	@Autowired
	public UserDetailsServiceImpl(UserDPO userDAO) {
		this.userDAO = userDAO;
	}
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
        Optional<UserVO> optionalUser = userDAO.getUserByUsername(userName);
        
        if(optionalUser.isPresent()) {
        	UserVO user = optionalUser.get();
        	
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