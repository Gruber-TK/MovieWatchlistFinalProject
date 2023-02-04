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
import com.moviefinalproject.watchlists.entity.MovieModel;
import com.moviefinalproject.watchlists.entity.RatingModel;


@Repository
public class DefaultMovieRepository implements MovieRepository {

  private NamedParameterJdbcTemplate jdbcTemplate;
  public DefaultMovieRepository(NamedParameterJdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }

  
  //Fetches all movies from the database 
  @Override
  public Stream<MovieModel> getMovies() {
    
    String sql = "SELECT movie_id, movie_title, release_date, runtime_minutes, rating, synopsis FROM movie ORDER BY movie_title;";  
    List<MovieModel> movies = jdbcTemplate.query(sql, new RowMapper<>() {
      public MovieModel mapRow(ResultSet rs, int rowNum) {
      try { 
        MovieModel movie = new MovieModel(
            rs.getLong("movie_id"),
            rs.getString("movie_title"),
            rs.getInt("release_date"),
            rs.getInt("runtime_minutes"),
            RatingModel.valueOf(rs.getString("rating")),
            rs.getString("synopsis")
            );
            return movie;
      } catch (SQLException e) {
        return null;
      }      
    }
  });
    return movies.stream(); 
  }


  //Fetches a movie by the movie id 
  @Override
  public Optional<MovieModel> getMovieById(Long movieId) {
       
      String sql = "SELECT movie_id, movie_title, release_date, runtime_minutes, rating, synopsis FROM movie WHERE movie_id = :movie_id;";      
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("movie_id", movieId);
      
      List<MovieModel> movies = jdbcTemplate.query(sql, parameters, new RowMapper<>() {
       public MovieModel mapRow(ResultSet rs, int rowNum) {
        try { 
          MovieModel movie = new MovieModel(
              rs.getLong("movie_id"),
              rs.getString("movie_title"),
              rs.getInt("release_date"),
              rs.getInt("runtime_minutes"),
              RatingModel.valueOf(rs.getString("rating")),
              rs.getString("synopsis")
              );
              return movie;
        } catch (SQLException e) {
          return null;
        } 
        }
       });
         if(movies.size() == 1) {
           return Optional.of(movies.get(0));
         }
         return Optional.empty();
      }
      
  
}
