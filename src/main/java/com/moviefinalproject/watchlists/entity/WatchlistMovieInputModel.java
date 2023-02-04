package com.moviefinalproject.watchlists.entity;

import javax.validation.constraints.NotNull;

public class WatchlistMovieInputModel {

  /**
   * Model for adding and updating a movie to watchlist 
   */
  @NotNull
  private Long watchlistId;
  
  @NotNull
  private Long movieId;
  
  @NotNull
  private boolean haveWatched;
  
  public WatchlistMovieInputModel(Long watchlistId, Long movieId, boolean haveWatched) {
    setWatchlistId(watchlistId);
    setMovieId(movieId);
    setHaveWatched(haveWatched);
  }
  
  public WatchlistMovieInputModel setWatchlistId(Long watchlistId) {
    this.watchlistId = watchlistId;
    return this;
  }
  public Long getWatchlistId() {
    return watchlistId;
  }

  public boolean isHaveWatched() {
    return haveWatched;
  }
  public WatchlistMovieInputModel setHaveWatched(boolean haveWatched) {
    this.haveWatched = haveWatched;
    return this;
  }

  public Long getMovieId() {
    return movieId;
  }

  public WatchlistMovieInputModel setMovieId(Long movieId) {
    this.movieId = movieId;
    return this;
  }
 
  
}
