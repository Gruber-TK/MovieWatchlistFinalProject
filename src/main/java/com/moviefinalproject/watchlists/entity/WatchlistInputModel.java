package com.moviefinalproject.watchlists.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class WatchlistInputModel {

  @NotNull
  private String userCode;
  
  @NotNull
  @Length(min = 3, max = 50)
  private String watchlistName;
 

  public WatchlistInputModel(String userCode, String watchlistName) {
    setUserCode(userCode);
    setWatchlistName(watchlistName);
 
    
  }
  public String getUserCode() {
    return userCode;
  }
  public WatchlistInputModel setUserCode(String userCode) {
    this.userCode = userCode;
    return this;
  }
  public String getWatchlistName() {
    return watchlistName;
  }
  public WatchlistInputModel setWatchlistName(String watchlistName) {
    this.watchlistName = watchlistName;
    return this;
  }
  
    /**
     * Checks to see if data is valid
     * @return True if valid, false if otherwise 
     */
  @JsonIgnore
    public boolean isWatchlistValid() {
      return getUserCode() != null && (! getUserCode().isEmpty()) && 
              getWatchlistName() != null && (! getWatchlistName().isEmpty());
    
  }

}
