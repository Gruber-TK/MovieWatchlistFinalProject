package com.moviefinalproject.watchlists.service;

import java.util.List;
import com.moviefinalproject.watchlists.entity.WatchlistInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistModel;
import com.moviefinalproject.watchlists.entity.WatchlistUpdateModel;

public interface WatchlistService {

  /**
   * Fetches all a watchlists for a user
   * @param userCode
   * @return will return a list of watchlists, otherwise an empty list
   */
  List<WatchlistModel> fetchAllWatchlistsByUser(String userCode);


  /**
   * Creates a new watchlist for a user 
   * @param userCode and watchlistName
   * @return will return a new watchlist if created, otherwise null 
   */
  WatchlistModel createUserWatchlist(WatchlistInputModel input);

  /**
   * Deletes a watchlist
   * @param watchlistId
   * @return Will return the watchlist if deleted, otherwise null
   */
  WatchlistModel deleteWatchlist(Long watchlistId);

  /**
   * Updates a watchlist name
   * @param input watchlist name and watchlist id
   * @return will return the updated watchlist if successful, otherwise null
   */
  WatchlistModel updateWatchlistName(WatchlistUpdateModel input);

}
