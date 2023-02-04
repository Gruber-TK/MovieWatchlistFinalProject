package com.moviefinalproject.watchlists.entity;

/**
 * Output Model for retrieving movies on a watchlist 
 * @author tgrub
 *
 */

public class WatchlistMovieModel {

  private Long watchlistId;
  private String watchlistName;
  private Long movieId;
  private String movieTitle;
  private boolean haveWatched;
  
  public WatchlistMovieModel(Long watchlistId, Long movieId, boolean haveWatched, String movieTitle, String watchlistName) {
    setWatchlistId(watchlistId);
    setMovieId(movieId);
    setHaveWatched(haveWatched);
    setMovieTitle(movieTitle);
    setWatchlistName(watchlistName);
  }
  
  public WatchlistMovieModel setWatchlistId(Long watchlistId) {
    this.watchlistId = watchlistId;
    return this;
  }
  public Long getWatchlistId() {
    return watchlistId;
  }

  public String getMovieTitle() {
    return movieTitle;
  }
  public WatchlistMovieModel setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
    return this;
  }
  public boolean isHaveWatched() {
    return haveWatched;
  }
  public WatchlistMovieModel setHaveWatched(boolean haveWatched) {
    this.haveWatched = haveWatched;
    return this;
  }

  public Long getMovieId() {
    return movieId;
  }

  public WatchlistMovieModel setMovieId(Long movieId) {
    this.movieId = movieId;
    return this;
  }

  public String getWatchlistName() {
    return watchlistName;
  }

  public WatchlistMovieModel setWatchlistName(String watchlistName) {
    this.watchlistName = watchlistName;
    return this;
  }
 
  
}
