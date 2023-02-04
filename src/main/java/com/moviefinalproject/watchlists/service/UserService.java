package com.moviefinalproject.watchlists.service;


import com.moviefinalproject.watchlists.entity.UserInputModel;
import com.moviefinalproject.watchlists.entity.UserModel;


public interface UserService {

  /**
   * Fetches a user by userCode 
   * @param userCode
   * @return will return user info if present, otherwise null  
   */
  UserModel fetchUser(String userCode);

  
  /**
   * Creates a new user
   * @param input userCode and watchlist name 
   * @return will return the new user if created, otherwise null
   */
  UserModel createUser(UserInputModel input);



}
