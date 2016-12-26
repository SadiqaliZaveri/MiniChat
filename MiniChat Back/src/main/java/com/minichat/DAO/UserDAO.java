package com.minichat.DAO;

import java.util.List;

import com.minichat.model.UserDetail;

public interface UserDAO {

	public UserDetail findById(int userId);
    public UserDetail findByName(String userName);
    public void saveUser(UserDetail user);
    public void updateUser(UserDetail user);
    public void deleteUserById(int userId);
    public List<UserDetail> findAllUsers(); 
    public void deleteAllUsers();
    public boolean isUserExist(UserDetail user);
}
