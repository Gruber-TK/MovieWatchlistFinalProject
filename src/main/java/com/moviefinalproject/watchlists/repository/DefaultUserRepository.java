package com.moviefinalproject.watchlists.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.moviefinalproject.watchlists.entity.UserInputModel;
import com.moviefinalproject.watchlists.entity.UserModel;


@Repository
public class DefaultUserRepository implements UserRepository {
  
  private NamedParameterJdbcTemplate jdbcTemplate;
  public DefaultUserRepository(NamedParameterJdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }
  
  
  
  //Method to fetch a user from the database 
  @Override
  public Optional<UserModel> getUser(String userCode) {
    
    if((userCode != null) && (! userCode.isEmpty())) {
      String sql = "SELECT user_id, user_code, user_name FROM users WHERE user_code = :user_code;";
      
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("user_code", userCode);
      
      List<UserModel> users = jdbcTemplate.query(sql, parameters, (rs, rowNum) ->{
        return toUserModel(rs,rowNum);
      });
      if (users.size() >= 1) {
        return Optional.of(users.get(0));
      }
    }
    return Optional.empty();
  }

  
  
  //Method to create a new user in the database 
  @Override
  public Optional<UserModel> createUser(UserInputModel input) {
    if ((input != null) && (input.isValid())) {
      String sql = "INSERT INTO users (user_code, user_name) VALUES (:user_code, :user_name);";
      
      try {
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("user_code", input.getUserCode());
      parameters.put("user_name", input.getUserName());
      
      int rows = jdbcTemplate.update(sql, parameters);
      
      if (rows == 1) {
        return getUser(input.getUserCode());
      }
    } catch (Exception e) {
      return Optional.empty();
    }
    }
    return Optional.empty();
  }
  
  
  
  /*
   * Takes a result set and converts it to a user model
   */
  private UserModel toUserModel(ResultSet rs, int rowNum) {
    //@formatter:off
    try {
      UserModel user = new UserModel(
          rs.getString("user_code"),
          rs.getString("user_name"));
      return user;
    //@formatter:on
    }
    catch (SQLException e) {
      return null;
    }
  }

}
