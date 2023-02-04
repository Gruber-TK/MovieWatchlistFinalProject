package com.moviefinalproject.watchlists.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.moviefinalproject.watchlists.entity.MovieModel;
import com.moviefinalproject.watchlists.repository.MovieRepository;

@Service
public class DefaultMovieService implements MovieService {

  private MovieRepository movieRepository;
  
  public DefaultMovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }
  
  
  @Override
  public List<MovieModel> getMovies() {
    Stream<MovieModel> movies = movieRepository.getMovies();
    return movies.toList();
  }

  
  @Override
  public MovieModel getMovieById(Long movieId) {
      Optional<MovieModel> movie = movieRepository.getMovieById(movieId);
      if(movie.isPresent()) {
        return movie.get();
      }
      return null;
  }

}
