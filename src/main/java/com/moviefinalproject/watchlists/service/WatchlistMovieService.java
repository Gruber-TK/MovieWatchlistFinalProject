package com.moviefinalproject.watchlists.service;

import java.util.List;
import com.moviefinalproject.watchlists.entity.WatchlistMovieInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistMovieModel;


public interface WatchlistMovieService {

  /**
   * Fetches all movies on a watchlist 
   * @param watchlistId
   * @returns a list of movies with movie id, movie title, and if the movie has been watched, will return empty list if no movies on watchlist
   */
  List<WatchlistMovieModel> getAllMoviesFromWatchlist(Long watchlistId);

  
  /**
   * Adds a movie to a watchlist 
   * @param input watchlist id, movie id, and have watched
   * @return a watchlist movie model if movie is added or is already in watchlist, otherwise null 
   */
  WatchlistMovieModel addMovieToWatchlist(WatchlistMovieInputModel input);

  
  /**
   * Updates the HaveWatched status on a movie in a watchlist
   * @param input watchlist id, movie id, and have watched
   * @return the updated movie, otherwise null 
   */
  WatchlistMovieModel updateMovieWatchlistStatus(WatchlistMovieInputModel input);


  /**
   * Deletes a movie from a watchlist
   * @param input a watchlist movie, watchlist id and movie id, will still return movie if no changes were made, will return null if movie not on watchlist
   * @returns the deleted movie 
   */
  WatchlistMovieModel deleteMovieFromWatchlist(Long watchlistId, Long movieId);

}
