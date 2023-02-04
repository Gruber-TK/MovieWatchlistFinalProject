package com.moviefinalproject.watchlists.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.moviefinalproject.watchlists.entity.WatchlistInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistModel;
import com.moviefinalproject.watchlists.entity.WatchlistUpdateModel;
import com.moviefinalproject.watchlists.repository.WatchlistRepository;

@Service
public class DefaultWatchlistService implements WatchlistService {

  private WatchlistRepository watchlistRepository;
  
  public DefaultWatchlistService(WatchlistRepository watchlistRepository) {
    this.watchlistRepository = watchlistRepository;
  }
  
  
  @Override
  public List<WatchlistModel> fetchAllWatchlistsByUser(String userCode) {
      Stream <WatchlistModel> watchlists = watchlistRepository.getAllWatchlistsByUser(userCode);
        return watchlists.toList();
  }
  
 
  @Override
  public WatchlistModel createUserWatchlist(WatchlistInputModel input) {
    if (input != null && input.isWatchlistValid()) {
      Optional<WatchlistModel> watchlist = watchlistRepository.createUserWatchlist(input);
      if(watchlist.isPresent()) {
        return watchlist.get();
      }
    }
    return null;
  }

  
  @Override
  public WatchlistModel deleteWatchlist(Long watchlistId) {
    if(watchlistId != null) {
      Optional<WatchlistModel> watchlist = watchlistRepository.deleteWatchlist(watchlistId);
      if(watchlist.isPresent()) {
        return watchlist.get();
      }
     }
    return null;
  }

  
  @Override
  public WatchlistModel updateWatchlistName(WatchlistUpdateModel input) {
    if (input != null) {
      Optional<WatchlistModel> watchlist = watchlistRepository.updateWatchlistName(input);
      if(watchlist.isPresent()) {
        return watchlist.get();
      }
    }
    return null;
  }

  
  
}
