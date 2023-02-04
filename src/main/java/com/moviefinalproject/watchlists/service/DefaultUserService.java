package com.moviefinalproject.watchlists.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moviefinalproject.watchlists.entity.UserInputModel;
import com.moviefinalproject.watchlists.entity.UserModel;
import com.moviefinalproject.watchlists.repository.UserRepository;


@Service
public class DefaultUserService implements UserService{

  private UserRepository userRepository;
  
  public DefaultUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  
  @Transactional
  @Override
  public UserModel fetchUser(String userCode) {
    if ((userCode != null ) && (! userCode.isEmpty())) {
      Optional<UserModel> user = userRepository.getUser(userCode);
      if(user.isPresent()) {
        return user.get();
      }
    }
    return null;
  }
  
  
  @Override
  public UserModel createUser(UserInputModel input) {
    if (input != null && input.isValid()) {
      Optional<UserModel> user = userRepository.createUser(input);
      if(user.isPresent()) {
        return user.get();
      }
    }
    return null;
  }

}
