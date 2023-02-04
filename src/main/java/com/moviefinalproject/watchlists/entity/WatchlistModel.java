package com.moviefinalproject.watchlists.entity;


public class WatchlistModel {
  
  private Long watchlistId;
  private String watchlistName;
  private String userCode;
  
  public WatchlistModel(String userCode, String watchlistName, Long watchlistId) {
    setUserCode(userCode);
    setWatchlistName(watchlistName);
    setWatchlistId(watchlistId);
  }
  
  public Long getWatchlistId() {
    return watchlistId;
  }
  public WatchlistModel setWatchlistId(Long watchlistId) {
    this.watchlistId = watchlistId;
    return this; 
  }
  public String getUserCode() {
    return userCode;
  }
  public WatchlistModel setUserCode(String userCode) {
    this.userCode = userCode;
    return this;
  }
  public String getWatchlistName() {
    return watchlistName;
  }
  public WatchlistModel setWatchlistName(String watchlistName) {
    this.watchlistName = watchlistName;
    return this;
  }

}
