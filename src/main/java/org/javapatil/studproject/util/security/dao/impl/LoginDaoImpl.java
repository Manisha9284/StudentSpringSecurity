package org.javapatil.studproject.util.security.dao.impl;

import org.javapatil.studproject.util.security.admin.LoginRequest;
import org.javapatil.studproject.util.security.admin.User;
import org.javapatil.studproject.util.security.dao.LoginDao;
import org.javapatil.studproject.util.security.dao.extractor.LoginExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
@Component
public class LoginDaoImpl implements LoginDao {
  private JdbcTemplate jdbcTemplate;
  private LoginExtractor loginExtractor; 
  //private CheckUserExtractor checkUserExtractor;

@Override
public User getUserData(LoginRequest loginRequest) {
	return jdbcTemplate.query("select * from login('" + loginRequest.getUserName() + "', '" + loginRequest.getPassword() + "')" , loginExtractor::extractData);
}
  
}
