package com.moviefinalproject.watchlists.repository;

import java.util.Optional;
import com.moviefinalproject.watchlists.entity.UserInputModel;
import com.moviefinalproject.watchlists.entity.UserModel;


public interface UserRepository {
  
  /**
   * Fetches a user by userCode 
   * @param userCode
   * @return returns user if found, otherwise null
   */
  Optional<UserModel> getUser(String userCode);

  
  /**
   * Creates a user 
   * @param input unique user code and user name 
   * @return new user if created, otherwise null 
   */
  Optional<UserModel> createUser(UserInputModel input);


}
