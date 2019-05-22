package lf.user.service;

import lf.user.dao.DaoFactory;
import lf.user.dao.UserDao;
import lf.user.dao.UserDaoImpl;
import lf.user.domain.User;

/**
 * User的业务逻辑层
 * @author 11031
 *
 */
public class UserService {
	//把具体的实现类隐藏到工厂中
	private UserDao userDao = DaoFactory.getUserDao();
	/**
	 * 注册功能
	 * @throws UserException 
	 */
	public void regist(User user) throws UserException  {
		/*
		 * 	查询user并返回
		 * 	若返回的是null则调用add()方法
		 * 	否则抛出异常
		 */
		User _user= userDao.findByUsername(user.getUsername());
		
		if(_user!=null) 
			throw new UserException("用户名已被注册不可用");
		userDao.addUser(user);
	}
	
	/**
	 * 登录功能
	 * @param User
	 * @return
	 * @throws UserException 
	 */
	public User login(User form) throws UserException {
		/*
		 * 1.使用form中的username进行查询，得到User user
		 */
		User user= userDao.findByUsername(form.getUsername());
		/*
		 * 2.如果返回null，说明用户名不存在，抛出异常
		 */
		if(user==null)
			throw new UserException("用户名不存在！");
		/*
		 * 3.比较user的password和form的password
		 * 	如果不同，抛出异常
		 */
		if(!form.getPassword().equals(user.getPassword()))
			throw new UserException("用户名或密码错误！");
		
		/*
		 *返回数据库中查出来的user（包含所有用户信息，而不只是用户名和密码） 
		 */
		return user;
	}
}
