package org.javapatil.studproject.util.security.dao;

import org.javapatil.studproject.util.security.admin.LoginRequest;
import org.javapatil.studproject.util.security.admin.User;

public interface LoginDao {
  //This is login method.
  User getUserData(LoginRequest loginRequest);
}
