package neu.edu.coe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.coe.dao.UserDao;
import neu.edu.coe.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDaoImp;
	
	public void registryUser(User user){
		userDaoImp.insert(user);
	}

}
