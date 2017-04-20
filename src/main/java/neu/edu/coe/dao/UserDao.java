package neu.edu.coe.dao;


import java.util.List;

import neu.edu.coe.model.User;


public class UserDao extends DAO {


	public UserDao(){}
	
	public void insert(User user) {
		System.out.println("insert called");
		System.out.println("Created Record Name = " + user.getUsername() + ", Password = " + user.getPassword());
		begin();
		getSession()
			.saveOrUpdate(user);
		System.out.println("Created Record Name = " + user.getUsername() + ", Password = " + user.getPassword());
		commit();
		close();
	}

	
	public User findByUserID(int userId) {
		System.out.println("findByUserID called");
		begin();
		User result =(User)getSession()
				.createQuery("from User where uid = :uid")
				.setParameter("uid", userId)
				.uniqueResult();	
		commit();
		close();	
		return result;
	}

	
	public List<User> getUsers() {
		
		System.out.println("getUser called");
		begin();
		@SuppressWarnings("unchecked")
		List<User> result =getSession()
				.createQuery( "from User" )
				.list();	
		commit();
		close();	
		return result;
	}

	
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		System.out.println("findByUserName called");
		begin();
		User result =(User)getSession()
				.createQuery("from User where username = :username")
				.setParameter("username", username)
				.uniqueResult();	
		commit();
		close();	
		return result;
	}

	
	public void update(User user) {
		
		System.out.println("update called");
		insert(user);	
	}

	
	public void deleteUser(int userId) {
		
		System.out.println("deleteUser called");
		begin();
		getSession()
			.createQuery("delete User " +
					"where uid =:uid")
			.setParameter("uid", userId)
			.executeUpdate();
		commit();
		close();
	}

}
