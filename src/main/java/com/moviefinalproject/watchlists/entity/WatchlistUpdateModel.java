package com.moviefinalproject.watchlists.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class WatchlistUpdateModel {

  @NotNull
  private Long watchlistId;
  
  @NotNull
  @Length(min = 3, max = 50)
  private String watchlistName;
  
  public WatchlistUpdateModel(String watchlistName, Long watchlistId) {
    setWatchlistName(watchlistName);
    setWatchlistId(watchlistId);
  }
  
  public Long getWatchlistId() {
    return watchlistId;
  }
  public WatchlistUpdateModel setWatchlistId(Long watchlistId) {
    this.watchlistId = watchlistId;
    return this; 
  }

  public String getWatchlistName() {
    return watchlistName;
  }
  public WatchlistUpdateModel setWatchlistName(String watchlistName) {
    this.watchlistName = watchlistName;
    return this;
  }
  
}
