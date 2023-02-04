package com.moviefinalproject.watchlists.service;

import java.util.List;
import com.moviefinalproject.watchlists.entity.GenreModel;
import com.moviefinalproject.watchlists.entity.MovieModel;

public interface GenreService {

  /**
   * Fetches a list of movies for a genre type
   * @param genre 
   * @returns a list of movies for a genre, otherwise will return an empty list 
   */
  List<MovieModel> getMoviesByGenre(GenreModel genre);
  
  
}