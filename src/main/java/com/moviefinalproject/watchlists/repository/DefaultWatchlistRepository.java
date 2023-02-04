package com.moviefinalproject.watchlists.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.moviefinalproject.watchlists.entity.UserModel;
import com.moviefinalproject.watchlists.entity.WatchlistInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistModel;
import com.moviefinalproject.watchlists.entity.WatchlistUpdateModel;


@Repository
public class DefaultWatchlistRepository implements WatchlistRepository{

  private NamedParameterJdbcTemplate jdbcTemplate;
  public DefaultWatchlistRepository(NamedParameterJdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }
  
  
  //Fetches all watchlists for a user from the database 
  @Override
  public Stream<WatchlistModel> getAllWatchlistsByUser(String userCode) {
    
    //formatter:off
    String sql = "SELECT watchlist_id, watchlist_name, user_code FROM watchlist WHERE user_code = :user_code;";
    //formatter:on
    
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("user_code", userCode);
    
    List<WatchlistModel> watchlists = jdbcTemplate.query(sql, parameters, new RowMapper<>(){
      
      public WatchlistModel mapRow(ResultSet rs, int rowNum) {
        //@formatter:off
        try {
          WatchlistModel watchlist = new WatchlistModel(
              rs.getString("user_code"),
              rs.getString("watchlist_name"),
              rs.getLong("watchlist_id")
              );
          return watchlist;
        //@formatter:on
        }
        catch (SQLException e) {
          return null;
      }
      }
    });
     return watchlists.stream();
    }


  
  //Creates a new watchlist in the database for a user 
  @Override
  public Optional<WatchlistModel> createUserWatchlist(WatchlistInputModel input) {
    Optional<UserModel> existing = ValidateUserExists(input.getUserCode());
    
    if ((input != null) && (input.isWatchlistValid()) && existing.isPresent()) {
      String sql = "INSERT INTO watchlist (user_code, watchlist_name) VALUES (:user_code, :watchlist_name);";

      MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("user_code", input.getUserCode());
      parameters.addValue("watchlist_name", input.getWatchlistName());
      
      KeyHolder keyHolder = new GeneratedKeyHolder();
      
      int rows = jdbcTemplate.update(sql, parameters, keyHolder);
      Long watchlistId = keyHolder.getKey().longValue();
      if (rows == 1) {
        return getWatchlist(watchlistId);
      }
    }
    return Optional.empty();
  } 
  
  
  
  //Fetches a watchlist from the database by watchlist id
  private Optional<WatchlistModel> getWatchlist(Long watchlistId) {
    if (watchlistId != null) {
      String sql = "SELECT watchlist_id, watchlist_name, user_code FROM watchlist WHERE (watchlist_id = :watchlist_id);";
      
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("watchlist_id", watchlistId);
      
      List<WatchlistModel> watchlists = jdbcTemplate.query(sql, parameters, (rs,rowNum) -> {
        return toWatchlistModel(rs,rowNum);
      });
      if (watchlists.size() >= 1) {
        return Optional.of(watchlists.get(0));
      }
    }
    return Optional.empty();
  }

  
  
  //Deletes a watchlist from the database 
  @Override
  public Optional<WatchlistModel> deleteWatchlist(Long watchlistId) {
    Optional<WatchlistModel> existing = getWatchlist(watchlistId);  
    
    if (existing.isPresent()) {
      String sql = "DELETE FROM watchlist WHERE (watchlist_id = :watchlist_id);";
      
      Map<String,Object> parameters = new HashMap<>();
        parameters.put("watchlist_id", existing.get().getWatchlistId());
      
      int rows = jdbcTemplate.update(sql, parameters);
      
      if (rows == 1) {
        return existing;
      }
    }
    return Optional.empty();
  }
  
  
  
  //Update the name of a watchlist in the database 
  @Override
  public Optional<WatchlistModel> updateWatchlistName(WatchlistUpdateModel input) {
    Optional<WatchlistModel> existing = getWatchlist(input.getWatchlistId());  
    
    if (existing.isPresent()) {
      String sql = "UPDATE watchlist SET watchlist_name = :watchlist_name WHERE watchlist_id = :watchlist_id;";
      
      try {
      Map<String,Object> parameters = new HashMap<>();
        parameters.put("watchlist_id", input.getWatchlistId());
        parameters.put("watchlist_name", input.getWatchlistName());
      
      int rows = jdbcTemplate.update(sql, parameters);
      if (rows != 1) {
        return Optional.empty();
      }
      Optional<WatchlistModel> updated = getWatchlist(input.getWatchlistId());
      return updated; 
      
      } catch (Exception e) {
        return Optional.empty();
      }
    }
    return Optional.empty();
  }
  
  
  /*
   * Takes a result set and returns a watchlist model
   */
  private WatchlistModel toWatchlistModel(ResultSet rs, int rowNum) {
    //@formatter:off
    try {
      WatchlistModel watchlist = new WatchlistModel(
          rs.getString("user_code"),
          rs.getString("watchlist_name"),
          rs.getLong("watchlist_id"));
      return watchlist;
    //@formatter:on
    }
    catch (SQLException e) {
      return null;
  }
  }
  
  
  /**
   * Validates that a userCode exists in the database 
   * @param userCode
   * @return A user Model if it exists, otherwise null
   */
  private Optional<UserModel> ValidateUserExists(String userCode) {
    if((userCode != null) && (! userCode.isEmpty())) {
      String sql = "SELECT user_id, user_code, user_name FROM users WHERE user_code = :user_code;";
      
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("user_code", userCode);
      
      List<UserModel> users = jdbcTemplate.query(sql, parameters, (rs, rowNum) ->{
        return toUserModel(rs,rowNum);
      });
      if (users.size() >= 1) {
        return Optional.of(users.get(0));
      }
    }
    return Optional.empty();
  }
  
  
  /**
   * Takes a result set and returns a User Model
   * @param result set 
   * @param rowNum
   * @return a User Model
   */
  private UserModel toUserModel(ResultSet rs, int rowNum) {
    //@formatter:off
    try {
      UserModel user = new UserModel(
          rs.getString("user_code"),
          rs.getString("user_name"));
      return user;
    //@formatter:on
    }
    catch (SQLException e) {
      return null;
    }
  }

  
}
