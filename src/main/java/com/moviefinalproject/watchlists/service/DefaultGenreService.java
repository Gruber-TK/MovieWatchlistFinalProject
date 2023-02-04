package com.moviefinalproject.watchlists.service;

import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.moviefinalproject.watchlists.entity.GenreModel;
import com.moviefinalproject.watchlists.entity.MovieModel;
import com.moviefinalproject.watchlists.repository.GenreRepository;


@Service
public class DefaultGenreService implements GenreService {
  
  private GenreRepository genreRepository;  
  public DefaultGenreService(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  
  @Override
  public List<MovieModel> getMoviesByGenre(GenreModel genre) {
    Stream<MovieModel> movies = genreRepository.getMoviesByGenre(genre);
    return movies.toList();
  }

}
