package com.moviefinalproject.watchlists.repository;

import java.util.Optional;
import java.util.stream.Stream;
import com.moviefinalproject.watchlists.entity.WatchlistMovieInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistMovieModel;

public interface WatchlistMovieRepository {

  /**
   * Fetches all movies on a watchlist from the database by watchlistId
   * @param watchlistId
   * @returns a stream of movies with movie id, movie title, watchlist name, and if the movie has been watched, will return empty if no movies on watchlist 
   */
  Stream<WatchlistMovieModel> getWatchlistMovies(Long watchlistId);

  
  /**
   * Adds a movie to a watchlist 
   * @param input watchlist id, movie id, and has watched
   * @returns a watchlist movie model if movie is added to watchlist, otherwise null  
   */
  Optional<WatchlistMovieModel> addMovieToWatchlist(WatchlistMovieInputModel input);

  
  /**
   * Updates the have watched status on a movie in a watchlist
   * @param input watchlist id, movie id, and has watched
   * @return the watchlist movie model with the updated status if updated, will return model if no changes were made, will return null if movie not on watchlist 
   */
  Optional<WatchlistMovieModel> updateMovieWatchlistStatus(WatchlistMovieInputModel input);

  
  /**
   * Deletes a move from a watchlist 
   * @param watchlistId
   * @param movieId
   * @return the deleted movie if movie deleted, otherwise null 
   */
  Optional<WatchlistMovieModel> deleteMovieFromWatchlist(Long watchlistId, Long movieId);

}
