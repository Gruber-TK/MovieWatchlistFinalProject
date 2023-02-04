package com.moviefinalproject.watchlists.service;

import java.util.List;
import com.moviefinalproject.watchlists.entity.MovieModel;


public interface MovieService {
  
  /**
   * Fetches all movies in database
   * @returnS A List of movies including the movie information  
   */
  List<MovieModel> getMovies();

  
  /**
   * Fetches a movie from the database
   * @param movieId 
   * @return a movie model (id, title, release year, runtime minutes, rating, and synopsis)
   */
  MovieModel getMovieById(Long movieId);


}
