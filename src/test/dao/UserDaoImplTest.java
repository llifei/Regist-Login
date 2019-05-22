package test.dao;


import org.junit.Test;

import lf.user.dao.UserDaoImpl;
import lf.user.domain.User;

/**
 * UserDao的测试
 * @author 11031
 *
 */
public class UserDaoImplTest {
	@Test
	public void testFindByUsername() {
		UserDaoImpl userDaoImpl =new UserDaoImpl();
	}
	
	@Test
	public void testAdd() {
		UserDaoImpl userDaoImpl =new UserDaoImpl();
		
		User user=new User();
		user.setUsername("结构化学");
		user.setPassword("shishabi");
		userDaoImpl.addUser(user);
	}
}
