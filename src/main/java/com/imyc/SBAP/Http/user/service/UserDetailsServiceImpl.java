package com.imyc.SBAP.Http.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.repo.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
	
    BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
        Optional<Users> optionalUser = usersRepository.findByUsername(userName);
        
        if(optionalUser.isPresent()) {
        	Users users = optionalUser.get();
        	
        	List<String> roleList = new ArrayList<String>();
        	for(Roles role : users.getRoles()) {
        		roleList.add(role.getName());
        	}
        	
            return User.builder()
            	.username(users.getUsername())
            	.password(bCryptPasswordEncoder.encode(users.getPassword()) )
            	.disabled(users.isDisabled())
            	.accountExpired(users.isAccountExpired())
            	.accountLocked(users.isAccountLocked())
            	.credentialsExpired(users.isCredentialsExpired())
            	.roles(roleList.toArray(new String[0]))
            	.build();
        } else {
        	throw new UsernameNotFoundException("User Name is not Found");
        }   
    }
}