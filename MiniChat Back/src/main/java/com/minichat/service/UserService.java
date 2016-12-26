package com.minichat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minichat.DAOImplementation.UserDAOImplementation;
import com.minichat.model.UserDetail;

@Service
public class UserService {
@Autowired
private UserDAOImplementation userDAOimplementation;

@Transactional
public UserDetail findById(int userId) 
{
   return userDAOimplementation.findById(userId);
}

@Transactional
public UserDetail findByName(String username) 
{
	return userDAOimplementation.findByName(username);
}

@Transactional
public void saveUser(UserDetail user) 
{
	userDAOimplementation.saveUser(user);
}

@Transactional
public void updateUser(UserDetail user) 
{
	userDAOimplementation.updateUser(user);
}

@Transactional
public void deleteUserById(int userId) 
{
	userDAOimplementation.deleteUserById(userId);
}

@Transactional
public List<UserDetail> findAllUsers() 
{
	return userDAOimplementation.findAllUsers();
}

@Transactional
public void deleteAllUsers() 
{
	userDAOimplementation.deleteAllUsers();
}

@Transactional
public boolean isUserExist(UserDetail user) 
{
	return userDAOimplementation.isUserExist(user);
}

}
