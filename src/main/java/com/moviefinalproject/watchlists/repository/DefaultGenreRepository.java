package com.moviefinalproject.watchlists.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.moviefinalproject.watchlists.entity.GenreModel;
import com.moviefinalproject.watchlists.entity.MovieModel;
import com.moviefinalproject.watchlists.entity.RatingModel;

@Repository
public class DefaultGenreRepository implements GenreRepository{

  private NamedParameterJdbcTemplate jdbcTemplate;
  public DefaultGenreRepository(NamedParameterJdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }


  //Fetches movies of a genre type
  @Override
  public Stream<MovieModel> getMoviesByGenre(GenreModel genre) {
    String sql = "SELECT movie.movie_id, movie.movie_title, movie.release_date, movie.runtime_minutes, movie.rating, movie.synopsis "
        + "FROM movie LEFT JOIN movie_genre ON movie.movie_id = movie_genre.movie_id "
        + "WHERE genre_name = :genre_name ORDER BY movie.movie_title;";
    
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("genre_name", genre.toString());
    
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
      return movies.stream(); 
    }
  
}
