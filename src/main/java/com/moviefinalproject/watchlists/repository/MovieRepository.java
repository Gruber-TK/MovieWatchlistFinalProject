package com.moviefinalproject.watchlists.repository;


import java.util.Optional;
import java.util.stream.Stream;
import com.moviefinalproject.watchlists.entity.MovieModel;


public interface MovieRepository {

  /**
   * Fetches all available movies from the database
   * @returns a stream of movies with movie information if found, otherwise null;
   */
  Stream<MovieModel> getMovies();

  /**
   * Fetches a movie from the database
   * @param movieId
   * @return a movie model if found, otherwise null
   */
  Optional<MovieModel> getMovieById(Long movieId);
 
}
