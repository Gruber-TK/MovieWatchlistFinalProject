package com.moviefinalproject.watchlists.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.moviefinalproject.watchlists.entity.WatchlistMovieInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistMovieModel;

@Repository
public class DefaultWatchlistMovieRepository implements WatchlistMovieRepository{

  private NamedParameterJdbcTemplate jdbcTemplate;

  public DefaultWatchlistMovieRepository(NamedParameterJdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }


  
  //Fetches all the movies on a watchlist
  @Override
  public Stream<WatchlistMovieModel> getWatchlistMovies(Long watchlistId) {
    //@formatter:off
    String sql = "SELECT watchlist_movie.watchlist_id, watchlist_movie.movie_id, watchlist_movie.have_watched, movie.movie_title, watchlist.watchlist_name " 
              + "FROM watchlist_movie LEFT JOIN  movie ON movie.movie_id = watchlist_movie.movie_id "
              + "LEFT JOIN watchlist ON watchlist_movie.watchlist_id = watchlist.watchlist_id WHERE watchlist_movie.watchlist_id = :watchlist_id"; 
    //@formatter:on
    
    Map<String,Object> parameters = new HashMap<>();
    parameters.put("watchlist_id", watchlistId);
    
    List<WatchlistMovieModel> movies = jdbcTemplate.query(sql, parameters, new RowMapper<>() {
      public WatchlistMovieModel mapRow(ResultSet rs, int rowNum) {
      try { 
        WatchlistMovieModel movie = new WatchlistMovieModel(
            rs.getLong("watchlist_id"),
            rs.getLong("movie_id"),
            rs.getBoolean("have_watched"),
            rs.getString("movie_title"),
            rs.getString("watchlist_name")
            );
            return movie;
      } catch (SQLException e) {
        return null;
      }      
    }
  });
    return movies.stream(); 
  }

  
  
  //Adds a movie to a watchlist, will return a the movie if it is created or if it is already on the watchlist 
  @Override
  public Optional<WatchlistMovieModel> addMovieToWatchlist(WatchlistMovieInputModel input){
    
    Optional<WatchlistMovieModel> existing = getMovieOnWatchlist(input.getWatchlistId(),input.getMovieId());  
    if(existing.isEmpty()) {
    String sql = "INSERT INTO watchlist_movie (watchlist_id, movie_id, have_watched) VALUES (:watchlist_id, :movie_id, :have_watched);";
    
      try {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("watchlist_id", input.getWatchlistId());
        parameters.put("movie_id", input.getMovieId());
        parameters.put("have_watched", input.isHaveWatched());
    
        int rows = jdbcTemplate.update(sql, parameters);
        if (rows != 1) {
          return Optional.empty();
        }
        Optional<WatchlistMovieModel> updated = getMovieOnWatchlist(input.getWatchlistId(),input.getMovieId());
        return updated;
     
      } catch (Exception e) {
        return Optional.empty();
      }
        
    } return existing; 
   }
 
  
  
  //Updates the watched status of a movie on a watchlist
  @Override
  public Optional<WatchlistMovieModel> updateMovieWatchlistStatus(WatchlistMovieInputModel input) {
    
    Optional<WatchlistMovieModel> existing = getMovieOnWatchlist(input.getWatchlistId(),input.getMovieId());   
    if(existing.isPresent()) {
      String sql = "UPDATE watchlist_movie SET have_watched = :have_watched WHERE watchlist_id = :watchlist_id and movie_id = :movie_id;";
      
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("watchlist_id", input.getWatchlistId());
        parameters.put("movie_id", input.getMovieId());
        parameters.put("have_watched", input.isHaveWatched());
    
        int rows = jdbcTemplate.update(sql, parameters);    
        if (rows != 1) {
          return Optional.empty();
        }
        Optional<WatchlistMovieModel> updated = getMovieOnWatchlist(input.getWatchlistId(),input.getMovieId());
        return updated;
 
      } return Optional.empty();
  }
  
  
  
  //Deletes a movie from a watchlist
  @Override
  public Optional<WatchlistMovieModel> deleteMovieFromWatchlist(Long watchlistId, Long movieId) {
    
    Optional<WatchlistMovieModel> existing = getMovieOnWatchlist(watchlistId, movieId);    
    if(existing.isPresent()) {
      String sql = "DELETE FROM watchlist_movie WHERE watchlist_id = :watchlist_id AND movie_id = :movie_id;";
      
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("watchlist_id", watchlistId);
        parameters.put("movie_id", movieId);
    
        int rows = jdbcTemplate.update(sql, parameters);       
        if (rows != 1) {
          return Optional.empty();
        }
        return existing;
        
      } return Optional.empty();  
  }
  
  
  
  /**
   * Method to select a single movie on a watchlist by the watchlist id and movie id 
   * @param watchlistId
   * @param movieId
   * @return a Watchlist movie model if found, otherwise will return null 
   */
  private Optional<WatchlistMovieModel> getMovieOnWatchlist(Long watchlistId, Long movieId) {    
    //@formatter:off
    String sql = "SELECT watchlist_movie.watchlist_id, watchlist_movie.movie_id, watchlist_movie.have_watched, movie.movie_title, watchlist.watchlist_name "
        + "FROM watchlist_movie LEFT JOIN movie ON movie.movie_id = watchlist_movie.movie_id "
        + "LEFT JOIN watchlist ON watchlist_movie.watchlist_id = watchlist.watchlist_id WHERE watchlist_movie.watchlist_id = :watchlist_id AND movie.movie_id = :movie_id;"; 
    //@formatter:on
    
    Map<String,Object> parameters = new HashMap<>();
    parameters.put("watchlist_id", watchlistId);
    parameters.put("movie_id", movieId);
    
    List<WatchlistMovieModel> movies = jdbcTemplate.query(sql, parameters, new RowMapper<>() {
      public WatchlistMovieModel mapRow(ResultSet rs, int rowNum) {
        try { 
            WatchlistMovieModel movie = new WatchlistMovieModel(
                rs.getLong("watchlist_id"),
                rs.getLong("movie_id"),
                rs.getBoolean("have_watched"),
                rs.getString("movie_title"),
                rs.getString("watchlist_name")
                );
              return movie;
            } catch (SQLException e) {
                  return null;
            }      
          } 
        });
        if(movies.size() != 1) {
          return Optional.empty();
        }
        return Optional.of(movies.get(0)); 
    }

 
  }

