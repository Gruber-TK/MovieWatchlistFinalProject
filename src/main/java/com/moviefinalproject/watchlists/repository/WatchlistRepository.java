package com.moviefinalproject.watchlists.repository;

import java.util.Optional;
import java.util.stream.Stream;
import com.moviefinalproject.watchlists.entity.WatchlistInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistModel;
import com.moviefinalproject.watchlists.entity.WatchlistUpdateModel;

public interface WatchlistRepository {

  
  /**
   * Fetches all the watchlists for a user 
   * @param userCode
   * @return will return a list of watchlists, otherwise null
   */
  Stream<WatchlistModel> getAllWatchlistsByUser(String userCode);

  
  /**
   * Creates a new watchlist for a user 
   * @param userCode and watchlistName
   * @return will return a new watchlist if created, otherwise null 
   */
  Optional<WatchlistModel> createUserWatchlist(WatchlistInputModel input);

  
  /**
   * Deletes a watchlist by watchlistId
   * @param watchlistId
   * @return will return the deleted watchlist if deleted, otherwise null
   */
  Optional<WatchlistModel> deleteWatchlist(Long watchlistId);

  
  /**
   * Updates a watchlist name 
   * @param input watchlist id, 
   * @return the updated watchlist if successful, otherwise will return null 
   */
  Optional<WatchlistModel> updateWatchlistName(WatchlistUpdateModel input);

}
