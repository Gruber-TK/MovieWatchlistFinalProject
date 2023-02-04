package com.moviefinalproject.watchlists.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.moviefinalproject.watchlists.entity.WatchlistMovieInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistMovieModel;
import com.moviefinalproject.watchlists.repository.WatchlistMovieRepository;

@Service
public class DefaultWatchlistMovieService implements WatchlistMovieService{

  private WatchlistMovieRepository watchlistMovieRepository;
  public DefaultWatchlistMovieService(WatchlistMovieRepository watchlistMovieRepository) {
    this.watchlistMovieRepository = watchlistMovieRepository;
  }
  
  
  @Override
  public List<WatchlistMovieModel> getAllMoviesFromWatchlist(Long watchlistId) {
    Stream<WatchlistMovieModel> movies = watchlistMovieRepository.getWatchlistMovies(watchlistId);
    return movies.toList();
  }
  
  
  @Override
  public WatchlistMovieModel addMovieToWatchlist(WatchlistMovieInputModel input){
    Optional<WatchlistMovieModel> movie = watchlistMovieRepository.addMovieToWatchlist(input);
      if(movie.isPresent()) {
        return movie.get();
      }
    return null;
  }


  @Override
  public WatchlistMovieModel updateMovieWatchlistStatus(WatchlistMovieInputModel input) {
    Optional<WatchlistMovieModel> movie = watchlistMovieRepository.updateMovieWatchlistStatus(input);
    if(movie.isPresent()) {
      return movie.get();
    }
    return null;
  }


  @Override
  public WatchlistMovieModel deleteMovieFromWatchlist(Long watchlistId, Long movieId) {
    Optional<WatchlistMovieModel> movie = watchlistMovieRepository.deleteMovieFromWatchlist(watchlistId, movieId);
    if(movie.isPresent()) {
      return movie.get();
    }
    return null;
  }
  
}
