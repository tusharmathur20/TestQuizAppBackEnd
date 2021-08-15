package com.exam.service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServieImpl implements UserService {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private RoleRepository rolerepo;
	
	
	//Creating User
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
User local=this.userrepo.findByuserName(user.getUserName());
if(local!=null) {
	System.out.println("User is already there !!");
	throw new UserFoundException();
}else {
	//user create
	for(UserRole ur:userRoles) {
		rolerepo.save(ur.getRole());
	}
	
	user.getUserRoles().addAll(userRoles);
	local = this.userrepo.save(user);
}
		return local;
	}

//getting user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userrepo.findByuserName(username);
	}

	@Override
	public void deleteUser(long userId) {
		// TODO Auto-generated method stub
		this.userrepo.deleteById(userId);
		
	}

}
