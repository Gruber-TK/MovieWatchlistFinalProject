package com.moviefinalproject.watchlists.repository;

import java.util.stream.Stream;
import com.moviefinalproject.watchlists.entity.GenreModel;
import com.moviefinalproject.watchlists.entity.MovieModel;

public interface GenreRepository {

  /**
   * Fetches a list of movies from the database by genre 
   * @param genre
   * @return a list of movies, otherwise will return null 
   */
  Stream<MovieModel> getMoviesByGenre(GenreModel genre);

}
