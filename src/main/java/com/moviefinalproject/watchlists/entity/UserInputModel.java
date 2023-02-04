package com.moviefinalproject.watchlists.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInputModel {
  
  @NotNull
  @Length(max = 30)
  private String userCode;
  
  @NotNull
  @Length(max = 30)
  private String userName;
  
  
  public UserInputModel(String userCode, String userName) {
    setUserCode(userCode);
    setUserName(userName);
  }

  public String getUserCode() {
    return userCode;
  }
  public UserInputModel setUserCode(String userCode) {
    this.userCode = userCode;
    return this;
  }
  public String getUserName() {
    return userName;
  }
  public UserInputModel setUserName(String userName) {
    this.userName = userName;
    return this;
  }
  
  /**
   * Checks to see if data is valid
   * @return True if valid, false if otherwise 
   */
  @JsonIgnore
  public boolean isValid() {
    return getUserCode() != null && (! getUserCode().isEmpty()) && 
            getUserName() != null && (! getUserName().isEmpty());
  }
}
