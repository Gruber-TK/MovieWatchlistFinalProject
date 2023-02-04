package com.moviefinalproject.watchlists.entity;


public class UserModel {
  
  private String userCode;
  private String userName;
  
  
  public UserModel(String userCode, String userName) {
    setUserCode(userCode);
    setUserName(userName);
  }

  public String getUserCode() {
    return userCode;
  }
  public UserModel setUserCode(String userCode) {
    this.userCode = userCode;
    return this;
  }
  public String getUserName() {
    return userName;
  }
  public UserModel setUserName(String userName) {
    this.userName = userName;
    return this;
  }
  
 

}
