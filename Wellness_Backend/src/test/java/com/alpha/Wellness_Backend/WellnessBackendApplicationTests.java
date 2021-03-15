package com.alpha.Wellness_Backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alpha.Wellness_Backend.dao.IBlogDao;
import com.alpha.Wellness_Backend.dao.IUserDao;
import com.alpha.Wellness_Backend.daoImpl.BlogDaoImpl;
import com.alpha.Wellness_Backend.daoImpl.UserDaoImpl;
import com.alpha.Wellness_Backend.model.User;

@SpringBootTest
class WellnessBackendApplicationTests {

	IUserDao userDao=null;
	IBlogDao blogDao=null;
	
	@Before
	public void setup() throws ClassNotFoundException, SQLException {
		userDao=new UserDaoImpl();
		blogDao=new BlogDaoImpl();
	}
	
	
	@Test
	void contextLoads() {
	}

	
}
