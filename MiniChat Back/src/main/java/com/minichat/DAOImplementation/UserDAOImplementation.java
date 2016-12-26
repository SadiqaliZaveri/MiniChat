package com.minichat.DAOImplementation;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minichat.DAO.UserDAO;
import com.minichat.model.UserDetail;

@Repository
public class UserDAOImplementation implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public UserDetail findById(int userId) 
	{
		String sql = "from UserDetail where userId="+userId;
		List<UserDetail> list = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
	    return null;
	}

	@SuppressWarnings("unchecked")
	public UserDetail findByName(String username) 
	{
		String sql = "from UserDetail where username='" +username+"'";
		List<UserDetail> list = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
	    return null;
	}

	public void saveUser(UserDetail user) 
	{
		sessionFactory.getCurrentSession().save(user);
	}

	public void updateUser(UserDetail user) 
	{
		sessionFactory.getCurrentSession().update(user);
	}

	public void deleteUserById(int userId) 
	{
		UserDetail userToDelete=new UserDetail();
		userToDelete.setUserId(userId);
		sessionFactory.getCurrentSession().delete(userToDelete);

	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> findAllUsers() 
	{
		List<UserDetail> list = sessionFactory.getCurrentSession().createQuery("from UserDetail").getResultList();
		if(list!=null && !list.isEmpty())
		{
			return list;
		}
	    return null;
	}

	public void deleteAllUsers() 
	{
	   sessionFactory.getCurrentSession().delete("from UserDetail");
	}

	public boolean isUserExist(UserDetail user) 
	{
        return findByName(user.getUsername())!=null;
	}

	
}
