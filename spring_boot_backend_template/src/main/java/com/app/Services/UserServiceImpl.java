package com.app.Services;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dao.UserDao;
import com.app.Entities.User;
import com.app.dto.UserRegisterDTO;
import com.app.dto.UserLoginDTO;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public String register(UserRegisterDTO user) {
 		User newUser = mapper.map(user,User.class); 
		dao.save(newUser);
		return "registered successfully!";
	}

	@Override
	public User login(UserLoginDTO u) {
		// TODO Auto-generated method stub
		User user = dao.findByEmail(u.getEmail()).orElseThrow(()->new RuntimeException("user not found"));
		if(user.getPassword().equals(u.getPassword())) {
			return user;
		}
		else {
			throw new RuntimeException("invalid credentials!");
		}
	}

	
	
	
}