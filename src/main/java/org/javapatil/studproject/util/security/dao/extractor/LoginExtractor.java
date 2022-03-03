package org.javapatil.studproject.util.security.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.javapatil.studproject.util.security.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginExtractor implements ResultSetExtractor<User> {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		User user=new User();
		
		if(rs.next())
		{
			user.setUserName(rs.getString(1));
	        user.setOrganizationId(rs.getInt(2));
	        user.setOrganizationName(rs.getString(3));
	        user.setPass(bCryptPasswordEncoder.encode(rs.getString(4)));
	        user.setStatus(rs.getString(5));
	        user.setEmailId(rs.getString(6));
	        user.setMobileNo(rs.getString(7));
	        user.setRoleName(rs.getString(8));	
		}
		
		return user;
	}
  
}
